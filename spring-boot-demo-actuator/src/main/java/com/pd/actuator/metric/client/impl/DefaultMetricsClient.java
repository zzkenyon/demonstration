package com.pd.actuator.metric.client.impl;

import com.pd.actuator.metric.client.MetricsClient;
import com.pd.actuator.metric.metrics.CounterMetric;
import com.pd.actuator.metric.metrics.TimerMetric;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhaozhengkang@cetiti.com
 * @description 非spring埋点实现，打日志并打印数据到控制台
 * @date 2019/11/25 15:32
 */

@Slf4j
public class DefaultMetricsClient implements MetricsClient {
    private Timer timer;
    private int period;
    private boolean mute;

    public DefaultMetricsClient() {
        this(10000);
    }

    private DefaultMetricsClient(int period) {
        this(period, false);
    }

    DefaultMetricsClient(int period, boolean mute) {
        System.out.println("default metrics client created"); // NOSONAR
        timer = new Timer("default_metrics_client_timer", true);
        this.period = period;
        this.mute = mute;
    }

    @Override
    public CounterMetric counter(String metricsName, String description, SortedMap<String, String> tagMap) {
        return new CounterMetric() {
            private AtomicLong c = new AtomicLong(0);
            private String mn = metricsName;
            private String d = description;
            private SortedMap<String, String> t = tagMap;

            @Override
            public void increment() {
                String res = "metric name: " + mn + ", description: " + d
                        + (t != null && !t.isEmpty() ? ", tags: " + t.toString() : "") + ", counter: "
                        + c.incrementAndGet();
                if (!mute) {
                    System.out.println(res);
                }
                log.info(res);
            }

            @Override
            public void incrementBy(long delta) {
                String res = "metric name: " + mn + ", description: " + d
                        + (t != null && !t.isEmpty() ? ", tags: " + t.toString() : "") + ", counter: "
                        + c.addAndGet(delta);
                if (!mute) {
                    System.out.println(res);
                }
                log.info(res);
            }
        };
    }

    @Override
    public TimerMetric timer(String metricsName, String description, SortedMap<String, String> tagMap) {
        return new TimerMetric() {
            private int limit = 1_000_000;
            private SortedMap<Long, Long> storage = buildStorage();

            private SortedMap<Long, Long> buildStorage() {
                return Collections.synchronizedSortedMap(new TreeMap<>());
            }

            @Override
            public void record(long millis) {
                if (storage.size() >= limit) {
                    storage = buildStorage();
                }
                storage.put(System.currentTimeMillis(), millis);
                String res = "time consumed: " + millis + " ms.";
                if (!mute) {
                    System.out.println(res);
                }
                log.info(res);
            }

            @Override
            public void record(long time, TimeUnit unit) {
                this.record(TimeUnit.MILLISECONDS.convert(time, unit));
            }
        };
    }

    @Override
    public void gauge(String metricsName, String description, SortedMap<String, String> tagMap,
                      Callable<Double> callable) {
        try {
            if (!mute) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(// NOSONAR
                                    "metric name: " + metricsName + ", description: " + description
                                            + (tagMap != null && !tagMap.isEmpty() ? ", tags: " + tagMap.toString()
                                            : "")
                                            + ", value: " + callable.call());
                        } catch (Exception e) {
                            e.printStackTrace(); // NOSONAR
                        }
                    }
                }, 0, period);
            }
        } catch (Exception e) {
            e.printStackTrace(); // NOSONAR
        }
    }

    /*public static void main(String[] args) {
        CounterMetric demo = new DefaultMetricsClient(10000,false)
                .counter("counter","this is a counter",new TreeMap<>());
        demo.increment();
        demo.incrementBy(12);
    }*/

}
