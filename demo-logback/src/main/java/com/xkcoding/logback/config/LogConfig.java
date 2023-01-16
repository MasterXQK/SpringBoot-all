package com.xkcoding.logback.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Morgan
 * @create 2023-01-17-0:16
 */
@Configuration
@Data
public class LogConfig {
    @Value("${log.env}")
    public String LOG_ENV;

    @Value("${log.level}")
    public String LOG_LEVEL;

    @Value("${log.path}")
    public String LOG_PATH;

    @Value("${log.type}")
    public String LOG_TYPE;
}
