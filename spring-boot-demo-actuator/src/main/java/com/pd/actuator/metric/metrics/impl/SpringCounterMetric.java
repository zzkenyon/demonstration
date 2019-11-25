package com.pd.actuator.metric.metrics.impl;

import com.pd.actuator.metric.metrics.CounterMetric;
import io.micrometer.core.instrument.Counter;

import java.util.Objects;
import java.util.SortedMap;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/25 15:26
 */
public class SpringCounterMetric implements CounterMetric {
    private String metricName;
    private SortedMap<String, String> tagMap;
    private Counter counter;

    public SpringCounterMetric(String metricName, SortedMap<String, String> tagMap, Counter counter) {
        this.metricName = metricName;
        this.tagMap = tagMap;
        this.counter = counter;
    }

    @Override
    public void increment() {
        this.counter.increment();
    }

    @Override
    public void incrementBy(long delta) {
        this.counter.increment(delta);
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public SortedMap<String, String> getTagMap() {
        return tagMap;
    }

    public void setTagMap(SortedMap<String, String> tagMap) {
        this.tagMap = tagMap;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricName, tagMap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SpringCounterMetric that = (SpringCounterMetric) o;
        return Objects.equals(metricName, that.metricName) && Objects.equals(tagMap, that.tagMap);
    }

    @Override
    public String toString() {
        return "SpringCounterMetric{" + "metricName='" + metricName + '\'' + ", tagMap="
                + (tagMap != null && !tagMap.isEmpty() ? tagMap.toString() : "{}") + ", counter=" + counter.count()
                + '}';
    }
}
