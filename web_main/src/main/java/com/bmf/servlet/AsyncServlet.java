package com.bmf.servlet;

import com.bmf.listener.AppAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class AsyncServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AppAsyncListener());
        asyncContext.setTimeout(TimeUnit.MINUTES.toMillis(10));
        asyncContext.start(() -> {
            try {
                System.out.println(2);
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                PrintWriter writer = asyncContext.getResponse().getWriter();
                writer.append("async text is showing");
                System.out.println(3);
                writer.flush();
                writer.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1);
//        PrintWriter writer = resp.getWriter();
//        writer.append("world");
////        asyncContext.complete();
////        asyncContext.dispatch();
//        writer.flush();
//        writer.close();

    }
}
