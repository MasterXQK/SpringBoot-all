package com.xkcoding.logback.service;

import com.xkcoding.logback.config.LogConfig;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskDecorator;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

import static com.xkcoding.logback.service.TraceIdFilter.TRACE_ID;
import static com.xkcoding.logback.service.TraceIdFilter.LOG_LEVEL;
import static com.xkcoding.logback.service.TraceIdFilter.LOG_ENV;
import static com.xkcoding.logback.service.TraceIdFilter.LOG_URL;
import static com.xkcoding.logback.service.TraceIdFilter.LOG_TYPE;

/**
 * @author Morgan
 * @create 2023-01-17-0:02
 */
public class MdcTaskDecorator implements TaskDecorator {
    @Autowired
    private LogConfig logConfig;

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
//                    traceId = UUID.randomUUID().toString();
                    MDC.put(TRACE_ID, traceId);
                }
                if (StringUtils.isEmpty(LOG_ENV))
                    MDC.put(LOG_ENV, logConfig.getLOG_ENV());
                if (StringUtils.isEmpty(LOG_LEVEL))
                    MDC.put(LOG_LEVEL, logConfig.getLOG_LEVEL());
                if (StringUtils.isEmpty(LOG_URL))
                    MDC.put(LOG_URL, logConfig.getLOG_PATH());
                if (StringUtils.isEmpty(LOG_TYPE))
                    MDC.put(LOG_TYPE, logConfig.getLOG_TYPE());

                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
