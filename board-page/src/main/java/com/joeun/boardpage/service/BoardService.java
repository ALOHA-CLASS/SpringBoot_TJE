package com.joeun.boardpage.service;

import java.util.List;

import com.joeun.boardpage.dto.Board;
import com.joeun.boardpage.dto.Page;

public interface BoardService {

    // 게시글 목록
    public List<Board> list(Page page) throws Exception;
    // 게시글 조회
    public Board select(int boardNo) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int boardNo) throws Exception;

    // 게시글 개수
    public int count() throws Exception;
    
}
 