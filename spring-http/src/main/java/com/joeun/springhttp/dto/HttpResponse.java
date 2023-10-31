package com.joeun.springhttp.dto;

import java.util.Map;

import lombok.Data;

@Data
public class HttpResponse {
    private Map<String, String> args;
    private Map<String, String> headers;
    private String origin;
    private String url;
}
