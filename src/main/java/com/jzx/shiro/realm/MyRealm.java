package com.jzx.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ClassName: MyRealm
 * @Description: TODO
 * @Author: Jzxxxxx
 * @Date: Created in 2019/8/13 0013上午 11:04
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "MyRealm";
    }

    //授权操作
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证操作
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 参数token，表示登录时封装的UsernamePasswordToken

        // 通过用户名到数据库中查找用户信息 封装成一个AuthenticationInfo对象返回 方便认证器进行对比
        // 获取token中的用户名
        String username= (String) token.getPrincipal();
        // 通过用户名查询数据库，将该用户对应数据查询返回
        // 假设查询数据库返回数据是:zhangsan 666
        if(!"zhangsan".equals(username)){
            return null;
        }
        String password="666";
        // info对象表示realm登录比对信息 参数1: 用户信息（真实登录中是登陆对象user对象） 参数2：密码 参数3：当前realm名字
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,password,getName());
        return info;
    }
}
