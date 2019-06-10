package com.example.springbootmybatis.shiro.realm;

import com.example.springbootmybatis.bean.Module;
import com.example.springbootmybatis.bean.User;
import com.example.springbootmybatis.service.UserService;
import com.example.springbootmybatis.shiro.bean.StatelessToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyshiroRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyshiroRealm.class);
    @Resource
    private UserService userService;

    /**
     * 认证、登录
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("[MyshiroRealm][AuthorizationInfo]...权限配置");
        //获取用户
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User user = (User) principals.getPrimaryPrincipal();
        //查询权限
        List<Module> list =  userService.getPrivilegeListByUser(user);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //授权
        for (Module module:list){
            authorizationInfo.addStringPermission(module.getName());
        }
        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken authToken = (StatelessToken) token;
        String key = "";
        if(null!=authToken){
           key = authToken.getToken();
        }
        //按照用户名查询用户
       User user =  userService.queryUserByUserName(key);
        if (null!=user){
            //比较token
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,authToken.getToken(),getName());
            return  authenticationInfo;
        }else {
            //用户不存在 抛出UnknownAccountException
            LOGGER.warn("[MyShiroRealm.doGetAuthenticationInfo] Error : AuthenticationException");
            //throw new AuthenticationException(authcToken1.getUsername());
            return null;
        }
    }
}
