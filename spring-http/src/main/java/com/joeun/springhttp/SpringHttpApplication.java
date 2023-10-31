package com.joeun.springhttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeun.springhttp.dto.HttpResponse;

@SpringBootApplication
public class SpringHttpApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringHttpApplication.class, args);

		restTemplate();
		restTemplateToObject();
		webClient();
		webClientToObject();
	}


	/**
	 * RestTemplate 을 사용한 HTTP 요청
	 */
	public static void restTemplate() {
		System.out.println("########### RestTemplate ###########\n");
		RestTemplate restTemplate = new RestTemplate();

		// GET 요청
        String getUrl = "https://httpbin.org/get";
        String response = restTemplate.getForObject(getUrl, String.class);

        System.out.println("########### GET Response ########### \n" + response);

		// POST 요청
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String postUrl = "https://httpbin.org/post";
        String requestData = "{key: value}";
        HttpEntity<String> request = new HttpEntity<>(requestData, headers);

        String postResponse = restTemplate.postForObject(postUrl, request, String.class);

        System.out.println("########### POST Response ########### \n" + postResponse);

	}

	/**
	 * RestTemplate 요청 -> 응답 -> 객체로 변환하기
	 * @throws JsonProcessingException
	 */
	public static void restTemplateToObject() throws JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
		
        // GET 요청
        String getUrl = "https://httpbin.org/get";
        ResponseEntity<String> getResponseEntity = restTemplate.getForEntity(getUrl, String.class);
        String getResponseBody = getResponseEntity.getBody();
		
		HttpResponse httpResponse = objectMapper.readValue(getResponseBody, HttpResponse.class);
		System.out.println("########### Response to Object (RestTemplate) ###########");
		System.out.println(httpResponse);
		System.out.println();
	}


	/**
	 * WebClient 를 사용한 HTTP 요청
	 */
	public static void webClient() {
		System.out.println("########### WebClient ###########\n");
		WebClient webClient = WebClient.create();

		// GET 요청
        String getUrl = "https://httpbin.org/get";

        String response = webClient.get()
            .uri(getUrl)
            .retrieve()
            .bodyToMono(String.class)
            .block(); // block()은 요청 완료를 대기하고 결과를 가져옵니다.

        System.out.println("########### GET Response ########### \n" + response);

		// POST 요청
		String postUrl = "https://httpbin.org/post";
		String requestData = "{ key : value }";

        String postResponse = webClient.post()			// POST 요청
            .uri(postUrl)								// uri 지정
            .contentType(MediaType.APPLICATION_JSON)	// contentType 요청 헤더 설정
            .body(BodyInserters.fromValue(requestData))	// 요청 본문(body)을 설정
            .retrieve()									// 요청
            .bodyToMono(String.class)		// 스트림 형태로 받은 응답을 모노로 변환
            .block(); 									// block()은 요청 완료를 대기하고 결과 받음

        System.out.println("########### POST Response ########### \n" + postResponse);
	}


	/**
	 * WebClient 요청 -> 응답 -> 객체로 변환하기
	 * @throws JsonProcessingException
	 */
	private static void webClientToObject() throws JsonProcessingException {

		WebClient webClient = WebClient.create();

        String getUrl = "https://httpbin.org/get";

        HttpResponse httpResponse = webClient.get()
												.uri(getUrl)
												.retrieve()
												.bodyToMono(HttpResponse.class)
												.block();

		// GetResponse를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(httpResponse);

        // JSON 문자열을 User 객체로 변환
        HttpResponse response = objectMapper.readValue(jsonResponse, HttpResponse.class);

		System.out.println("########### Response to Object (WebClient) ###########");
		System.out.println(response.toString());
		System.out.println();
	}



}
