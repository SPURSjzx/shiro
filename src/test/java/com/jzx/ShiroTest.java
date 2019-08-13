package com.jzx;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

/**
 * 测试shiro的验证
 * @ClassName: ShiroTest
 * @Description: TODO
 * @Author: Jzxxxxx
 * @Date: Created in 2019/8/13 0013上午 9:45
 */

public class ShiroTest {
    @Test
    //使用ini方式进行角色判断
    public void testHasRoleByRealm() throws Exception{
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken Token=new UsernamePasswordToken("zhangsan","666");
        subject.login(Token);
        //进行权限操作时 用户必须先通过认证
        //判断当前用户是否拥有某个权限
        System.out.println(subject.isPermitted("user:delete"));
        //当前用户是否拥有某个角色  返回true表示有 返回false表示没有
        System.out.println(subject.hasRole("role1"));
    }
    @Test
    @Ignore
    //使用ini方式进行角色判断
    public void testHasRole() throws Exception{
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken Token=new UsernamePasswordToken("zhangsan","666");
        subject.login(Token);
        //进行权限操作时 用户必须先通过认证
        //判断当前用户是否拥有某个权限
        System.out.println(subject.isPermitted("user:delete"));
        //判断当前用户是否拥有一些权限 返回true表示有 返回false表示不全部拥有
        System.out.println(subject.isPermittedAll("user:create","user:delete"));
        //判断当前用户是否拥有一些权限 返回true表示有 false表示没有
        System.out.println(Arrays.toString(subject.isPermitted("user:create","user:list")));
        //当前用户是否拥有某个角色  如果有该角色则没有返回值 如果没有角色则报UnauthorizedException
        subject.checkPermission("user:list");
        //当前用户是否拥有某个角色  返回true表示有 返回false表示没有
        //System.out.println(subject.hasRole("role1"));
        //当前用户是否拥有几个角色  返回true表示有 返回false表示不全部拥有
        //System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2","role3")));
        //当前用户是否拥有几个角色  返回一个boolean类型数组
        //System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2","role3"))));
        //当前用户是否拥有某个角色  如果有该角色则没有返回值 如果没有角色则报UnauthorizedException
        //subject.checkRole("role3");
        //判断当前用户是否拥有一些角色
        //subject.checkRoles("role1","role2","role3");
    }
    @Test
    @Ignore
    public void testLoginByPasswordRealm() throws Exception{
        //1 构建securityManager工厂对象,加载配置文件，创建工厂对象
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");
        //2 通过工厂对象 创建 SecurityManager对象
        SecurityManager securityManager=factory.getInstance();
        //3 将SecurityManager绑定到当前运行环境中，让系统随时都能访问SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);
        //4 创建当前登录主体 注意:此时主体没有经过认证
        Subject subject=SecurityUtils.getSubject();
        //5 绑定主体登录的身份/凭证 即账号密码
        UsernamePasswordToken Token=new UsernamePasswordToken("zhangsan","666");
        //6 主体登录
        try {
            subject.login(Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //7 判断登录是否成功
        System.out.println("验证登录是否成功:"+subject.isAuthenticated());
        //8 注销
        subject.logout();
        System.out.println("验证注销是否成功:"+subject.isAuthenticated());

    }
    @Test
    @Ignore
    public void testLoginByRealm() throws Exception{
        //1 构建securityManager工厂对象,加载配置文件，创建工厂对象
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2 通过工厂对象 创建 SecurityManager对象
        SecurityManager securityManager=factory.getInstance();
        //3 将SecurityManager绑定到当前运行环境中，让系统随时都能访问SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);
        //4 创建当前登录主体 注意:此时主体没有经过认证
        Subject subject=SecurityUtils.getSubject();
        //5 绑定主体登录的身份/凭证 即账号密码
        UsernamePasswordToken Token=new UsernamePasswordToken("zhangsan","666");
        //6 主体登录
        try {
            subject.login(Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //7 判断登录是否成功
        System.out.println("验证登录是否成功:"+subject.isAuthenticated());
        //8 注销
        subject.logout();
        System.out.println("验证登录是否成功:"+subject.isAuthenticated());

    }
    @Test
    @Ignore
    public void testLogin() throws Exception{
        //1 构建securityManager工厂对象,加载配置文件，创建工厂对象
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        //2 通过工厂对象 创建 SecurityManager对象
        SecurityManager securityManager=factory.getInstance();
        //3 将SecurityManager绑定到当前运行环境中，让系统随时都能访问SecurityManager对象
        SecurityUtils.setSecurityManager(securityManager);

        //4 创建当前登录主体 注意:此时主体没有经过认证
        Subject subject=SecurityUtils.getSubject();
        //5 绑定主体登录的身份/凭证 即账号密码
        UsernamePasswordToken Token=new UsernamePasswordToken("zhangsan","666");
        //6 主体登录
        subject.login(Token);
        //7 判断登录是否成功
        System.out.println("验证登录是否成功:"+subject.isAuthenticated());
        //8 注销
        subject.logout();
        System.out.println("验证登录是否成功:"+subject.isAuthenticated());

    }
}
