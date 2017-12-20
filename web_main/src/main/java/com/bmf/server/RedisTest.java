package com.bmf.server;

import com.bmf.tools.RedisTool;
import com.util.calculate.Calculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by BMF on 2017/6/4.
 */
@Controller
@RequestMapping("/sv1/bmf")
public class RedisTest {

    @Autowired
    private RedisTool redisTool;

    @RequestMapping("/put")
    @ResponseBody
    public Object put(String key, String value, Long time) {
        return redisTool.put(key, value, time);
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(String a) {
        return redisTool.get(a);
    }

    //http://localhost/sv1/bmf/test/1;b=3
    @RequestMapping(value = "/test/{a}")
    @ResponseBody
    public Object test(@PathVariable Integer a, @MatrixVariable(name = "b") Integer b) {
        Integer add = Calculate.add(a, b);
        User u = new User();
        u.setId(add);
        u.setName("张三");
        return u;
    }

    //
    @RequestMapping(value = "/{var1}/test/{var2}", params = "myParam=myValue"
//            , headers = "myParam=myValue"
//            ,consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ResponseBody
    public Object test2(@MatrixVariable MultiValueMap<String, Object> a,
                        @MatrixVariable(pathVar = "var2") MultiValueMap<String, Object> b) {
        return a + ":" + b;
    }

    private class User {
        private String name;
        private Integer Id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (name != null ? !name.equals(user.name) : user.name != null) return false;
            return Id != null ? Id.equals(user.Id) : user.Id == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (Id != null ? Id.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", Id=" + Id +
                    '}';
        }
    }
}
