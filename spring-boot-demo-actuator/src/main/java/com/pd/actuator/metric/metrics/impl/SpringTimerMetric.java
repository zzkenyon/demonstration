package com.pd.actuator.metric.metrics.impl;

import com.pd.actuator.metric.metrics.TimerMetric;
import io.micrometer.core.instrument.Timer;

import java.util.Objects;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/25 15:25
 */
public class SpringTimerMetric implements TimerMetric {
    private String metricName;
    private SortedMap<String, String> tagMap;
    private Timer timer;

    public SpringTimerMetric(String metricName, SortedMap<String, String> tagMap, Timer timer) {
        this.metricName = metricName;
        this.tagMap = tagMap;
        this.timer = timer;
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void record(long millis) {
        this.record(millis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void record(long time, TimeUnit unit) {
        timer.record(time, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SpringTimerMetric that = (SpringTimerMetric) o;
        return Objects.equals(metricName, that.metricName) && Objects.equals(tagMap, that.tagMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricName, tagMap);
    }

    @Override
    public String toString() {
        return "SpringTimerMetric{" + "metricName='" + metricName + '\'' + ", tagMap="
                + (tagMap != null && !tagMap.isEmpty() ? tagMap.toString() : "{}") + '}';
    }
}
