SET NAMES utf8;
SET time_zone = '-03:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `banco`;
CREATE DATABASE `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;

DROP TABLE IF EXISTS `CAIXA`;
CREATE TABLE `CAIXA` (
  `codigo` int(2) NOT NULL AUTO_INCREMENT,
  `cedula` int(4) NOT NULL,
  `quantidade` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `CAIXA` (`cedula`, `quantidade`) VALUES
(100,	'0'),
(50,	'0'),
(20,	'0'),
(10,	'0'),
(5,	'0'),
(2,	'0');