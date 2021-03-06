package com.pd.actuator.metric.metrics.impl;

import java.util.Objects;
import java.util.SortedMap;
import java.util.concurrent.Callable;
import java.util.function.ToDoubleFunction;

/**
 * @author zhaozhengkang@cetiti.com
 * @description
 * @date 2019/11/25 15:26
 */
public class SpringGuageMetric {
    public static final ToDoubleFunction<Callable<Double>> metricFunc = doubleCallable -> {
        try {
            return doubleCallable.call();
        } catch (Exception e) {
            e.printStackTrace(); // NOSONAR
            return 0L;
        }
    };
    private String metricsName;
    private String description;
    private SortedMap<String, String> tagMap;
    private Callable<Double> callable;

    public SpringGuageMetric(String metricsName, String description, SortedMap<String, String> tagMap,
                             Callable<Double> callable) {
        this.metricsName = metricsName;
        this.description = description;
        this.tagMap = tagMap;
        this.callable = callable;
    }

    public String getMetricsName() {
        return metricsName;
    }

    public void setMetricsName(String metricsName) {
        this.metricsName = metricsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SortedMap<String, String> getTagMap() {
        return tagMap;
    }

    public void setTagMap(SortedMap<String, String> tagMap) {
        this.tagMap = tagMap;
    }

    public Callable<Double> getCallable() {
        return callable;
    }

    public void setCallable(Callable<Double> callable) {
        this.callable = callable;
    }

    @Override
    public String toString() {
        return "SpringGuageMetric{" + "metricsName='" + metricsName + '\'' + ", description='" + description + '\''
                + ", tagMap=" + (tagMap != null && !tagMap.isEmpty() ? tagMap.toString() : "{}") + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SpringGuageMetric myGuage = (SpringGuageMetric) o;
        return Objects.equals(metricsName, myGuage.metricsName) && Objects.equals(description, myGuage.description)
                && Objects.equals(tagMap, myGuage.tagMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricsName, description, tagMap);
    }
}
