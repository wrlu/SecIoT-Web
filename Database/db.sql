 
-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.3.9-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 seciot 的数据库结构
CREATE DATABASE IF NOT EXISTS `seciot` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `seciot`;

-- 导出  表 seciot.cve 结构
CREATE TABLE IF NOT EXISTS `cve` (
  `cve_num` varchar(50) NOT NULL,
  `level` varchar(20) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `statment` varchar(50) DEFAULT NULL,
  `payload` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cve_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存CVE信息';

-- 数据导出被取消选择。
-- 导出  表 seciot.cve_category 结构
CREATE TABLE IF NOT EXISTS `cve_category` (
  `cve_num` varchar(50) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cve_num`),
  CONSTRAINT `FK_cve_category_cve` FOREIGN KEY (`cve_num`) REFERENCES `cve` (`cve_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联cve和分析类型';

-- 数据导出被取消选择。
-- 导出  表 seciot.library_risk 结构
CREATE TABLE IF NOT EXISTS `library_risk` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `cve_num` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_library_risk_cve` (`cve_num`),
  KEY `FK_library_risk_third_part_library` (`name`),
  CONSTRAINT `FK_library_risk_cve` FOREIGN KEY (`cve_num`) REFERENCES `cve` (`cve_num`),
  CONSTRAINT `FK_library_risk_third_part_library` FOREIGN KEY (`name`) REFERENCES `third_part_library` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存某个库按版本号的漏洞情况';

-- 数据导出被取消选择。
-- 导出  表 seciot.fw_third_part_library 结构
CREATE TABLE IF NOT EXISTS `third_part_library` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `latest_version` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存第三方库的种类';

-- 数据导出被取消选择。
-- 导出  表 seciot.platform_risk 结构
CREATE TABLE IF NOT EXISTS `platform_risk` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `payload` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存平台和代码安全风险定义';

-- 数据导出被取消选择。
-- 导出  表 seciot.platform_risk_category 结构
CREATE TABLE IF NOT EXISTS `platform_risk_category` (
  `id` varchar(36) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK__platform_risk` FOREIGN KEY (`id`) REFERENCES `platform_risk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联表platform_risk和分析类型';

-- 数据导出被取消选择。
-- 导出  表 seciot.protocol 结构
CREATE TABLE IF NOT EXISTS `protocol` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `layer` int(11) NOT NULL COMMENT '2-5',
  `description` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 COMMENT='保存各种类型的协议';

-- 数据导出被取消选择。
-- 导出  表 seciot.protocol_risk 结构
CREATE TABLE IF NOT EXISTS `protocol_risk` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `payload` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存协议安全风险定义';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
