package com.system.config;

import com.system.pojo.User;
import com.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        info.addRole(user.getRole() + "");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.login(token.getUsername());
        if (user == null) {//说明查无此人
            return null;
        }
        //密码认证,shiro做
        return new SimpleAuthenticationInfo(user, user.getPassWord(), "");//放入User对象

//        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
//        Object principal = user;
//        //2). credentials: 密码.即从数据库中获取的密码
//        Object credentials = user.getPassWord();
//        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
//        String realmName = getName();
//        //4). credentialsSalt: 盐值,这里我使用的是用户名
//        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getId());
//        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
    }

    // MD5 盐值加密
//    public static void main(String[] args) {
//        String hashAlgorithmName = "MD5";
//        Object credentials = "123456";
//        Object salt = ByteSource.Util.bytes("201921125099");
//        int hashIterations = 1024;
//
//        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//        System.out.println(result);
//    }
}
