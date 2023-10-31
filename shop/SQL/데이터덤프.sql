-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: joeun
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_no` int NOT NULL AUTO_INCREMENT COMMENT '주문번호',
  `ship_name` varchar(100) NOT NULL COMMENT '받는사람이름',
  `zip_code` varchar(100) NOT NULL COMMENT '우편번호',
  `country` varchar(100) NOT NULL COMMENT '국가',
  `address` varchar(200) NOT NULL COMMENT '주소',
  `date` varchar(200) NOT NULL COMMENT '배송일자',
  `order_pw` varchar(200) DEFAULT NULL COMMENT '주문 비밀번호',
  `user_id` varchar(10) DEFAULT NULL COMMENT '회원아이디',
  `total_price` int DEFAULT '0' COMMENT '총가격',
  `phone` varchar(20) DEFAULT NULL COMMENT '비회원 전화번호',
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (10,'김조은','22222','대한민국','부평동 스테이션 타워 708호','2023년 09월 19일',NULL,'joeun',135000,'01020003000'),(11,'비회원-김조은','22222','대한민국','부평동 스테이션 타워 708호','2023년 09월 19일','123456',NULL,100000,'01020003000');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(100) NOT NULL COMMENT '상품ID',
  `name` varchar(100) NOT NULL COMMENT '상품명',
  `unit_price` int DEFAULT NULL COMMENT '가격(단가)',
  `description` text COMMENT '설명',
  `manufacturer` varchar(100) DEFAULT NULL COMMENT '제조업체',
  `category` varchar(100) DEFAULT NULL COMMENT '카테고리',
  `units_in_stock` int NOT NULL DEFAULT '0' COMMENT '재고 수',
  `condition` varchar(100) DEFAULT NULL COMMENT '상태',
  `file` text COMMENT '파일경로',
  `quantity` int NOT NULL DEFAULT '0' COMMENT '장바구니 개수',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('P100001','자바 프로그래밍',50000,'안녕하세요 자바프로그래밍 강의입니다.','알로하클래스','강의',97,'NEW','/static/img/JAVA.jpg',0),('P100002','오라클 데이터베이스',20000,'오라클 데이터베이스 입니다.','알로하클래스','강의',94,'NEW','/static/img/DB.jpg',0),('P100003','HTML CSS JAVASCRIPT',15000,'웹 기초 강의입니다.','알로하클래스','강의',98,'NEW','/static/img/WEB.jpg',0),('P100004','JSP',80000,'JSP 강의입니다.','알로하클래스','강의',99,'NEW','/static/img/JSP.jpg',0),('P123001','톰캣',1000,'1000','1','1',0,'RE','/joeun2708/tomcat/webapps/ROOT/UPLOAD/aa8d23c5-2d_톰캣설치.webp',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_io`
--

DROP TABLE IF EXISTS `product_io`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_io` (
  `io_no` int NOT NULL AUTO_INCREMENT COMMENT '입출고번호',
  `product_id` varchar(100) NOT NULL COMMENT '상품아이디',
  `order_no` int DEFAULT NULL COMMENT '주문번호',
  `amount` int DEFAULT NULL COMMENT '입출고량',
  `type` varchar(100) DEFAULT NULL COMMENT '입고(IN),출고(OUT)',
  `io_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '입출고날짜',
  `user_id` varchar(10) DEFAULT NULL COMMENT '회원아이디',
  PRIMARY KEY (`io_no`),
  KEY `product_id_FK` (`product_id`),
  KEY `product_id_FK_1` (`order_no`),
  CONSTRAINT `order_no_FK` FOREIGN KEY (`order_no`) REFERENCES `order` (`order_no`),
  CONSTRAINT `product_id_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 입출고';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_io`
--

LOCK TABLES `product_io` WRITE;
/*!40000 ALTER TABLE `product_io` DISABLE KEYS */;
INSERT INTO `product_io` VALUES (9,'P100003',10,1,'OUT','2023-10-25 01:15:02','joeun'),(10,'P100002',10,1,'OUT','2023-10-25 01:15:02','joeun'),(11,'P100001',10,2,'OUT','2023-10-25 01:15:02','joeun'),(12,'P100001',11,1,'OUT','2023-10-24 20:39:11',''),(13,'P100002',11,1,'OUT','2023-10-24 20:39:11',''),(14,'P100003',11,2,'OUT','2023-10-24 20:39:11','');
/*!40000 ALTER TABLE `product_io` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `USER_NO` int NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(100) NOT NULL,
  `USER_PW` varchar(200) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `REG_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ENABLED` int DEFAULT '1',
  PRIMARY KEY (`USER_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','$2a$10$MUMHu.kRXYGc80is1iPwWeMXT/ra/PaGp4z7NtwloxkUlLC7cuzj2','사용자33','user@mail.com33','2023-10-24 08:49:12','2023-10-24 08:49:12',1),(2,'admin','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92','관리자','admin@mail.com','2023-10-24 08:49:13','2023-10-24 08:49:13',1),(3,'joeun','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92','김조은','user@mail.com','2023-10-25 03:02:58','2023-10-25 03:02:58',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_auth` (
  `auth_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `auth` varchar(100) NOT NULL,
  PRIMARY KEY (`auth_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES (1,'user','ROLE_USER'),(2,'admin','ROLE_USER'),(3,'admin','ROLE_ADMIN'),(7,'joeun','ROLE_USER'),(8,'joeun','ROLE_USER');
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-25 14:44:52
