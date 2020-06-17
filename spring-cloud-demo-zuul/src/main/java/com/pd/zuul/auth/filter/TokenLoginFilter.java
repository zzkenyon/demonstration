package com.pd.zuul.auth.filter;

import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/16 10:25
 */
public class TokenLoginFilter extends AdviceFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }
}
