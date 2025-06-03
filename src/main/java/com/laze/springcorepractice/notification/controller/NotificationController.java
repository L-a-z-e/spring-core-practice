package com.laze.springcorepractice.notification.controller;

import com.laze.springcorepractice.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
        System.out.println("NotificationService 생성" + notificationService.getServiceType() );
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String message) {
        notificationService.send(to, message);
        return "타입 : " + notificationService.getServiceType() + "알림 발송 완료 / 대상 : " + to;
    }
}
