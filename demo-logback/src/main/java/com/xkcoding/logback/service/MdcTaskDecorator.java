package com.xkcoding.logback.service;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskDecorator;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

import static com.xkcoding.logback.service.TraceIdFilter.TRACE_ID;

/**
 * @author Morgan
 * @create 2023-01-17-0:02
 * @description 线程池装饰器
 */
public class MdcTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> map = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(map);
                String traceId = MDC.get(TRACE_ID);
                if (StringUtils.isEmpty(traceId)) {
                    String MORGAN = "Morgan-熊乾坤";
                    traceId = String.format("%s-%s", MORGAN, UUID.randomUUID());
                    MDC.put(TRACE_ID, traceId);
                }
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
