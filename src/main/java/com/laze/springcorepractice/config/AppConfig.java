package com.laze.springcorepractice.config;

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
}
