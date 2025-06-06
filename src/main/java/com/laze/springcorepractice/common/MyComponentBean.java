package com.laze.springcorepractice.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class MyComponentBean {
    public MyComponentBean() {
        System.out.println("MyComponentBean 기본 생성자 호출됨");
    }

    public void doSomething() {
        System.out.println("MyComponentBean의 doSomething 메서드 호출됨");
    }

    @PostConstruct
    public void init() {
        System.out.println("MyComponentBean의 @PostConstruct 메서드 호출됨");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("MyComponentBean의 @PreDestroy 메서드 호출됨");
    }
}
