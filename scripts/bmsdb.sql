CREATE DATABASE  IF NOT EXISTS `bmsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bmsdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bmsdb
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Table structure for table `Amenity`
--

DROP TABLE IF EXISTS `Amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Amenity` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Common, 1 = Aditional',
  `ProviderTypeId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Amenity_Name_Type_ProviderTypeId` (`Name`,`Type`,`ProviderTypeId`),
  KEY `FK_Amenity_ProviderTypeId_idx` (`ProviderTypeId`),
  KEY `FK_Amenity_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Amenity_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Amenity_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Amenity_ProviderTypeId` FOREIGN KEY (`ProviderTypeId`) REFERENCES `ProviderType` (`Id`),
  CONSTRAINT `FK_Amenity_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AmenityCharge`
--

DROP TABLE IF EXISTS `AmenityCharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AmenityCharge` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `AmenityId` bigint(20) NOT NULL,
  `ProviderId` bigint(20) NOT NULL,
  `Charge` double(18,2) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_AmenityCharge_AmenityId_ProviderId` (`AmenityId`,`ProviderId`),
  KEY `FK_AmenityCharge_AmenityId_idx` (`AmenityId`),
  KEY `FK_AmenityCharge_ProviderId_idx` (`ProviderId`),
  KEY `FK_AmenityCharge_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_AmenityCharge_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_AmenityCharge_AmenityId` FOREIGN KEY (`AmenityId`) REFERENCES `Amenity` (`Id`),
  CONSTRAINT `FK_AmenityCharge_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_AmenityCharge_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_AmenityCharge_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AuditLog`
--

DROP TABLE IF EXISTS `AuditLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuditLog` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EventType` tinyint(4) NOT NULL,
  `EventDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UserId` bigint(20) NOT NULL,
  `IpAddress` varchar(20) NOT NULL,
  `EventDesc` varchar(250) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_AuditLog_UserId_idx` (`UserId`),
  CONSTRAINT `FK_AuditLog_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BookingStatus`
--

DROP TABLE IF EXISTS `BookingStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookingStatus` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Remarks` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_BookingStatus_Name` (`Name`),
  KEY `FK_BookingStatus_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_BookingStatus_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_BookingStatus_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_BookingStatus_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BookingType`
--

DROP TABLE IF EXISTS `BookingType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookingType` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_BookingType_Name` (`Name`),
  KEY `FK_BookingType_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_BookingType_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_BookingType_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_BookingType_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `StateId` bigint(20) NOT NULL,
  `Remarks` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_City_Name_StateId` (`StateId`,`Name`),
  KEY `FK_City_StateId_idx` (`StateId`),
  KEY `FK_City_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_City_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_City_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_City_StateId` FOREIGN KEY (`StateId`) REFERENCES `State` (`Id`),
  CONSTRAINT `FK_City_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Country`
--

DROP TABLE IF EXISTS `Country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Country` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Remarks` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Country_Name` (`Name`),
  KEY `FK_Country_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Country_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Country_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Country_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EmailAddress`
--

DROP TABLE IF EXISTS `EmailAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmailAddress` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Email` varchar(127) NOT NULL,
  `IsVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Non Verified, 1 = Verified',
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = Not Exist, 1 = Exist',
  `IsPrimary` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Secodary, 1 = Primary',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_EmailAddress_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_EmailAddress_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_EmailAddress_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_EmailAddress_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Group`
--

DROP TABLE IF EXISTS `Group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Group` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Group_Name` (`Name`),
  KEY `FK_Group_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Group_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Group_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Group_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `GroupPolicy`
--

DROP TABLE IF EXISTS `GroupPolicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupPolicy` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `GroupId` bigint(20) NOT NULL,
  `PolicyId` bigint(20) NOT NULL,
  `Level` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = View, 3 = Modify, 6 = Create, 9 = Remove',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_GroupPolicy_GroupID_PolicyID` (`GroupId`,`PolicyId`),
  KEY `FK_GroupPolicy_GroupID_idx` (`GroupId`),
  KEY `FK_GroupPolicy_PolicyID_idx` (`PolicyId`),
  KEY `FK_GroupPolicy_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_GroupPolicy_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_GroupPolicy_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_GroupPolicy_GroupID` FOREIGN KEY (`GroupId`) REFERENCES `group` (`ID`),
  CONSTRAINT `FK_GroupPolicy_PolicyID` FOREIGN KEY (`PolicyId`) REFERENCES `policy` (`ID`),
  CONSTRAINT `FK_GroupPolicy_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `StarRating` tinyint(1) NOT NULL,
  `NumberOfFloor` int(4) NOT NULL,
  `Latitude` double(8,6) NOT NULL,
  `Longitude` double(8,6) NOT NULL,
  `CheckInTime` time NOT NULL,
  `CheckOutTime` time NOT NULL,
  `Website` varchar(247) DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Hotel_ProviderId` (`ProviderId`),
  KEY `FK_Hotel_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Hotel_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Hotel_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Hotel_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_Hotel_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Image`
--

DROP TABLE IF EXISTS `Image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Image` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Url` varchar(127) NOT NULL,
  `Type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Other, 1 = Profile, 2 = Cover',
  `IsDefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Inactive, 1 = Active',
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = Not Exist, 1 = Exist',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_Image_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Image_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Image_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Image_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemCategoryId` bigint(20) NOT NULL,
  `ItemNo` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Rent` double(18,2) NOT NULL DEFAULT '0.00',
  `Status` int(1) NOT NULL DEFAULT '1' COMMENT '0 = Inactive, 1 = Active',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Item_ItemNo` (`ItemNo`),
  KEY `FK_Item_ItemCategoryId_idx` (`ItemCategoryId`),
  KEY `FK_Item_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Item_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Item_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Item_ItemCategoryId` FOREIGN KEY (`ItemCategoryId`) REFERENCES `itemcategory` (`Id`),
  CONSTRAINT `FK_Item_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemAmenity`
--

DROP TABLE IF EXISTS `ItemAmenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemAmenity` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemId` bigint(20) NOT NULL,
  `AmenityId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ItemAmenity_ItemId_AmenityId` (`ItemId`,`AmenityId`),
  KEY `FK_ItemAmenity_ItemId_idx` (`ItemId`),
  KEY `FK_ItemAmenity_AmenityId_idx` (`AmenityId`),
  KEY `FK_ItemAmenity_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemAmenity_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemAmenity_AmenityId` FOREIGN KEY (`AmenityId`) REFERENCES `Amenity` (`Id`),
  CONSTRAINT `FK_ItemAmenity_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemAmenity_ItemId` FOREIGN KEY (`ItemId`) REFERENCES `Item` (`Id`),
  CONSTRAINT `FK_ItemAmenity_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemBookigDetailAmenity`
--

DROP TABLE IF EXISTS `ItemBookigDetailAmenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemBookigDetailAmenity` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `AmenityId` bigint(20) NOT NULL,
  `ItemBookigDetailId` bigint(20) NOT NULL,
  `Charge` double(18,2) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ItemBookigDetailAmenity_AmenityId_idx` (`AmenityId`),
  KEY `FK_ItemBookigDetailAmenity_ItemBookigDetailId_idx` (`ItemBookigDetailId`),
  KEY `FK_ItemBookigDetailAmenity_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemBookigDetailAmenity_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemBookigDetailAmenity_AmenityId` FOREIGN KEY (`AmenityId`) REFERENCES `Amenity` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ItemBookigDetailAmenity_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemBookigDetailAmenity_ItemBookigDetailId` FOREIGN KEY (`ItemBookigDetailId`) REFERENCES `ItemBookingDetail` (`Id`),
  CONSTRAINT `FK_ItemBookigDetailAmenity_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemBooking`
--

DROP TABLE IF EXISTS `ItemBooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemBooking` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `InvoiceNo` varchar(45) NOT NULL,
  `TransactionId` varchar(45) NOT NULL,
  `BookingTypeId` bigint(20) NOT NULL,
  `BookingStatusId` bigint(20) NOT NULL,
  `TotalAdult` int(4) NOT NULL,
  `TotalChild` int(4) NOT NULL,
  `BookingDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CheckInDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CheckOutDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CustomerId` bigint(20) NOT NULL,
  `BookedBy` bigint(20) NOT NULL,
  `SecurityNumber` varchar(45) DEFAULT NULL,
  `PassportNumber` varchar(45) DEFAULT NULL,
  `DrivingLicenceNumber` varchar(45) DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ItemBooking_CustomerId_idx` (`CustomerId`),
  KEY `FK_ItemBooking_BookedBy_idx` (`BookedBy`),
  KEY `FK_ItemBooking_ProviderId_idx` (`ProviderId`),
  KEY `FK_ItemBooking_BookingTypeId_idx` (`BookingTypeId`),
  KEY `FK_ItemBooking_BookingStatusId_idx` (`BookingStatusId`),
  KEY `FK_ItemBooking_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemBooking_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemBooking_BookedBy` FOREIGN KEY (`BookedBy`) REFERENCES `User` (`ID`),
  CONSTRAINT `FK_ItemBooking_BookingStatusId` FOREIGN KEY (`BookingStatusId`) REFERENCES `BookingStatus` (`Id`),
  CONSTRAINT `FK_ItemBooking_BookingTypeId` FOREIGN KEY (`BookingTypeId`) REFERENCES `BookingType` (`Id`),
  CONSTRAINT `FK_ItemBooking_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemBooking_CustomerId` FOREIGN KEY (`CustomerId`) REFERENCES `User` (`ID`),
  CONSTRAINT `FK_ItemBooking_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_ItemBooking_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemBookingDetail`
--

DROP TABLE IF EXISTS `ItemBookingDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemBookingDetail` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemBookingId` bigint(20) NOT NULL,
  `ItemId` bigint(20) NOT NULL,
  `Rent` double(18,2) NOT NULL,
  `ServiceChange` double(18,2) NOT NULL DEFAULT '0.00',
  `Discount` double(18,2) NOT NULL DEFAULT '0.00',
  `AssignedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `IsCancelled` tinyint(1) NOT NULL DEFAULT '0',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ItemBookingDetail_ItemBookingId_ItemId_AssignedDate` (`ItemBookingId`,`ItemId`,`AssignedDate`),
  KEY `FK_ItemBookingDetail_ItemBookingId_idx` (`ItemBookingId`),
  KEY `FK_ItemBookingDetail_ItemId_idx` (`ItemId`),
  KEY `FK_ItemBookingDetail_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemBookingDetail_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemBookingDetail_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemBookingDetail_ItemBookingId` FOREIGN KEY (`ItemBookingId`) REFERENCES `ItemBooking` (`Id`),
  CONSTRAINT `FK_ItemBookingDetail_ItemId` FOREIGN KEY (`ItemId`) REFERENCES `Item` (`Id`),
  CONSTRAINT `FK_ItemBookingDetail_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemBookingPayment`
--

DROP TABLE IF EXISTS `ItemBookingPayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemBookingPayment` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemBookingId` bigint(20) NOT NULL,
  `PaymentTypeId` bigint(20) NOT NULL,
  `PaymentMethodId` bigint(20) NOT NULL,
  `PaymentDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `PaymentAmount` double(18,2) NOT NULL,
  `UserCardId` bigint(20) DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ItemBookingPayment_ItemBookingId_idx` (`ItemBookingId`),
  KEY `FK_ItemBookingPayment_PaymentMethodId_idx` (`PaymentMethodId`),
  KEY `FK_ItemBookingPayment_PaymentTypeId_idx` (`PaymentTypeId`),
  KEY `FK_ItemBookingPayment_UserCardId_idx` (`UserCardId`),
  KEY `FK_ItemBookingPayment_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemBookingPayment_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemBookingPayment_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemBookingPayment_ItemBookingId` FOREIGN KEY (`ItemBookingId`) REFERENCES `ItemBooking` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ItemBookingPayment_PaymentMethodId` FOREIGN KEY (`PaymentMethodId`) REFERENCES `PaymentMethod` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ItemBookingPayment_PaymentTypeId` FOREIGN KEY (`PaymentTypeId`) REFERENCES `PaymentType` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ItemBookingPayment_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemBookingPayment_UserCardId` FOREIGN KEY (`UserCardId`) REFERENCES `UserCard` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemCategory`
--

DROP TABLE IF EXISTS `ItemCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemCategory` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `ItemTypeId` bigint(20) NOT NULL,
  `Rent` double(18,2) NOT NULL,
  `Remarks` varchar(127) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ItemCategory_Name_ItemTypeId` (`ItemTypeId`,`Name`),
  KEY `FK_ItemCategory_ItemTypeId_idx` (`ItemTypeId`),
  KEY `FK_ItemCategory_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemCategory_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemCategory_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemCategory_ItemTypeId` FOREIGN KEY (`ItemTypeId`) REFERENCES `ItemType` (`Id`),
  CONSTRAINT `FK_ItemCategory_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemCategoryAmenity`
--

DROP TABLE IF EXISTS `ItemCategoryAmenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemCategoryAmenity` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemCategoryId` bigint(20) NOT NULL,
  `AmenityId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ItemCategoryAmenity_ItemCategoryId_AmenityId` (`ItemCategoryId`,`AmenityId`),
  KEY `FK_ItemCategoryAmenity_ItemCategoryId_idx` (`ItemCategoryId`),
  KEY `FK_ItemCategoryAmenity_AmenityId_idx` (`AmenityId`),
  KEY `FK_ItemCategoryAmenity_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemCategoryAmenity_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemCategoryAmenity_AmenityId` FOREIGN KEY (`AmenityId`) REFERENCES `Amenity` (`Id`),
  CONSTRAINT `FK_ItemCategoryAmenity_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemCategoryAmenity_ItemCategoryId` FOREIGN KEY (`ItemCategoryId`) REFERENCES `ItemCategory` (`Id`),
  CONSTRAINT `FK_ItemCategoryAmenity_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemCategoryImage`
--

DROP TABLE IF EXISTS `ItemCategoryImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemCategoryImage` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ImageId` bigint(20) NOT NULL,
  `ItemCategoryId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ItemCategoryImage_ItemCategoryId_idx` (`ItemCategoryId`),
  KEY `FK_ItemCategoryImage_ImageId_idx` (`ImageId`),
  KEY `FK_ItemCategoryImage_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemCategoryImage_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemCategoryImage_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemCategoryImage_ImageId` FOREIGN KEY (`ImageId`) REFERENCES `Image` (`Id`),
  CONSTRAINT `FK_ItemCategoryImage_ItemCategoryId` FOREIGN KEY (`ItemCategoryId`) REFERENCES `ItemCategory` (`Id`),
  CONSTRAINT `FK_ItemCategoryImage_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemType`
--

DROP TABLE IF EXISTS `ItemType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemType` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `ProviderId` bigint(20) NOT NULL,
  `Remarks` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ItemType_Name_ProviderId` (`Name`,`ProviderId`),
  KEY `FK_ItemType_ProviderId_idx` (`ProviderId`),
  KEY `FK_ItemType_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ItemType_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ItemType_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ItemType_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_ItemType_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PaymentMethod`
--

DROP TABLE IF EXISTS `PaymentMethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentMethod` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Remarks` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_PaymentMethod_Name` (`Name`),
  KEY `FK_PaymentMethod_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_PaymentMethod_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_PaymentMethod_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_PaymentMethod_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PaymentType`
--

DROP TABLE IF EXISTS `PaymentType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentType` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_PaymentType_Name` (`Name`),
  KEY `FK_PaymentType_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_PaymentType_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_PaymentType_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_PaymentType_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PhoneNumber`
--

DROP TABLE IF EXISTS `PhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PhoneNumber` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Type` tinyint(1) NOT NULL COMMENT '0 = Mobile, 1 = Phone, 2 = Fax',
  `Number` varchar(20) NOT NULL,
  `Code` varchar(10) NOT NULL,
  `IsVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Non Verified, 1 = Verified',
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = Not Exist, 1 = Exist',
  `IsPrimary` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Secodary, 1 = Primary',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_PhoneNumber_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_PhoneNumber_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_PhoneNumber_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_PhoneNumber_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PointOfInterest`
--

DROP TABLE IF EXISTS `PointOfInterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PointOfInterest` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `ProviderTypeId` bigint(20) NOT NULL,
  `Remarks` varchar(300) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_PointOfInterest_Name_ProviderTypeId` (`Name`,`ProviderTypeId`),
  KEY `FK_PointOfInterest_ProviderTypeId_idx` (`ProviderTypeId`),
  KEY `FK_PointOfInterest_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_PointOfInterest_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_PointOfInterest_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_PointOfInterest_ProviderTypeId` FOREIGN KEY (`ProviderTypeId`) REFERENCES `ProviderType` (`Id`),
  CONSTRAINT `FK_PointOfInterest_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Policy`
--

DROP TABLE IF EXISTS `Policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Policy` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Code` varchar(45) NOT NULL,
  `ParentId` bigint(20) DEFAULT NULL,
  `Type` int(4) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Policy_Code` (`Code`),
  KEY `FK_Policy_ParentId_idx` (`ParentId`),
  KEY `FK_Policy_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Policy_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Policy_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Policy_ParentId` FOREIGN KEY (`ParentId`) REFERENCES `Policy` (`Id`),
  CONSTRAINT `FK_Policy_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PostalAddress`
--

DROP TABLE IF EXISTS `PostalAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PostalAddress` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Line1` varchar(150) DEFAULT NULL,
  `Line2` varchar(150) DEFAULT NULL,
  `CityId` bigint(20) DEFAULT NULL COMMENT 'City/Town/Village',
  `StateId` bigint(20) DEFAULT NULL COMMENT 'State/Province',
  `CountryId` bigint(20) DEFAULT NULL,
  `PostCode` varchar(10) DEFAULT NULL COMMENT 'Post/Zip-code',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_PostalAddress_CountryId_idx` (`CountryId`),
  KEY `FK_PostalAddress_CityId_idx` (`CityId`),
  KEY `FK_PostalAddress_StateId_idx` (`StateId`),
  KEY `FK_PostalAddress_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_PostalAddress_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_PostalAddress_CityId` FOREIGN KEY (`CityId`) REFERENCES `City` (`Id`),
  CONSTRAINT `FK_PostalAddress_CountryId` FOREIGN KEY (`CountryId`) REFERENCES `Country` (`Id`),
  CONSTRAINT `FK_PostalAddress_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_PostalAddress_StateId` FOREIGN KEY (`StateId`) REFERENCES `State` (`Id`),
  CONSTRAINT `FK_PostalAddress_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Provider`
--

DROP TABLE IF EXISTS `Provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Provider` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) CHARACTER SET latin1 NOT NULL,
  `ProviderTypeId` bigint(20) NOT NULL,
  `Specification` varchar(250) DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_Provider_ProviderTypeId_idx` (`ProviderTypeId`),
  KEY `FK_Provider_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Provider_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Provider_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Provider_ProviderTypeId` FOREIGN KEY (`ProviderTypeId`) REFERENCES `providertype` (`Id`),
  CONSTRAINT `FK_Provider_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderAdmin`
--

DROP TABLE IF EXISTS `ProviderAdmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderAdmin` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `UserId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ProviderAdmin_ProviderId_UserId` (`ProviderId`,`UserId`),
  KEY `FK_ProviderAdmin_ProviderId_idx` (`ProviderId`),
  KEY `FK_ProviderAdmin_UserId_idx` (`UserId`),
  KEY `FK_ProviderAdmin_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderAdmin_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderAdmin_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderAdmin_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_ProviderAdmin_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderAdmin_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderEmailAddress`
--

DROP TABLE IF EXISTS `ProviderEmailAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderEmailAddress` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `EmailAddressId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ProviderEmailAddress_ProviderId_idx` (`ProviderId`),
  KEY `FK_ProviderEmailAddress_EmailAddressId_idx` (`EmailAddressId`),
  KEY `FK_ProviderEmailAddress_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderEmailAddress_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderEmailAddress_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderEmailAddress_EmailAddressId` FOREIGN KEY (`EmailAddressId`) REFERENCES `EmailAddress` (`Id`),
  CONSTRAINT `FK_ProviderEmailAddress_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `provider` (`Id`),
  CONSTRAINT `FK_ProviderEmailAddress_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderImage`
--

DROP TABLE IF EXISTS `ProviderImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderImage` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ImageId` bigint(20) NOT NULL,
  `ProviderId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ProviderImage_ProviderId_idx` (`ProviderId`),
  KEY `FK_ProviderImage_ImageId_idx` (`ImageId`),
  KEY `FK_ProviderImage_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderImage_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderImage_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderImage_ImageId` FOREIGN KEY (`ImageId`) REFERENCES `Image` (`Id`),
  CONSTRAINT `FK_ProviderImage_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_ProviderImage_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderPhoneNumber`
--

DROP TABLE IF EXISTS `ProviderPhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderPhoneNumber` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `PhoneNumberId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ProviderPhoneNumber_ProviderId_idx` (`ProviderId`),
  KEY `FK_ProviderPhoneNumber_PhoneNumberId_idx` (`PhoneNumberId`),
  KEY `FK_ProviderPhoneNumber_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderPhoneNumber_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderPhoneNumber_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderPhoneNumber_PhoneNumberId` FOREIGN KEY (`PhoneNumberId`) REFERENCES `PhoneNumber` (`Id`),
  CONSTRAINT `FK_ProviderPhoneNumber_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `provider` (`Id`),
  CONSTRAINT `FK_ProviderPhoneNumber_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderPointOfInterest`
--

DROP TABLE IF EXISTS `ProviderPointOfInterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderPointOfInterest` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `PointOfInterestId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ProviderPointOfInterest_ProviderId_idx` (`ProviderId`),
  KEY `FK_ProviderPointOfInterest_PointOfInterestId_idx` (`PointOfInterestId`),
  KEY `FK_ProviderPointOfInterest_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderPointOfInterest_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderPointOfInterest_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderPointOfInterest_PointOfInterestId` FOREIGN KEY (`PointOfInterestId`) REFERENCES `PointOfInterest` (`Id`),
  CONSTRAINT `FK_ProviderPointOfInterest_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `Provider` (`Id`),
  CONSTRAINT `FK_ProviderPointOfInterest_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderPostalAddress`
--

DROP TABLE IF EXISTS `ProviderPostalAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderPostalAddress` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProviderId` bigint(20) NOT NULL,
  `PostalAddressId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_ProviderPostalAddress_ProviderId_idx` (`ProviderId`),
  KEY `FK_ProviderPostalAddress_PostalAddressId_idx` (`PostalAddressId`),
  KEY `FK_ProviderPostalAddress_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderPostalAddress_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderPostalAddress_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderPostalAddress_PostalAddressId` FOREIGN KEY (`PostalAddressId`) REFERENCES `PostalAddress` (`Id`),
  CONSTRAINT `FK_ProviderPostalAddress_ProviderId` FOREIGN KEY (`ProviderId`) REFERENCES `provider` (`Id`),
  CONSTRAINT `FK_ProviderPostalAddress_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ProviderType`
--

DROP TABLE IF EXISTS `ProviderType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProviderType` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Remarks` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_ProviderType_Name` (`Name`),
  KEY `FK_ProviderType_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_ProviderType_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_ProviderType_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_ProviderType_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Priority` int(4) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Role_Name` (`Name`),
  KEY `FK_Role_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Role_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Role_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Role_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RolePolicy`
--

DROP TABLE IF EXISTS `RolePolicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RolePolicy` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleId` bigint(20) NOT NULL,
  `PolicyId` bigint(20) NOT NULL,
  `Level` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = View, 3 = Modify, 6 = Create, 9 = Remove',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_RolePolicy_RoleID_PolicyID` (`RoleId`,`PolicyId`),
  KEY `FK_RolePolicy_RoleID_idx` (`RoleId`),
  KEY `FK_RolePolicy_PolicyID_idx` (`PolicyId`),
  KEY `FK_RolePolicy_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_RolePolicy_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_RolePolicy_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_RolePolicy_PolicyID` FOREIGN KEY (`PolicyId`) REFERENCES `policy` (`ID`),
  CONSTRAINT `FK_RolePolicy_RoleID` FOREIGN KEY (`RoleId`) REFERENCES `role` (`ID`),
  CONSTRAINT `FK_RolePolicy_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ServiceItemId` bigint(20) NOT NULL,
  `FloorNo` int(4) NOT NULL,
  `Size` int(4) DEFAULT NULL,
  `LandLine` varchar(20) DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_Room_ItemId` (`ServiceItemId`),
  KEY `FK_Room_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_Room_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_Room_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_Room_ItemId` FOREIGN KEY (`ServiceItemId`) REFERENCES `item` (`Id`),
  CONSTRAINT `FK_Room_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RoomCategory`
--

DROP TABLE IF EXISTS `RoomCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RoomCategory` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemCategoryId` bigint(20) NOT NULL,
  `Size` int(4) DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_RoomCategory_ItemCategoryId` (`ItemCategoryId`),
  KEY `FK_RoomCategory_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_RoomCategory_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_RoomCategory_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_RoomCategory_ItemCategoryId` FOREIGN KEY (`ItemCategoryId`) REFERENCES `itemcategory` (`Id`),
  CONSTRAINT `FK_RoomCategory_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `State`
--

DROP TABLE IF EXISTS `State`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `State` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `CountryId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_State_Name_CountryId` (`CountryId`,`Name`),
  KEY `FK_State_CountryId_idx` (`CountryId`),
  KEY `FK_State_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_State_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_State_CountryId` FOREIGN KEY (`CountryId`) REFERENCES `Country` (`Id`),
  CONSTRAINT `FK_State_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_State_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `StaticPage`
--

DROP TABLE IF EXISTS `StaticPage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StaticPage` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Description` mediumtext NOT NULL,
  `PageLinkTitle` varchar(45) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_StaticPage_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_StaticPage_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_StaticPage_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_StaticPage_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Username` varchar(127) NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Inactive, 1 = Active',
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = Not Exist, 1 = Exist',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) DEFAULT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) DEFAULT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_User_Username` (`Username`),
  KEY `FK_User_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_User_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_User_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `User` (`Id`),
  CONSTRAINT `FK_User_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserCard`
--

DROP TABLE IF EXISTS `UserCard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserCard` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `CardNumber` varchar(45) CHARACTER SET latin1 NOT NULL,
  `HolderName` varchar(127) CHARACTER SET latin1 NOT NULL,
  `CvvNumber` varchar(45) CHARACTER SET latin1 NOT NULL,
  `ExpireDate` date NOT NULL,
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = Not Exist, 1 = Exist',
  `IsDefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Not Default, 1 = Default',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_UserCard_UserId_idx` (`UserId`),
  KEY `FK_UserCard_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserCard_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserCard_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserCard` (`Id`),
  CONSTRAINT `FK_UserCard_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserCard` (`Id`),
  CONSTRAINT `FK_UserCard_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserDevice`
--

DROP TABLE IF EXISTS `UserDevice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserDevice` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Token` varchar(255) NOT NULL,
  `ImeiNumber` varchar(45) DEFAULT NULL,
  `Platform` tinyint(1) NOT NULL COMMENT '1 = Android, 2 = iOS, 3 = Web, 4 = Desktop',
  `FirstUsedTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LastUsedTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_UserDevice_UserId_Token_Platform` (`UserId`,`Token`,`Platform`),
  KEY `FK_UserDevice_UserId_idx` (`UserId`),
  KEY `FK_UserDevice_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserDevice_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserDevice_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserDevice` (`Id`),
  CONSTRAINT `FK_UserDevice_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserDevice` (`Id`),
  CONSTRAINT `FK_UserDevice_UserID` FOREIGN KEY (`UserId`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserEmailAddress`
--

DROP TABLE IF EXISTS `UserEmailAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserEmailAddress` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `EmailAddressId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_UserEmailAddress_UserId_idx` (`UserId`),
  KEY `FK_UserEmailAddress_EmailAddressId_idx` (`EmailAddressId`),
  KEY `FK_UserEmailAddress_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserEmailAddress_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserEmailAddress_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserEmailAddress` (`Id`),
  CONSTRAINT `FK_UserEmailAddress_EmailAddressId` FOREIGN KEY (`EmailAddressId`) REFERENCES `EmailAddress` (`Id`),
  CONSTRAINT `FK_UserEmailAddress_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserEmailAddress` (`Id`),
  CONSTRAINT `FK_UserEmailAddress_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserGroup`
--

DROP TABLE IF EXISTS `UserGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserGroup` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `GroupId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_UserGroup_UserID_GroupID` (`UserId`,`GroupId`),
  KEY `FK_UserGroup_UserId` (`UserId`),
  KEY `FK_UserGroup_GroupId_idx` (`GroupId`),
  KEY `FK_UserGroup_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserGroup_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserGroup_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserGroup` (`Id`),
  CONSTRAINT `FK_UserGroup_GroupID` FOREIGN KEY (`GroupId`) REFERENCES `group` (`ID`),
  CONSTRAINT `FK_UserGroup_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserGroup` (`Id`),
  CONSTRAINT `FK_UserGroup_UserID` FOREIGN KEY (`UserId`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserImage`
--

DROP TABLE IF EXISTS `UserImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserImage` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ImageId` bigint(20) NOT NULL,
  `UserId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_UserImage_UserId_idx` (`UserId`),
  KEY `FK_UserImage_ImageId_idx` (`ImageId`),
  KEY `FK_UserImage_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserImage_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserImage_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserImage` (`Id`),
  CONSTRAINT `FK_UserImage_ImageId` FOREIGN KEY (`ImageId`) REFERENCES `Image` (`Id`),
  CONSTRAINT `FK_UserImage_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserImage` (`Id`),
  CONSTRAINT `FK_UserImage_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserPhoneNumber`
--

DROP TABLE IF EXISTS `UserPhoneNumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPhoneNumber` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `PhoneNumberId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_UserPhoneNumber_UserId_idx` (`UserId`),
  KEY `FK_UserPhoneNumber_PhoneNumberId_idx` (`PhoneNumberId`),
  KEY `FK_UserPhoneNumber_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserPhoneNumber_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserPhoneNumber_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserPhoneNumber` (`Id`),
  CONSTRAINT `FK_UserPhoneNumber_PhoneNumberId` FOREIGN KEY (`PhoneNumberId`) REFERENCES `PhoneNumber` (`Id`),
  CONSTRAINT `FK_UserPhoneNumber_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserPhoneNumber` (`Id`),
  CONSTRAINT `FK_UserPhoneNumber_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserPolicy`
--

DROP TABLE IF EXISTS `UserPolicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPolicy` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `PolicyId` bigint(20) NOT NULL,
  `Level` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = View, 3 = Modify, 6 = Create, 9 = Remove',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_UserPolicy_UserID_PolicyID` (`UserId`,`PolicyId`),
  KEY `FK_UserPolicy_UserId_idx` (`UserId`),
  KEY `FK_UserPolicy_PolicyId_idx` (`PolicyId`),
  KEY `FK_UserPolicy_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserPolicy_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserPolicy_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserPolicy` (`Id`),
  CONSTRAINT `FK_UserPolicy_PolicyID` FOREIGN KEY (`PolicyId`) REFERENCES `policy` (`ID`),
  CONSTRAINT `FK_UserPolicy_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserPolicy` (`Id`),
  CONSTRAINT `FK_UserPolicy_UserID` FOREIGN KEY (`UserId`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserPostalAddress`
--

DROP TABLE IF EXISTS `UserPostalAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPostalAddress` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `PostalAddressId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  KEY `FK_UserPostalAddress_UserId_idx` (`UserId`),
  KEY `FK_UserPostalAddress_PostalAddressId_idx` (`PostalAddressId`),
  KEY `FK_UserPostalAddress_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserPostalAddress_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserPostalAddress_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserPostalAddress` (`Id`),
  CONSTRAINT `FK_UserPostalAddress_PostalAddressId` FOREIGN KEY (`PostalAddressId`) REFERENCES `PostalAddress` (`Id`),
  CONSTRAINT `FK_UserPostalAddress_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserPostalAddress` (`Id`),
  CONSTRAINT `FK_UserPostalAddress_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserProfile`
--

DROP TABLE IF EXISTS `UserProfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserProfile` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `FirstName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `LastName` varchar(45) CHARACTER SET latin1 NOT NULL,
  `BirthDay` timestamp NULL DEFAULT NULL,
  `Gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 = Male, 2 = Female',
  `SecurityNumber` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `PassportNumber` varchar(45) DEFAULT NULL,
  `DrivingLicenceNumber` varchar(45) DEFAULT NULL,
  `Caption` varchar(300) CHARACTER SET latin1 DEFAULT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_UserProfile_UserId` (`UserId`),
  KEY `FK_UserProfile_UserId_idx` (`UserId`),
  KEY `FK_UserProfile_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserProfile_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserProfile_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserProfile` (`Id`),
  CONSTRAINT `FK_UserProfile_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserProfile` (`Id`),
  CONSTRAINT `FK_UserProfile_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserRole`
--

DROP TABLE IF EXISTS `UserRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRole` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `RoleId` bigint(20) NOT NULL,
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_UserRole_UserId_RoleId` (`UserId`,`RoleId`),
  KEY `FK_UserRole_UserId_idx` (`UserId`),
  KEY `FK_UserRole_RoleId_idx` (`RoleId`),
  KEY `FK_UserRole_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserRole_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserRole_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserRole` (`Id`),
  CONSTRAINT `FK_UserRole_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`ID`),
  CONSTRAINT `FK_UserRole_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserRole` (`Id`),
  CONSTRAINT `FK_UserRole_UserId` FOREIGN KEY (`UserId`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserSocialAccount`
--

DROP TABLE IF EXISTS `UserSocialAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserSocialAccount` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `Type` tinyint(1) NOT NULL COMMENT '1 = Facebook, 2 = Twitter, 3 = Google Plus',
  `AccountId` varchar(200) NOT NULL,
  `IsVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = Non Verified, 1 = Verified',
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = Not Exist, 1 = Exist',
  `Remarks` varchar(150) DEFAULT NULL,
  `CreatedBy` bigint(20) NOT NULL,
  `CreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UpdatedBy` bigint(20) NOT NULL,
  `UpdatedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_UserSocialAccount_Type_AccountId` (`Type`,`AccountId`),
  KEY `FK_UserSocialAccount_UserId_idx` (`UserId`),
  KEY `FK_UserSocialAccount_CreatedBy_idx` (`CreatedBy`),
  KEY `FK_UserSocialAccount_UpdatedBy_idx` (`UpdatedBy`),
  CONSTRAINT `FK_UserSocialAccount_CreatedBy` FOREIGN KEY (`CreatedBy`) REFERENCES `UserSocialAccount` (`Id`),
  CONSTRAINT `FK_UserSocialAccount_UpdatedBy` FOREIGN KEY (`UpdatedBy`) REFERENCES `UserSocialAccount` (`Id`),
  CONSTRAINT `FK_UserSocialAccount_UserId` FOREIGN KEY (`UserId`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'bmsdb'
--

--
-- Dumping routines for database 'bmsdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-15  9:27:53
