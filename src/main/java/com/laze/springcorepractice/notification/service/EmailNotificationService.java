package com.laze.springcorepractice.notification.service;

public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        System.out.println("Email 발송: " + to + " -> " + message);
    }

    @Override
    public String getServiceType() {
        return "Email";
    }
}
