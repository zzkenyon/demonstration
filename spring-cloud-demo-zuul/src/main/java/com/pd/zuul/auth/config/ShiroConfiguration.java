package com.pd.zuul.auth.config;


import com.pd.zuul.auth.filter.AccessTokenLonginFilter;
import com.pd.zuul.auth.matcher.AccessTokenCredentialsMatcher;
import com.pd.zuul.auth.matcher.SqlDatabaseCredentialsMatcher;
import com.pd.zuul.auth.realm.AccessTokenRealm;
import com.pd.zuul.auth.realm.SqlDatabaseRealm;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

/**
 * shiro 配置
 * @author zhaozhengkang
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 项目启动，shiroFilter首先会被初始化,并且逐层传入SecurityManager，Realm，matcher
     * @param manager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("tokenFilter", new AccessTokenLonginFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(manager);
        factoryBean.setUnauthorizedUrl("/401");
        //登陆界面
//        factoryBean.setLoginUrl("/login");
        //成功后页面
//        bean.setSuccessUrl("/index");
        //无权限后的页面
//        bean.setUnauthorizedUrl("/unauthorized");
        //键值对:请求-拦截器(权限配置)

        LinkedHashMap<String,String> filterRuleMap = new LinkedHashMap<String, String>();
        //首页地址index，使用authc过滤器进行处理
//        filterRuleMap.put("/index","authc");
        //登陆不需要任何过滤
        //filterRuleMap.put("/login","anon");
        //不做身份验证
//        filterRuleMap.put("/loginUser","anon");
        //只有角色中拥有admin才能访问admin
//        filterRuleMap.put("/admin","roles[admin]");
        //拥有edit权限
//        filterRuleMap.put("/edit","perms[edit]");
        //druid
 //       filterRuleMap.put("/druid/**","anon");
        //其他请求只验证是否登陆过
        filterRuleMap.put("/login","anon");
        filterRuleMap.put("/user-api/**","tokenFilter");
        filterRuleMap.put("/**","tokenFilter");
        //放入Shiro过滤器
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 将定义好的Realm放入安全会话中心
     * @param sqlDatabaseRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("sqlDatabaseRealm") SqlDatabaseRealm sqlDatabaseRealm,
                                           @Qualifier("accessTokenRealm")AccessTokenRealm accessTokenRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(sqlDatabaseRealm);
        realms.add(accessTokenRealm);
        manager.setRealms(realms);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }
    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulThrowableStrategy());
        return modularRealmAuthenticator;
    }

    /**
     * 将自定义的校验规格放入Realm
     * @param matcher
     * @return
     */
    @Bean("sqlDatabaseRealm")
    public SqlDatabaseRealm sqlDatabaseRealm(@Qualifier("sqlDatabaseCredentialsMatcher") SqlDatabaseCredentialsMatcher matcher){

        SqlDatabaseRealm sqlDatabaseRealm = new SqlDatabaseRealm();
        //信息放入缓存
        sqlDatabaseRealm.setCacheManager(new MemoryConstrainedCacheManager());
        sqlDatabaseRealm.setCredentialsMatcher(matcher);
        return sqlDatabaseRealm;
    }

    @Bean("accessTokenRealm")
    public AccessTokenRealm accessTokenRealm(@Qualifier("accessTokenCredentialsMatcher")AccessTokenCredentialsMatcher matcher){
        AccessTokenRealm accessTokenRealm = new AccessTokenRealm();
        accessTokenRealm.setCredentialsMatcher(matcher);
        return accessTokenRealm;
    }

    /**
     * 校验规则
     * @return 校验实例
     */
    @Bean("sqlDatabaseCredentialsMatcher")
    public SqlDatabaseCredentialsMatcher sqlDatabaseCredentialsMatcher(){
        return  new SqlDatabaseCredentialsMatcher();
    }
    @Bean("accessTokenCredentialsMatcher")
    public AccessTokenCredentialsMatcher accessTokenCredentialsMatcher(){
        return new AccessTokenCredentialsMatcher();
    }

    /**
     * Spring与 Shiro 关联
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
