package com.system.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权
        filterMap.put("/student", "roles[1]");
        filterMap.put("/student/*", "roles[1]");
        filterMap.put("/teacher", "roles[2]");
        filterMap.put("/teacher/*", "roles[2]");
        filterMap.put("/academic", "roles[3]");
        filterMap.put("/academic/*", "roles[3]");
        filterMap.put("/personnel", "roles[4]");
        filterMap.put("/personnel/*", "roles[4]");

        //设置登陆的请求
        bean.setLoginUrl("/login");

        //设置未授权的请求
        bean.setUnauthorizedUrl("/login");

        bean.setFilterChainDefinitionMap(filterMap);

        return bean;

    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        manager.setRealm(userRealm);

        return manager;
    }

    //创建realm对象
    @Bean(name = "userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        return new UserRealm();
//        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher(matcher);
//        return userRealm;
    }

    /**
     * 替换当前 Realm 的 credentialsMatcher 属性.
     * 直接使用 HashedCredentialsMatcher 对象, 并设置加密算法即可.
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的
     * 防止密码在数据库中明码表示,当然在登录认证的时候,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }

    //整合shiroDialect:用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
