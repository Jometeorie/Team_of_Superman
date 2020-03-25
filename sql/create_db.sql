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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`case1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `case1`;

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

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `READER_ID` varchar(11) NOT NULL,
  `READER_NAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL DEFAULT '12345678',
  `E_MAIL` varchar(32) DEFAULT NULL,
  `READER_DEPOSIT` decimal(6,2) NOT NULL DEFAULT '300.00',
  `READER_FINE` decimal(6,2) NOT NULL DEFAULT '0.00',
  `STATE` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`READER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reader` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
