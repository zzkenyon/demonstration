package com.pd.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-20 14:14
 */
@Service
@Slf4j
public class EsTestService {

    public void logToEs() {
        log.info("this is a test log to es");
    }
}
