package com.xkcoding.log.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Morgan
 * @create 2023-01-18-0:29
 * @description 另起一个Controller 但是都被切片包含在内了 执行完后会执行log()
 */
@Slf4j
@RestController
public class TestController2 {

    @GetMapping("test2")
    public String log() {
        log.info("test2");
        return "test2";
    }
}
