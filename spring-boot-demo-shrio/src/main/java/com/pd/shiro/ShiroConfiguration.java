package com.pd.shiro;


import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Author: Jay_Liu
 * @Description: 配置类
 * @Date: Created in 19:14 2018/3/26 0026
 * @Modified By:
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 项目启动shiroFilter首先会被初始化,并且逐层传入SecurityManager，Realm，matcher
     * @param manager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //登陆界面
        bean.setLoginUrl("/login");
        //成功后页面
        bean.setSuccessUrl("/index");
        //无权限后的页面
        bean.setUnauthorizedUrl("/unauthorized");
        //键值对:请求-拦截器(权限配置)
        LinkedHashMap<String,String> filterChainDefinitonMap = new LinkedHashMap<String, String>();
        //首页地址index，使用authc过滤器进行处理
        filterChainDefinitonMap.put("/index","authc");
        //登陆不需要任何过滤
        filterChainDefinitonMap.put("/login","anon");
        //不做身份验证
        filterChainDefinitonMap.put("/loginUser","anon");
        //只有角色中拥有admin才能访问admin
        filterChainDefinitonMap.put("/admin","roles[admin]");
        //拥有edit权限
        filterChainDefinitonMap.put("/edit","perms[edit]");
        //druid
        filterChainDefinitonMap.put("/druid/**","anon");
        //其他请求只验证是否登陆过
        filterChainDefinitonMap.put("/**","user");
        //放入Shiro过滤器
        bean.setFilterChainDefinitionMap(filterChainDefinitonMap);
        return bean;
    }

    /**
     * 将定义好的Realm放入安全会话中心
     * @param authRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    /**
     * 将自定义的校验规格放入Realm
     * @param matcher
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialmatcher") Credentialmatcher matcher){

        AuthRealm authRealm = new AuthRealm();
        //信息放入缓存
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return  authRealm;
    }

    /**
     * 校验规则
     * @return 校验实例
     */
    @Bean("credentialmatcher")
    public Credentialmatcher credentialmatcher(){
        return  new Credentialmatcher();
    }

    /**
     * Spring与Shiro关联
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager){

        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 开启代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
