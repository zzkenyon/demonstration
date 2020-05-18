package com.pd.helloworld.ioc;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-04-24 16:56
 */
public class ComboPooledDataSource {
    private String driverClass;
    private String jdbcUrl;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
