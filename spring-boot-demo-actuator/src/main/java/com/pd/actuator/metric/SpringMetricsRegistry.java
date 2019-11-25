package com.pd.actuator.metric;

import com.pd.actuator.metric.metrics.impl.SpringGuageMetric;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

/**
 * @author zhaozhengkang@cetiti.com
 * @description metric注册器，所有自定义的metric需要在此提交给spring
 * @date 2019/11/25 15:31
 */
@Slf4j
public class SpringMetricsRegistry implements MeterBinder {

    private MeterRegistry registry;

    @PreDestroy
    public void destroy() {
        if (registry != null && !registry.isClosed()) {
            registry.close();
        }
    }

    @Override
    public void bindTo(MeterRegistry registry) {
        if (this.registry == null) {
            this.registry = registry;
            log.info("MeterRegistry is set...");
        }
    }

    public void registerGauge(SpringGuageMetric g) {
        checkState();
        List<Tag> tags = getTags(g.getTagMap());
        Gauge.builder(g.getMetricsName(), g.getCallable(), SpringGuageMetric.metricFunc).tags(tags)
                .description(g.getDescription()).register(this.registry);
    }

    public Counter registerCounter(String metricsName, String description, SortedMap<String, String> tagMap) {
        checkState();
        List<Tag> tags = getTags(tagMap);
        return Counter.builder(metricsName).tags(tags).description(description).register(this.registry);
    }

    public Timer registerTimer(String metricsName, String description, SortedMap<String, String> tagMap) {
        checkState();
        List<Tag> tags = getTags(tagMap);
        return Timer.builder(metricsName).tags(tags).description(description).register(this.registry);
    }

    /**
     * 从sortedMap中获取tag列表
     * @param tagMap
     * @return
     */
    private List<Tag> getTags(SortedMap<String, String> tagMap) {
        return tagMap.entrySet().stream().map(entry -> Tag.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private void checkState() {
        if (this.registry == null) {
            throw new IllegalStateException("Metrics registry is not initialized yet!");
        }
    }
}
