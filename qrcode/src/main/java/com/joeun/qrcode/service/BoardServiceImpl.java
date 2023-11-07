package com.joeun.qrcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joeun.qrcode.dto.Board;
import com.joeun.qrcode.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<Board> list() throws Exception {
        List<Board> boardList = boardMapper.list();
        return boardList;
    }

    @Override
    public Board select(int boardNo) throws Exception {
        Board board = boardMapper.select(boardNo);
        // 조회수 증가...
        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        String parentTable = "board";
        int parentNo = boardMapper.maxPk();

        // 파일 업로드 
        List<MultipartFile> fileList = board.getFile();

        int count = fileService.upload(parentTable, parentNo, fileList);
        log.info(count + " 개의 파일을 업로드하였습니다.");

        // QR 코드 생성
        


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
    
}
  