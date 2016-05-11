CREATE DATABASE  IF NOT EXISTS `cycling` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cycling`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: cycling
-- ------------------------------------------------------
-- Server version	5.6.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cyclist_stage_points`
--

DROP TABLE IF EXISTS `cyclist_stage_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cyclist_stage_points` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cyclistId` int(11) NOT NULL,
  `stageId` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cyclistId_UNIQUE` (`cyclistId`),
  UNIQUE KEY `stageId_UNIQUE` (`stageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyclist_stage_points`
--

LOCK TABLES `cyclist_stage_points` WRITE;
/*!40000 ALTER TABLE `cyclist_stage_points` DISABLE KEYS */;
/*!40000 ALTER TABLE `cyclist_stage_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cyclistraces`
--

DROP TABLE IF EXISTS `cyclistraces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cyclistraces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cyclistId` int(11) NOT NULL,
  `raceId` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cyclistId` (`cyclistId`,`raceId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyclistraces`
--

LOCK TABLES `cyclistraces` WRITE;
/*!40000 ALTER TABLE `cyclistraces` DISABLE KEYS */;
INSERT INTO `cyclistraces` VALUES (8,7,1,0,100),(9,2,1,0,150),(11,1,1,0,450),(12,3,1,0,50),(13,5,1,0,50),(14,6,1,0,50),(15,8,1,0,50),(16,9,1,0,50),(17,10,1,0,200),(18,11,1,0,75),(19,12,1,0,150),(20,14,1,0,400),(21,15,1,0,75),(22,13,1,0,50),(23,4,1,0,50),(24,16,1,0,250),(25,17,1,0,75),(26,18,1,0,200),(27,19,1,0,175),(28,22,1,0,100),(29,21,1,0,50),(30,20,1,0,75);
/*!40000 ALTER TABLE `cyclistraces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cyclists`
--

DROP TABLE IF EXISTS `cyclists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cyclists` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) COLLATE utf8_bin NOT NULL,
  `lastName` varchar(45) COLLATE utf8_bin NOT NULL,
  `birthday` date NOT NULL,
  `teamId` int(11) NOT NULL,
  `country` varchar(3) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `firstName` (`firstName`,`lastName`,`birthday`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyclists`
--

LOCK TABLES `cyclists` WRITE;
/*!40000 ALTER TABLE `cyclists` DISABLE KEYS */;
INSERT INTO `cyclists` VALUES (1,'Alejandro','Valverde','1980-04-25',1,'ESP'),(2,'Igor','Anton','1983-03-02',1,'ESP'),(3,'Eros','Capecchi','1986-06-13',1,'ITA'),(4,'Jonathan','Castroviejo','1987-04-27',1,'ESP'),(5,'Beñat','Intxausti','1986-03-20',1,'ESP'),(6,'Adriano','Malori','1988-01-28',1,'ITA'),(7,'Giovanni','Visconti','1983-01-13',1,'ITA'),(8,'Ion','Izagirre','1989-02-04',1,'ESP'),(9,'Gorka','Izagirre','1987-10-07',1,'ESP'),(10,'Winner','Anacona','1988-08-11',1,'COL'),(11,'Gianluca','Brambilla','1987-08-22',4,'ITA'),(12,'Matteo','Trentin','1989-08-02',4,'ITA'),(13,'Fabio','Sabatini','1985-02-18',4,'ITA'),(14,'Fabio','Aru','1990-07-03',5,'ITA'),(15,'Michele','Scarponi','1979-09-25',5,'ITA'),(16,'Domenico','Pozzovivo','1982-11-30',7,'ITA'),(17,'Ivan','Santaromita','1984-04-30',12,'ITA'),(18,'Nikias','Arndt','1991-11-18',14,'DEU'),(19,'Giacomo','Nizzolo','1989-01-30',19,'ITA'),(20,'Daniele','Bennati','1980-09-24',18,'ITA'),(21,'Giampaolo','Caruso','1980-08-15',15,'ITA'),(22,'Salvatore','Puccio','1989-08-31',17,'ITA');
/*!40000 ALTER TABLE `cyclists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generals`
--

DROP TABLE IF EXISTS `generals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  `cyclist4` int(11) NOT NULL,
  `cyclist5` int(11) NOT NULL,
  `cyclist6` int(11) NOT NULL,
  `cyclist7` int(11) NOT NULL,
  `cyclist8` int(11) NOT NULL,
  `cyclist9` int(11) NOT NULL,
  `cyclist10` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generals`
--

LOCK TABLES `generals` WRITE;
/*!40000 ALTER TABLE `generals` DISABLE KEYS */;
INSERT INTO `generals` VALUES (19,3,11,9,15,16,8,14,23,20,22,21);
/*!40000 ALTER TABLE `generals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intermediatesprint`
--

DROP TABLE IF EXISTS `intermediatesprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intermediatesprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intermediatesprint`
--

LOCK TABLES `intermediatesprint` WRITE;
/*!40000 ALTER TABLE `intermediatesprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `intermediatesprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m1`
--

DROP TABLE IF EXISTS `m1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  `cyclist4` int(11) NOT NULL,
  `cyclist5` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m1`
--

LOCK TABLES `m1` WRITE;
/*!40000 ALTER TABLE `m1` DISABLE KEYS */;
/*!40000 ALTER TABLE `m1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m2`
--

DROP TABLE IF EXISTS `m2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m2`
--

LOCK TABLES `m2` WRITE;
/*!40000 ALTER TABLE `m2` DISABLE KEYS */;
/*!40000 ALTER TABLE `m2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m3`
--

DROP TABLE IF EXISTS `m3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m3`
--

LOCK TABLES `m3` WRITE;
/*!40000 ALTER TABLE `m3` DISABLE KEYS */;
/*!40000 ALTER TABLE `m3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m4`
--

DROP TABLE IF EXISTS `m4`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `cyclist1` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m4`
--

LOCK TABLES `m4` WRITE;
/*!40000 ALTER TABLE `m4` DISABLE KEYS */;
/*!40000 ALTER TABLE `m4` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mhc`
--

DROP TABLE IF EXISTS `mhc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mhc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  `cyclist4` int(11) NOT NULL,
  `cyclist5` int(11) NOT NULL,
  `cyclist6` int(11) NOT NULL,
  `cyclist7` int(11) NOT NULL,
  `cyclist8` int(11) NOT NULL,
  `stageId` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mhc`
--

LOCK TABLES `mhc` WRITE;
/*!40000 ALTER TABLE `mhc` DISABLE KEYS */;
/*!40000 ALTER TABLE `mhc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mountain`
--

DROP TABLE IF EXISTS `mountain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mountain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mountain`
--

LOCK TABLES `mountain` WRITE;
/*!40000 ALTER TABLE `mountain` DISABLE KEYS */;
/*!40000 ALTER TABLE `mountain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(1000) COLLATE utf8_bin NOT NULL,
  `signInDate` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'jarriaga86@gmail.com','e9c8a63c447de662a5cee7895410df8b','2015-02-03'),(2,'jon_arriaga@hotmail.com','9d4217bca73c7e1d199972d5a0a5961f','2015-02-04'),(3,'jon@jon.com','6cb570acdab0e0bfc8e3dcb7bb4edf','2015-02-04');
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playerteams`
--

DROP TABLE IF EXISTS `playerteams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playerteams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playerId` int(11) NOT NULL,
  `raceId` int(11) NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  `cyclist4` int(11) NOT NULL,
  `cyclist5` int(11) NOT NULL,
  `cyclist6` int(11) NOT NULL,
  `cyclist7` int(11) NOT NULL,
  `cyclist8` int(11) NOT NULL,
  `cyclist9` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `playerId` (`playerId`,`raceId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playerteams`
--

LOCK TABLES `playerteams` WRITE;
/*!40000 ALTER TABLE `playerteams` DISABLE KEYS */;
INSERT INTO `playerteams` VALUES (5,1,1,11,20,17,24,27,16,12,29,13);
/*!40000 ALTER TABLE `playerteams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `points`
--

DROP TABLE IF EXISTS `points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `points` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `points`
--

LOCK TABLES `points` WRITE;
/*!40000 ALTER TABLE `points` DISABLE KEYS */;
/*!40000 ALTER TABLE `points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `races`
--

DROP TABLE IF EXISTS `races`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `races` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`year`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `races`
--

LOCK TABLES `races` WRITE;
/*!40000 ALTER TABLE `races` DISABLE KEYS */;
INSERT INTO `races` VALUES (1,'GIRO',2015);
/*!40000 ALTER TABLE `races` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stages`
--

DROP TABLE IF EXISTS `stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raceId` int(11) NOT NULL,
  `description` varchar(50) COLLATE utf8_bin NOT NULL,
  `mHC` int(11) NOT NULL DEFAULT '0',
  `m1` int(11) NOT NULL DEFAULT '0',
  `m2` int(11) NOT NULL DEFAULT '0',
  `m3` int(11) NOT NULL DEFAULT '0',
  `m4` int(11) NOT NULL DEFAULT '0',
  `sInt` int(11) NOT NULL DEFAULT '0',
  `stageNumber` int(11) NOT NULL,
  `isTTT` bit(1) NOT NULL DEFAULT b'0',
  `hasMC` bit(1) NOT NULL,
  `hasRC` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `raceId` (`raceId`,`stageNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stages`
--

LOCK TABLES `stages` WRITE;
/*!40000 ALTER TABLE `stages` DISABLE KEYS */;
INSERT INTO `stages` VALUES (3,1,'San Lorenzo Al Mare - San Remo (CRE) / 17,6 Km.',0,0,0,0,0,0,1,'','\0','\0');
/*!40000 ALTER TABLE `stages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stagesprint`
--

DROP TABLE IF EXISTS `stagesprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stagesprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `cyclist1` int(11) NOT NULL,
  `cyclist2` int(11) NOT NULL,
  `cyclist3` int(11) NOT NULL,
  `cyclist4` int(11) NOT NULL,
  `cyclist5` int(11) NOT NULL,
  `cyclist6` int(11) NOT NULL,
  `cyclist7` int(11) NOT NULL,
  `cyclist8` int(11) NOT NULL,
  `cyclist9` int(11) NOT NULL,
  `cyclist10` int(11) NOT NULL,
  `cyclist11` int(11) NOT NULL,
  `cyclist12` int(11) NOT NULL,
  `cyclist13` int(11) NOT NULL,
  `cyclist14` int(11) NOT NULL,
  `cyclist15` int(11) NOT NULL,
  `cyclist16` int(11) NOT NULL,
  `cyclist17` int(11) NOT NULL,
  `cyclist18` int(11) NOT NULL,
  `cyclist19` int(11) NOT NULL,
  `cyclist20` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stageId` (`stageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stagesprint`
--

LOCK TABLES `stagesprint` WRITE;
/*!40000 ALTER TABLE `stagesprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `stagesprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stagettt`
--

DROP TABLE IF EXISTS `stagettt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stagettt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stageId` int(11) NOT NULL,
  `team1` int(11) NOT NULL,
  `team2` int(11) NOT NULL,
  `team3` int(11) NOT NULL,
  `team4` int(11) NOT NULL,
  `team5` int(11) NOT NULL,
  `team6` int(11) NOT NULL,
  `team7` int(11) NOT NULL,
  `team8` int(11) NOT NULL,
  `team9` int(11) NOT NULL,
  `team10` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stageId` (`stageId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stagettt`
--

LOCK TABLES `stagettt` WRITE;
/*!40000 ALTER TABLE `stagettt` DISABLE KEYS */;
INSERT INTO `stagettt` VALUES (1,3,1,5,14,15,17,12,7,4,19,18);
/*!40000 ALTER TABLE `stagettt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `uciCode` varchar(3) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`uciCode`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (7,'Ag2r La Mondiale','ALM'),(5,'Astana Pro Team','AST'),(6,'BMC Racing Team','BMC'),(4,'Etixx - Quick Step','EQS'),(8,'FDJ','FDJ'),(9,'IAM Cycling','IAM'),(10,'Lampre - Merida','LAM'),(11,'Lotto - Soudal','LTS'),(1,'Movistar Team','MOV'),(12,'Orica - GreenEDGE','OGE'),(13,'Team Cannondale - Garmin','TCG'),(14,'Team Giant - Alpecin','TGA'),(15,'Team Katusha','KAT'),(16,'Team LottoNL - Jumbo','TLJ'),(17,'Team Sky','SKY'),(18,'Tinkoff - Saxo','TCS'),(19,'Trek Factory Racing','TFR');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-15 22:34:09
