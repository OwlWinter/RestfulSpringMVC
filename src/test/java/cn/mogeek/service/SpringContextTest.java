package cn.mogeek.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * @ClassName SpringContextTest
 * @Description TODO
 * @Author owlwinter
 * @Date 2020/5/21 13:22
 * @Version 1.0
 **/
public class SpringContextTest {
    //@SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        String message = context.getMessage("msg.text", null, Locale.getDefault());
        System.out.println(message);
    }
}
