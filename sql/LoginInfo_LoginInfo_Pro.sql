-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: admin.cys6irkoowji.ap-northeast-2.rds.amazonaws.com    Database: LoginInfo
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `LoginInfo_Pro`
--

DROP TABLE IF EXISTS `LoginInfo_Pro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LoginInfo_Pro` (
  `Username` char(20) NOT NULL,
  `password` char(20) NOT NULL,
  `Email` char(30) DEFAULT NULL,
  `Name` char(20) NOT NULL,
  `Id_number` int(11) NOT NULL AUTO_INCREMENT,
  `Chat_Flag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginInfo_Pro`
--

LOCK TABLES `LoginInfo_Pro` WRITE;
/*!40000 ALTER TABLE `LoginInfo_Pro` DISABLE KEYS */;
INSERT INTO `LoginInfo_Pro` VALUES ('GGS1234','1234','GGS1234@kpu.ac.kr','GongGiSuk',11,0),('KYG1234','1234','KYG1234@kpu.ac.kr','KimYoungGon',12,0),('NBK1234','1234','NBK1234@kpu.ac.kr','NaBoKyon',13,0),('NYJ1234','1234','NYJ1234@kpu.ac.kr','NoYoungJoo',14,0),('BJM1234','1234','BJM1234@kpu.ac.kr','BakJungMin',15,0),('BYC1234','1234','BYC1234@kpu.ac.kr','BangYoungCheol',16,0),('BYS1234','1234','BYS1234@kpu.ac.kr','BaeYouSuk',17,0),('SDY1234','1234','SDY1234@kpu.ac.kr','SeoDaeYoung',18,0),('LBK1234','1234','LBK1234@kpu.ac.kr','LeeBoKyung',19,0),('LSH1234','1234','LSH1234@kpu.ac.kr','LeeSangHo',20,0),('LLJ1234','1234','LJJ1234@kpu.ac.kr','LeeJungJoon',21,0),('JST1234','1234','JST1234@kpu.ac.kr','JungSungTak',22,0),('CJP1234','1234','alpacapacapaca@naver.com','ChoiJongPill',23,0),('CJG1234','1234','CJG1234@kpu.ac.kr','ChoiJinGu',24,0),('LYN1234','1234','LYN1234@kpu.ac.kr','LeeYoungNam',25,0),('KJJ1234','1234','KJJ1234@kpu.ac.kr','KimJungJoon',26,0),('SSW1234','1234','SSW1234@kpu.ac.kr','ShinSungWook',27,0),('MJH1234','1234','MJH1234@kpu.ac.kr','MinJungHwan',28,0),('BKW1234','1234','BKW1234@kpu.ac.kr','BakKyungWon',29,0),('LSG1234','1234','LSG1234@kpu.ac.kr','LeeSuGill',30,0),('BMG1234','1234','nick1324@naver.com','BakMinGook',32,0);
/*!40000 ALTER TABLE `LoginInfo_Pro` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 22:37:20
