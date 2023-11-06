<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.apache.http.HttpResponse"%>
<%@page import="org.apache.http.client.methods.HttpPost"%>
<%@page import="org.apache.http.impl.client.HttpClients"%>
<%@page import="org.apache.http.client.HttpClient"%>
<%@page import="org.apache.http.HttpEntity"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.http.entity.ContentType"%>
<%@page import="org.apache.http.entity.mime.content.FileBody"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="org.apache.http.entity.mime.HttpMultipartMode"%>
<%@page import="org.apache.http.entity.mime.MultipartEntityBuilder"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8	" pageEncoding="utf-8"%>
<%
	try{
		
		final String encodingType = "utf-8";
		final String boundary = "____boundary____";
	
		/**************** 문자전송하기 예제 ******************/
		/* "result_code":결과코드,"message":결과문구, */
		/* "msg_id":메세지ID,"error_cnt":에러갯수,"success_cnt":성공갯수 */
		/* 각각 다른 개별 내용 > 동시 전송용 입니다.  
		/******************** 인증정보 ********************/
		String sms_url = "https://apis.aligo.in/send_mass/"; // 전송요청 URL
		
		Map<String, String> sms = new HashMap<String, String>();
		
		sms.put("user_id", ""); // SMS 아이디
		sms.put("key", ""); //인증키
		
		/******************** 인증정보 ********************/
		
		/******************** 전송정보 ********************/
		sms.put("sender", ""); // 발신번호
		sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
		sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
		sms.put("testmode_yn", "Y"); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
		sms.put("msg_type", "SMS"); // SMS(단문) , LMS(장문), MMS(그림문자)  = 필수항목
		
		String msg = "(광고)알리고\n회원님 알리고를 추천드려요!!\n무료거부:080xxxxxxxxx"; // 메세지 내용
		int cnt = 500;
		for(int i=1; i<=cnt; i++){
			sms.put("rec_" + i, "01100000" + (i<100 ? (i<10 ? "00"+i : "0"+i) : i) ); // 수신번호_$i 번째  = 필수항목
			sms.put("msg_" + i, msg.replaceFirst("회원님", "회원" + i + "님")); // 내용_$i번째  = 필수항목
		}
		
		sms.put("cnt", String.valueOf(cnt));
		//sms.put("title", "제목입력"); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)
		
		String image = "";
		//image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치
		
		/******************** 전송정보 ********************/
		
		
		/*****/
		/*** ※ 중요 - 기존 send 와 다른 부분  ***
		 *  msg_type 추가 : SMS 와 LMS 구분자 = 필수항목
		 *  receiver(수신번호) 와 msg(내용) 가 rec_1 ~ rec_500 과 msg_1 ~ msg_500 으로 설정가능 = 필수입력(최소 1개이상)
		 * cnt 추가 : 위 rec_갯수 와 msg_갯수에 지정된 갯수정보 지정 = 필수항목 (최대 500개)
		/******/
		
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		
		builder.setBoundary(boundary);
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(Charset.forName(encodingType));
		
		for(Iterator<String> i = sms.keySet().iterator(); i.hasNext();){
			String key = i.next();
			builder.addTextBody(key, sms.get(key)
					, ContentType.create("Multipart/related", encodingType));
		}
		
		File imageFile = new File(image);
		if(image!=null && image.length()>0 && imageFile.exists()){
	
			builder.addPart("image",
					new FileBody(imageFile, ContentType.create("application/octet-stream"),
							URLEncoder.encode(imageFile.getName(), encodingType)));
		}
		
		HttpEntity entity = builder.build();
		
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(sms_url);
		post.setEntity(entity);
		
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