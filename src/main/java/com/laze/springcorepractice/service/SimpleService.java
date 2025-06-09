package com.laze.springcorepractice.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    public String doSomethingSimple(String input) {
        System.out.println("SimpleService: doSomethingSimple called with input: " + input);

        if ("error".equalsIgnoreCase(input)) {
            throw new IllegalArgumentException("Simulated error in SimpleService for input: " + input);
        }
        return "Processed: " + input.toUpperCase();
    }

    public void anotherMethod() {
        System.out.println("SimpleService: anotherMethod called");
    }

    public String executePrivateLogic(String privateInput) {
        System.out.println("SimpleService: executePrivateLogic called");
        String result = privateMethod(privateInput);
        return result;
    }

    private String privateMethod(String privateInput) {
        System.out.println("SimpleService: privateMethod called / input: " + privateInput);
        return "Private Processed:" + privateInput.toUpperCase();
    }
}
