package com.xkcoding.logback.controller;

import com.xkcoding.logback.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Morgan
 * @create 2023-01-16-23:06
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test-log")
    public String testLog() {
        log.info("【TestController】info");
        log.warn("【TestController】warn");
        log.error("【TestController】error");
        return "test-log";
    }

    @GetMapping("/test-log2")
    public void testThreadPoolTaskExecutor(){
        log.info("进入Controller层 调用testThreadPoolTaskExecutor方法");
        testService.testLog();
    }
}
