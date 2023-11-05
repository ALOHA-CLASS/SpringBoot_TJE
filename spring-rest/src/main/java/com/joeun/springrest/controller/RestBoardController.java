package com.joeun.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.springrest.dto.Board;
import com.joeun.springrest.service.BoardService;

import lombok.extern.slf4j.Slf4j;

/**
 * 🟢 RestFul
 * 게시판 컨트롤러
 * - 게시글 목록            - [GET] - /board
 * - 게시글 조회            - [GET] - /board/10
 * - 게시글 등록 처리       - [POST] - /board
 * - 게시글 수정 처리       - [PUT] - /board
 * - 게시글 삭제 처리       - [DELETE] - /board/10
 */
@Slf4j
@RestController
@RequestMapping("/posts") 
public class RestBoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 게시글 목록
     * [GET]
     * /board
     * response : boardList
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> findAll() {
        log.info("[GET] - /board");
        try {
            List<Board> boardList = boardService.list();
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 게시글 조회
     * [GET]
     * /board/{id}
     * request  : id
     * response : board
     * @param id - 게시글 번호
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        log.info("[GET] - /board/" + id);
        try {
            Board board = boardService.select(id);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 게시글 등록
     * [POST]
     * /board
     * request  : board 
     * response : ⭕, ❌
     * @param board
     * @return
     */
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Board board) {
        log.info("[POST] - /board");
        try {
            int result = boardService.insert(board);
            if( result == 0 )
                return ResponseEntity.badRequest().build();
            return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 게시글 수정
     * [PUT]
     * /board
     * request  : board 
     * response : ⭕, ❌
     * @param board
     * @return
     */
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Board board) {
        log.info("[PUT] - /board");
        try {
            int result = boardService.update(board);
            if( result == 0 )
                return ResponseEntity.badRequest().build();
            return new ResponseEntity<>("UPDATED", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 게시글 삭제
     * [DELETE]
     * /board/{id}
     * request  : id
     * response : ⭕, ❌
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        log.info("[DELETE] - /board/" + id);
        try {
            int result = boardService.delete(id);
            if( result == 0 )
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
