package com.laze.springcorepractice.common;

import org.springframework.beans.factory.annotation.Value;

public class MySimpleBean {
    private String message = "Hello from MySimpleBean (configured via @Bean)";

    @Value("${app.greeting}")
    private String greetingMessage;

    @Value("${app.default.user.name}")
    private String defaultUserName;

    @Value("${app.optional.message:Default optional message property not found}")
    private String optionalMessage;

    @Value("${app.max.connections}")
    private int maxConnections;

    @Value("${app.feature.enabled}")
    private boolean featureEnabled;

    public MySimpleBean (){
        System.out.println("MySimpleBean 기본 생성자 호출됨");
    };

    public MySimpleBean(String message) {
        this.message = message;
        System.out.println("MySimpleBean(String message) 생성자 호출됨 message: " + message);
    }

    public void sayHello() {
        System.out.println(message);
        System.out.println("Greeting from Properties: " + greetingMessage);
        System.out.println("Default User Name: " + defaultUserName);
        System.out.println("Optional Message: " + optionalMessage);
        System.out.println("Max Connections: " + maxConnections);
        System.out.println("Is Feature Enabled? " + featureEnabled);
    }

    public void setMessage(String message) {
        System.out.println("setMessage 호출됨 message: " + message);
        this.message = message;
    }
}
