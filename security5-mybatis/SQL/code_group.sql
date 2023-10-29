-- 🎫 code_group : 코드 그룹 테이블
CREATE TABLE code_group (
    group_code VARCHAR(4) NOT NULL,                             -- 업무를 파악할 수 있는 그룹코드값 ex) 유저 (USER, AUTH, POST, ...)
    group_name VARCHAR(100) NOT NULL,                           -- 그룹 코드 명
    use_yn CHAR(1) NOT NULL DEFAULT 'Y',                        -- 그룹 코드 사용 여부 (Y/N)
    reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,      -- 등록일자
    upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,      -- 수정일자
    upd_id VARCHAR(100) NOT NULL,                               -- 마지막 수정 아이디
    PRIMARY KEY (group_code)
);
