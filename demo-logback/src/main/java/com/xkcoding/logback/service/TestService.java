package com.xkcoding.logback.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Morgan
 * @create 2023-01-17-0:06
 */
@Service
@Slf4j
public class TestService {

    // 开启异步线程打印日志 测试traceID是否一致 如果一致说明OK
    @Async("threadPoolTaskExecutor")
    public void testLog() {
        log.info("【TestService】info");
        log.warn("【TestService】warn");
        log.error("【TestService】error");
    }
}
