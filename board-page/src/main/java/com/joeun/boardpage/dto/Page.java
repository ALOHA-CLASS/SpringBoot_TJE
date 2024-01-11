package com.joeun.boardpage.dto;

import lombok.Data;

/**
 *  현재 페이지 번호        : 1
 *  페이지당 게시글 수      : 10
 *  노출 페이지 개수        : 10
 *  전체 데이터 개수        : ?
 * 
 *  시작 번호               
 *  끝 번호
 *  첫 번호
 *  마지막 번호
 */
@Data
public class Page {

    private static final int PAGE_NUM = 1;        // 현재 페이지 기본값
    private static final int ROWS = 10;           // 페이지당 게시글 수 기본값
    private static final int COUNT = 10;          // 노출 페이지 개수 기본값

    // ✅ 필수 데이터
    private int page;           // 현재 페이지 번호
    private int rows;           // 페이지 당 게시글 수
    private int count;          // 노출 페이지 개수
    private int total;          // 전체 데이터 수

    // ✔ 수식 데이터
    private int start;          // 시작 번호
    private int end;            // 끝 번호
    private int first;          // 첫 번호
    private int last;           // 마지막 번호

    private int prev;           // 이전 번호
    private int next;           // 다음 번호
    
    private int index;          // 데이터 순서 번호

    // 생성자
    public Page() {
        this(0);
    }

    public Page(int total) {
        this(PAGE_NUM, total);
    }

    public Page(int page, int total) {
        this(page, ROWS, COUNT, total);
    }

    public Page(int page, int rows, int count, int total) {
        this.page = page;
        this.rows = rows;
        this.count = count;
        this.total = total;
        calc();
    }

    // setter
    public void setTotal(int total) {
        this.total = total;
        calc();
    }


    // 페이지 처리
    public void calc() {
        // 첫 번호
        this.first = 1;
        // 마지막 번호
        this.last = (this.total -1) / rows + 1;
        // 시작 번호
        this.start = ((this.page-1) / this.count) * this.count + 1;
        // 끝 번호
        this.end = ((this.page-1) / this.count + 1) * this.count;
        if( this.end > this.last ) this.end = this.last;
        // 이전 번호
        this.prev = this.page > 1 ? this.page - 1 : 1;
        // 다음 번호
        this.next = this.page >= this.last ? this.last : this.page + 1;
        // 데이터 시작  순서 번호
        this.index = (this.page - 1) * this.rows;
    }


}
