package com.bmf.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by BMF on 2017/6/10.
 */
@Controller
@RequestMapping(name = "图片上传", value = "/sv1/upload")
public class FileUploadController {

    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(name = "图片上传", value = "/picture")
    @ResponseBody
//    public Object picture(@RequestParam(required = false, value = "multipartFile") MultipartFile[] multipartFile) {
    public Object picture(@RequestParam(required = false, value = "multipartFile") Part[] multipartFile) {
        List<String> file = new ArrayList<>();
        if (multipartFile != null && multipartFile.length > 0) {
            for (int i = 0; i < multipartFile.length; i++) {
                logger.info(multipartFile[i].getSubmittedFileName());
                file.add(multipartFile[i].getSubmittedFileName());
            }
        }
        UrlList urlList = new UrlList();
        urlList.setFile(file);
        return urlList;
    }

    private class UrlList {
        private List<String> file;

        public UrlList() {
        }

        public List<String> getFile() {
            return file;
        }

        public UrlList setFile(List<String> file) {
            this.file = file;
            return this;
        }
    }

    @RequestMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("upload");
    }
}
