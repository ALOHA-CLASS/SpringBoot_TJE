package com.joeun.springswagger.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Board 
 * - 게시글 정보
 */
@Data
public class Board {
    @Schema(description = "게시글 번호 (PUT, DELETE 요청 시, 필요합니다.)")
    private int boardNo;        
    @Schema(description = "제목 (POST, PUT 요청 시, 필요합니다.)")
    private String title;
    @Schema(description = "작성자 (POST, PUT 요청 시, 필요합니다.)")
    private String writer;
    @Schema(description = "내용 (POST, PUT 요청 시, 필요합니다.)")
    private String content;
    @Schema(description = "등록 일자 (DEFAULT : now(), DB 에서 현재 시간으로 등록됩니다.)")
    private Date regDate;
    @Schema(description = "수정 일자 (DEFAULT : now(), DB 에서 현재 시간으로 등록됩니다.)")
    private Date updDate;
    @Schema(description = "조회수 (DEFALUT : 0, 사용자가 조회 시, 1씩 증가합니다.)")
    private int views;
    
    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    
}
 