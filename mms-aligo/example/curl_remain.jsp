<%@ page language="java" contentType="text/html; charset=utf-8	" pageEncoding="utf-8"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%
	try{

		/**************** 최근 전송 목록 ******************/
		/* "result_code":결과코드,"message":결과문구, */
		/** list : 전송된 목록 배열 ***/
		/******************** 인증정보 ********************/
		String sms_url = "https://apis.aligo.in/remain/"; // 전송요청 URL
		
		String sms = "";
		sms += "user_id=" + ""; // SMS 아이디 
		sms += "&key=" + ""; //인증키
		/******************** 인증정보 ********************/
		
		URL url = new URL(sms_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		OutputStream os = conn.getOutputStream();
		os.write(sms.getBytes());
		os.flush();
		os.close();
		
		String result = "";
		String buffer = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		while((buffer = in.readLine())!=null){
			result += buffer;
		}
		
		in.close();
		
		out.print(result);
		
	}catch(MalformedURLException e1){
		out.print(e1.getMessage());
	}catch(IOException e2){
		out.print(e2.getMessage());
	}
	
%>