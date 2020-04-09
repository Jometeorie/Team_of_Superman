/*
REQUIREMENT: MySQL
项目运行前先执行下sql代码，在命令行中cd到该脚本所在的目录，接着进入mysql命令行，输入'source create_db.sql;'
要创建新表最好按照下面的格式补充，注意大小写以及反引号，减少不必要的问题，最好能保证第三范式
*/

/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.40 : Database - case1
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`teamwork` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `teamwork`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `ADMIN_ID` varchar(11) NOT NULL,
  `ADMIN_NAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `STATE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`ADMIN_ID`,`ADMIN_NAME`,`PASSWORD`,`STATE`) values ('17130166001','Bob','152152',0),('17130166002','wayne ','123456',0),('17130166003','Lacy','1255',0),('17130166004','Nacy','123456',0);

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `BOOK_ID` varchar(50) NOT NULL,
  `BOOK_NAME` varchar(32) NOT NULL,
  `AUTHOR` varchar(32) NOT NULL,
  `LOCATION` varchar(45) NOT NULL,
  `PRICE` decimal(5,2) NOT NULL,
  `CATEGORY` varchar(32) DEFAULT NULL,
  `STATE` tinyint(1) NOT NULL DEFAULT '0',
  `INFO` varchar(45) DEFAULT 'NULL',
  PRIMARY KEY (`BOOK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`BOOK_ID`,`BOOK_NAME`,`AUTHOR`,`LOCATION`,`PRICE`,`CATEGORY`,`STATE`,`INFO`) values ('00e8682064fa4d1f92f622fc9c5de278','The Catcher in the Rye','J.D.SALINGER','floor2-202-A','20.00','Literature',0,'NULL'),('06a139dc758c42e98de260fa5ed0916f','TO KILL A MOCKINGBIRD','Nelle Harper Lee','floor4-434-C','26.00','Literature',0,'NULL');

/*Table structure for table `book_deleted` */

DROP TABLE IF EXISTS `book_deleted`;

CREATE TABLE `book_deleted` (
  `BOOK_ID` varchar(50) NOT NULL,
  `BOOK_NAME` varchar(32) NOT NULL,
  `LIBR_ID` varchar(11) NOT NULL,
  PRIMARY KEY (`BOOK_ID`),
  KEY `LIBR_ID` (`LIBR_ID`),
  CONSTRAINT `book_deleted_ibfk_1` FOREIGN KEY (`LIBR_ID`) REFERENCES `librarian` (`LIBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book_deleted` */

insert  into `book_deleted`(`BOOK_ID`,`BOOK_NAME`,`LIBR_ID`) values ('422e4f0f6a1f4e36bbcf75c3ea045e14','hhhhh','17130177001');

/*Table structure for table `librarian` */

DROP TABLE IF EXISTS `librarian`;

CREATE TABLE `librarian` (
  `LIBR_ID` varchar(11) NOT NULL,
  `LIBR_NAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL DEFAULT '00010001',
  `STATE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`LIBR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `librarian` */

insert  into `librarian`(`LIBR_ID`,`LIBR_NAME`,`PASSWORD`,`STATE`) values ('17130177001','Tracy','1234555',0),('17130177002','Brandt','111111',0),('17130177003','Karen','152',0),('17130177004','Alex','00010001',0);

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `READER_ID` varchar(11) NOT NULL,
  `READER_NAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL DEFAULT '12345678',
  `E_MAIL` varchar(32) NOT NULL,
  `READER_DEPOSIT` decimal(6,2) NOT NULL DEFAULT '300.00',
  `READER_FINE` decimal(6,2) NOT NULL DEFAULT '0.00',
  `STATE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`READER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reader` */

insert  into `reader`(`READER_ID`,`READER_NAME`,`PASSWORD`,`E_MAIL`,`READER_DEPOSIT`,`READER_FINE`,`STATE`) values ('13412345679','Kelly','12345678','12345678@qq.com','300.00','0.00',0),('13512345678','Amy','123252','1234567777@463.com','300.00','12.00',0),('13512345688','Joyce','12345678','1234567888@163.com','300.00','0.00',0);
