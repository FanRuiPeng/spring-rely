package com.bmf.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryUsage;

@Controller
@RequestMapping("sv1/memory/")
public class MemoryApi {

    @RequestMapping("/show")
    @ResponseBody
    public Object show() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        heapMemoryUsage.getCommitted();
        return memoryMXBean;
    }
}
