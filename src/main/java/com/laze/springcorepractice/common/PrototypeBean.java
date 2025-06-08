package com.laze.springcorepractice.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.time.LocalDateTime;

public class PrototypeBean {
    private String creationTime;

    public PrototypeBean() {
        this.creationTime = LocalDateTime.now().toString();
        System.out.println("PrototypeBean Constructor called at : " + creationTime);
    }

    public void showCreationTime() {
        System.out.println("This PrototypeBean instance was created at : " + creationTime);
    }

    @PostConstruct
    public void init() {
        System.out.println("PrototypeBean @PostConstruct called at : " + creationTime);
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("PrototypeBean @PreDestory called at : " + creationTime);
    }

}
