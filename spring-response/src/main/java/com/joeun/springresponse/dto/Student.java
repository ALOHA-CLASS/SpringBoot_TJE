package com.joeun.springresponse.dto;

import lombok.Data;

/**
 * 🍃 빈 정의
 * @Component
 */
@Data
public class Student extends Person {
    private int studentId;
    private String grade;

    public Student() {
        super();
        this.studentId = 1001;
        this.grade = "1";
    }

    @Override
    public String toString() {
        return "Student{name='" + getName() + "', age=" + getAge() + ", studentId=" + studentId + ", grade='" + grade + "'}";
    }
    
}