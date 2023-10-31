package com.joeun.springswagger.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.springswagger.dto.TestResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    @Operation(summary = "GET Test endpoint", description = "GET method using Swagger Annotations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestResponseDto.class)))
    })
    public TestResponseDto getTest(
            @Parameter(description = "Input for the GET method", required = true)
            @RequestParam String input) {
        TestResponseDto response = new TestResponseDto("Received input: " + input);
        return response;
    }

    @PostMapping
    @Operation(summary = "POST Test endpoint", description = "POST method using Swagger Annotations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestResponseDto.class)))
    })
    public TestResponseDto postTest(
            @Parameter(description = "Input for the POST method", required = true)
            @RequestBody String input) {
        TestResponseDto response = new TestResponseDto("Received input: " + input);
        return response;
    }

    @PutMapping
    @Operation(summary = "PUT Test endpoint", description = "PUT method using Swagger Annotations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestResponseDto.class)))
    })
    public TestResponseDto putTest(
            @Parameter(description = "Input for the PUT method", required = true)
            @RequestBody String input) {
        TestResponseDto response = new TestResponseDto("Received input: " + input);
        return response;
    }

    @DeleteMapping
    @Operation(summary = "DELETE Test endpoint", description = "DELETE method using Swagger Annotations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestResponseDto.class)))
    })
    public TestResponseDto deleteTest(
            @Parameter(description = "Input for the DELETE method", required = true)
            @RequestParam String input) {
        TestResponseDto response = new TestResponseDto("Received input: " + input);
        return response;
    }
}
