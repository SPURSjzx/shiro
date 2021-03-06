package com.jzx.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: PasswordRealm
 * @Description: TODO
 * @Author: Jzxxxxx
 * @Date: Created in 2019/8/13 0013下午 2:14
 */
public class PasswordRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "PasswordRealm";
    }

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
//        认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username=(String)token.getPrincipal();
        System.out.println(username);
        if(!"zhangsan".equals(username)){
            return null;
        }
        //模拟数据库中保存的加密之后的密文： 666+账号（盐）+散列次数3
        String password="cd757bae8bd31da92c6b14c235668091";
        System.out.println(password);
        // info对象表示realm登录比对信息 参数1: 用户信息（真实登录中是登陆对象user对象） 参数2：密码 参数3：盐  参数4：当前realm的名字
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes(username),getName());
        return info;
    }
}
