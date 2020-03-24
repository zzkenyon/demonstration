package com.pd.zuul.filter;/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 10:10
 */

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author zhaozhengkang
 * @description
 * @version
 * @date 2020/3/23 10:10
 */
@Component
public class ZuulPreFilter extends ZuulFilter {
    private static final String ACCESS_TOKEN_NAME = "PD_TOKEN";
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(ACCESS_TOKEN_NAME)){

            }else {
                continue;
            }
        }
        return null;
    }
}
