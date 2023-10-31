package com.joeun.springdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.joeun.springdi.bean.Test;

@SpringBootApplication
public class SpringDiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringDiApplication.class, args);

        context = new AnnotationConfigApplicationContext("com.joeun.springdi.bean");
        // IoC 컨테이너에 등록된 모든 빈의 이름 조회
        String[] allBeanNames = context.getBeanDefinitionNames();
        // 조회된 빈 이름 출력
        for (String beanName : allBeanNames) {
            System.out.println("빈 이름: " + beanName);
        }

        Test test = context.getBean(Test.class);
        test.print();
    }
}

