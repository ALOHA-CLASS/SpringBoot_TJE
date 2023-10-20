package com.joeun.springsecurity.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Users {
    private int userNo;
    private String userId;
    private String userPw;
    private String name;
    private String email;
    private Date regDate;
    private Date updDate;
    private int enabled;            // 휴면여부

}
