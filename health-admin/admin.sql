CREATE DATABASE IF NOT EXISTS ADMIN;
use admin
-- create table IF NOT EXISTS `superadmin` (
-- `superadmin_id` INT AUTO_INCREMENT,
-- `username` VARCHAR(30) NOT NULL,
-- `password` VARCHAR(30) NOT NULL,
-- `remarks` VARCHAR(200),
-- `create_time` TIMESTAMP ,
-- `update_time` TIMESTAMP,
--  PRIMARY KEY (`superadmin_id`)
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
--
-- create table IF NOT EXISTS `service_admin` (
-- `service_admin_id` INT AUTO_INCREMENT,
-- `username` VARCHAR(30) NOT NULL,
-- `password` VARCHAR(30) NOT NULL,
-- `name`  VARCHAR(30),
-- `ID_number` VARCHAR(30),
-- `bank_type` VARCHAR(20),
-- `account_number` varchar(30),
-- `phone` VARCHAR(20) ,
-- `email` VARCHAR(30),
-- `checked` INT,
-- `remarks` VARCHAR(200),
-- `create_time` TIMESTAMP ,
-- `update_time` TIMESTAMP,
--  PRIMARY KEY (`service_admin_id`)
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- create table IF NOT EXISTS `business_admin` (
-- `business_admin_id` INT AUTO_INCREMENT,
-- `username` VARCHAR(30) NOT NULL,
-- `password` VARCHAR(30) NOT NULL,
-- `name`  VARCHAR(30),
-- `ID_number` VARCHAR(30),
-- `bank_type` VARCHAR(20),
-- `account_number` varchar(30),
-- `phone` VARCHAR(20) ,
-- `email` VARCHAR(30),
-- `checked` INT,
-- `remarks` VARCHAR(200),
-- `create_time` TIMESTAMP ,
-- `update_time` TIMESTAMP,
--  PRIMARY KEY (`business_admin_id`)
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table IF NOT EXISTS `admin` (
`id` INT AUTO_INCREMENT,
`username` VARCHAR(40) NOT NULL,
`password` VARCHAR(30) NOT NULL,
`role_id` INT,
`name`  VARCHAR(30),
`ID_number` VARCHAR(30),
`bank_type` VARCHAR(20),
`account_number` varchar(30),
`phone` VARCHAR(20) ,
`email` VARCHAR(30),
`checked` INT,
`remarks` VARCHAR(200),
`create_time` TIMESTAMP ,
`update_time` TIMESTAMP,
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

