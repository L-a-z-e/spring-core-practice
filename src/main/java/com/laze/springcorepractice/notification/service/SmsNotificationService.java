package com.laze.springcorepractice.notification.service;

public class SmsNotificationService implements NotificationService{
    @Override
    public void send(String to, String message) {
        System.out.println("Sms 발송: " + to + " -> " + message);
    }

    @Override
    public String getServiceType() {
        return "SMS";
    }
}
