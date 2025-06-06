package com.laze.springcorepractice.runner;

import com.laze.springcorepractice.common.MyComponentBean;
import com.laze.springcorepractice.common.MySimpleBean;
import com.laze.springcorepractice.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanTestRunner implements CommandLineRunner {

    private ApplicationContext applicationContext;
    private NotificationService primaryNotificationService;
    private NotificationService emailNotificationService;
    private MyComponentBean myComponentBean;
    // 생성자 주입
    public BeanTestRunner(ApplicationContext applicationContext, NotificationService primaryNotificationService, @Qualifier(value = "emailNotificationService") NotificationService emailNotificationService, MyComponentBean myComponentBean) {
        this.applicationContext = applicationContext;
        this.primaryNotificationService = primaryNotificationService;
        this.emailNotificationService = emailNotificationService;
        this.myComponentBean = myComponentBean;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("[BeanTestRunner] run - Inspecting Bean From AppConfig");

        MySimpleBean simpleBean1 = applicationContext.getBean("mySimpleBean", MySimpleBean.class);
        simpleBean1.sayHello();

        MySimpleBean simpleBean2 = applicationContext.getBean("customMessageBean", MySimpleBean.class);
        simpleBean2.sayHello();

        MySimpleBean simpleBean3 = applicationContext.getBean("mySimpleBeanViaSetter", MySimpleBean.class);
        simpleBean3.sayHello();

        System.out.println("[BeanTestRunner] - List Bean (filtered)");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            if (beanName.startsWith("com.laze.springcorepractice") || beanName.contains("Notification") || beanName.contains("mySimpleBean") || beanName.contains("Config") || beanName.contains("Runner")) {
                Object beanInstance = applicationContext.getBean(beanName);
                System.out.println(" - " + beanName + " Type : " + beanInstance.getClass().getName());
            }
        }

        System.out.println("생성자 주입된 MyComponentBean 사용:");
        if (myComponentBean != null) {
            myComponentBean.doSomething();
        } else {
            System.out.println("MyComponentBean이 주입되지 않았습니다.");
        }

        // applicationContext.getBean()으로 MyComponentBean 가져오기 (타입으로 조회 시도)
        try {
            System.out.println("\nMyComponentBean 타입으로 조회 시도 (예상: 성공, 하나만 있으므로):");
            MyComponentBean componentBeanFromContext = applicationContext.getBean(MyComponentBean.class);
            componentBeanFromContext.doSomething();
        } catch (Exception e) {
            System.out.println("MyComponentBean 조회 중 오류 발생: " + e.getMessage());
        }
    }
}
