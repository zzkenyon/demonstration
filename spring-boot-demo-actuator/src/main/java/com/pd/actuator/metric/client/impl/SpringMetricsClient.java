package com.pd.actuator.metric.client.impl;

import com.pd.actuator.metric.SpringMetricsProperties;
import com.pd.actuator.metric.SpringMetricsRegistry;
import com.pd.actuator.metric.metrics.CounterMetric;
import com.pd.actuator.metric.metrics.TimerMetric;
import com.pd.actuator.metric.client.MetricsClient;
import com.pd.actuator.metric.metrics.impl.SpringCounterMetric;
import com.pd.actuator.metric.metrics.impl.SpringGuageMetric;
import com.pd.actuator.metric.metrics.impl.SpringTimerMetric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/25 15:29
 */
@Slf4j
public class SpringMetricsClient implements MetricsClient {
    @Autowired
    private SpringMetricsRegistry register;
    @Autowired
    private SpringMetricsProperties springMetricsProperties;
    private Set<SpringGuageMetric> gauges = ConcurrentHashMap.newKeySet();
    private ConcurrentMap<String, CounterMetric> counters = new ConcurrentHashMap<>();
    private ConcurrentMap<String, TimerMetric> timers = new ConcurrentHashMap<>();
    private MetricsClient defaultClient;

    @PostConstruct
    public void init() {
        if (!springMetricsProperties.isEnabled()) {
            defaultClient = new DefaultMetricsClient(springMetricsProperties.getDefaultClientPeriod(),
                    springMetricsProperties.isMuteDefaultClient());
        }
    }

    @PreDestroy
    public void destroy() {
        gauges.clear();
        counters.clear();
        timers.clear();
    }

    @Override
    public void gauge(String metricName, String description, SortedMap<String, String> tagMap,
                      Callable<Double> callable) {
        SpringGuageMetric g = new SpringGuageMetric(metricName, description, tagMap, callable);
        if (gauges.add(g)) {
            if (springMetricsProperties.isEnabled()) {
                register.registerGauge(g);
                log.info("gauge metric added for: {}", g);
            } else {
                defaultClient.gauge(metricName, description, tagMap, callable);
            }
        } else {
            log.warn("duplicated gauge: {}", g);
        }
    }

    @Override
    public CounterMetric counter(String metricName, String description, SortedMap<String, String> tagMap) {
        CounterMetric c;
        String key = getKey(metricName, tagMap);
        if ((c = counters.get(key)) == null) {
            c = counters.computeIfAbsent(key, k -> getSpringMetricCounter(metricName, description, tagMap));
        }
        return c;
    }

    @Override
    public TimerMetric timer(String metricName, String description, SortedMap<String, String> tagMap) {
        TimerMetric t;
        String key = getKey(metricName, tagMap);
        if ((t = timers.get(key)) == null) {
            t = timers.computeIfAbsent(key, k -> getSpringMetricTimer(metricName, description, tagMap));
        }
        return t;
    }

    private TimerMetric getSpringMetricTimer(String metricName, String description, SortedMap<String, String> tagMap) {
        if (springMetricsProperties.isEnabled()) {
            return new SpringTimerMetric(metricName, tagMap, register.registerTimer(metricName, description, tagMap));
        } else {
            return defaultClient.timer(metricName, description, tagMap);
        }
    }

    /**
     * 此处build Counter对象并注册到SpringMetricsRegistry
     * @param metricName
     * @param description
     * @param tagMap
     * @return
     */
    private CounterMetric getSpringMetricCounter(String metricName, String description,
                                                 SortedMap<String, String> tagMap) {
        if (springMetricsProperties.isEnabled()) {
            return new SpringCounterMetric(metricName, tagMap,
                    register.registerCounter(metricName, description, tagMap));
        } else {
            return defaultClient.counter(metricName, description, tagMap);
        }
    }

    private String getKey(String metricName, SortedMap<String, String> tagMap) {
        return metricName + (tagMap != null ? tagMap.toString() : "");
    }

    public Set<SpringGuageMetric> getGauges() {
        return gauges;
    }

    public Map<String, CounterMetric> getCounters() {
        return counters;
    }

    public Map<String, TimerMetric> getTimers() {
        return timers;
    }
}
