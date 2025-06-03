package com.laze.springcorepractice.notification.service;

public interface NotificationService {
    void send(String to, String message);
    String getServiceType();
}
