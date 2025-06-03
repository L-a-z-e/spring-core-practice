package com.laze.springcorepractice.notification.service;

import org.springframework.stereotype.Service;

@Service
public class MailNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        System.out.println("Email 발송: " + to + " -> " + message);
    }

    @Override
    public String getServiceType() {
        return "Email";
    }
}
