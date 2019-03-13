CREATE DATABASE IF NOT EXISTS service_category;
use service_category
create table IF NOT EXISTS `service_category` (
`id` INT AUTO_INCREMENT,
`name` varchar(50) NOT NULL,
`parent_id`  INT,
`level` INT,
`icon` varchar(40),
`gmt_create` TIMESTAMP,
`gmt_modified` TIMESTAMP,
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;