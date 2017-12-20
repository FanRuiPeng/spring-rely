package com.bmf.server;

import com.bmf.model.User;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/sv1/cache")
public class CacheController {

    @GetMapping("/book/{id}")
    public ResponseEntity showBook(@PathVariable Long id, WebRequest request) {

        boolean b = request.checkNotModified("1.1");
        if(b) {
            System.out.println(1);
            return null;
        }

            System.out.println(2);
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
                .eTag("1.1")
                .body(new User().setId(1).setName("11"));

    }
}
