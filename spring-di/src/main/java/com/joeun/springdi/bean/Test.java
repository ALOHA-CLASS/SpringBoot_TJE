package com.joeun.springdi.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Test {
    
    /**
     * 💉 의존성 자동 주입
     * @Autowired
     */
    @Autowired
    private Person person;

    @Autowired
    private Student student;


    public void print() {
        System.out.println("person : " + person);
        System.out.println("student : " + student);
    }

}
