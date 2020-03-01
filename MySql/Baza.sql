/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.18 : Database - clojure
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`clojure` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `clojure`;

/*Table structure for table `regis` */

DROP TABLE IF EXISTS `regis`;

CREATE TABLE `regis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NazivIS` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `FazaZivotnogCiklusa` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Oblast` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Tip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Nosilac` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `OperativniSistem` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `regis` */

insert  into `regis`(`ID`,`NazivIS`,`FazaZivotnogCiklusa`,`Oblast`,`Tip`,`Nosilac`,`OperativniSistem`) values 
(3,'ИС Војевид','У употреби','Људство','Десктоп','Управа за планирање','Windows 7'),
(37,'ИС Људских Ресурса','У развоју','Људство','Web апликација','Одсек за људске ресурсе','Windows XP'),
(38,'МАК','Ван употребе','Материјални ресурси','Десктоп апликација','Логистика','Windows XP'),
(39,'Библиотека','У развоју','Образовање','Десктоп','Одсек за образовање','Windows 10'),
(41,'ИС ПРИЗ','Модификација','Планирање','Desktop апликација','Управа','Windows 7');

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `Ime` varchar(30) DEFAULT NULL,
  `Prezime` varchar(30) DEFAULT NULL,
  `JMBG` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`Ime`,`Prezime`,`JMBG`) values 
('Lazar','Stevanovic','2404995730046'),
('Milos','Jovic','8404995730046'),
('Lazar','Jovic','5404995730046'),
('Maja','Jovanovic','1404995730046'),
('Milan','Bogdanovic','2404995730046');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
