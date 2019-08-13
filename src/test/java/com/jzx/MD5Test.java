package com.jzx;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @ClassName: MD5Test
 * @Description: TODO
 * @Author: Jzxxxxx
 * @Date: Created in 2019/8/13 0013上午 11:52
 */
public class MD5Test {
    @Test
    public void testMD5() throws Exception{
        String password="666";
        //加密   MD5
        Md5Hash md5Hash=new Md5Hash(password);
        System.out.println(md5Hash.toString());
        //加密  md5+盐
        Md5Hash md5Hash1=new Md5Hash(password,"zhangsan");
        System.out.println(md5Hash1);
        //加密  md5+盐+散列次数
        Md5Hash md5Hash2=new Md5Hash(password,"zhangsan",3);
        System.out.println(md5Hash2);
    }
}

