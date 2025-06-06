package com.laze.springcorepractice.config;

import com.laze.springcorepractice.common.MySimpleBean;
import com.laze.springcorepractice.notification.service.EmailNotificationService;
import com.laze.springcorepractice.notification.service.NotificationService;
import com.laze.springcorepractice.notification.service.SmsNotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public NotificationService smsNotificationService() {
        return new SmsNotificationService();
    }

    @Bean
    public NotificationService emailNotificationService() {
        return new EmailNotificationService();
    }

    @Bean
    public MySimpleBean mySimpleBean() {
        System.out.println("Creating MySimpleBean(default message) in AppConfig");
        return new MySimpleBean();
    }

    @Bean(name = "customMessageBean")
    public MySimpleBean MySimpleBeanWithCustomMessage() {
        System.out.println("Creating MySimpleBean(custom message) in AppConfig");
        return new MySimpleBean("Hello from CustomMessageBean");
    }

    @Bean
    public MySimpleBean mySimpleBeanViaSetter() {
        System.out.println("Creating MysimpleBean(setter) in AppConfig");
        MySimpleBean bean = new MySimpleBean();
        bean.setMessage("Hello from MySimpleBeanViaSetter");
        return bean;
    }
}
