-- 🎫 user_auth : 권한 테이블
CREATE TABLE `user_auth` (
      auth_no int NOT NULL AUTO_INCREMENT       -- 권한번호
    , user_id varchar(100) NOT NULL             -- 아이디
    , auth varchar(100) NOT NULL                -- 권한 (USER, ADMIN, ...)
    , PRIMARY KEY(auth_no)                      
);


-- 기본 데이터
-- 사용자 
-- * 권한 : USER
INSERT INTO user_auth ( user_id,  auth )
VALUES ( 'user', 'USER' );

-- 관리자
-- * 권한 : USER, ADMIN
INSERT INTO user_auth ( user_id,  auth )
VALUES ( 'admin', 'USER' );

INSERT INTO user_auth ( user_id,  auth )
VALUES ( 'admin', 'ADMIN' );


-- ROLE_XXX  ➡  XXX 변환 
UPDATE user_auth
SET auth = SUBSTRING(auth, 6) 
WHERE auth LIKE 'ROLE_%';


