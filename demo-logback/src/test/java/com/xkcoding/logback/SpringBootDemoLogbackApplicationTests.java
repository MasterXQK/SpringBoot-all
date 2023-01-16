package com.xkcoding.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootDemoLogbackApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Async("threadPoolTaskExecutor")
    public void testThreadPoolTaskExecutor(){
        log.info("Async 测试一下");
        log.warn("Async 测试一下");
        log.error("Async 测试一下");
    }

}
