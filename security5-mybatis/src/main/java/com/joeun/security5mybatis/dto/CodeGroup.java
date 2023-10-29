package com.joeun.security5mybatis.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CodeGroup {
    private String groupCode;
    private String groupName;
    private String useYn;
    private Timestamp regDate;
    private Timestamp updDate;
    private String updId;
}
