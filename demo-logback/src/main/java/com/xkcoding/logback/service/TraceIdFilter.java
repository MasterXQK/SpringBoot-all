package com.xkcoding.logback.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Morgan
 * @create 2023-01-17-0:00
 * @description 日志链路追踪过滤器
 */
@Slf4j
@WebFilter(filterName = "traceIdFilter", urlPatterns = "/*")
@Order(0)
@Component
public class TraceIdFilter implements Filter {

    /**
     * 日志跟踪标识
     */
    public static final String TRACE_ID = "traceId";
    public static final String METHOD = "method";
    public static final String URL = "url";
//    public static final String LOCAL_NAME = "localName";
    public static final String PROTOCOL = "protocol";

    @Override
    public void init(FilterConfig filterConfig) {
    }

//    value="[%d{yyyy-MM-dd HH:mm:ss.SSS}]
//    %clr([%thread])
//    %clr([${LOG_ENV}])
//    %clr([%level])
//    %clr([%X{traceId}]){magenta}
//    [%X{parentId}]
//    [%X{spanId}]
//    [%X{url}]
//    [${LOG_PROTOCOL}]
//    [${LOG_TYPE}]
//    [%logger{50}]
//    %clr([%msg]) %n"/>

    /** 你想要在logback里面 %X{str}显示什么 就要在MDC里面put进去同名的参数 */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        String method = "";
        String url = "";
        String localName = "";
        String protocol = "";

        // 转换成httpRequest
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (httpRequest != null) {
            method = httpRequest.getMethod();
            /** 在这里从httpRequest里面找出URI /demo/test-log2 放进MDC 从而收录进logback */
            url = httpRequest.getRequestURI();
//            localName = httpRequest.getLocalName();
            protocol = httpRequest.getProtocol();
        }

        // TODO 在这里可以自定义你的traceID 比如可以把userID放进来 方便查看日志
        String MORGAN = "Morgan-熊乾坤";
        String traceId = String.format("%s-%s", MORGAN, UUID.randomUUID());

        // MDC put
        MDC.put(METHOD, method);
        MDC.put(TRACE_ID, traceId);
//        MDC.put(LOCAL_NAME, localName);
        MDC.put(PROTOCOL, protocol);
        MDC.put(URL, url);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
