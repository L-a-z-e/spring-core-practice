package com.laze.springcorepractice.notification.controller;

import com.laze.springcorepractice.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService defaultNotificationService;
    private final NotificationService emailNotificationService;
    private final NotificationService smsNotificationService;

    @Autowired
    public NotificationController(NotificationService defaultNotificationService, @Qualifier("emailNotificationService") NotificationService emailNotificationService, @Qualifier("smsNotificationService") NotificationService smsNotificationService) {
        this.defaultNotificationService = defaultNotificationService;
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
        System.out.println("NotificationService 생성 : 기본값 " + defaultNotificationService.getServiceType());
        System.out.println("NotificationService 생성" + emailNotificationService.getServiceType() );
        System.out.println("NotificationService 생성" + smsNotificationService.getServiceType() );
    }

    @GetMapping("/send-default")
    public String sendDefault(@RequestParam String to, @RequestParam String message) {
        defaultNotificationService.send(to, message);
        return "타입 : " + defaultNotificationService.getServiceType() + "알림 발송 완료 / 대상 : " + to;
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String message) {
        emailNotificationService.send(to, message);
        return "타입 : " + emailNotificationService.getServiceType() + "알림 발송 완료 / 대상 : " + to;
    }

    @GetMapping("/send-sms")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        smsNotificationService.send(to, message);
        return "타입 : " + smsNotificationService.getServiceType() + "알림 발송 완료 / 대상 : " + to;
    }

}
