function $ajax(type, url, data, async=false) {
			
	// var token = $("meta[name='_csrf']").attr("content");
	// var header = $("meta[name='_csrf_header']").attr("content");

    var header = "[[${_csrf.parameterName}]]"
    var token = "[[${_csrf.token}]]"

	let result;
	$.ajax({
		type: type, 						// HTTP method type(GET, POST) 형식이다.
		url: url, 							// 컨트롤러에서 대기중인 URL 주소이다.
		data: data, 						// Json 형식의 데이터이다.
		async: async,
		beforeSend: function(xhr) {   		/*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
			xhr.setRequestHeader(header, token);
		},
		success: function(res) { 			// 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
			result = res;
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
			// console.log("데이터 전송에 실패하였습니다.")
			console.log("데이터 전송에 실패하였습니다.");
		}
	
	});
	
	return result;

}