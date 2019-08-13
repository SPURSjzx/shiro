package com.jzx.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.info;

/**
 * @ClassName: PermissionRealm
 * @Description: TODO
 * @Author: Jzxxxxx
 * @Date: Created in 2019/8/13 0013下午 4:11
 */
public class PermissionRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "PermissionRealm";
    }

    //    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //传入参数：principals:用户认证凭证信息
        //SimpleAuthenticationInfo 认证方法返回封装认证信息中的第一个参数 ：用户信息
        //当前登录用户信息 ： 用户凭证
        String username= (String) principals.getPrimaryPrincipal();
        //模拟查询数据库 查询用户事先指定的角色以及用户权限
        List<String> roles=new ArrayList<String>();   //角色集合
        List<String> permission=new ArrayList<String>();  //权限集合
        //假设用户在数据库中拥有role1角色
        roles.add("role1");
        //假设用户在数据库中拥有 user:delete权限
        permission.add("user:delete");

        //返回用户在数据库中的权限与角色
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permission);
        return info;
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
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(username),getName());
        return info;
    }
}
