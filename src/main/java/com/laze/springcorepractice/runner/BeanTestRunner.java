package com.laze.springcorepractice.runner;

import com.laze.springcorepractice.common.MyComponentBean;
import com.laze.springcorepractice.common.MySimpleBean;
import com.laze.springcorepractice.common.PrototypeBean;
import com.laze.springcorepractice.common.SingletonConsumingPrototype;
import com.laze.springcorepractice.config.AppProperties;
import com.laze.springcorepractice.notification.service.NotificationService;
import com.laze.springcorepractice.service.ProfileSpecificMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanTestRunner implements CommandLineRunner {

    private final ApplicationContext applicationContext;
    private final NotificationService primaryNotificationService;
    private final NotificationService emailNotificationService;
    private final MyComponentBean myComponentBean;
    private final AppProperties appProperties;

    @Autowired(required = false)
    private ProfileSpecificMessageService profileSpecificMessageService;

    // 생성자 주입
    public BeanTestRunner(ApplicationContext applicationContext, NotificationService primaryNotificationService, @Qualifier(value = "emailNotificationService") NotificationService emailNotificationService, MyComponentBean myComponentBean, AppProperties appProperties) {
        this.applicationContext = applicationContext;
        this.primaryNotificationService = primaryNotificationService;
        this.emailNotificationService = emailNotificationService;
        this.myComponentBean = myComponentBean;
        this.appProperties = appProperties;
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

        System.out.println("[BeanTestRunner] Testing Singleton Scope (MySimpleBean)");
        MySimpleBean singletonBean1 = applicationContext.getBean("mySimpleBean", MySimpleBean.class);
        MySimpleBean singletonBean2 = applicationContext.getBean("mySimpleBean", MySimpleBean.class);

        System.out.println("singletonBean1: " + singletonBean1);
        System.out.println("singletonBean2: " + singletonBean2);
        System.out.println("is it same instance? : " + (singletonBean1 == singletonBean2));
        singletonBean1.setMessage("Message set by singletonBean1");
        System.out.println("singletonBean2 after singletonBean1.setMessage : ");
        singletonBean2.sayHello();

        System.out.println("[BeanTestRunner] Testing Prototype Scope");
        PrototypeBean prototypeBean1 = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        PrototypeBean prototypeBean2 = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        System.out.println("is it same instance prototypeBean1 == prototypeBean2 ? " + (prototypeBean1==prototypeBean2));

        prototypeBean1.showCreationTime();
        prototypeBean2.showCreationTime();

        System.out.println("[BeanTestRunner] Testing Singleton consuming Prototype");
        SingletonConsumingPrototype consumer1 = applicationContext.getBean("singletonConsumingPrototype", SingletonConsumingPrototype.class);
        SingletonConsumingPrototype consumer2 = applicationContext.getBean("singletonConsumingPrototype", SingletonConsumingPrototype.class);
        System.out.println("is it same instance consumer1 == consumer2 ? " + (consumer1==consumer2));

        System.out.println("Consumer1's Prototype : ");
        consumer1.usePrototype();
        System.out.println("Consumer2's Prototype : ");
        consumer2.usePrototype();

        System.out.println("Is prototype in consumer1 same as prototype in consumer2? " + (consumer1.getInjectedPrototypeBean() == consumer2.getInjectedPrototypeBean()));

        PrototypeBean freshPrototypeFromContext = applicationContext.getBean(PrototypeBean.class);
        System.out.println("Is prototype in consumer1 same as a fresh prototype from context? " + (consumer1.getInjectedPrototypeBean() == freshPrototypeFromContext));

        System.out.println("\n--- [BeanTestRunner] Testing @ConfigurationProperties (AppProperties) ---");
        System.out.println(appProperties.toString());
        System.out.println("App Name from Properties: " + appProperties.getName());
        System.out.println("Server Host: " + appProperties.getServer().getHost());
        System.out.println("Authorized Roles: " + appProperties.getAuthorizedRoles());

        System.out.println("\n--- [BeanTestRunner] Testing Spring Profiles ---");
        if (profileSpecificMessageService != null) {
            System.out.println(profileSpecificMessageService.getMessage());
        } else {
            System.out.println("No ProfileSpecificMessageService bean found. Check active profiles.");
            // 현재 활성화된 프로파일 확인
            String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
            if (activeProfiles.length == 0) {
                System.out.println("Active profiles: none (default profile is implicitly active if 'default' bean exists)");
            } else {
                System.out.println("Active profiles: " + String.join(", ", activeProfiles));
            }
            // 기본 프로파일 확인
            String[] defaultProfiles = applicationContext.getEnvironment().getDefaultProfiles();
            System.out.println("Default profiles: " + String.join(", ", defaultProfiles));
        }
    }
}
