package com.laze.springcorepractice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevMessageService implements ProfileSpecificMessageService{

    @Value("${app.profile.message}")
    private String message;

    public DevMessageService() {
        System.out.println("DevMessageService 생성자 호출됨");
    }

    @Override
    public String getMessage() {
        return message;
    }

}
