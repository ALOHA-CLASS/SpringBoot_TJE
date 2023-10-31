-- 상품 테이블
DROP TABLE IF EXISTS `product` CASCADE;

CREATE TABLE product (
	product_id varchar(100) NOT NULL COMMENT '상품ID',
	name varchar(100) NOT NULL COMMENT '상품명',
	unit_price INT NULL COMMENT '가격(단가)',
	description TEXT NULL COMMENT '설명',
	manufacturer varchar(100) NULL COMMENT '제조업체',
	category varchar(100) NULL COMMENT '카테고리',
	units_in_stock INT DEFAULT 0 NOT NULL COMMENT '재고 수',
	`condition` varchar(100) NULL COMMENT '상태',
	file TEXT NULL COMMENT '파일경로',
	quantity INT DEFAULT 0 NOT NULL COMMENT '장바구니 개수', 
	CONSTRAINT product_pk PRIMARY KEY (product_id)
) COMMENT '상품';






INSERT INTO product
(product_id, name, unit_price, description, manufacturer, category, units_in_stock, `condition`, file)
VALUES('P100001', '자바 프로그래밍', 50000, '안녕하세요 자바프로그래밍 강의입니다.', '알로하클래스', '강의', 100, 'NEW', '/static/img/JAVA.jpg');

INSERT INTO product
(product_id, name, unit_price, description, manufacturer, category, units_in_stock, `condition`, file)
VALUES('P100002', '오라클 데이터베이스', 20000, '오라클 데이터베이스 입니다.', '알로하클래스', '강의', 100, 'NEW', '/static/img/DB.jpg');


INSERT INTO product
(product_id, name, unit_price, description, manufacturer, category, units_in_stock, `condition`, file)
VALUES('P100003', 'HTML CSS JAVASCRIPT', 15000, '웹 기초 강의입니다.', '알로하클래스', '강의', 100, 'NEW', '/static/img/WEB.jpg');


INSERT INTO product
(product_id, name, unit_price, description, manufacturer, category, units_in_stock, `condition`, file)
VALUES('P100004', 'JSP', 80000, 'JSP 강의입니다.', '알로하클래스', '강의', 100, 'NEW', '/static/img/JSP.jpg');