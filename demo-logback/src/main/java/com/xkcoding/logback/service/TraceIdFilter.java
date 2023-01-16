package com.xkcoding.logback.service;

import com.xkcoding.logback.config.LogConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Morgan
 * @create 2023-01-17-0:00
 */
@Slf4j
@WebFilter(filterName = "traceIdFilter", urlPatterns = "/*")
@Order(0)
@Component
public class TraceIdFilter implements Filter {

    @Autowired
    private LogConfig logConfig;
    /**
     * 日志跟踪标识
     */
    public static final String TRACE_ID = "traceId";
    public static final String LOG_URL = "url";
    public static final String LOG_LEVEL = "level";
    public static final String LOG_ENV = "ENV";
    public static final String LOG_TYPE = "logType";


    @Override
    public void init(FilterConfig filterConfig) {
    }

//    value="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%thread]
//    [${ENV}] [%level] [%X{traceId}]
//    [%X{parentId}] [%X{spanId}]
//    [%X{url}][%X{protocol}] [%X{logType}] %logger{50} - %msg%n"/>
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        // TODO 在这里可以自定义你的traceID 比如可以把userID放进来 方便查看日志
        String MORGAN = "Morgan-熊乾坤";
        String traceId = String.format("%s-%s", MORGAN, UUID.randomUUID());
        MDC.put(TRACE_ID, traceId);
        MDC.put(LOG_URL, logConfig.getLOG_PATH());
        MDC.put(LOG_LEVEL, logConfig.getLOG_LEVEL());
        MDC.put(LOG_ENV, logConfig.getLOG_ENV());
        MDC.put(LOG_ENV, "ENV_TEST");
        MDC.put(LOG_TYPE, logConfig.getLOG_TYPE());


        // MDC 需要put
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
