CREATE TABLE `ac_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `grp_id` varchar(10) DEFAULT NULL,
  `user_type` varchar(11) DEFAULT 'USER',
  `passwd` varchar(200) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `nicname` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `reg_time` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT 'WAITING',
  PRIMARY KEY (`id`),
  KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `ac_grp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grp_id` varchar(10) NOT NULL DEFAULT '',
  `grp_name` varchar(50) DEFAULT NULL,
  `grp_text` varchar(200) DEFAULT NULL,
  `grp_dept_id` varchar(10) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'ACTIVE',
  `reg_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`grp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `ac_grp_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grp_dept_id` varchar(10) NOT NULL,
  `grp_dept_name` varchar(50) DEFAULT NULL,
  `grp_dept_text` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`,`grp_dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;