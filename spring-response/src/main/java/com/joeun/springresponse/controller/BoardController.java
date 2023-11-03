package com.joeun.springresponse.controller;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.springresponse.dto.Board;

import lombok.extern.slf4j.Slf4j;


 

/**
 *  게시판 컨트롤러
 * - 게시글 목록            - [GET] - /board/list
 * - 게시글 조회            - [GET] - /board/read
 * - 게시글 등록            - [GET] - /board/insert
 * - 게시글 등록 처리       - [POST] - /board/insert
 * - 게시글 수정            - [GET] - /board/update
 * - 게시글 수정 처리       - [POST] - /board/update
 * - 게시글 삭제 처리       - [POST] - /board/delete
 */
@Slf4j              // 로그 사용 어노테이션
@Controller
@RequestMapping("/board")
public class BoardController {

    /**
     * 게시글 목록
     * [GET]
     * /board/list
     * model : boardList
     * @return
     * @throws Exception
     */
    @GetMapping(value="/list")
    public String list(Model model) throws Exception {
        log.info("[GET] - /board/list");

        // 데이터 요청
        List<Board> boardList = new ArrayList<>();
        boardList.add(new Board("제목1", "작성자1", "내용1"));
        boardList.add(new Board("제목2", "작성자2", "내용2"));
        boardList.add(new Board("제목3", "작성자3", "내용3"));

        // 모델 등록
        model.addAttribute("boardList", boardList);
        // 뷰 페이지 지정
        return "board/list";
    }


    /**
     * 게시글 조회
     * [GET] 
     * /board/read
     * - model : board, fileList
     * @param model
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping(value="/read")
    public String read(Model model, int boardNo, Files files) throws Exception {
        log.info("[GET] - /board/read");

        // 데이터 요청
        Board board = new Board("제목1", "작성자1", "내용1");    

        // 모델 등록
        model.addAttribute("board", board);
        // 뷰 페이지 지정
        return "board/read";
    }


    /**
     * 게시글 쓰기
     * [GET]
     * /board/insert
     * model : ❌ 
     * @return
     */
    @GetMapping(value="/insert")
    public String insert(@ModelAttribute Board board) {
        int result = new Random().nextInt(2);
        if( result == 0 ) {
            return "redirect:/board/insert";
        }
        return "board/insert";
    }
    
    /**
     * 게시글 쓰기 처리
     * [POST]
     * /board/insert
     * model : ❌
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insert")
    public String insertPro(@ModelAttribute Board board) throws Exception {
        // @ModelAttribute : 모델에 자동으로 등록해주는 어노테이션
        // 데이터 처리
        int result = new Random().nextInt(2);

        // 게시글 쓰기 실패 ➡ 게시글 쓰기 화면
        if( result == 0 ) return "board/insert";

        // 뷰 페이지 지정
        return "redirect:/board/list";
    }
    
    /**
     * 게시글 수정
     * [GET]
     * /board/update
     * model : board
     * @param model
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping(value="/update")
    public String update(Model model, int boardNo) throws Exception {
        // 데이터 요청
        Board board = new Board("제목1", "작성자1", "내용1");    
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰 페이지 지정
        return "board/update";
    }
    /**
     * 게시글 수정 처리
     * [POST]
     * /board/update
     * model : board
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping(value="/update")
    public String updatePro(Board board) throws Exception {
        // 데이터 처리
        int result = new Random().nextInt(2);
        int boardNo = board.getBoardNo();

        // 게시글 수정 실패 ➡ 게시글 수정 화면
        if( result == 0 ) return "redirect:/board/update?boardNo=" + boardNo;
        
        // 뷰 페이지 지정
        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제 처리
     * [POST]
     * /board/delete
     * model : ❌
     * @param boardNo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/delete")
    public String deletePro(int boardNo) throws Exception {
        // 데이터 처리
        int result = new Random().nextInt(2);
        
        // 게시글 삭제 실패 ➡ 게시글 수정 화면
        if( result == 0 ) return "redirect:/board/update?boardNo=" + boardNo;

        // 뷰 페이지 지정
        return "redirect:/board/list";
    }
    
    
}
