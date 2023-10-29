-- 🎫 code : 공통 코드 테이블
CREATE TABLE code (
    code_no INT AUTO_INCREMENT,                                 -- 공통코드 번호
    group_code VARCHAR(4) NOT NULL,                             -- 그룹 코드
    `name` VARCHAR(100) NOT NULL,                               -- 코드 이름
    `value` VARCHAR(100) NOT NULL,                              -- 코드 값
    seq INT NOT NULL,                                           -- 순서
    use_yn CHAR(1) NOT NULL DEFAULT 'Y',                        -- 사용 여부
    reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,      -- 등록 일자
    upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,      -- 수정 일자
    upd_id VARCHAR(100) NOT NULL,                               -- 마지막 수정 아이디
    PRIMARY KEY (code_no),              
    FOREIGN KEY (group_code) REFERENCES code_group(group_code)
);


