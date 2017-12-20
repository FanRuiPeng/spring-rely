package com.bmf.server;

import com.bmf.model.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Controller
@RequestMapping("/sv1/mvc")
public class MvcTest {

    /**
     * @param body 接受前端post请求体中的数据
     * @return
     */
    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    @ResponseBody
    public User test(@RequestBody User body) {
        return body;
    }

    /**
     * 获取请求得header body 并且设置返回的header body stats等信息
     *
     * @param httpEntity
     * @return
     */
    @RequestMapping("/header")
    public ResponseEntity<String> handle(HttpEntity<byte[]> httpEntity) {
        HttpHeaders headers = httpEntity.getHeaders();
        byte[] body = httpEntity.getBody();
        headers.forEach((k, v) -> System.out.println(k + ":" + v));
        Stream.of(body).forEach(System.out::println);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("myHeader", "hello world");
        return new ResponseEntity<String>("Hello World", httpHeaders, HttpStatus.CREATED);
    }

//    @ModelAttribute
//    public User addUser(@RequestParam Integer id) {
//        User user = new User();
//        user.setId(id);
//        user.setName("xiix");
//        System.out.println("xixi");
//        return user;
//    }
//
//    @ModelAttribute
//    public void populateModel(@RequestParam Integer id, Model model) {
//        User user = new User();
//        user.setId(id);
//        user.setName("haha");
//        System.out.println("haha");
//        model.addAttribute(user);
//    }

    @RequestMapping("/page")
    public String page() {
        System.out.println("page");
        return "show";
    }

    @PostMapping("/pages")
//    @PostMapping("pages/{id}/show/{name}")
    public String pages(@Valid @ModelAttribute User user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
            return "err_page/page_500";
        }
        model.addAttribute(user);
        return "show";
    }

    @PostMapping("page/{user}")
    public String pageUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute(user);
        return "show";
    }

    /**
     * 获取cookie中的某个参数
     *
     * @param cookie
     */
    @RequestMapping("cookie")
    public void heaferInfo(@CookieValue("JSESSIONID") String cookie) {
        System.out.println(cookie);
    }

    @RequestMapping("headSingle")
    public void headSingle(@RequestHeader("Accept-Encoding") List<String> encoding,
                           @RequestHeader("Connection") String connection,
                           @RequestHeader MultiValueMap<String, String> header, Date date) {
//        System.out.println(encoding);
        System.out.println(date);
        encoding.forEach(System.out::println);
        System.out.println(connection);
        header.forEach((k, v) -> System.out.println("key: " + k + " - value " + v));
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        System.out.println("I'm executing");
    }

    /**
     * 异步请求
     *
     * @param param
     * @return
     */
    @RequestMapping("/callback")
    public Callable<String> process(String param) {
        System.out.println(System.currentTimeMillis());
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                System.out.println(System.currentTimeMillis());
                return "show";
            }
        };
    }

    @RequestMapping("/event")
    public ResponseBodyEmitter handle() {
        ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(() -> {
            try {
                responseBodyEmitter.send("hello");
                System.out.println("hello");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> {
            try {
                responseBodyEmitter.send("world");
                System.out.println("world");
                responseBodyEmitter.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 20, TimeUnit.SECONDS);
        System.out.println("exec");
        return responseBodyEmitter;
    }

    @RequestMapping("/stream")
    public StreamingResponseBody stream() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\BMF\\Desktop\\file\\resources.xml")) {
                    int temp;
                    while ((temp = fileInputStream.read()) != -1) {
                        outputStream.write(temp);
                    }
                    outputStream.flush();
                    outputStream.close();
                }
            }
        };
    }

    @RequestMapping("/mutli")
    public ModelAndView multi() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("show");
        modelAndView.addObject("user", new User().setId(1).setName("yiy"));
        return modelAndView;
    }

    @RequestMapping("/mutil")
    public User mutil() {
        ModelAndView modelAndView = new ModelAndView();
        User yiy = new User().setId(1).setName("yiy");
        return yiy;
    }

    /**
     * 重定向传值到下一个请求
     *
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/flash1/{name}")
    public String flash(RedirectAttributes redirectAttributes, @PathVariable String name) {
        User user = new User().setId(1).setName(name);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/sv1/mvc/inner";
    }

    @RequestMapping("/flash2")
    public String flash2(Model model) {
        User user = new User().setId(1).setName("yiy");
        model.addAttribute(user);
        return "redirect:/sv1/mvc/inner";
    }

    @RequestMapping("/inner")
    public String inner() {
        return "show";
    }

    @RequestMapping("/buildurl")
    public void buildurl(HttpServletRequest request) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                "http://localhost/sv1/mvc/flash1/{name}").build();

        URI uri = uriComponents.expand("张三").encode().toUri();
        System.out.println(uri.toString());

        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost").path("/sv1/mvc/flash1/{name}").build()
                .expand("42")
                .encode();
        System.out.println(uriComponents.toUriString());

        uriComponents = ServletUriComponentsBuilder.fromRequest(request)
                .replaceQueryParam("accountId", "{id}").build()
                .expand("123")
                .encode();
        System.out.println(uriComponents.toUriString());

        uriComponents = ServletUriComponentsBuilder.fromContextPath(request)
                .path("/accounts").build().encode();
        System.out.println(uriComponents.toUriString());

        uriComponents = ServletUriComponentsBuilder.fromServletMapping(request)
                .path("/accounts").build().encode();
        System.out.println(uriComponents.toUriString());


    }
}
