package com.laze.springcorepractice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonConsumingPrototype {
    private final PrototypeBean prototypeBean;

    @Autowired
    public SingletonConsumingPrototype(PrototypeBean prototypeBean) {
        System.out.println("SingletonConsumingPrototype using PrototypeBean: ");
        prototypeBean.showCreationTime();
        this.prototypeBean = prototypeBean;
    }

    public void usePrototype() {
        System.out.println("SingletonConsumingPrototype using PrototypeBean");
        prototypeBean.showCreationTime();
    }

    public PrototypeBean getInjectedPrototypeBean() {
        return prototypeBean;
    }
}
