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
-- Table structure for table `LoginInfo`
--

DROP TABLE IF EXISTS `LoginInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LoginInfo` (
  `Username` varchar(20) NOT NULL,
  `password` char(20) NOT NULL,
  `Id_number` int(11) NOT NULL AUTO_INCREMENT,
  `Email` char(40) NOT NULL,
  `Name` char(20) NOT NULL,
  `Student_ID` char(20) NOT NULL,
  `Department` char(20) NOT NULL,
  PRIMARY KEY (`Id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginInfo`
--

LOCK TABLES `LoginInfo` WRITE;
/*!40000 ALTER TABLE `LoginInfo` DISABLE KEYS */;
INSERT INTO `LoginInfo` VALUES ('Young','1234',2,'qkralsrnr206@naver.com','aaaa','20150048','computer'),('Lisa','1234',3,'Lisa@naver.com','Lisa','20140011','computer'),('Via','1234',4,'Via@gmail.com','Via','20113215','computer'),('Vera ','1234',5,'Vera @gmail.com','Vera','20241255','computer'),('Kylie','1234',6,'Kylie@gmail.com','Kylie','20184565','computer'),('Isla','1234',7,'youngyou1324@naver.com','Isla','20194555','computer'),('London','1234',8,'London@gmail.com','London','20102222','computer'),('Alice','1234',9,'Alice@gmail.com','Alice','20122121','computer'),('Gianna','1234',10,'Gianna@gmail.com','Gianna','20117748','computer'),('Naomi','1234',11,'Naomi@gmail.com','Naomi','20156586','computer'),('Julia','1234',12,'Julia@gmail.com','Julia','20156598','computer'),('Beiley','1234',13,'Beiley@gmail.com','Beiley','20135098','computer'),('Sadie','1234',14,'Sadie@gmail.com','Sadie','20197845','computer'),('Jocelyn','1234',15,'Jocelyn@gmail.com','Jocelyn','20163322','computer'),('Clara','1234',16,'Clara@gmail.com','Clara','20145560','computer'),('Emma','1234',17,'Emma@gmail.com','Emma','20165498','computer'),('Olivia','1234',18,'Olivia@gmail.com','Olivia','20135466','computer'),('Isabella','1234',19,'Isabella@gmail.com','Isabella','20175466','computer'),('Amelia','1234',20,'Amelia@gmail.com','Amelia','20154893','computer'),('Mia','1234',21,'Mia@gmail.com','Mia','20112325','computer'),('Ava','1234',22,'Ava@gmail.com','Ava','20125699','computer'),('Dong','1234',23,'Dong1324@naver.com','YouDongKyon','2014150025','computer'),('Sung','1234',24,'Sung1324@naver.com','YouSungKyon','201510025','computer');
/*!40000 ALTER TABLE `LoginInfo` ENABLE KEYS */;
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

-- Dump completed on 2019-12-09 22:37:51
