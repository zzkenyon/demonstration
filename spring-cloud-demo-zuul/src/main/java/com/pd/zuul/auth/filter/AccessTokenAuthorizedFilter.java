package com.pd.zuul.auth.filter;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/6/15 20:49
 */
public class AccessTokenAuthorizedFilter extends PermissionsAuthorizationFilter {

    private String[] perms;
    private final AccessTokenLoginFilter accessTokenLoginFilter = new AccessTokenLoginFilter();
    public AccessTokenAuthorizedFilter(String[] perms){
        this.perms = perms;
    }
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        accessTokenLoginFilter.executeLogin(request,response);
        return super.isAccessAllowed(request,response,perms);
    }

    @Override
    public void setName(String name) {
        super.setName("ps");
    }
}
