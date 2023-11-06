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
		
		/**************** 최근 전송 목록 ******************/
		/* "result_code":결과코드,"message":결과문구, */
		/** list : 전송된 목록 배열 ***/
		/******************** 인증정보 ********************/
		String sms_url = "https://apis.aligo.in/list/"; // 전송요청 URL
		
		List<NameValuePair> sms = new ArrayList<NameValuePair>();
		
		sms.add(new BasicNameValuePair("user_id", "")); // SMS 아이디 
		sms.add(new BasicNameValuePair("key", "")); //인증키
		/******************** 인증정보 ********************/
		
		sms.add(new BasicNameValuePair("page", "1")); //조회 시작번호1
		sms.add(new BasicNameValuePair("page_size", "10")); //출력 갯수
		sms.add(new BasicNameValuePair("start_date", "")); //조회일 시작
		sms.add(new BasicNameValuePair("limit_day", "7")); //조회일수
	
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
		
	}catch(Exception e){
		out.print(e.getMessage());
	}
	
%>