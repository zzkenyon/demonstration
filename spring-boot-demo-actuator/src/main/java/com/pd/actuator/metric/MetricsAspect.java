package com.pd.actuator.metric;

import com.pd.actuator.metric.client.MetricsClient;
import com.pd.actuator.metric.metrics.CounterMetric;
import com.pd.actuator.metric.metrics.TimerMetric;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zhaozhengkang@cetiti.com
 * @description 使用aop方式在service层接口调用时埋点，统计调用次数以及总耗时
 * @date 2019/11/25 15:22
 */

@Slf4j
@Aspect
@Component
public class MetricsAspect {
    @Autowired
    private MetricsClient metricsClient;
    private volatile TimerMetric publishTimer;
    private volatile CounterMetric publishCounter;

    private ThreadLocal<Long> start = new ThreadLocal<>();

    @Pointcut("execution(public * com.pd.actuator.*.*(..))")
    public void logServiceApi(){}

    @Before("logServiceApi()")
    public void doBefore(){
        TreeMap<String,String> tags = new TreeMap<>();
        tags.put("exception","no exception");
        tags.put("url","/");
        start.set(System.currentTimeMillis());
        getPublishCounter(tags).increment();
    }

    @After("logServiceApi()")
    public void doAfter(){
        getPublishTimer().record(System.currentTimeMillis() - start.get());
    }

    private CounterMetric getPublishCounter(SortedMap<String,String> tags) {
        if (this.publishCounter == null) {
            synchronized (this) {
                if (this.publishCounter == null) {
                    this.publishCounter = metricsClient
                            .counter("counter.demo", "full publish counter", tags);
                }
            }
        }
        return this.publishCounter;
    }
    private TimerMetric getPublishTimer() {
        if (this.publishTimer == null) {
            synchronized (this) {
                if (this.publishTimer == null) {
                    this.publishTimer = metricsClient
                            .timer("timer.demo", "full publish timer", new TreeMap<>());
                }
            }
        }
        return this.publishTimer;
    }
}
