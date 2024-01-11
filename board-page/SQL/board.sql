-- Active: 1701683907642@@127.0.0.1@3306@joeun
-- board 테이블 생성
DROP TABLE IF EXISTS board;

CREATE TABLE `board` (
  `board_no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `writer` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`board_no`)
);

INSERT INTO `board` (`title`, `writer`, `content`)
VALUES
  ('제목1', '작성자1', '내용1'),
  ('제목2', '작성자2', '내용2'),
  ('제목3', '작성자3', '내용3'),
  ('제목4', '작성자4', '내용4'),
  ('제목5', '작성자5', '내용5'),
  ('제목6', '작성자6', '내용6'),
  ('제목7', '작성자7', '내용7'),
  ('제목8', '작성자8', '내용8'),
  ('제목9', '작성자9', '내용9'),
  ('제목10', '작성자10', '내용10');

INSERT INTO `board` (`title`, `writer`, `content`)
SELECT title, writer, content
FROM board;

SELECT COUNT(*) FROM board;