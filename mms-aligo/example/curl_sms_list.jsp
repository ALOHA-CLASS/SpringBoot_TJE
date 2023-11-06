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
			
		/*************  문자전송 결과 상세보기 *****************/
		/** SMS_CNT / LMS_CNT / MMS_CNT : 전송유형별 잔여건수 ***/
		/******************** 인증정보 ********************/
		String sms_url = "https://apis.aligo.in/sms_list/"; // 전송요청 URL
		
		List<NameValuePair> sms = new ArrayList<NameValuePair>();
		
		sms.add(new BasicNameValuePair("user_id", "")); // SMS 아이디 
		sms.add(new BasicNameValuePair("key", "")); //인증키
		/******************** 인증정보 ********************/
		
		sms.add(new BasicNameValuePair("mid", "39003")); // 메세지ID
		sms.add(new BasicNameValuePair("page", "0")); //조회 시작번호1
		sms.add(new BasicNameValuePair("page_size", "10")); //출력 갯수
		
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