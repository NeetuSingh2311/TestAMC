-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: testamc
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `holdings`
--

DROP TABLE IF EXISTS `holdings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `holdings` (
  `holding_id` bigint NOT NULL AUTO_INCREMENT,
  `instrument_id` bigint DEFAULT NULL,
  `market_value` double DEFAULT NULL,
  `portfolio_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`holding_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holdings`
--

LOCK TABLES `holdings` WRITE;
/*!40000 ALTER TABLE `holdings` DISABLE KEYS */;
INSERT INTO `holdings` VALUES (1,1,100000,1,100,'Equity'),(2,2,50000,1,50,'Equity'),(3,3,50000,1,1000,'Fixed Income'),(4,4,50000,1,500,'Fixed Income'),(5,1,10000,2,400,'Equity'),(6,3,40000,2,700,'Fixed Income'),(7,1,40000,3,120,'Equity'),(8,2,10000,3,50,'Equity'),(9,4,30000,3,70,'Fixed Income'),(10,3,20000,3,89,'Fixed Income');
/*!40000 ALTER TABLE `holdings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruments`
--

DROP TABLE IF EXISTS `instruments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instruments` (
  `instrument_id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ticker_symbol` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`instrument_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruments`
--

LOCK TABLES `instruments` WRITE;
/*!40000 ALTER TABLE `instruments` DISABLE KEYS */;
INSERT INTO `instruments` VALUES (1,1,'Infosys','INFY','Equity of Infosys','Equity'),(2,1,'Deloitte','DEL','Equity of Deloitte','Equity'),(3,2,'Apple Inc. Bonds ','APPLBD','5 year corporate bonds','Fixed Income'),(4,2,'U.S. Treasury Bonds','USTDB','Govt Bond','Fixed Income');
/*!40000 ALTER TABLE `instruments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portfolios`
--

DROP TABLE IF EXISTS `portfolios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portfolios` (
  `portfolio_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `total_market_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`portfolio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portfolios`
--

LOCK TABLES `portfolios` WRITE;
/*!40000 ALTER TABLE `portfolios` DISABLE KEYS */;
INSERT INTO `portfolios` VALUES (1,4,'250000'),(2,6,'50000'),(3,7,'100000');
/*!40000 ALTER TABLE `portfolios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `pan` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'abcd','8001044567','Neetu','123dbhgc','$2a$10$Xew.wAVSr1KJDNDrqlgUIOh.SOLCS88OJCcfoUmhHQCoB.Iz66Moe','USER','neetu@gmail.com'),(5,'abgc','12345','Vetri','123rfgv','$2a$10$KwxX6Jajfz2/fbw1Wo0FMeb57KBGhFiLxV5GZ5Q7oALQ/IAHo02pS','ADMIN','admin@gmail.com'),(6,'Vista','7908519920','Nikhil','DEWPR0987Y','$2a$10$u8B7TZv8MdO6mIPFYpbzHuoYO.LrTA3x0oygfvxDI/mUxiW5J5s22','USER','nikhil@gmail.com'),(7,'Kormangla','8004055678','Surya','DRWHR0986Y','$2a$10$phQcg.ZNC90SNYFl1BuEt.lKk/LXcQCMz9y1eI4EZMOdiPkrsqF56','USER','surya@gmail.com'),(24,'','','','','$2a$10$6Ke9VCOmGwn2vUAULW8rSufMdRfR8HKYwKWuJZe7BDQ0q/LLr2PMm','USER','');
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

-- Dump completed on 2025-07-13 10:47:21
