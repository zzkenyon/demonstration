package com.pd.actuator.metric.metrics;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaozhengkang@cetiti.com
 * @description 计时器埋点类型
 * @date 2019/11/25 15:23
 */


public interface TimerMetric {
    /**
     * 记录消耗的时间（毫秒）
     *
     * @param millis
     */
    void record(long millis);

    /**
     * 记录消耗的时间（指定时间单位）
     *
     * @param time
     * @param unit
     */
    void record(long time, TimeUnit unit);
}