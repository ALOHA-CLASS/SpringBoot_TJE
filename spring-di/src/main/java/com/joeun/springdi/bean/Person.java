package com.joeun.springdi.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("person") // 이 클래스를 스프링 빈으로 등록하기 위한 어노테이션
public class Person {
    private String name;
    private int age;

    // 기본 생성자
    public Person() {
        this.name = "김조은";
        this.age = 20;
    }

}
