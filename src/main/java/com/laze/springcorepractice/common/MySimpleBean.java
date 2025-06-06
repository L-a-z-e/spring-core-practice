package com.laze.springcorepractice.common;

public class MySimpleBean {
    private String message = "Hello from MySimpleBean (configured via @Bean)";

    public MySimpleBean (){
        System.out.println("MySimpleBean 기본 생성자 호출됨");
    };

    public MySimpleBean(String message) {
        this.message = message;
        System.out.println("MySimpleBean(String message) 생성자 호출됨 message: " + message);
    }

    public void sayHello() {
        System.out.println(message);
    }

    public void setMessage(String message) {
        System.out.println("setMessage 호출됨 message: " + message);
        this.message = message;
    }
}
