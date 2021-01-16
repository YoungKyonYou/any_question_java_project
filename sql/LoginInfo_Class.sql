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
-- Table structure for table `Class`
--

DROP TABLE IF EXISTS `Class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Class` (
  `Class_No` int(11) NOT NULL AUTO_INCREMENT,
  `Classname` char(20) DEFAULT NULL,
  `Class_pro` char(20) DEFAULT NULL,
  PRIMARY KEY (`Class_No`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Class`
--

LOCK TABLES `Class` WRITE;
/*!40000 ALTER TABLE `Class` DISABLE KEYS */;
INSERT INTO `Class` VALUES (4,'Database','GongGiSuk'),(5,'Python','GongGiSuk'),(6,'Language','GongGiSuk'),(7,'Java','GongGiSuk'),(8,'Database','KimYoungGon'),(9,'Computer Architectur','KimYoungGon'),(10,'Java','KimYoungGon'),(11,'Math','NaBoKyon'),(12,'Music','NaBoKyon'),(13,'Language','NaBoKyon'),(14,'Programming','NoYoungJoo'),(15,'Music','NoYoungJoo'),(16,'Philiosophy','NoYoungJoo'),(17,'Python','NoYoungJoo'),(18,'Python','BakJungMin'),(19,'Database','BakJungMin'),(20,'Computer Architectur','BakJungMin'),(21,'Music','BakJungMin'),(22,'Music','BangYoungCheol'),(23,'Language','BangYoungCheol'),(24,'Python','BangYoungCheol'),(25,'PE','BangYoungCheol'),(26,'Music','BaeYouSuk'),(27,'Philiosophy','BaeYouSuk'),(28,'Python','BaeYouSuk'),(29,'Python','SeoDaeYoung'),(30,'Database','SeoDaeYoung'),(31,'Java','SeoDaeYoung'),(32,'Programming','SeoDaeYoung'),(33,'Math','LeeBoKyung'),(34,'Language','LeeBoKyung'),(35,'Philiosophy','LeeBoKyung'),(36,'Programming','LeeBoKyung'),(37,'Music','LeeSangHo'),(38,'Programming','LeeSangHo'),(39,'PE','LeeSangHo'),(40,'Python','LeeSangHo'),(41,'Philiosophy','LeeJungJoon'),(42,'Database','LeeJungJoon'),(43,'Java','LeeJungJoon'),(44,'Language','JungSungTak'),(45,'Database','JungSungTak'),(46,'Java','JungSungTak'),(47,'Programming','JungSungTak'),(48,'Math','ChoiJongPill'),(49,'Programming','ChoiJongPill'),(50,'Philiosophy','ChoiJongPill'),(51,'Math','ChoiJongPill'),(52,'Music','ChoiJongPill'),(53,'Python','ChoiJinGu'),(54,'Computer Architectur','ChoiJinGu'),(55,'Language','ChoiJinGu'),(56,'Music','LeeYoungNam'),(57,'Programming','LeeYoungNam'),(58,'Philiosophy','LeeYoungNam'),(59,'Philiosophy','KimJungJoon'),(60,'Math','KimJungJoon'),(61,'Language','KimJungJoon'),(62,'Python','ShinSungWook'),(63,'Music','ShinSungWook'),(64,'PE','ShinSungWook'),(65,'Python','MinJungHwan'),(66,'Philiosophy','MinJungHwan'),(67,'Computer Architectur','MinJungHwan'),(68,'Java','MinJungHwan'),(69,'Database','BakKyungWon'),(70,'Philiosophy','BakKyungWon'),(71,'Language','BakKyungWon'),(72,'Programming','BakKyungWon'),(73,'Math','LeeSuGill'),(74,'Database','LeeSuGill'),(75,'Programming','LeeSuGill'),(76,'Language','LeeSuGill'),(77,'PE','NoYoungJoo'),(78,'Language','LeeBoKyung'),(79,'Java','test'),(80,'Music','BakMinGook'),(81,'Language','BakMinGook'),(82,'Python','BakMinGook'),(83,'Math','BakMinGook');
/*!40000 ALTER TABLE `Class` ENABLE KEYS */;
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

-- Dump completed on 2019-12-09 22:37:28
