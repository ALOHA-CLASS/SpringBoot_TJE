package com.joeun.boardpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.boardpage.dto.Board;
import com.joeun.boardpage.dto.Page;
import com.joeun.boardpage.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
    // quick fix : ctrl + .

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> list(Page page) throws Exception {
        int total = boardMapper.count();
        page.setTotal(total);
        List<Board> boardList = boardMapper.list(page);
        return boardList;
    }

    @Override
    public Board select(int boardNo) throws Exception {
        Board board = boardMapper.select(boardNo);
        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result;
    }
    
    @Override
    public int delete(int boardNo) throws Exception {
        int result = boardMapper.delete(boardNo);
        return result;
    }

    @Override
    public int count() throws Exception {
        return boardMapper.count();
    }
    
}
  