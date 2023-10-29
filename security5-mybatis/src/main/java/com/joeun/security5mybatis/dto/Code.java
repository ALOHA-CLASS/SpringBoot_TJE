package com.joeun.security5mybatis.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Code {
    private String groupCode;
    private String name;
    private String value;
    private int seq;
    private String useYn;
    private Timestamp regDate;
    private Timestamp updDate;
    private String updId;
}
