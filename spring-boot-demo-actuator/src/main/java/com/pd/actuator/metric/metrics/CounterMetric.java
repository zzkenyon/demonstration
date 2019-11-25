package com.pd.actuator.metric.metrics;

/**
 * @author zhaozhengkang@cetiti.com
 * @description 累加器埋点类型
 * @date 2019/11/25 15:24
 */
public interface CounterMetric {
    /**
     * 累加器加1
     */
    void increment();

    /**
     * 累加器加delta
     *
     * @param delta
     */
    void incrementBy(long delta);
}
