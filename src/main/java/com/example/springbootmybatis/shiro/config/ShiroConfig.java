package com.example.springbootmybatis.shiro.config;

import com.example.springbootmybatis.shiro.realm.MyshiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        LOGGER.info("[ShiroConfig][ShiroFilterFactoryBean]");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap = new HashMap<>();
        //配置不被拦截的URL
        filterChainDefinitionMap.put("/templates/**","anon");
        //配置退出
        filterChainDefinitionMap.put("logout","logout");
        //除配置外URL都必须认证
        filterChainDefinitionMap.put("/**","authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager  securityManager(){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //设置Relam
        defaultSecurityManager.setRealm(myShiroRelam());
        return defaultSecurityManager;
    }

    @Bean
    public Realm myShiroRelam() {
        MyshiroRealm myshiroRealm = new MyshiroRealm();
        return myshiroRealm;
    }
}
