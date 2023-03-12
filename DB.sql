-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zli
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `branchemployee`
--

DROP TABLE IF EXISTS `branchemployee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branchemployee` (
  `UserID` int NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `StatusInSystem` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `PhoneNumber` varchar(45) DEFAULT NULL,
  `Branch` varchar(45) DEFAULT NULL,
  `permission` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branchemployee`
--

LOCK TABLES `branchemployee` WRITE;
/*!40000 ALTER TABLE `branchemployee` DISABLE KEYS */;
INSERT INTO `branchemployee` VALUES (25,'sam','jan','CONFIRMED',NULL,NULL,'NORTH',NULL);
/*!40000 ALTER TABLE `branchemployee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branchmanager`
--

DROP TABLE IF EXISTS `branchmanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branchmanager` (
  `userID` int NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `branchType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branchmanager`
--

LOCK TABLES `branchmanager` WRITE;
/*!40000 ALTER TABLE `branchmanager` DISABLE KEYS */;
INSERT INTO `branchmanager` VALUES (2,'Emma','Watson','NORTH'),(21,'br','white','SOUTH'),(22,'rami','flen','WEST'),(23,'jack','floden','EAST');
/*!40000 ALTER TABLE `branchmanager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog` (
  `Code` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(60) DEFAULT NULL,
  `Details` varchar(100) DEFAULT NULL,
  `Category` varchar(70) DEFAULT NULL,
  `Type` varchar(70) DEFAULT NULL,
  `Occasions` varchar(50) DEFAULT NULL,
  `Color` varchar(50) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,' Black FlowerPot','1 FlowerPot Black','Item','FlowerPots',NULL,'Black',10),(2,' Pink  FlowerPots','1 Pink FlowerPot','Item','FlowerPots',NULL,'Pink',10),(3,' White  FlowerPot','1   White  FlowerPot','Item','FlowerPots',NULL,'White',10),(4,'Auria Branche','1  Auria Branches','Item','FlowerBranches',NULL,'Black',7),(5,'B. Special','5 Yellow Roses , 6 Red Roses , 8 White Roses','Product','Bouqute','MOM','Multicolor',210),(6,'Be Happy','1 FlowerPot Pink , 50 White roses','Product','FlowerPots','Birthday','White',170),(7,'Blossom','1 Blossom','Item','FlowerBranches',NULL,'White',5),(8,'Broccoli ','1  Broccoli seediling','Item','Seedlings',NULL,'Green',5),(9,'Chili pepper ','1 Chili pepper seedling','Item','Seedlings',NULL,'Green',5),(10,'Cosmos','1 Cosmos Branch','Item','FlowerBranches',NULL,'Pink',12),(11,'Cotton Candy',' 30 Pink Guzmania , 1 Pink Fower pot','Product','FlowerPots','MOM','Pink',190),(12,'Fresh Start','10 Orange gerbera,5 Green Branches','Product','Bouqute','Graduation','Orange',170),(13,'Full Moon','1 White FlowerPot , 20  White Rose ','Product','FlowerPots','Wedding','White',190),(14,'Green Branches','1 Green Branches','Item','FlowerBranches',NULL,'Green',5),(15,'Guzmania','1   Pink Guzmania','Item','Flowers',NULL,'Pink',10),(16,'Her Side','10 Pink Roses , 5 Purple','Product','Bouqute','Birthday','Pink ',150),(17,'Life style',' 1 Orchid FlowerBranch , 1 White FlowerPot','Product','FlowerPots','Graduation','Pink',80),(18,'Love Is in the Air','20 Orange Roses ,10 Black Auria Branches.','Product','Bouqute','Wedding','Orange',150),(19,'Loved','20 Red Roses , 1 Black FlowerPot','Product','FlowerPots','Birthday','Red',95),(20,'Mom’s Love',' 10 violets, 5  pink lilies','Product','Bouqute','MOM','Purple',130),(21,'Orange Rose','1 Orange Rose','Item','Flowers',NULL,'Orange',7),(22,'Orchid','1 Orchid FlowerBranch','Item','FlowerBranches',NULL,'Pink',10),(23,'Pink Lilies','1 Pink Liles Rose','Item','Flowers',NULL,'Pink',5),(24,'Pink Rose','1 Pink Rose','Item','Flowers',NULL,'Pink',7),(25,'Pure','20 White lily','Product','Bouqute','Graduation','White',190),(27,'Pure & Simple','10 White lily, 10 white lisianthus','Product','Bouqute','Wedding','White',210),(28,'Purple ','1 Purple Rose','Item','Flowers',NULL,'Purple',7),(29,'Red Rose','1 Red Rose ','Item','Flowers','','Red',5),(30,'Simply Red','30 Red Roses','Product','Bouqute','Wedding','Red',150),(31,'Sunflower','1 sunFlowers','Item','Flowers',NULL,'Yellow',10),(32,'Sunrise','30  sunflowers','Product','Bouqute','MOM','Yellow',90),(33,'Tomatos ','1 Tomatos seedling','Item','Seedlings',NULL,'Green',10),(34,'Viloet','1 Violets Rose','Item','Flowers',NULL,'Purple',10),(35,'Vogue','30 White Roses , 10 Red Roses , 5 Green Branches','Product','Bouqute','Graduation','Multicolor',220),(36,'White Lily ','1 White Lily','Item','Flowers',NULL,'White',10),(37,'White Lisianthus','1 White Lisianthus','Item ','Flowers',NULL,'White',12),(38,'White Rose','1 White Rose','Item','Flowers',NULL,'White',9),(39,'Yellow Rose','1 Yellow Rose','Item','Flowers',NULL,'Yellow',6),(40,'Yellow Submarine','15 Yellow , 5 Orange','Product','Bouqute','Birthday','Yellow',120),(41,'111','4',NULL,NULL,NULL,NULL,50);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `UserID` int NOT NULL,
  `FirstName` varchar(70) DEFAULT NULL,
  `LastName` varchar(70) DEFAULT NULL,
  `StatusInSystem` varchar(70) DEFAULT NULL,
  `IsLoggedIn` tinyint DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `PhoneNumber` varchar(50) DEFAULT NULL,
  `CreditCardNumber` varchar(50) DEFAULT NULL,
  `CreditCardCVVCode` varchar(50) DEFAULT NULL,
  `CreditCardDateOfExpiration` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Olivia','Saren','FROZEN',0,'Olivia.Sarn@gmail.com','050-1234567','10000','123','07/22'),(9,'Luna','Kell','FROZEN',0,'Luna.Kell@gmail.com','052-9876543','10003','963','08/22'),(10,'Sam','Morelo','PENDING_APPROVAL',0,'Sam.Morelo@gmail.com','053-1239678','10009','632','09/23'),(11,'Ella','Stone','PENDING_APPROVAL',0,'Ella.Stone@gmail.com','054-7896543','10010','786','10/26'),(13,'Nora','Styles','CONFIRMED',0,'Nora.Styles@gmail.com','052-3214555','10018','747','07/29'),(16,'Jad','Jonas','CONFIRMED',0,'Jad.Jonas@gmail.com','050-7922361','10031','364','12/25'),(18,'Daylan ','Obrien','CONFIRMED',0,'Daylan.Obrien@gmail.com','054-9367412','10045','340','10/24'),(30,'n','NetWorker',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,'BE','BranchEmployee',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,'a','SurveyExpert',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,'Emma','BranchManager',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `usertype` varchar(45) DEFAULT NULL,
  `active_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'Olivia','Olivia1','Customer',0),(3,'Charlotte','Charlotte','NetWorker',0),(4,'Amelia','Amelia','ServiceWorker',0),(6,'Sophia','Sophia','NetWorker',0),(7,'Isabella','Isabella','ServiceWorker',0),(8,'Mia','Mia','NetWorker',0),(9,'Luna','Luna','Customer',0),(10,'Sam','Sam','Customer',0),(11,'Ella','Ella','Customer',0),(12,'Mila','Mila','CEO',0),(13,'Nora','Nora','Customer',0),(14,'Zoe','Zoe','ServiceWorker',0),(15,'Anna','Anna','ServiceWorker',0),(16,'cu','cu','Customer',0),(18,'d','d','Customer',1),(20,'Eden','Eden','ServiceWorker',0),(22,'b','b','BranchManager',1),(30,'n','n','MarketingWorker',0),(70,'be','be','BranchEmployee',0),(80,'a','a','SurveyExpert',0),(90,'Emma','Emma2','BranchManager',0);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marketingworker`
--

DROP TABLE IF EXISTS `marketingworker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marketingworker` (
  `UserID` int NOT NULL,
  `FirstName` varchar(70) DEFAULT NULL,
  `LastName` varchar(70) DEFAULT NULL,
  `StatusInSystem` varchar(70) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `PhoneNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketingworker`
--

LOCK TABLES `marketingworker` WRITE;
/*!40000 ALTER TABLE `marketingworker` DISABLE KEYS */;
INSERT INTO `marketingworker` VALUES (3,'Charlotte','Kun','FROZEN','Charlotte.Kun@gmail.com','051-7456320'),(6,'Sophia','Luis','PENDING_APPROVAL','Sophia.Luis@gmail.com','055-1234567'),(8,'Mia','Morelo','CONFIRMED','Mia.Morelo@gmail.com','054-7896541'),(17,'Nia','Sar','CONFIRMED','Nia.Sar@gmail.com','053-9632587');
/*!40000 ALTER TABLE `marketingworker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `CustomerID` int NOT NULL,
  `OrderNumber` int DEFAULT NULL,
  `result` varchar(45) DEFAULT NULL,
  `compensation` double DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (18,57,'Order Confirmed Successfully:)',10),(21,99,'You won\'t get compensation.',0),(999,99,'You got full compenastion.',11000);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderNumber` int NOT NULL AUTO_INCREMENT,
  `CustomerId` int DEFAULT NULL,
  `Details` varchar(600) DEFAULT NULL,
  `Branch` varchar(50) DEFAULT NULL,
  `Activity` varchar(70) DEFAULT NULL,
  `Status` varchar(70) DEFAULT NULL,
  `SupplyType` varchar(70) DEFAULT NULL,
  `ReceiverName` varchar(45) DEFAULT NULL,
  `ReceiverAddress` varchar(45) DEFAULT NULL,
  `receiverPhoneNumber` varchar(45) DEFAULT NULL,
  `DeliveryFee` double DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `TotalPrice` double DEFAULT NULL,
  `OrderDate` datetime DEFAULT NULL,
  `ReceivedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`OrderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (24,18,'details','North','Purchased','CONFIRM','TAKE_AWAY',NULL,NULL,NULL,0,100,85,'2022-01-02 21:22:00','2022-06-28 22:20:00'),(25,18,'details','South','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,126,'2022-06-06 02:00:35','2022-07-09 00:00:00'),(26,18,'nullItem Name: Loved Price:  95.0 Quantity:  1','South','Purchased','CONFIRM','TAKE_AWAY',NULL,NULL,NULL,0,100,103.5,'2022-06-06 02:12:02','2022-06-20 15:30:00'),(27,18,'nullItem Name: Loved Price:  95.0 Quantity:  1Item Name: Her Side Price:  150.0 Quantity:  1','South','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,238.5,'2022-06-06 02:13:45','2022-06-22 15:30:00'),(28,18,'nullItem Name: Loved Price:  95.0 Quantity:  1 ','South','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,103.5,'2022-06-06 02:15:19','2022-06-15 17:00:00'),(29,18,'nullItem Name: Loved Price:  95.0 Quantity:  1 ','South','Purchased','CONFIRM','TAKE_AWAY',NULL,NULL,NULL,0,100,103.5,'2022-06-06 02:16:42','2022-06-06 18:00:00'),(30,18,'Item Name: Yellow Submarine Price:  120.0 Quantity:  1 ','West','Cancel','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,112,'2022-06-06 02:17:51','2022-06-01 18:00:33'),(31,18,'Item Name: Her Side Price:  150.0 Quantity:  1 ','North','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,85,'2022-06-06 02:34:36','2022-06-09 15:00:00'),(32,18,' ',NULL,'Purchased','C','TAKE_AWAY',NULL,NULL,NULL,0,100,0,'2022-06-06 02:37:46',NULL),(33,18,'Item Name: Loved Price:  95.0 Quantity:  1 ','East','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,109.25,'2022-06-06 02:40:08','2022-06-22 15:00:00'),(34,18,'Item Name: Be Happy Price:  170.0 Quantity:  1 ',NULL,'Purchased','REJECTED','TAKE_AWAY',NULL,NULL,NULL,0,100,190,'2022-06-06 03:12:37',NULL),(37,18,'Item Name: Yellow Submarine Price:  120.0 Quantity:  1 ','South','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,126,'2022-06-06 03:37:41','2022-06-17 12:00:00'),(38,18,'Item Name: Loved Price:  95.0 Quantity:  1 Item Name: Yellow Submarine Price:  120.0 Quantity:  1 ','West','Purchased','Rejected','DELIVARY','aaa','aaa','aaa',0,100,188,'2022-06-06 03:40:08','2022-06-18 16:30:00'),(39,18,'Item Name: Loved Price:  95.0 Quantity:  1 Item Name: Yellow Submarine Price:  120.0 Quantity:  1 ','West','Purchased','Waiting','DELIVARY','1aaa','aaa','aaa',0,100,188,'2022-06-06 03:40:58','2022-06-10 11:00:00'),(40,18,'Item Name: Be Happy Price:  170.0 Quantity:  1 ',NULL,'Purchased','Waiting','EXPRESS',NULL,NULL,NULL,0,100,0,'2022-06-06 03:56:44',NULL),(41,18,'Item Name: Loved Price:  95.0 Quantity:  1 ',NULL,'Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,0,'2022-06-06 04:51:59',NULL),(42,18,'Item Name: Loved Price:  95.0 Quantity:  1 ',NULL,'Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,115,'2022-06-06 04:59:14',NULL),(43,18,'Item Name: Loved Price:  120.0 Quantity:  1 ',NULL,'Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,140,'2022-06-06 18:22:22',NULL),(44,18,'Item Name: Loved Price:  120.0 Quantity:  1 Item Name: Pure & Simple Price:  210.0 Quantity:  1 Item Name: Simply Red Price:  150.0 Quantity:  1 ',NULL,'Purchased','Waiting','EXPRESS',NULL,NULL,NULL,0,100,500,'2022-06-06 18:24:14',NULL),(45,18,' ',NULL,'Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,0,'2022-06-06 18:24:36',NULL),(46,18,'Item Name: Orchid Price:  10.0 Quantity:  1 Item Name: Loved Price:  120.0 Quantity:  1 Item Name: Pure & Simple Price:  210.0 Quantity:  1 Item Name: Orange Rose Price:  7.0 Quantity:  1 ','WEST','Cancel','Waiting','DELIVARY','gg','oo','098',20,100,293.6,'2022-06-07 21:16:18','2022-06-14 12:00:00'),(47,18,'Item Name: Mom’s Love Price:  130.0 Quantity:  2 Item Name: Red Rose Price:  5.0 Quantity:  1 Item Name: Pink Lilies Price:  5.0 Quantity:  1 Item Name: Mom’s Love Price:  130.0 Quantity:  2 Item Name: Red Rose Price:  5.0 Quantity:  1 Item Name: Pink Lilies Price:  5.0 Quantity:  1 ','SOUTH','Cancel','Waiting','DELIVARY',NULL,NULL,NULL,0,100,448,'2022-06-07 22:15:57','2022-06-16 12:00:00'),(48,18,'Item Name: Loved Price:  120.0 Quantity:  1 Item Name: Red Rose Price:  5.0 Quantity:  1 Item Name: Orange Rose Price:  7.0 Quantity:  1 ','WEST','Cancel','Waiting','DELIVARY','dd','dd88','66',20,100,121.60000000000001,'2022-06-08 12:50:11','2022-06-05 13:00:00'),(49,18,'Item Name: Her Side Price:  150.0 Quantity:  1 ','NORTH','Purchased','Waiting','DELIVARY','','','',20,100,136,'2022-06-09 19:28:02','2022-06-08 00:00:00'),(50,18,'','NORTH','Cancel','Waiting','TAKE_AWAY',' ','  ',' ',0,100,0,'2022-06-09 19:32:29','2022-06-08 00:00:00'),(51,18,'','EAST','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,0,'2022-06-09 20:38:59','2022-06-06 00:00:00'),(52,18,'','EAST','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,0,'2022-06-09 20:38:59','2022-06-06 00:00:00'),(53,18,'','EAST','Purchased','Waiting','TAKE_AWAY',NULL,NULL,NULL,0,100,0,'2022-06-09 20:38:59','2022-06-06 00:00:00');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartley_reports`
--

DROP TABLE IF EXISTS `quartley_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quartley_reports` (
  `year` varchar(45) NOT NULL,
  `quarter` varchar(45) NOT NULL,
  `branchType` varchar(45) NOT NULL,
  `reportDetails` longblob,
  PRIMARY KEY (`year`,`quarter`,`branchType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartley_reports`
--

LOCK TABLES `quartley_reports` WRITE;
/*!40000 ALTER TABLE `quartley_reports` DISABLE KEYS */;
INSERT INTO `quartley_reports` VALUES ('2015','2','EAST',_binary '1,56,2,30,3,120,4,330,5,130,6,556,7,759,8,103,9,922,10,222'),('2016','3','WEST',_binary '1,500,2,232,3,422,4,122,5,121,6,46,7,648,8,100,9,750,10,452'),('2017','4','EAST',_binary '1,50,2,250,3,470,4,100,5,210,6,56,7,450,8,150,9,550,10,132'),('2020','2','SOUTH',_binary '1,566,2,200,3,420,4,10,5,110,6,456,7,740,8,100,9,950,10,432'),('2022','1','NORTH',_binary '1,0,2,0,3,0,4,0,5,0,6,456,7,740,8,100,9,950,10,432');
/*!40000 ALTER TABLE `quartley_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `month` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `reportType` varchar(45) NOT NULL,
  `branchType` varchar(45) NOT NULL,
  `reportDetails` longblob,
  `reportDuration` varchar(45) NOT NULL,
  PRIMARY KEY (`month`,`year`,`reportType`,`branchType`,`reportDuration`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES ('1','2010','Orders','EAST',_binary 'This is a report about the orders of the branch :\r\nnext to each item written number of times it was ordered. \r\nYellow Submarine-5\r\nYellow Rose-3\r\nWhite Rose-2\r\nWhite Lisianthus-1\r\nWhite Lily -7\r\nVogue-5\r\nViloet-5\r\nTomatos-1\r\nSunrise-35\r\nSunflower-35\r\nSimply Red-38\r\nRed Rose-20\r\nPurple -1\r\nPure & Simple-1\r\nPure-0\r\nPink Rose-1\r\nPink Lilies-4\r\nOrchid-5\r\nOrange Rose-16\r\nMom’s Love-0\r\nLoved-2\r\nLove Is in the Air-1\r\nLife style-17\r\nHer Side-20\r\nGuzmania-10\r\nGreen Branches-19\r\nFull Moon-12\r\nFresh Start-11\r\nCotton Candy-7\r\nCosmos-18\r\nChili pepper-0\r\nBroccoli-2\r\nBlossom-6\r\nBe Happy-3\r\nB. Special-3\r\nAuria Branche-4\r\n White  FlowerPot-9\r\n Pink  FlowerPots-2\r\n Black FlowerPot-4\r\nTotal orders:335','monthly'),('1','2018','Performance','EAST',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 50 orders.\r\n 3 arrived late.\r\npercentage of the late orders is : 6%.\r\n9 customers complained .\r\npercentage of the complained customers: 18%.\r\n2 orders were canceled.\r\npercentage of the canceled orders:4%.\r\n','monthly'),('10','2019','Performance','WEST',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 300orders.\r\n 20 arrived late.\r\npercentage of the late orders is : 6.6%.\r\n33 customers complained .\r\npercentage of the complained customers: 11%.\r\n13 orders were canceled.\r\npercentage of the canceled orders:4.3%.\r\n','monthly'),('11','2022','Income','NORTH',_binary 'This is the first try for displaying the reports to the breanch manger .\r\nthe report type is income, branch type is NORTH.\r\nTHE INCOME OF THE BRANCH IN THIS MONTH IS 45,000 SHIEKLS','monthly'),('12','2021','Income','NORTH',_binary 'this is the second try \r\nasdasd\r\nasdas\r\ndasd\r\nasdasd\r\nasdas\r\nsdasd\r\nasdasd\r\nassdasda\r\nsdasdasd\r\nadassdas\r\ndasdasdasdsadasdass\r\ndassdasdasdasdasd\r\n','monthly'),('2','2014','Income','SOUTH',_binary 'this is income report of the branch.\r\nOrders income 70000\r\nElectrical expenses: 5000.\r\nwater expenses: 10000\r\nbranch rent expense:10000.\r\nnet income : 45000','monthly'),('2','2016','Orders','WEST',_binary 'This is a report about the orders of the branch :\r\nnext to each item written number of times it was ordered. \r\nYellow Submarine-5\r\nYellow Rose-60\r\nWhite Rose-2\r\nWhite Lisianthus-1\r\nWhite Lily -6\r\nVogue-7\r\nViloet-5\r\nTomatos-2\r\nSunrise-22\r\nSunflower-5\r\nSimply Red-37\r\nRed Rose-28\r\nPurple -71\r\nPure & Simple-20\r\nPure-7\r\nPink Rose-1\r\nPink Lilies-5\r\nOrchid-6\r\nOrange Rose-8\r\nMom’s Love-1\r\nLoved-3\r\nLove Is in the Air-1\r\nLife style-72\r\nHer Side-10\r\nGuzmania-10\r\nGreen Branches-13\r\nFull Moon-12\r\nFresh Start-15\r\nCotton Candy-17\r\nCosmos-20\r\nChili pepper-30\r\nBroccoli-26\r\nBlossom-86\r\nBe Happy-35\r\nB. Special-3\r\nAuria Branche-4\r\n White  FlowerPot-9\r\n Pink  FlowerPots-2\r\n Black FlowerPot-4\r\nTotal orders:671','monthly'),('2','2017','Performance','EAST',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 222 orders.\r\n12 arrived late.\r\npercentage of the late orders is : 5.4%.\r\n3 customers complained .\r\npercentage of the complained customers:1.35%.\r\n3 orders were canceled.\r\npercentage of the canceled orders:1.35%.\r\n','monthly'),('3','2012','Income','EAST',_binary 'this is income report of the branch.\r\nOrders income 100000\r\nElectrical expenses: 5000.\r\nwater expenses: 5000.\r\nbranch rent expense:10000.\r\nnet income : 89000.','monthly'),('3','2017','Income','NORTH',_binary 'this is income report of the branch.\r\nOrders income 50000\r\nElectrical expenses: 1000\r\nwater expenses: 2000\r\nbranch rent expense:10000.\r\nnet income : 37000','monthly'),('3','2017','Orders','NORTH',_binary 'This is a report about the orders of the branch :\r\nnext to each item written number of times it was ordered. \r\nYellow Submarine-50\r\nYellow Rose-6\r\nWhite Rose-3\r\nWhite Lisianthus-0\r\nWhite Lily -5\r\nVogue-6\r\nViloet-1\r\nTomatos-1\r\nSunrise-3\r\nSunflower-3\r\nSimply Red-3\r\nRed Rose-2\r\nPurple -7\r\nPure & Simple-2\r\nPure-4\r\nPink Rose-1\r\nPink Lilies-0\r\nOrchid-0\r\nOrange Rose-6\r\nMom’s Love-10\r\nLoved-32\r\nLove Is in the Air-11\r\nLife style-7\r\nHer Side-2\r\nGuzmania-1\r\nGreen Branches-1\r\nFull Moon-1\r\nFresh Start-1\r\nCotton Candy-7\r\nCosmos-8\r\nChili pepper-10\r\nBroccoli-22\r\nBlossom-36\r\nBe Happy-33\r\nB. Special-33\r\nAuria Branche-74\r\n White  FlowerPot-9\r\n Pink  FlowerPots-22\r\n Black FlowerPot-44\r\nTotal orders:434','monthly'),('3','2021','Performance','WEST',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 200orders.\r\n12 arrived late.\r\npercentage of the late orders is :6%.\r\n10 customers complained .\r\npercentage of the complained customers:5%.\r\n3 orders were canceled.\r\npercentage of the canceled orders:1.5%.\r\n','monthly'),('4','2020','Performance','NORTH',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 65 orders.\r\n2 arrived late.\r\npercentage of the late orders is : 3.07%.\r\n0 customers complained .\r\npercentage of the complained customers:0%.\r\n0 orders were canceled.\r\npercentage of the canceled orders:0%.\r\n','monthly'),('5','2020','Performance','WEST',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 44 orders.\r\n1 arrived late.\r\npercentage of the late orders is : 2.2%.\r\n13 customers complained .\r\npercentage of the complained customers:29.5%.\r\n3 orders were canceled.\r\npercentage of the canceled orders:6.8%.\r\n','monthly'),('9','2020','Performance','SOUTH',_binary 'This is a report about the performance of the branch.\r\nat this month:\r\nwe had 150 orders.\r\n 30 arrived late.\r\npercentage of the late orders is : 20%.\r\n13 customers complained .\r\npercentage of the complained customers: 8.6%.\r\n3 orders were canceled.\r\npercentage of the canceled orders:2%.\r\n','monthly');
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `BranchName` varchar(50) NOT NULL,
  `Sales` varchar(45) DEFAULT NULL,
  `Activation` enum('ACTIVE','FROZEN') DEFAULT NULL,
  PRIMARY KEY (`BranchName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES ('EAST','20%','ACTIVE'),('NORTH','20%','ACTIVE'),('SOUTH','20%','FROZEN'),('WEST','20%','ACTIVE');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `SurveyId` int NOT NULL AUTO_INCREMENT,
  `Q1` varchar(100) DEFAULT NULL,
  `Q2` varchar(100) DEFAULT NULL,
  `Q3` varchar(100) DEFAULT NULL,
  `Q4` varchar(100) DEFAULT NULL,
  `Q5` varchar(100) DEFAULT NULL,
  `Q6` varchar(100) DEFAULT NULL,
  `StartDate` varchar(45) DEFAULT NULL,
  `EndDate` varchar(45) DEFAULT NULL,
  `Branches` varchar(45) DEFAULT NULL,
  `ProductID` varchar(45) DEFAULT NULL,
  `DurationS` varchar(45) DEFAULT NULL,
  `DurationE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SurveyId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'ghj','bnm','bnm','yuio','yujk','hjk','2022-06-07','2022-06-09','[North, East]','678','2022-06-14','2022-06-09'),(2,'ghj','ghj','hnm','hjk','jkl','jk','2022-06-10','2022-06-16','[West]','','2022-06-14','2022-06-09'),(3,'jk','bn','cvbn','vbn','nm','cvbn','2022-06-10','2022-06-16','[West]','987','2022-06-14','2022-06-09'),(4,'hjkl','bnm','ghjk','hjk','nm,','hjk','2022-06-20','2022-06-21','[North, South, East, West]','','2022-06-16','2022-06-10');
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_answer`
--

DROP TABLE IF EXISTS `survey_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_answer` (
  `SurveyID` int DEFAULT NULL,
  `Q1` varchar(45) DEFAULT NULL,
  `Q2` varchar(45) DEFAULT NULL,
  `Q3` varchar(45) DEFAULT NULL,
  `Q4` varchar(45) DEFAULT NULL,
  `Q5` varchar(45) DEFAULT NULL,
  `Q6` varchar(45) DEFAULT NULL,
  `Branch` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_answer`
--

LOCK TABLES `survey_answer` WRITE;
/*!40000 ALTER TABLE `survey_answer` DISABLE KEYS */;
INSERT INTO `survey_answer` VALUES (NULL,'0','0','0','0','0','0','North'),(NULL,'1','1','1','0','0','0','North'),(NULL,'0','0','0','0','0','0','North'),(NULL,'0','0','0','0','0','0','North'),(NULL,'1','5','10','10','10','3','North'),(NULL,'1','9','1','8','1','7','North'),(NULL,'9','9','10','2','10','2','North'),(NULL,'1','1','1','1','1','1','North'),(NULL,'1','1','1','1','1','1','North'),(4,'9','2','10','10','10','2','North'),(4,'10','1','1','1','1','1','North'),(1,'2','2','2','10','10','5','North');
/*!40000 ALTER TABLE `survey_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surveyexpert`
--

DROP TABLE IF EXISTS `surveyexpert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surveyexpert` (
  `UserID` int NOT NULL,
  `FirstName` varchar(70) DEFAULT NULL,
  `LastName` varchar(70) DEFAULT NULL,
  `StatusInSystem` varchar(70) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  `PhoneNumber` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surveyexpert`
--

LOCK TABLES `surveyexpert` WRITE;
/*!40000 ALTER TABLE `surveyexpert` DISABLE KEYS */;
INSERT INTO `surveyexpert` VALUES (30,'Bob','Dikson','PENDING_REGISTRATION','Bob.Dikson@gmail.com','051-3214756'),(31,'Natalie','Saren','FROZEN','Natalie.Saren@gmail.com','056-7745321'),(80,'Alexander','Micheal','CONFIRMED','Alexander.Micheal@gmail.com','050-3125891');
/*!40000 ALTER TABLE `surveyexpert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surveyreports`
--

DROP TABLE IF EXISTS `surveyreports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surveyreports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `surveyPDF` longblob,
  `datee` varchar(50) DEFAULT NULL,
  `fileName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surveyreports`
--

LOCK TABLES `surveyreports` WRITE;
/*!40000 ALTER TABLE `surveyreports` DISABLE KEYS */;
/*!40000 ALTER TABLE `surveyreports` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10  8:22:11
