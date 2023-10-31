package com.joeun.springswagger.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TestResponseDto {

    @Schema(description = "Result of the test operation")
    private String result;

    public TestResponseDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
