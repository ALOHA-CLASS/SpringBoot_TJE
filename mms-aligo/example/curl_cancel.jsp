<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.apache.http.HttpResponse"%>
<%@page import="org.apache.http.client.entity.UrlEncodedFormEntity"%>
<%@page import="org.apache.http.client.methods.HttpPost"%>
<%@page import="org.apache.http.impl.client.HttpClients"%>
<%@page import="org.apache.http.client.HttpClient"%>
<%@page import="org.apache.http.message.BasicNameValuePair"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.http.NameValuePair"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8	" pageEncoding="utf-8"%>
<%
	try{
		
		final String encodingType = "utf-8";

		/**************** 예약문자전송취소 ******************/
		/* "result_code":결과코드,"message":결과문구, */
		/** cancel_date : 취소일자  ***/
		/******************** 인증정보 ********************/
		String sms_url = "https://apis.aligo.in/cancel/"; // 전송요청 URL
		
		List<NameValuePair> sms = new ArrayList<NameValuePair>();
		
		sms.add(new BasicNameValuePair("user_id", "")); // SMS 아이디 
		sms.add(new BasicNameValuePair("key", "")); //인증키
		/******************** 인증정보 ********************/
		
		sms.add(new BasicNameValuePair("mid", "8697445")); // 취소할 메세지ID (필수입력)
		
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(sms_url);
		post.setEntity(new UrlEncodedFormEntity(sms, encodingType));
		
		HttpResponse res = client.execute(post);
		
		String result = "";
		if(res != null){
			BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
			String buffer = null;
			while((buffer = in.readLine())!=null){
				result += buffer;
			}
			in.close();
		}
		
		out.print(result);
		
		/*** 에러코드 ****
		-801 : 메세지ID 미입력
		-802 : 메세지ID 오류
		-803 : 예약대기중인 문자 없음
		-804 : 발송 5분전까지만 취소가능
		-805 : 전송완료로 취소불가
		-809 : 기타오류
		/*****/
		
	}catch(Exception e){
		out.print(e.getMessage());
	}
	
%>