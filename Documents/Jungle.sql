-- MySQL dump 10.16  Distrib 10.2.19-MariaDB, for Linux (x86_64)
--
-- Host: faure    Database: connorbc
-- ------------------------------------------------------
-- Server version	10.2.19-MariaDB

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
-- Table structure for table `match_invite`
--

DROP TABLE IF EXISTS `match_invite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_invite` (
  `Inviter` varchar(255) NOT NULL,
  `Invitee` varchar(255) NOT NULL,
  PRIMARY KEY (`Inviter`,`Invitee`),
  KEY `Invitee` (`Invitee`),
  CONSTRAINT `match_invite_ibfk_1` FOREIGN KEY (`Inviter`) REFERENCES `user` (`Username`),
  CONSTRAINT `match_invite_ibfk_2` FOREIGN KEY (`Invitee`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_invite`
--

LOCK TABLES `match_invite` WRITE;
/*!40000 ALTER TABLE `match_invite` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_invite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_record`
--

DROP TABLE IF EXISTS `match_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Winner` varchar(255) NOT NULL,
  `Loser` varchar(255) NOT NULL,
  `Game_Start` bigint(20) NOT NULL,
  `Game_End` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Winner` (`Winner`),
  KEY `Loser` (`Loser`),
  CONSTRAINT `match_record_ibfk_1` FOREIGN KEY (`Winner`) REFERENCES `user` (`Username`),
  CONSTRAINT `match_record_ibfk_2` FOREIGN KEY (`Loser`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_record`
--

LOCK TABLES `match_record` WRITE;
/*!40000 ALTER TABLE `match_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_state`
--

DROP TABLE IF EXISTS `match_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_state` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User1` varchar(255) NOT NULL,
  `User2` varchar(255) NOT NULL,
  `State` varchar(3000) NOT NULL,
  `Start_Date` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `User1` (`User1`),
  KEY `User2` (`User2`),
  CONSTRAINT `match_state_ibfk_1` FOREIGN KEY (`User1`) REFERENCES `user` (`Username`),
  CONSTRAINT `match_state_ibfk_2` FOREIGN KEY (`User2`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_state`
--

LOCK TABLES `match_state` WRITE;
/*!40000 ALTER TABLE `match_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('deletedUser','deletedUser');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-03 14:08:43
