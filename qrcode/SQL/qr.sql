CREATE TABLE `qr` (
  `qr_no` int NOT NULL AUTO_INCREMENT,
  `parent_table` varchar(45) NOT NULL,
  `parent_no` int NOT NULL,
  `url` text NOT NULL,
  `name` text,
  `file_path` text NOT NULL,
  `file_size` int NOT NULL DEFAULT '0',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`qr_no`)
) COMMENT='QR';