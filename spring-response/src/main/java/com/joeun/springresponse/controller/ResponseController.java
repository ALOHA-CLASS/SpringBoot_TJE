package com.joeun.springresponse.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joeun.springresponse.dto.Board;
import com.joeun.springresponse.dto.Person;
import com.joeun.springresponse.dto.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * 🟢🟡🔴 @RestController
 * : JSON 또는 XML과 같은 데이터를 반환하는 컨트롤러를 지정하는 어노테이션
 * 🔵 RESTful 웹 서비스를 생성하는 데 사용
 * 🔵 @Controller 와 @ResponseBody를 합한 역할을 하는 어노테이션
 * ✔ @Controller       ➡ View 를 반환
 * ✔ @RestController   ➡ 응답 데이터(메시지[상태코드,응답헤더,응답본문(body)])를 반환
 */
@Slf4j
@RestController
public class ResponseController {

    /**
     * 🚀 void
     * - 어떤 작업을 수행하고 응답이 필요 없는 경우
     * ✅ 상태코드 : 200 OK
     * ✅ body : ❌
     */
    @GetMapping("/void")
    public void voidResponse() {
        log.info("[GET] - /void");
    }

    
    /**
     * 🚀 String 
     * - 반환하는 문자열로, HTTP 응답의 본문(body)에 지정한다.
     * ✅ 상태코드 : 200 OK
     * ✅ body : "문자열 데이터를 응답합니다."
     */
    @GetMapping("/string")
    public String stringResponse() {
        log.info("[GET] - /string");
        return "문자열 데이터를 응답합니다.";
    }

    /**
     * 🚀 객체 
     * - 객체를 JSON 형식으로 변환하고, 이를 HTTP 응답 본문에 지정한다.
     * ✅ 상태코드 : 200 OK
     * ✅ body : {"boardNo":0,"title":"제목1","writer":"작성자1","content":"내용1","regDate":null,"updDate":null,"views":0}
     * @return
     */
    @GetMapping("/object")
    public Board objectResponse() {
        log.info("[GET] - /object");
        Board board = new Board("제목1", "작성자1", "내용1");    
        return board;
    }

    /**
     * 🚀 컬렉션 
     * - 컬렉션을 JSON 배열 형식으로 변환하고, 이를 HTTP 응답 본문에 지정한다.
     * ✅ 상태코드 : 200 OK
     * ✅ body  : [{"boardNo":0,"title":"제목1","writer":"작성자1","content":"내용1","regDate":null,"updDate":null,"views":0},{"boardNo":0,"title":"제목2","writer":"작성자2","content":"내용2","regDate":null,"updDate":null,"views":0},{"boardNo":0,"title":"제목3","writer":"작성자3","content":"내용3","regDate":null,"updDate":null,"views":0}]
     * @return
     */
    @GetMapping("/collection")
    public List<Board> collectionResponse() {
        log.info("[GET] - /collection");
        List<Board> boardList = new ArrayList<>();
        boardList.add(new Board("제목1", "작성자1", "내용1"));
        boardList.add(new Board("제목2", "작성자2", "내용2"));
        boardList.add(new Board("제목3", "작성자3", "내용3"));
        return boardList;
    }


    /**
     * 🚀 Map<String, ?>
     * - Map 컬렉션을 JSON 형식으로 변환하여, 
     *   key 에 대한 value 로 다양한 객체들을 계층적인 구조로 HTTP 응답 본문에 지정한다.
     * ✅ 상태코드 : 200 OK
     * ✅ body : {"student":{"name":"김조은","age":20,"studentId":1001,"grade":"1"},"person":{"name":"김조은","age":20},"board":{"boardNo":0,"title":"제목","writer":"작성자","content":"내용","regDate":null,"updDate":null,"views":0}}
     * @return
     */
    @GetMapping("/map")
    public Map<String, ?> mapResponse() {
        log.info("[GET] - /map");

        Map<String, Object> map = new HashMap<>();
        map.put("board", new Board("제목", "작성자", "내용"));
        map.put("person", new Person());
        map.put("student", new Student());

        return map;
    }
    

    /**
     * 🚀 ResponseEntity<⚡>
     * : ResponseEntity 객체를 사용하면, "상태코드/헤더/본문"을 지정하여 응답할 수 있다.
     * ✅ <⚡> 타입 매개변수에 응답할 데이터의 타입을 지정할 수 있다.
     *      - String, Object, Collection 등 지정 가능
     *      - ? 와일드 카드로 지정하면, 특정하지 않고 상황에 따라 동적으로 처리 가능
     * 
     * 1️⃣ 상태코드       : ok(), badRequest(), created(URI), status()
     *  ✔ HttpStatus 응답 상태 코드를 가지고 있는 enum (열거타입)
     * 
     * 2️⃣ 헤더           : ResponseEntity.1️⃣.header("헤더 명", "헤더 값")
     * 
     * 3️⃣ 본문⭕        : ResponseEntity.1️⃣.body( data )
     *                     ResponseEntity.1️⃣.2️⃣.body( data ) 
     * 
     * 4️⃣ 본문❌        : ResponseEntity.1️⃣.build( data )
     *                     ResponseEntity.1️⃣.2️⃣.build( data ) 
     * @return
     */
    @GetMapping("/responseString")
    public ResponseEntity<String> responseEntityResponse() {
        log.info("[GET] - /responseString");
        // 🟢 요청 성공 
        // - 상태코드: 200
        // - body :"200 OK - 요청 성공"
        // return ResponseEntity.ok("200 OK - 요청 성공");
        
        // 🟠 잘못된 요청 - 상태코드
        // - 상태코드: 400
        // - body :"400 Bad Request - 잘못된 요청"
        // return ResponseEntity.badRequest().body("400 Bad Request - 잘못된 요청");

        // 🔴 서버 에러
        // - 상태코드: 500
        // - body :"500 Internal Server Error - 서버 내부 에러"
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 Internal Server Error - 서버 내부 에러");
        return ResponseEntity.ok("200 OK - 요청 성공");
    }

    /**
     * 🚀 ResponseEntity<byte[]>
     * @return
     * @throws IOException
     */
    @GetMapping("/img")
    public ResponseEntity<byte[]> thumbnailImg() throws IOException {
        log.info("[GET] - /img");

        // 이미지 파일을 읽어옴
        ClassPathResource imgFile = new ClassPathResource("sample.png");
        byte[] bytes = Files.readAllBytes(imgFile.getFile().toPath());

        // 이미지 파일을 읽어서 응답
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    /**
     * 🚀 ResponseEntity<byte[]>
     * @return
     * @throws IOException
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        log.info("[GET] - /download");

        // 이미지 파일을 읽어옴
        ClassPathResource imgFile = new ClassPathResource("sample.png");
        byte[] bytes = Files.readAllBytes(imgFile.getFile().toPath());

        // 파일을 다운로드 받도록 응답
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "sample.png");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    /**
     * ResponseEntity<?>
     * - ? 와일드 카드로 지정하면, 특정하지 않고 상황에 따라 동적으로 처리 가능
     * @param board
     * @return
     */
    @PostMapping("/board")
    public ResponseEntity<?> insert(Board board) {
        log.info("[POST] - /board");

        // ✅ 서비스로 데이터 등록 요청
        // ... (생략)
        // newResourceId : 데이터 등록 후, 새로 생성된 자원 id (ex. no, id - boardNo, boardId 등)
        String newResourceId = UUID.randomUUID().toString();    // 여기서는 서비스로 데이터 등록 후, 받아왔다고 가정
        URI location = ServletUriComponentsBuilder      // uri 생성 객체
                        .fromCurrentRequest()           // 현재 요청 경로
                        .path("/{id}")             // /(요청경로)/{id}
                        .buildAndExpand(newResourceId)  // /(요청경로)/{id} ⬅ newResourceId 매핑
                        .toUri();                       // uri 로 변환

        // 🟢 201 CREATED
        // [POST] 데이터 등록 후에는 주로 201 CREATED 상태코드로 응답함
        // ✅ Location 헤더에 : /currentPath/{id} 로 새로운 리소스 URI 를 반환하는 것이 권장됨
        return ResponseEntity.created(location).body("201 CREATED");

        // 🟢 204 No Content
        // 클라이언트에게 추가 데이터를 제공하지 않을 때 사용
        // ✅ 생성된 no, id 값을 전달하지 않는 경우
        // return ResponseEntity.noContent().build();
    }



    /**
     * ResponseEntity<?>
     * - ? 와일드 카드로 지정하면, 특정하지 않고 상황에 따라 동적으로 처리 가능
     * @param board
     * @return
     */
    @PutMapping("/board")
    public ResponseEntity<?> update(Board board) {
        log.info("[PUT] - /board");

        // 🟢 200 OK
        // [PUT] 데이터 수정 후에는 주로 200 OK 상태코드로 응답함
        return ResponseEntity.ok().body("updated");

        // 🟢 204 No Content
        // 클라이언트에게 추가 데이터를 제공하지 않을 때 사용
        // ✅ 생성된 no, id 값을 전달하지 않는 경우
        // return ResponseEntity.noContent().build();
    }


    /**
     * ResponseEntity<?>
     * - ? 와일드 카드로 지정하면, 특정하지 않고 상황에 따라 동적으로 처리 가능
     * @param board
     * @return
     */
    @DeleteMapping("/board/{boardNo}")
    public ResponseEntity<?> delete(@PathVariable int boardNo) {
        log.info("[DELETE] - /board");


        // 🟢 200 OK
        // [DELETE] 데이터 수정 후에는 주로 200 OK 상태코드로 응답함
        return ResponseEntity.ok().build();

        // 🟢 204 No Content
        // 클라이언트에게 추가 데이터를 제공하지 않을 때 사용
        // ✅ DELETE 에는 일반적으로 본문(body)는 포함하지 않음
        // return ResponseEntity.noContent().build();
    }

    
}
