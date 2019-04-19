-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2019-04-19 12:36:39
-- 服务器版本： 10.2.22-MariaDB
-- PHP 版本： 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `seciot`
--

-- --------------------------------------------------------

--
-- 表的结构 `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('root', 'authenticated');

-- --------------------------------------------------------

--
-- 表的结构 `cve`
--

CREATE TABLE `cve` (
  `cve_num` varchar(50) NOT NULL,
  `level` varchar(20) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `payload` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存CVE信息';

--
-- 转存表中的数据 `cve`
--

INSERT INTO `cve` (`cve_num`, `level`, `description`, `platform`, `payload`) VALUES
('CVE-2015-0207 ', 'Medium', '', 'Linux', ''),
('CVE-2015-0208', 'Medium', '', 'Linux', ''),
('CVE-2015-0209', 'Low', '', 'Linux', ''),
('CVE-2015-0285', 'Low', '', 'Linux', ''),
('CVE-2015-0286 ', 'Medium', '', 'Linux', ''),
('CVE-2015-0287 ', 'Medium', '', 'Linux', ''),
('CVE-2015-0288', 'Low', '', 'Linux', ''),
('CVE-2015-0289', 'Medium', '', 'Linux', ''),
('CVE-2015-0290', 'Medium', '', 'Linux', ''),
('CVE-2015-0291', 'High', '', 'Linux', ''),
('CVE-2015-0293', 'Medium', '', 'Linux', ''),
('CVE-2015-1787', 'Medium', '', 'Linux', ''),
('CVE-2015-1788', 'Medium', '', 'Linux', ''),
('CVE-2015-1789', 'Medium', '', 'Linux', ''),
('CVE-2015-1790 ', 'Medium', '', 'Linux', ''),
('CVE-2015-1791', 'Low', '', 'Linux', ''),
('CVE-2015-1792', 'Medium', '', 'Linux', ''),
('CVE-2015-1793', 'High', '', 'Linux', ''),
('CVE-2015-1794  ', 'Low', '', 'Linux', ''),
('CVE-2015-3193', 'Medium', '', 'Linux', ''),
('CVE-2015-3194', 'Medium', '', 'Linux', ''),
('CVE-2015-3195', 'Medium', '', 'Linux', ''),
('CVE-2015-3196', 'Low', '', 'Linux', ''),
('CVE-2015-3197  ', 'Low', '', 'Linux', ''),
('CVE-2016-0701', 'High', '', 'Linux', ''),
('CVE-2016-0702 ', 'Low', '', 'Linux', ''),
('CVE-2016-0703', 'High', '', 'Linux', ''),
('CVE-2016-0704', 'Medium', '', 'Linux', ''),
('CVE-2016-0705', 'Low', '', 'Linux', ''),
('CVE-2016-0797', 'Low', '', 'Linux', ''),
('CVE-2016-0798 ', 'Low', '', 'Linux', ''),
('CVE-2016-0799 ', 'Low', '', 'Linux', ''),
('CVE-2016-0800', 'High', '', 'Linux', ''),
('CVE-2016-2105', 'Low', '', 'Linux', ''),
('CVE-2016-2106', 'Low', '', 'Linux', ''),
('CVE-2016-2107', 'High', '', 'Linux', ''),
('CVE-2016-2108 ', 'High', '', 'Linux', ''),
('CVE-2016-2109', 'Low', '', 'Linux', ''),
('CVE-2016-2176', 'Low', '', 'Linux', ''),
('CVE-2016-2177 ', 'Low', '', 'Linux', ''),
('CVE-2016-2178', 'Low', '', 'Linux', ''),
('CVE-2016-2179', 'Low', '', 'Linux', ''),
('CVE-2016-2180', 'Low', '', 'Linux', ''),
('CVE-2016-2181', 'Low', '', 'Linux', ''),
('CVE-2016-2182 ', 'Low', '', 'Linux', ''),
('CVE-2016-6302 ', 'Low', '', 'Linux', ''),
('CVE-2016-6303 ', 'Low', '', 'Linux', ''),
('CVE-2016-6304 ', 'High', '', 'Linux', ''),
('CVE-2016-6306 ', 'Low', '', 'Linux', ''),
('CVE-2016-6307 ', 'Low', '', 'Linux', ''),
('CVE-2016-6308', 'Low', '', 'Linux', ''),
('CVE-2016-6309', 'High', '', 'Linux', ''),
('CVE-2016-7052 ', 'Medium', '', 'Linux', ''),
('CVE-2016-7053 ', 'Medium', '', 'Linux', ''),
('CVE-2016-7054', 'High', '', 'Linux', ''),
('CVE-2016-7055 ', 'Low', '', 'Linux', ''),
('CVE-2017-3730 ', 'High', '', 'Linux', ''),
('CVE-2017-3731', 'Medium', '', 'Linux', ''),
('CVE-2017-3732 ', 'Medium', '', 'Linux', ''),
('CVE-2017-3733', 'High', '', 'Linux', ''),
('CVE-2017-3735', 'Low', '', 'Linux', ''),
('CVE-2017-3736', 'High', '', 'Linux', ''),
('CVE-2017-3737', 'Medium', '', 'Linux', ''),
('CVE-2017-3738 ', 'Low', '', 'Linux', ''),
('CVE-2018-0732 ', 'Low', '', 'Linux', ''),
('CVE-2018-0733 ', 'High', '', 'Linux', ''),
('CVE-2018-0734', 'Low', '', 'Linux', ''),
('CVE-2018-0735', 'Low', '', 'Linux', ''),
('CVE-2018-0737', 'Low', '', 'Linux', ''),
('CVE-2018-0739 ', 'High', '', 'Linux', ''),
('CVE-2018-5407 ', 'Low', '', 'Linux', ''),
('CVE-2019-1559', 'High', '', 'Linux', '');

-- --------------------------------------------------------

--
-- 表的结构 `cve_category`
--

CREATE TABLE `cve_category` (
  `cve_num` varchar(50) NOT NULL,
  `category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联cve和分析类型';

--
-- 转存表中的数据 `cve_category`
--

INSERT INTO `cve_category` (`cve_num`, `category`) VALUES
('CVE-2015-0207 ', 'Firmware'),
('CVE-2015-0208', 'Firmware'),
('CVE-2015-0209', 'Firmware'),
('CVE-2015-0285', 'Firmware'),
('CVE-2015-0286 ', 'Firmware'),
('CVE-2015-0287 ', 'Firmware'),
('CVE-2015-0288', 'Firmware'),
('CVE-2015-0289', 'Firmware'),
('CVE-2015-0290', 'Firmware'),
('CVE-2015-0291', 'Firmware'),
('CVE-2015-0293', 'Firmware'),
('CVE-2015-1787', 'Firmware'),
('CVE-2015-1788', 'Firmware'),
('CVE-2015-1789', 'Firmware'),
('CVE-2015-1790 ', 'Firmware'),
('CVE-2015-1791', 'Firmware'),
('CVE-2015-1792', 'Firmware'),
('CVE-2015-1793', 'Firmware'),
('CVE-2015-1794  ', 'Firmware'),
('CVE-2015-3193', 'Firmware'),
('CVE-2015-3194', 'Firmware'),
('CVE-2015-3195', 'Firmware'),
('CVE-2015-3196', 'Firmware'),
('CVE-2015-3197  ', 'Firmware'),
('CVE-2016-0701', 'Firmware'),
('CVE-2016-0702 ', 'Firmware'),
('CVE-2016-0703', 'Firmware'),
('CVE-2016-0704', 'Firmware'),
('CVE-2016-0705', 'Firmware'),
('CVE-2016-0797', 'Firmware'),
('CVE-2016-0798 ', 'Firmware'),
('CVE-2016-0799 ', 'Firmware'),
('CVE-2016-0800', 'Firmware'),
('CVE-2016-2105', 'Firmware'),
('CVE-2016-2106', 'Firmware'),
('CVE-2016-2107', 'Firmware'),
('CVE-2016-2108 ', 'Firmware'),
('CVE-2016-2109', 'Firmware'),
('CVE-2016-2176', 'Firmware'),
('CVE-2016-2177 ', 'Firmware'),
('CVE-2016-2178', 'Firmware'),
('CVE-2016-2179', 'Firmware'),
('CVE-2016-2180', 'Firmware'),
('CVE-2016-2181', 'Firmware'),
('CVE-2016-2182 ', 'Firmware'),
('CVE-2016-6302 ', 'Firmware'),
('CVE-2016-6303 ', 'Firmware'),
('CVE-2016-6304 ', 'Firmware'),
('CVE-2016-6306 ', 'Firmware'),
('CVE-2016-6307 ', 'Firmware'),
('CVE-2016-6308', 'Firmware'),
('CVE-2016-6309', 'Firmware'),
('CVE-2016-7052 ', 'Firmware'),
('CVE-2016-7053 ', 'Firmware'),
('CVE-2016-7054', 'Firmware'),
('CVE-2016-7055 ', 'Firmware'),
('CVE-2017-3730 ', 'Firmware'),
('CVE-2017-3731', 'Firmware'),
('CVE-2017-3732 ', 'Firmware'),
('CVE-2017-3733', 'Firmware'),
('CVE-2017-3735', 'Firmware'),
('CVE-2017-3736', 'Firmware'),
('CVE-2017-3737', 'Firmware'),
('CVE-2017-3738 ', 'Firmware'),
('CVE-2018-0732 ', 'Firmware'),
('CVE-2018-0733 ', 'Firmware'),
('CVE-2018-0734', 'Firmware'),
('CVE-2018-0735', 'Firmware'),
('CVE-2018-0737', 'Firmware'),
('CVE-2018-0739 ', 'Firmware'),
('CVE-2018-5407 ', 'Firmware'),
('CVE-2019-1559', 'Firmware');

-- --------------------------------------------------------

--
-- 表的结构 `library_risk`
--

CREATE TABLE `library_risk` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `cve_num` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存某个库按版本号的漏洞情况';

--
-- 转存表中的数据 `library_risk`
--

INSERT INTO `library_risk` (`id`, `name`, `version`, `cve_num`) VALUES
('0040f519-3304-4c71-85f5-56100a4ac36e', 'OpenSSL', '1.0.2a', 'CVE-2016-2182 '),
('0105e40c-9ee2-4a4a-a790-c5d5bb72df8c', 'OpenSSL', '1.0.2k', 'CVE-2018-0734'),
('0107e80a-f2cf-4be2-ba10-5a1b9fc9d371', 'OpenSSL', '1.0.2d', 'CVE-2018-0739 '),
('0198df8f-b80b-4456-87c8-223abbc74950', 'OpenSSL', '1.1.0g', 'CVE-2018-0734'),
('02153cfb-22ef-4b80-b4cd-705266961c9d', 'OpenSSL', '1.0.2a', 'CVE-2016-2178'),
('022a3234-534f-416a-bab6-8ba945420462', 'OpenSSL', '1.0.2n', 'CVE-2018-5407 '),
('0259520d-5bf1-42dc-9e70-298103fbff6e', 'OpenSSL', '1.1.0e', 'CVE-2018-0732 '),
('043be744-64f5-4074-8043-8eeaeaf33076', 'OpenSSL', '1.0.2j', 'CVE-2019-1559'),
('0461fc67-1b5e-48d7-b308-433ffe0db495', 'OpenSSL', '1.0.2g', 'CVE-2016-6304 '),
('049128d9-af20-40d4-827d-0683e65808c4', 'OpenSSL', '1.0.2g', 'CVE-2017-3736'),
('04f91655-1d57-49c5-b6ec-42647c4669d6', 'OpenSSL', '1.0.2e', 'CVE-2016-6306 '),
('058bfd20-5da2-4761-a02e-103bb1eb517b', 'OpenSSL', '1.1.0d', 'CVE-2018-0739 '),
('05c88c98-57f7-459d-927d-929442f424c0', 'OpenSSL', '1.0.2g', 'CVE-2016-6306 '),
('06068905-8ef8-472f-b443-5908f579660b', 'OpenSSL', '1.0.2e', 'CVE-2016-2179'),
('069ef141-8944-4e4a-b3fc-950aa1ad11a4', 'OpenSSL', '1.1.0b', 'CVE-2017-3736'),
('074afaa1-a2c1-4d45-b2a4-0c3676df9f74', 'OpenSSL', '1.0.2', 'CVE-2016-2181'),
('0848949f-0a19-440f-9024-00c1148f3fc6', 'OpenSSL', '1.1.0i', 'CVE-2018-0735'),
('08f1d0d7-a66f-4efc-bb0e-45da27fef8c2', 'OpenSSL', '1.0.2b', 'CVE-2016-0705'),
('0941d89b-ada5-469c-83da-d1767cd103a2', 'OpenSSL', '1.1.0d', 'CVE-2018-5407 '),
('09a8e882-9d3f-4b9a-a9f0-d99d1bb8e044', 'OpenSSL', '1.0.2m', 'CVE-2017-3738 '),
('09bc2883-8e65-48e9-9d00-1c3d6d6bf4f4', 'OpenSSL', '1.1.0e', 'CVE-2018-0733 '),
('0a0459a1-3f44-44d0-ba16-3555cb63cd4f', 'OpenSSL', '1.0.2f', 'CVE-2016-0798 '),
('0a2f1900-e6ee-4383-8e46-ddc42a3fd2d9', 'OpenSSL', '1.0.2', 'CVE-2015-0287 '),
('0b27377d-d81b-41bb-ad96-0d818a138907', 'OpenSSL', '1.0.2m', 'CVE-2018-0734'),
('0c20efe0-2eb1-478b-8344-5128f096014b', 'OpenSSL', '1.0.2a', 'CVE-2016-7055 '),
('0ce65101-5b63-4593-9e7c-74f56ec5a1ce', 'OpenSSL', '1.1.0f', 'CVE-2018-0734'),
('0d293f21-6e66-4735-8d60-500f7ca4d4b1', 'OpenSSL', '1.0.2', 'CVE-2016-6303 '),
('0d398470-f9b9-460c-b170-1e92132f3cd8', 'OpenSSL', '1.0.2l', 'CVE-2018-0737'),
('0e247251-0c74-457c-a728-2a290f30b81d', 'OpenSSL', '1.0.2j', 'CVE-2017-3737'),
('0e3b77e6-2a8f-4346-84d5-45b92401234d', 'OpenSSL', '1.0.2i', 'CVE-2017-3732 '),
('0ec8ddad-ab26-4f4a-a17d-a4fda465e153', 'OpenSSL', '1.0.2a', 'CVE-2016-6303 '),
('0f5e1293-b3e4-4ded-bc69-111c36b60172', 'OpenSSL', '1.0.2l', 'CVE-2018-0739 '),
('0f8d3a0b-5306-43e6-b7f6-e70d6f482bd9', 'OpenSSL', '1.0.2b', 'CVE-2017-3732 '),
('0f91c578-d190-4524-943e-0565ed6d9e86', 'OpenSSL', '1.1.0', 'CVE-2017-3735'),
('104489a4-ca98-4353-8507-56bb90f6113a', 'OpenSSL', '1.0.2c', 'CVE-2016-2106'),
('105def52-35ae-45f3-b07a-c0d1af9050f3', 'OpenSSL', '1.1.0f', 'CVE-2018-0732 '),
('1102609b-d8f8-4b12-8ac9-f1a59ddf39b3', 'OpenSSL', '1.0.2j', 'CVE-2018-0732 '),
('115b151a-749d-4d24-9b05-4c98c82963b2', 'OpenSSL', '1.0.2o', 'CVE-2019-1559'),
('11760097-feac-44ca-b1a1-355e5fb98da3', 'OpenSSL', '1.0.2', 'CVE-2016-0798 '),
('118173a0-c609-4ec5-ad79-f25d5be7630e', 'OpenSSL', '1.0.2d', 'CVE-2016-2180'),
('11d0ba89-a9d9-4a79-9efa-29a71c9e8823', 'OpenSSL', '1.0.2i', 'CVE-2016-7055 '),
('11eba3a9-d1eb-428c-8d95-d5114b2a61cc', 'OpenSSL', '1.0.2d', 'CVE-2016-0705'),
('125459c5-d534-4631-8e96-ff158ca1d08f', 'OpenSSL', '1.0.2c', 'CVE-2016-2179'),
('1265aeb7-5e8b-4e3b-b97b-8909e722c492', 'OpenSSL', '1.0.2o', 'CVE-2018-0734'),
('129399ce-cb8a-4387-9575-1213a23a0a8d', 'OpenSSL', '1.0.2', 'CVE-2015-0285'),
('12ec5311-1a89-4b59-9e2d-78aa7eb9b7a4', 'OpenSSL', '1.0.2d', 'CVE-2016-2107'),
('1328a8e4-8e0f-43e4-8f48-4bbded880616', 'OpenSSL', '1.0.2', 'CVE-2015-1794  '),
('13a5f879-2482-45fa-acbd-96e1a0bde084', 'OpenSSL', '1.0.2h', 'CVE-2016-2182 '),
('13ccea2e-ad66-4889-a88c-f84672a1deef', 'OpenSSL', '1.0.2f', 'CVE-2017-3735'),
('14689e75-bfc4-475a-a21e-241e846038d9', 'OpenSSL', '1.0.2d', 'CVE-2015-3193'),
('147ae0ef-8b4b-4937-b94d-fd4feb9edcd0', 'OpenSSL', '1.1.0e', 'CVE-2017-3738 '),
('14cf9a20-ff22-4460-b9be-66cb19d97406', 'OpenSSL', '1.1.0c', 'CVE-2018-0737'),
('15128d62-6f15-454f-adc3-99ce36c425b9', 'OpenSSL', '1.0.2c', 'CVE-2017-3735'),
('15b10f46-6e9e-427b-b6a1-a8da6ea54810', 'OpenSSL', '1.0.2d', 'CVE-2018-0734'),
('18bc6f06-e724-4895-a1ab-ee6fccec657a', 'OpenSSL', '1.1.0g', 'CVE-2018-5407 '),
('190ec406-b43a-4e87-92ac-36fdb0caabb1', 'OpenSSL', '1.0.2e', 'CVE-2017-3731'),
('19957d0f-7c02-472e-9914-47043bfd4871', 'OpenSSL', '1.0.2a', 'CVE-2017-3732 '),
('1a80a5fb-d423-4c7c-ab68-74cb0def0db8', 'OpenSSL', '1.0.2e', 'CVE-2016-0798 '),
('1c11c4e4-18a1-4e28-b8e0-b5e6267f64e1', 'OpenSSL', '1.0.2j', 'CVE-2018-0737'),
('1d622c28-c6d8-4104-93e1-33a5a59320b7', 'OpenSSL', '1.0.2c', 'CVE-2016-6306 '),
('1dbec2d0-b617-4556-9293-6567b7472ac5', 'OpenSSL', '1.0.2g', 'CVE-2016-2179'),
('1e44cb20-a3f0-4f07-b744-a9de409d0f93', 'OpenSSL', '1.0.2', 'CVE-2015-0291'),
('1e80bf63-8511-450f-90ad-d68120456a1a', 'OpenSSL', '1.0.2c', 'CVE-2015-3195'),
('1eee041b-d1d9-4b02-9768-3a8cd84d6474', 'OpenSSL', '1.0.2e', 'CVE-2017-3737'),
('1f11e61c-c5fd-4996-9184-51179fd8a79b', 'OpenSSL', '1.0.2b', 'CVE-2018-0737'),
('20fdce7d-42fa-4923-9cc4-dfdb60e9eccb', 'OpenSSL', '1.0.2c', 'CVE-2016-0701'),
('212dd39f-30f6-42dd-bc82-83182e799db2', 'OpenSSL', '1.0.2a', 'CVE-2015-3196'),
('21679ae3-bc9d-470d-a9c9-1f80da601fad', 'OpenSSL', '1.0.2f', 'CVE-2016-2106'),
('21f63ce0-c7e9-4cd4-8eb7-9c50a289ea41', 'OpenSSL', '1.0.2a', 'CVE-2019-1559'),
('227845f5-7ede-40a9-9453-aa614ff5d262', 'OpenSSL', '1.0.2d', 'CVE-2016-0800'),
('2279dcc3-686a-47ee-85ee-d96b0fa42851', 'OpenSSL', '1.0.2l', 'CVE-2018-0734'),
('22efc3de-53f1-4b1c-9b6d-acf576b56f23', 'OpenSSL', '1.0.2h', 'CVE-2017-3737'),
('2314931e-2fd4-4df2-af69-d3f3da952223', 'OpenSSL', '1.0.2l', 'CVE-2017-3737'),
('23bd3adc-c315-47e8-bdbf-32488cb38570', 'OpenSSL', '1.0.2a', 'CVE-2015-3197  '),
('247a4a5a-2ad7-420e-8acb-1630888d40ab', 'OpenSSL', '1.0.2', 'CVE-2015-0209'),
('259ebf21-ff04-44de-8be0-a6561f1169c1', 'OpenSSL', '1.0.2e', 'CVE-2017-3735'),
('25a59c7d-a844-4d81-ab3a-6766ef6252eb', 'OpenSSL', '1.0.2', 'CVE-2016-2108 '),
('2678243e-e7fb-472c-92ac-17e9ea365536', 'OpenSSL', '1.0.2d', 'CVE-2016-2109'),
('26a68f66-576f-404e-a2e4-82e665680edf', 'OpenSSL', '1.0.2', 'CVE-2017-3732 '),
('271aeaa6-1c46-445b-809c-eeac18340ea3', 'OpenSSL', '1.1.0c', 'CVE-2017-3733'),
('276b0c49-6244-4b45-bc31-42f584d355a9', 'OpenSSL', '1.0.2c', 'CVE-2015-3194'),
('280a9ab6-2992-43ee-9ce4-b579c4289fd4', 'OpenSSL', '1.0.2e', 'CVE-2016-2176'),
('28158114-a0c3-44e9-ba98-a8943678757e', 'OpenSSL', '1.0.2', 'CVE-2015-3195'),
('28353141-39d7-4406-a0be-59d50e2b9258', 'OpenSSL', '1.0.2c', 'CVE-2017-3732 '),
('284c8fda-b37f-4027-a0c5-b204b38ffa72', 'OpenSSL', '1.0.2i', 'CVE-2016-7052 '),
('29d58c22-0516-4ecc-9d9d-df2fd2195713', 'OpenSSL', '1.0.2f', 'CVE-2016-0797'),
('29e96cb2-93ba-4ef6-8508-9c09fc93b0fd', 'OpenSSL', '1.0.2d', 'CVE-2015-3197  '),
('2a9c67db-a283-4305-b674-b5c8776b5fdf', 'OpenSSL', '1.0.2h', 'CVE-2017-3736'),
('2ad9fb73-0870-4606-9ea0-b85607182579', 'OpenSSL', '1.0.2d', 'CVE-2016-2176'),
('2b598c64-a6c0-49e9-a06e-1cd33f058886', 'OpenSSL', '1.0.2b', 'CVE-2018-0734'),
('2bb61832-dfbb-49b9-b079-1972913e7dc1', 'OpenSSL', '1.0.2e', 'CVE-2016-2182 '),
('2c249d73-742a-47f5-bf22-241e4effaf30', 'OpenSSL', '1.0.2b', 'CVE-2016-6304 '),
('2cf060a5-969c-4503-8346-e8b84db60a25', 'OpenSSL', '1.0.2', 'CVE-2015-1787'),
('2d4ae244-0c49-4c5d-89d1-150f44e1dcfa', 'OpenSSL', '1.0.2l', 'CVE-2017-3738 '),
('2e05fe37-df05-4098-bc3c-2600950f9c24', 'OpenSSL', '1.1.0a', 'CVE-2018-0734'),
('2e2b2f7b-9b95-4b22-ad22-1788c3eefb45', 'OpenSSL', '1.0.2j', 'CVE-2017-3735'),
('2ef6ac83-2394-42ea-a519-9a9e604da2a6', 'OpenSSL', '1.0.2b', 'CVE-2017-3735'),
('2f2640c6-52f8-4c9f-bf65-845fb7a7f239', 'OpenSSL', '1.0.2d', 'CVE-2017-3738 '),
('2f360a80-bc13-4dd1-9c43-4aa52ee71b9d', 'OpenSSL', '1.0.2j', 'CVE-2018-0739 '),
('2f4516d0-84f5-4e90-b092-915cd3272a64', 'OpenSSL', '1.1.0b', 'CVE-2018-0733 '),
('3056d58b-5259-4489-92be-9a6d1e01e25f', 'OpenSSL', '1.0.2g', 'CVE-2016-2176'),
('309727af-53e5-49ea-9952-418ce16a7f13', 'OpenSSL', '1.0.2i', 'CVE-2018-0737'),
('3160cca7-81af-452c-8cf6-78c2c871802c', 'OpenSSL', '1.1.0a', 'CVE-2016-7054'),
('33f08bae-9514-4d26-b2d8-bbb27302cadd', 'OpenSSL', '1.0.2d', 'CVE-2015-1794  '),
('3440b157-9613-436d-89ab-24d52b315c4f', 'OpenSSL', '1.0.2b', 'CVE-2015-3197  '),
('34ce2fb6-d036-46bb-b8d2-9cf61507fef3', 'OpenSSL', '1.0.2e', 'CVE-2016-0702 '),
('34e3f59b-d6aa-44e1-808d-b2c011538a37', 'OpenSSL', '1.0.2j', 'CVE-2018-0734'),
('34ee96a7-60f2-4e8e-8f7a-57342f73a710', 'OpenSSL', '1.0.2g', 'CVE-2016-2109'),
('3522ae10-7ae6-403f-aff1-6b45b41b6547', 'OpenSSL', '1.0.2a', 'CVE-2015-3195'),
('3621a1a1-376a-47b7-a745-c22ffcd83fdf', 'OpenSSL', '1.0.2o', 'CVE-2018-5407 '),
('367e27a0-cec9-4e99-9a66-a29d481ffe2d', 'OpenSSL', '1.0.2h', 'CVE-2016-6302 '),
('3718faf4-dd62-4e1a-af6b-1be52ab62102', 'OpenSSL', '1.0.2c', 'CVE-2016-0800'),
('37835698-29b1-4b37-bdda-5bb08a5f7115', 'OpenSSL', '1.0.2', 'CVE-2016-0799 '),
('386c0e23-2eac-49df-a054-427ff5d5299d', 'OpenSSL', '1.0.2k', 'CVE-2017-3735'),
('38743acc-b223-4311-a93e-2df91efaf410', 'OpenSSL', '1.0.2', 'CVE-2015-0208'),
('387e6a98-01aa-4a22-ba48-3fb611d24069', 'OpenSSL', '1.0.2i', 'CVE-2018-0732 '),
('38a30fb4-3250-4474-94f0-8c9260f86402', 'OpenSSL', '1.0.2h', 'CVE-2017-3738 '),
('39a823fd-47ae-41d0-8723-fdaf20deac7a', 'OpenSSL', '1.0.2a', 'CVE-2016-2181'),
('39afab78-748c-4f99-8af8-cc83503fb3ae', 'OpenSSL', '1.0.2i', 'CVE-2019-1559'),
('39d2a40e-a8fa-42ea-8cb2-1f535ae40008', 'OpenSSL', '1.0.2e', 'CVE-2015-3197  '),
('39df8977-44e3-4875-9a59-aaeb3bc1031d', 'OpenSSL', '1.1.0e', 'CVE-2018-5407 '),
('3aef76e7-8930-4e56-b6c3-3d497f9d6d29', 'OpenSSL', '1.1.0a', 'CVE-2017-3731'),
('3b637ea1-fc18-4e70-a935-41540bcba5af', 'OpenSSL', '1.0.2h', 'CVE-2017-3731'),
('3baf8eea-2405-4519-8ddd-a628400ca086', 'OpenSSL', '1.1.0a', 'CVE-2018-0732 '),
('3bc04ff7-a384-4170-b8c3-b6a0c74f2548', 'OpenSSL', '1.1.0g', 'CVE-2018-0735'),
('3c208026-2c39-49a3-a707-ca28b6500c0d', 'OpenSSL', '1.0.2g', 'CVE-2018-0739 '),
('3c43755c-6a5d-450e-9b69-3f5a2c2092bd', 'OpenSSL', '1.0.2f', 'CVE-2016-2182 '),
('3d26619e-bdde-446e-8308-3758c64a54e1', 'OpenSSL', '1.0.2', 'CVE-2016-6302 '),
('3d55185b-6223-459d-8a89-cc933353476a', 'OpenSSL', '1.0.2f', 'CVE-2019-1559'),
('3d84db34-5029-4353-8db1-503261f80e73', 'OpenSSL', '1.0.2i', 'CVE-2017-3737'),
('3dbc540f-a5b8-40ec-a705-1c92b83464b2', 'OpenSSL', '1.1.0', 'CVE-2018-0737'),
('3ee1a997-16f6-426f-b1bc-2d95d5f9b551', 'OpenSSL', '1.1.0', 'CVE-2016-7053 '),
('40968a8a-3826-4786-8286-ab1442f48b98', 'OpenSSL', '1.1.0i', 'CVE-2018-0734'),
('40fb438f-e9d7-4e59-a017-2fefbfa7ab48', 'OpenSSL', '1.0.2c', 'CVE-2016-2178'),
('414ac376-c938-4f1f-95d1-9007872a7507', 'OpenSSL', '1.0.2b', 'CVE-2016-2181'),
('41f7e627-389b-4bd7-bb61-599672e07227', 'OpenSSL', '1.0.2g', 'CVE-2016-2177 '),
('4293959c-6e29-4799-ab4a-5fd865e856e2', 'OpenSSL', '1.0.2g', 'CVE-2017-3737'),
('432cbed7-74b1-4113-8699-cdda40e8ba65', 'OpenSSL', '1.0.2c', 'CVE-2016-7055 '),
('4365631f-64cb-4522-a48e-1f0e333fd0ae', 'OpenSSL', '1.0.2c', 'CVE-2018-0739 '),
('43907950-3ec2-47ff-905f-3e3a6791e128', 'OpenSSL', '1.0.2d', 'CVE-2018-5407 '),
('43b61216-138a-4dfe-a098-3ea9c7f12200', 'OpenSSL', '1.0.2a', 'CVE-2016-2180'),
('457747f7-537c-4655-b723-f2c05f97ecc6', 'OpenSSL', '1.1.0b', 'CVE-2017-3735'),
('46882e97-d40a-4a41-98e4-1e0301d8f219', 'OpenSSL', '1.0.2', 'CVE-2018-0732 '),
('46db5c35-7476-459f-9bfb-d34e10e1eba6', 'OpenSSL', '1.0.2a', 'CVE-2015-1790 '),
('46f994d7-0e23-4432-82e7-8f40ac2f2928', 'OpenSSL', '1.0.2c', 'CVE-2016-0798 '),
('49351e7f-57d8-4d2f-af29-a328f301e541', 'OpenSSL', '1.0.2k', 'CVE-2017-3737'),
('4a36cdc5-5613-4941-a6a9-2081a0510af1', 'OpenSSL', '1.0.2d', 'CVE-2016-6306 '),
('4b7f454c-478c-46ea-99e0-23a4ab85d477', 'OpenSSL', '1.1.0e', 'CVE-2017-3736'),
('4b963a0a-074b-4ef3-b9b8-31370ebe1106', 'OpenSSL', '1.0.2', 'CVE-2016-0702 '),
('4bd61467-0a24-4104-93a2-73aa3a736622', 'OpenSSL', '1.1.0', 'CVE-2016-7055 '),
('4bed8442-95a0-4fbd-abad-76f22379e6cd', 'OpenSSL', '1.1.0e', 'CVE-2018-0734'),
('4c5075dc-81a8-4b05-b170-9fea2f615800', 'OpenSSL', '1.0.2', 'CVE-2018-0737'),
('4c6d83ed-3d52-405b-9ccb-f445abb96e45', 'OpenSSL', '1.1.0d', 'CVE-2017-3733'),
('4dd7f534-f821-42e2-a254-77bc1e7c7da0', 'OpenSSL', '1.0.2c', 'CVE-2016-2180'),
('4deb9b04-573d-4c50-8b8a-1660911b82d6', 'OpenSSL', '1.0.2c', 'CVE-2016-2177 '),
('4e2071df-7905-4968-a5d0-8cc79907a29a', 'OpenSSL', '1.0.2', 'CVE-2017-3736'),
('4e34a1ee-02f3-4047-92f5-bc761852bda0', 'OpenSSL', '1.1.0a', 'CVE-2017-3738 '),
('4e74ae2e-b227-4cda-ab3f-ec5db9d6b584', 'OpenSSL', '1.1.0c', 'CVE-2018-0735'),
('4e8eee84-af29-4966-9161-d4a298a7ca9b', 'OpenSSL', '1.0.2h', 'CVE-2016-2180'),
('4ed8c4da-c07b-4b9b-b605-d5897fdf9abe', 'OpenSSL', '1.0.2', 'CVE-2016-0703'),
('4edeb8d3-7e22-4b1d-88a6-a41f75636f86', 'OpenSSL', '1.0.2b', 'CVE-2016-2178'),
('4eebe82e-d6b7-4bfa-8014-9ee03859e34b', 'OpenSSL', '1.0.2b', 'CVE-2016-0798 '),
('4f3158c7-6df2-4950-981b-3b037fc69ec7', 'OpenSSL', '1.0.2n', 'CVE-2018-0737'),
('4f3f5b97-f022-4f92-b88c-8e54dcf85ab1', 'OpenSSL', '1.0.2b', 'CVE-2016-2109'),
('4f9424d1-b68a-44cc-aecf-d6fe1ac0853a', 'OpenSSL', '1.0.2c', 'CVE-2015-3197  '),
('4ff12906-0d37-47e1-a895-4a84eeabeb2b', 'OpenSSL', '1.1.0h', 'CVE-2018-0737'),
('506286bd-ab5b-476f-ad93-a2cc98166df5', 'OpenSSL', '1.0.2', 'CVE-2016-0701'),
('5082a50f-23d1-4cc6-a6e6-6f8e39c1df66', 'OpenSSL', '1.0.2d', 'CVE-2016-0799 '),
('5154dc00-ae5f-4870-bd84-bcec7aa52312', 'OpenSSL', '1.0.2a', 'CVE-2016-2107'),
('516a919e-865b-4497-ad2f-5958218f7932', 'OpenSSL', '1.1.0c', 'CVE-2017-3731'),
('5180ff31-7c16-4495-8256-4726529d3ab3', 'OpenSSL', '1.0.2', 'CVE-2015-3193'),
('52877a56-3191-46f5-97e3-dfbc9b38374d', 'OpenSSL', '1.1.0b', 'CVE-2018-0735'),
('529215fb-3479-4771-9371-84e1f797d0d9', 'OpenSSL', '1.0.2i', 'CVE-2017-3736'),
('535d83a1-cb1f-4c0d-a08d-60e1e91897f0', 'OpenSSL', '1.0.2b', 'CVE-2017-3736'),
('538c8aa0-c3ef-4238-99e7-13798d70c638', 'OpenSSL', '1.0.2e', 'CVE-2016-6302 '),
('54576632-b2a7-4aa0-a3b9-19a68fcb7939', 'OpenSSL', '1.0.2c', 'CVE-2017-3738 '),
('554c2daa-06bd-4411-be51-d9520cf0caab', 'OpenSSL', '1.1.0a', 'CVE-2016-7055 '),
('55b30333-01dd-494b-9dfd-9e22fc6ab842', 'OpenSSL', '1.0.2', 'CVE-2017-3735'),
('55cddcb5-1dbb-440c-bbae-253af082d2f0', 'OpenSSL', '1.0.2f', 'CVE-2016-6303 '),
('55e407f1-b3c6-4a4d-b841-5629261e95f8', 'OpenSSL', '1.0.2c', 'CVE-2017-3737'),
('55e5a68d-1339-4b3e-9016-d60ee6b0ce4a', 'OpenSSL', '1.0.2g', 'CVE-2016-7055 '),
('56900f62-87fe-41d6-b1d1-2761f37fe369', 'OpenSSL', '1.0.2h', 'CVE-2018-5407 '),
('56e1ab9c-a14e-4ce4-a679-9e33767b1822', 'OpenSSL', '1.0.2g', 'CVE-2017-3738 '),
('57811605-a976-410b-a4b9-9102d13d3650', 'OpenSSL', '1.0.2', 'CVE-2015-0290'),
('57a6f9ee-b965-491a-b6a1-86e5adb838b1', 'OpenSSL', '1.0.2k', 'CVE-2018-0739 '),
('57e80e43-3d69-44bb-aab1-4b36eb5025fd', 'OpenSSL', '1.0.2d', 'CVE-2015-3195'),
('588e4314-48c1-4d53-8989-c4777a8b5469', 'OpenSSL', '1.1.0c', 'CVE-2018-5407 '),
('58a590e0-1531-44b5-9ba9-4bcc11f3c66c', 'OpenSSL', '1.0.2f', 'CVE-2017-3738 '),
('58be4bd9-3491-4bf8-9a81-d1152fd47ebb', 'OpenSSL', '1.0.2b', 'CVE-2016-2176'),
('58e9844e-1767-4311-88e6-41e9a90eedf1', 'OpenSSL', '1.0.2d', 'CVE-2016-7055 '),
('596147de-db4a-4907-bbab-3c3873e16107', 'OpenSSL', '1.1.0g', 'CVE-2018-0739 '),
('597b273d-47d0-4376-bae1-264c0997701d', 'OpenSSL', '1.0.2', 'CVE-2016-2176'),
('597ce014-2d33-4489-a126-2392ea6a6fb2', 'OpenSSL', '1.0.2n', 'CVE-2018-0739 '),
('59ccdd8f-442d-4c04-9b77-26ba87c011df', 'OpenSSL', '1.0.2g', 'CVE-2016-6302 '),
('5a0e1d32-590e-4b0c-bf97-160235251ac5', 'OpenSSL', '1.0.2g', 'CVE-2017-3731'),
('5a2a54d5-b65b-4568-a522-4d115b103fec', 'OpenSSL', '1.0.2b', 'CVE-2016-7055 '),
('5a75a947-3c84-400d-8d48-09d62950b27a', 'OpenSSL', '1.1.0', 'CVE-2016-7054'),
('5b70c2b1-ace1-49e6-b3af-43b2c7d4b1c5', 'OpenSSL', '1.0.2b', 'CVE-2016-6303 '),
('5bfb5221-5180-4be0-9d94-00ffac34a6df', 'OpenSSL', '1.0.2b', 'CVE-2016-2107'),
('5cc1f6d0-ee93-436a-b5b8-030a7b6f3430', 'OpenSSL', '1.0.2f', 'CVE-2017-3732 '),
('5cc545eb-551e-47a2-bdd4-fca07c1e1939', 'OpenSSL', '1.0.2p', 'CVE-2019-1559'),
('5e7a3ad4-8535-4176-b630-c29ce16f13e7', 'OpenSSL', '1.0.2a', 'CVE-2017-3738 '),
('5e931959-9cf9-4264-b8ce-d4bd68f9b196', 'OpenSSL', '1.0.2e', 'CVE-2016-7055 '),
('5e96e3fd-56e2-488e-87a4-7b00d8f57d15', 'OpenSSL', '1.1.0', 'CVE-2017-3730 '),
('5eda3485-8c48-4b17-b493-45a6894f08f2', 'OpenSSL', '1.0.2c', 'CVE-2017-3731'),
('5f4683ba-9d74-4454-b842-809a483f8dc0', 'OpenSSL', '1.0.2f', 'CVE-2016-0702 '),
('5f83c713-f741-4a52-8e84-42703d100c06', 'OpenSSL', '1.0.2d', 'CVE-2016-0701'),
('60a12d2d-8b3d-4eb5-b2fa-9104748b47a0', 'OpenSSL', '1.1.0', 'CVE-2017-3738 '),
('60d702de-a750-4949-832c-076c877b9eb3', 'OpenSSL', '1.1.0j', 'CVE-2018-0735'),
('6342d26f-cc2e-4bb3-8295-b312d1a1f791', 'OpenSSL', '1.1.0a', 'CVE-2018-5407 '),
('635e39f3-9d52-42bb-a08c-af2069a98363', 'OpenSSL', '1.1.0c', 'CVE-2018-0739 '),
('636c188d-bbff-4d44-8575-bf1c75b9f000', 'OpenSSL', '1.0.2h', 'CVE-2017-3732 '),
('643e026c-cc46-4e56-b431-13c002688132', 'OpenSSL', '1.0.2c', 'CVE-2015-1793'),
('6455f462-cdbc-4b89-84f7-1b3555b1bcc2', 'OpenSSL', '1.0.2g', 'CVE-2016-2182 '),
('64724547-04c5-4356-9b73-c8b7f533d63f', 'OpenSSL', '1.0.2', 'CVE-2016-2180'),
('6644bc6e-746d-4091-a60c-46fb473d31ce', 'OpenSSL', '1.0.2m', 'CVE-2017-3737'),
('66e65e84-b56a-4627-8ce1-7190cf566801', 'OpenSSL', '1.0.2b', 'CVE-2016-2182 '),
('66f23a22-03a7-471e-87ed-92f22b6c139e', 'OpenSSL', '1.0.2f', 'CVE-2017-3737'),
('670d14ca-6346-47d2-934c-f858321f3fc9', 'OpenSSL', '1.0.2m', 'CVE-2018-0732 '),
('67ae364c-5bbe-40b8-937a-4c50e3548cc3', 'OpenSSL', '1.1.0b', 'CVE-2016-7054'),
('682a4ace-1098-4297-ba41-2fcf65956870', 'OpenSSL', '1.0.2d', 'CVE-2016-2178'),
('68a728b7-872a-49c2-96de-7afc9691fae2', 'OpenSSL', '1.0.2m', 'CVE-2018-5407 '),
('68c85da5-8cb1-4655-b0f5-1fdf2820ff41', 'OpenSSL', '1.0.2g', 'CVE-2016-2181'),
('692957e6-2776-4b4a-bfc2-893aff2aa919', 'OpenSSL', '1.0.2a', 'CVE-2016-0702 '),
('695d9d40-5f89-4339-9cf5-8dde68dbf2b9', 'OpenSSL', '1.0.2c', 'CVE-2016-2181'),
('69b40f99-a189-4580-a0ef-889778566b7b', 'OpenSSL', '1.1.0', 'CVE-2017-3731'),
('69c30e96-7f70-4175-b3ae-a8399ce5dcbc', 'OpenSSL', '1.0.2h', 'CVE-2016-2181'),
('69ddd069-801d-410f-8953-f90182db7a2b', 'OpenSSL', '1.1.1', 'CVE-2018-0735'),
('6a4efaeb-1a70-42bd-b75e-0e975e0a755a', 'OpenSSL', '1.0.2m', 'CVE-2019-1559'),
('6a94449a-28a9-40fd-b0a4-d26fdf706826', 'OpenSSL', '1.0.2p', 'CVE-2018-5407 '),
('6ac0a91f-61ac-4dde-9ee0-82e6b09f94ab', 'OpenSSL', '1.0.2f', 'CVE-2018-5407 '),
('6bbc7755-f7f6-4eae-b8b7-697b57a1e6b2', 'OpenSSL', '1.1.0d', 'CVE-2018-0732 '),
('6be84178-4e8f-4c61-82bb-d0543a7a8f94', 'OpenSSL', '1.0.2b', 'CVE-2017-3731'),
('6bf5cadf-1c2c-4e65-80a7-b375c4f6bc54', 'OpenSSL', '1.0.2', 'CVE-2016-0705'),
('6c507f9b-e843-4a1e-8066-9982e6500d8a', 'OpenSSL', '1.0.2k', 'CVE-2017-3736'),
('6cae8062-b41f-41af-b03d-1b7aaaa88f4e', 'OpenSSL', '1.0.2a', 'CVE-2016-2105'),
('6ce3cb2f-c97e-4830-a3d7-4dec941a320a', 'OpenSSL', '1.0.2b', 'CVE-2016-0702 '),
('6ced342b-b5ef-4c6d-8a48-57423865ebe6', 'OpenSSL', '1.1.0g', 'CVE-2018-0733 '),
('6cf43e00-ca8f-49f6-bc93-e1fa784fafc7', 'OpenSSL', '1.0.2g', 'CVE-2018-0734'),
('6e06cfeb-0f68-412e-a78e-82fb4ffedf89', 'OpenSSL', '1.1.0d', 'CVE-2018-0733 '),
('6e57b6c8-8ddf-4d53-a059-e8f4b99521ea', 'OpenSSL', '1.0.2c', 'CVE-2016-2182 '),
('6f193c93-5be9-4fa9-9a3c-9cc80945fec6', 'OpenSSL', '1.0.2', 'CVE-2016-2178'),
('6f1dc5aa-c047-44ad-a76e-3b6b892d2446', 'OpenSSL', '1.0.2e', 'CVE-2016-2178'),
('6f743778-a3d4-4949-a016-6d27b77959c4', 'OpenSSL', '1.1.0e', 'CVE-2018-0737'),
('708d5570-4e61-457c-b0e4-62f4382b9031', 'OpenSSL', '1.1.0g', 'CVE-2018-0737'),
('717ac0f8-2054-4458-8ae0-e0eeb87bbf7b', 'OpenSSL', '1.0.2j', 'CVE-2016-7055 '),
('7225d8f3-35fb-42b5-9793-664c751de43b', 'OpenSSL', '1.0.2j', 'CVE-2017-3732 '),
('729c621c-5fcb-46a0-86f8-bd55387e05e9', 'OpenSSL', '1.0.2g', 'CVE-2018-0732 '),
('72ec1cf6-a924-4d4d-b76b-c7016a4e8ad6', 'OpenSSL', '1.0.2g', 'CVE-2016-2107'),
('730c7c5b-dae4-48b9-b4b9-aea9c9d91bb5', 'OpenSSL', '1.1.0d', 'CVE-2017-3736'),
('73188515-2432-4e17-bb25-91eac119cbcc', 'OpenSSL', '1.0.2b', 'CVE-2016-6306 '),
('73269d6b-44b5-4074-a710-50e6bb5cc1a5', 'OpenSSL', '1.0.2b', 'CVE-2015-3196'),
('73289d47-c0db-494c-adb6-5db47f6bc566', 'OpenSSL', '1.0.2c', 'CVE-2016-2105'),
('733022af-1ec0-4a53-9f05-b6c78c065a3e', 'OpenSSL', '1.0.2c', 'CVE-2016-0702 '),
('7332fdc1-4ab7-400f-affd-c1076d533ae6', 'OpenSSL', '1.0.2g', 'CVE-2016-2105'),
('73578f1c-215a-4fd1-9ec4-aa129051bc9c', 'OpenSSL', '1.0.2a', 'CVE-2016-2106'),
('73a9026c-d9e6-49a9-8dfa-9d7d5f9babe9', 'OpenSSL', '1.0.2b', 'CVE-2016-2106'),
('7529cde7-0162-4853-89a1-b15cddd80a9f', 'OpenSSL', '1.0.2d', 'CVE-2016-2182 '),
('75a1c674-5e49-4de0-b06f-b35f718b6622', 'OpenSSL', '1.0.2i', 'CVE-2017-3738 '),
('76630671-d4a5-43bc-8374-6286f1b4cba8', 'OpenSSL', '1.0.2h', 'CVE-2018-0732 '),
('76dffba8-7361-4d61-a3de-ef25e637b924', 'OpenSSL', '1.1.0c', 'CVE-2017-3732 '),
('76ef25eb-fbf4-4558-a43e-7d99dbc19b0e', 'OpenSSL', '1.1.0', 'CVE-2018-5407 '),
('76f1dc21-8f9c-4e1f-a95d-b659af880c0d', 'OpenSSL', '1.0.2a', 'CVE-2018-5407 '),
('7712c3fe-cb52-44d1-990e-ccb8883ae85d', 'OpenSSL', '1.0.2e', 'CVE-2018-5407 '),
('7725b91f-c337-4ce5-a73a-c65c7c369fc2', 'OpenSSL', '1.0.2a', 'CVE-2015-1791'),
('773592ca-6910-4f9a-be6d-0eab5d075c97', 'OpenSSL', '1.1.0b', 'CVE-2016-7053 '),
('77641e92-56c6-4b9b-a71a-a4a7017f7209', 'OpenSSL', '1.0.2e', 'CVE-2016-2105'),
('7810961d-88e9-4f09-9717-1cafcfc0af63', 'OpenSSL', '1.1.0a', 'CVE-2017-3735'),
('78ea1a4a-64fa-4a90-aed2-9d31a5c21e49', 'OpenSSL', '1.0.2', 'CVE-2019-1559'),
('7a489487-94c7-4459-ae7c-c1469c47ef51', 'OpenSSL', '1.0.2a', 'CVE-2016-6302 '),
('7a63e304-6cab-4932-bc10-1e14e367b5a4', 'OpenSSL', '1.0.2', 'CVE-2016-2107'),
('7a70020b-4692-45eb-acf1-0431cebc4f76', 'OpenSSL', '1.0.2a', 'CVE-2016-2179'),
('7a92dd05-b202-4786-824a-3da5ea710fe2', 'OpenSSL', '1.0.2j', 'CVE-2017-3731'),
('7ac88348-64e8-48b6-861f-c4fad9fe2b26', 'OpenSSL', '1.0.2', 'CVE-2016-6306 '),
('7b37ccbe-7f0d-4caa-9eb2-abb5dd389503', 'OpenSSL', '1.1.0', 'CVE-2018-0734'),
('7b49c761-4b22-4e7c-be73-8f2bdbe9e825', 'OpenSSL', '1.0.2f', 'CVE-2016-2177 '),
('7b5f7568-42d7-4371-8c2c-3843c16990fa', 'OpenSSL', '1.0.2c', 'CVE-2016-2176'),
('7baa5bd5-66b1-4b0c-8d9d-c9e6ac9285cf', 'OpenSSL', '1.0.2b', 'CVE-2015-1794  '),
('7be45df8-8f70-4a80-9269-f05d5743cd35', 'OpenSSL', '1.0.2h', 'CVE-2016-2179'),
('7bfa960f-57ee-4991-89db-ac9dacdaf9c0', 'OpenSSL', '1.0.2a', 'CVE-2018-0734'),
('7c04aece-dc19-4d1f-890b-678e21eae76b', 'OpenSSL', '1.0.2a', 'CVE-2016-0797'),
('7c21b6da-2724-48c5-8301-5eaeeb436c40', 'OpenSSL', '1.1.0c', 'CVE-2018-0732 '),
('7d334345-098f-4964-b9f0-0fbedb7a58f4', 'OpenSSL', '1.0.2f', 'CVE-2016-2181'),
('7d589e35-57bd-475e-a933-d3ab255f71dc', 'OpenSSL', '1.0.2d', 'CVE-2017-3735'),
('7d9ed376-7c69-4fee-98f6-16a7f291cdc5', 'OpenSSL', '1.0.2', 'CVE-2016-2182 '),
('7e0e78ae-efd0-4063-91e1-f39fdaa488b8', 'OpenSSL', '1.1.0f', 'CVE-2018-0739 '),
('7e417b83-d2d3-44b3-b648-d355ea4c72f9', 'OpenSSL', '1.0.2d', 'CVE-2016-2179'),
('7e48d9b4-7874-4814-b619-508e23de2a9a', 'OpenSSL', '1.0.2l', 'CVE-2019-1559'),
('7ec78ba8-9ddc-488a-be62-419414aaa60f', 'OpenSSL', '1.0.2', 'CVE-2018-5407 '),
('80158bee-4799-48ca-9e73-19949b4ea775', 'OpenSSL', '1.0.2h', 'CVE-2016-7055 '),
('809da794-1738-4b2c-b16f-92158c48fd61', 'OpenSSL', '1.0.2c', 'CVE-2018-0732 '),
('80b96ad1-7fa9-4055-bb9d-f214760c56e6', 'OpenSSL', '1.0.2f', 'CVE-2016-6304 '),
('812e943f-b445-434a-87fb-e5ba1e7d71b0', 'OpenSSL', '1.1.1', 'CVE-2018-0734'),
('818f11ab-7025-4b90-9862-55debfddc5f9', 'OpenSSL', '1.0.2a', 'CVE-2016-2177 '),
('81d3e1fb-7d33-4c5f-8321-3ee6d93aa3a4', 'OpenSSL', '1.0.2b', 'CVE-2019-1559'),
('81d77bc2-1a9f-47fe-a9f6-10c9eacd5c66', 'OpenSSL', '1.0.2o', 'CVE-2018-0732 '),
('81e35a6b-2f22-4460-ab6b-6f698e0ddf14', 'OpenSSL', '1.0.2', 'CVE-2015-3197  '),
('81ebc064-42b1-40f5-b5d9-f9c4026dddb5', 'OpenSSL', '1.0.2a', 'CVE-2015-3194'),
('8243f4aa-4fd2-41c5-aa28-d82ca28d2edd', 'OpenSSL', '1.0.2f', 'CVE-2018-0734'),
('82d32991-6db9-44e1-9c5f-568dfb4461a6', 'OpenSSL', '1.0.2h', 'CVE-2019-1559'),
('82eab5ce-06f0-4544-897f-3d95e083bc82', 'OpenSSL', '1.0.2l', 'CVE-2017-3736'),
('83b0dfc8-b7ed-4dc8-b7fb-2726fa647c0a', 'OpenSSL', '1.0.2e', 'CVE-2019-1559'),
('83ef3497-416b-4f38-bc01-450ba766b583', 'OpenSSL', '1.0.2', 'CVE-2015-1789'),
('846663e6-9a97-4450-9ade-17f1f2b34b17', 'OpenSSL', '1.0.2f', 'CVE-2016-2179'),
('846b91dd-2d18-4ee5-bbf3-054384778d9e', 'OpenSSL', '1.0.2c', 'CVE-2016-6304 '),
('847ea314-93de-4994-9992-5fc191df6e4b', 'OpenSSL', '1.0.2a', 'CVE-2017-3736'),
('8538bb53-2750-47e5-b611-fef75907b8c8', 'OpenSSL', '1.0.2', 'CVE-2015-0288'),
('863c5e03-5c7b-4856-817c-9db666cf4876', 'OpenSSL', '1.0.2f', 'CVE-2017-3736'),
('8773b2a8-7ac1-49d6-91c3-d6fd8473fd0d', 'OpenSSL', '1.0.2f', 'CVE-2017-3731'),
('88883539-dcbf-47de-a6c1-20e06750fddd', 'OpenSSL', '1.0.2', 'CVE-2015-0207 '),
('8985d57f-cd21-413f-9f8e-8563e20a2bf3', 'OpenSSL', '1.0.2c', 'CVE-2018-0734'),
('89b0b1e8-52f0-4cbf-82da-9ff69ad677f4', 'OpenSSL', '1.1.0g', 'CVE-2017-3738 '),
('89d255bf-fc15-490f-9732-d8538fcd1991', 'OpenSSL', '1.0.2j', 'CVE-2017-3738 '),
('8a08e973-0ba1-40ac-83d6-70847ce51d71', 'OpenSSL', '1.0.2a', 'CVE-2015-1794  '),
('8a582af1-5631-48e6-9c9d-6248c10f5110', 'OpenSSL', '1.0.2g', 'CVE-2019-1559'),
('8b31e3c9-30ed-4ed6-a1db-c71a53574e28', 'OpenSSL', '1.1.0a', 'CVE-2018-0737'),
('8dfdbcb2-3724-4228-88a9-f6c7ab053cfd', 'OpenSSL', '1.0.2e', 'CVE-2018-0737'),
('8e2f9bca-af95-4d5d-8363-b0f467a8fd43', 'OpenSSL', '1.0.2c', 'CVE-2016-0705'),
('8e82fb3e-e4f3-4aa5-b8fe-54a588b5ccbf', 'OpenSSL', '1.0.2i', 'CVE-2018-0734'),
('8ec2510d-cec4-4ddb-b18f-f1dad8470581', 'OpenSSL', '1.0.2b', 'CVE-2017-3737'),
('8f8b55fd-6207-499e-be53-f9d52e0ec08c', 'OpenSSL', '1.1.0', 'CVE-2018-0739 '),
('8fe914f7-46c8-4271-9482-1d580d747d33', 'OpenSSL', '1.0.2a', 'CVE-2015-1788'),
('8ff63ca0-559e-4760-b242-65f60d4d5413', 'OpenSSL', '1.0.2b', 'CVE-2017-3738 '),
('90430b24-68d8-47e2-a0c4-6b34ebdd54da', 'OpenSSL', '1.0.2g', 'CVE-2016-2178'),
('91b49e9f-5e53-4459-b7d0-383f133db383', 'OpenSSL', '1.0.2c', 'CVE-2016-6303 '),
('91b4b444-3b3c-426d-929d-f21b7245656b', 'OpenSSL', '1.0.2b', 'CVE-2016-0800'),
('91b5e839-9431-43f0-84e4-fa60a44ae3e1', 'OpenSSL', '1.0.2b', 'CVE-2015-3193'),
('91db126e-5228-449d-b996-23687ea7b36f', 'OpenSSL', '1.0.2a', 'CVE-2016-2109'),
('91fe1f16-bda5-475a-b366-b93888dc8da7', 'OpenSSL', '1.1.0b', 'CVE-2018-0734'),
('9265558c-448a-4158-abd6-8adfdf0c0196', 'OpenSSL', '1.0.2e', 'CVE-2017-3732 '),
('92f2bf74-5942-411e-9ea9-d6ca43ea9534', 'OpenSSL', '1.0.2e', 'CVE-2016-2177 '),
('94a9dd48-5495-4024-858a-0fca438d74de', 'OpenSSL', '1.0.2m', 'CVE-2018-0737'),
('9526036c-c789-4f4e-899e-690f0168e3d7', 'OpenSSL', '1.0.2f', 'CVE-2016-2176'),
('957e3be0-26a8-432d-9149-9495a84c264b', 'OpenSSL', '1.0.2a', 'CVE-2016-2108 '),
('95dcaaf0-101d-47ad-9fd8-dbdfb804a834', 'OpenSSL', '1.1.0b', 'CVE-2018-5407 '),
('95fb7ac9-86e7-49f9-922b-73b99b707090', 'OpenSSL', '1.0.2e', 'CVE-2016-0701'),
('964ada79-8925-48c0-9a3e-2fc02a5e390e', 'OpenSSL', '1.0.2b', 'CVE-2016-6302 '),
('96655beb-4bd4-4d06-90e8-3fc18d12b5ce', 'OpenSSL', '1.0.2', 'CVE-2015-0293'),
('96b9c991-2f78-403a-8baf-76732e2686f4', 'OpenSSL', '1.1.0b', 'CVE-2018-0739 '),
('976d38ec-9394-4a33-a2e5-174d7c259c13', 'OpenSSL', '1.0.2f', 'CVE-2016-0800'),
('976e33fd-8e6b-416a-8f0e-9e9878a2534f', 'OpenSSL', '1.0.2', 'CVE-2018-0734'),
('97b9d957-70f6-4bed-a268-7c061b7ab67b', 'OpenSSL', '1.0.2e', 'CVE-2016-6303 '),
('97d2e9a4-5ec5-44b2-81e2-9770f9bb5de1', 'OpenSSL', '1.0.2c', 'CVE-2015-3196'),
('97efb7b5-7f4e-4fe7-9851-49020ccaad2e', 'OpenSSL', '1.0.2g', 'CVE-2018-0737'),
('986fb07f-9d23-4e87-aa93-93f5ce3c5ddb', 'OpenSSL', '1.0.2a', 'CVE-2017-3735'),
('98c82113-d048-4529-8ca1-17a82f45071e', 'OpenSSL', '1.0.2l', 'CVE-2017-3735'),
('990b8593-abf3-4ac8-8074-4d0e2e98572d', 'OpenSSL', '1.1.0c', 'CVE-2017-3730 '),
('9919e9ed-f659-48e2-85f7-434d1faef846', 'OpenSSL', '1.0.2c', 'CVE-2018-5407 '),
('9a07d703-81f8-4d0f-8f66-6a24e506e5cc', 'OpenSSL', '1.0.2a', 'CVE-2016-6306 '),
('9a31b6d3-5b1a-4daa-b6f7-cf16329d4e83', 'OpenSSL', '1.0.2h', 'CVE-2016-2177 '),
('9a591348-5c45-4f5e-895d-ad7ca36771ba', 'OpenSSL', '1.0.2d', 'CVE-2016-0702 '),
('9ac64fea-f75a-451f-9622-1bddb45045fd', 'OpenSSL', '1.1.0h', 'CVE-2018-0735'),
('9ad15b2e-ba55-449a-97ec-890ff5a19507', 'OpenSSL', '1.0.2a', 'CVE-2016-2176'),
('9b4d118a-ce68-454e-be37-e13d6e2b11e6', 'OpenSSL', '1.1.0f', 'CVE-2017-3735'),
('9b7af108-657b-402f-9bbc-f7954f8d089a', 'OpenSSL', '1.0.2j', 'CVE-2017-3736'),
('9c14617c-e77b-49a7-9acf-27e8265840f0', 'OpenSSL', '1.1.0f', 'CVE-2018-0733 '),
('9db31bdd-45da-46a5-b37a-0212c8491f3e', 'OpenSSL', '1.0.2d', 'CVE-2016-2106'),
('9e4d7c3d-3d81-447c-8982-8aeddb3cc9b0', 'OpenSSL', '1.0.2g', 'CVE-2017-3732 '),
('9f627c73-a7b7-47f9-80eb-969ae703a476', 'OpenSSL', '1.0.2d', 'CVE-2017-3732 '),
('9fdf23b5-09b0-4e41-8f39-cbe0da8c4e3a', 'OpenSSL', '1.0.2f', 'CVE-2016-2109'),
('a0a9c923-ab49-49da-9db4-d285f604817e', 'OpenSSL', '1.1.0a', 'CVE-2018-0733 '),
('a0e12d3e-4ab5-44e5-8039-002f6f1f5eb2', 'OpenSSL', '1.1.0', 'CVE-2018-0735'),
('a1a11e4e-0f7e-4826-b149-1f2c55cb20e3', 'OpenSSL', '1.0.2n', 'CVE-2018-0732 '),
('a1b6c249-2b98-4fae-96db-29bb5ff1d8b2', 'OpenSSL', '1.1.0', 'CVE-2016-6304 '),
('a1def03f-97d0-442a-88f7-73bd48f96876', 'OpenSSL', '1.1.0f', 'CVE-2018-0737'),
('a1fe6382-ddb9-45cb-af4d-2554a37d5180', 'OpenSSL', '1.1.0g', 'CVE-2018-0732 '),
('a227b126-655f-44be-9540-10d192835c0c', 'OpenSSL', '1.1.0f', 'CVE-2018-0735'),
('a3aab20b-4359-4360-951f-baf899c23504', 'OpenSSL', '1.0.2c', 'CVE-2016-2107'),
('a6156f82-c3f6-4651-8f67-5d8d16a07054', 'OpenSSL', '1.0.2c', 'CVE-2016-6302 '),
('a6848cf4-a2fa-4cb7-8b18-2c66b4e2180e', 'OpenSSL', '1.0.2b', 'CVE-2016-2179'),
('a70c2eab-2f1b-4f65-96bd-306368573db3', 'OpenSSL', '1.0.2d', 'CVE-2016-6302 '),
('a790e3a4-a00d-4d0e-aa86-0786a4d269f1', 'OpenSSL', '1.0.2e', 'CVE-2017-3738 '),
('a7ed9632-4e1a-4ab2-b2f0-50ebd7caebdd', 'OpenSSL', '1.0.2e', 'CVE-2018-0734'),
('a82355fb-b296-4ee2-93d6-10b0fa54f56b', 'OpenSSL', '1.0.2', 'CVE-2016-0800'),
('a863252f-ed4c-4763-bfee-5cea240c2041', 'OpenSSL', '1.0.2f', 'CVE-2018-0732 '),
('a8963c7e-3e08-4c11-bcac-e1892cd17afc', 'OpenSSL', '1.0.2', 'CVE-2017-3738 '),
('a94c023d-a32c-4d4a-9e99-9629cfe9148c', 'OpenSSL', '1.0.2b', 'CVE-2015-3194'),
('a9931ce6-3b3d-4058-a33c-c1c2e9fc9ff4', 'OpenSSL', '1.0.2d', 'CVE-2016-6304 '),
('aa721c85-986a-4e18-8571-19c2fb92a036', 'OpenSSL', '1.1.0', 'CVE-2017-3733'),
('ab1b2a89-540b-4433-9e7f-5a964306f9c4', 'OpenSSL', '1.0.2e', 'CVE-2016-2180'),
('abd487dc-949c-40e6-be9f-98a1897da54f', 'OpenSSL', '1.0.2h', 'CVE-2016-6303 '),
('abf9f933-0404-49ec-8533-865910e77e68', 'OpenSSL', '1.0.2h', 'CVE-2018-0739 '),
('ac208ce2-1387-4ee8-a3b7-cdb60db375e7', 'OpenSSL', '1.0.2k', 'CVE-2017-3738 '),
('ac5b1a45-1689-450d-9086-cca177628936', 'OpenSSL', '1.0.2c', 'CVE-2016-2109'),
('af160dc6-68da-410c-a0e8-7af6920ef7e4', 'OpenSSL', '1.1.0c', 'CVE-2018-0734'),
('afad7fce-b7e2-4b62-a1bd-767f4538808a', 'OpenSSL', '1.1.0f', 'CVE-2017-3736'),
('afc1bb99-76c5-4e9f-ab28-9b32f2cf9ef1', 'OpenSSL', '1.1.0b', 'CVE-2018-0737'),
('afec8bb6-67cd-4de1-8d24-39219dce9785', 'OpenSSL', '1.0.2i', 'CVE-2017-3731'),
('b0206d7d-10ea-4167-b07d-c78edf4688f9', 'OpenSSL', '1.0.2d', 'CVE-2016-2177 '),
('b0361f18-d8f4-47e8-b2d7-8e6c33b326a5', 'OpenSSL', '1.0.2d', 'CVE-2017-3731'),
('b0f48ef1-78f7-4f27-b4b1-125f1716b686', 'OpenSSL', '1.0.2c', 'CVE-2015-3193'),
('b105cde1-5cbe-4b5f-9808-a04c3604cb98', 'OpenSSL', '1.0.2e', 'CVE-2016-2181'),
('b1466868-f5d5-45c6-bcb0-4b64aae7130b', 'OpenSSL', '1.0.2', 'CVE-2016-2105'),
('b1a0f679-acbe-482b-8768-c85c01fa0228', 'OpenSSL', '1.0.2e', 'CVE-2016-0799 '),
('b2171d6e-5f70-4a74-8a0b-53fd8c85cb7d', 'OpenSSL', '1.0.2h', 'CVE-2016-2178'),
('b2a37353-198c-400f-af13-a184bca8db7d', 'OpenSSL', '1.0.2f', 'CVE-2016-0705'),
('b2ea5f1a-e391-44d9-8d61-f13b7df51baf', 'OpenSSL', '1.0.2c', 'CVE-2016-0799 '),
('b3054123-6c2c-4e3f-ba15-4267247d7879', 'OpenSSL', '1.0.2', 'CVE-2016-2106'),
('b314bb87-1c69-4ce4-96db-0fd30ab463ff', 'OpenSSL', '1.0.2', 'CVE-2015-0286 '),
('b398237b-a9a0-4996-b4ab-073aa67335f5', 'OpenSSL', '1.0.2k', 'CVE-2018-0732 '),
('b50b1f74-0423-4301-a65b-6e9f7b619f10', 'OpenSSL', '1.0.2', 'CVE-2016-2109'),
('b51db812-83fa-4786-806d-b9f1aa541ba8', 'OpenSSL', '1.0.2b', 'CVE-2016-0701'),
('b5a9c4c9-2019-471d-81d8-2d33485c0b37', 'OpenSSL', '1.0.2d', 'CVE-2016-0797'),
('b5e4c0c1-7234-4ed2-a330-21550d4dfae3', 'OpenSSL', '1.1.0f', 'CVE-2018-5407 '),
('b607cd98-ac38-43b1-b202-626a271a51d0', 'OpenSSL', '1.1.0b', 'CVE-2016-7055 '),
('b6396c34-f790-4324-b551-d00155baa11a', 'OpenSSL', '1.0.2o', 'CVE-2018-0737'),
('b672a01f-40c2-4666-af41-c709264f645b', 'OpenSSL', '1.0.2', 'CVE-2016-0704'),
('b67e9d3f-6a90-4d22-8574-20fe013bd1b9', 'OpenSSL', '1.0.2', 'CVE-2016-6304 '),
('b690155a-9b4c-4f50-8ea0-bf0405c6e874', 'OpenSSL', '1.1.0f', 'CVE-2017-3738 '),
('b6981782-fbd5-4024-b343-0cfc2db2f376', 'OpenSSL', '1.1.0', 'CVE-2016-6308'),
('b77e32f1-05b5-42f8-afa2-37dea730a067', 'OpenSSL', '1.0.2e', 'CVE-2016-0800'),
('b906e9a4-bfe7-407a-b580-8844d6dadae0', 'OpenSSL', '1.1.0c', 'CVE-2017-3735'),
('b91a300f-7460-47f6-af1d-9f14bc546110', 'OpenSSL', '1.0.2l', 'CVE-2018-0732 '),
('b9a7c4b6-9636-4803-8b8d-f21affbe1ad1', 'OpenSSL', '1.0.2c', 'CVE-2018-0737'),
('b9e59d3b-ddb4-41e5-955f-755c16cc4e8f', 'OpenSSL', '1.0.2', 'CVE-2015-3196'),
('bb2a3198-4597-4db7-a2df-85965711b295', 'OpenSSL', '1.0.2e', 'CVE-2018-0732 '),
('bb58f143-9acb-4dcf-b9e7-12c6e5c34fca', 'OpenSSL', '1.1.0h', 'CVE-2018-0732 '),
('bb9b3fd4-3f70-42bd-9090-c61648f591ad', 'OpenSSL', '1.0.2f', 'CVE-2018-0739 '),
('bc030e28-05ef-4dd6-8955-2f5c85b98fb3', 'OpenSSL', '1.0.2b', 'CVE-2018-0732 '),
('bc4f3acb-28f1-4cca-a661-c41ecee86077', 'OpenSSL', '1.0.2h', 'CVE-2016-6306 '),
('bcfe4939-7cbf-46d3-b0ff-b9ec406f6032', 'OpenSSL', '1.0.2a', 'CVE-2015-3193'),
('bd9b0f1a-b2c5-489c-95ae-ecfa3f151b2e', 'OpenSSL', '1.0.2g', 'CVE-2016-2180'),
('be571c10-779f-46ad-8e12-01008e55a251', 'OpenSSL', '1.0.2a', 'CVE-2016-0701'),
('be7f2d46-c53a-42ed-92bc-c63e22bf930b', 'OpenSSL', '1.0.2', 'CVE-2017-3731'),
('bfd40ec3-479e-49a4-8bed-9fb063692ea5', 'OpenSSL', '1.0.2', 'CVE-2015-1791'),
('c00c27e4-b24c-4ce6-90d8-8cb39be295f5', 'OpenSSL', '1.0.2a', 'CVE-2016-6304 '),
('c0ace5bf-fbed-40c7-9466-7528d4a3d45c', 'OpenSSL', '1.0.2a', 'CVE-2016-0799 '),
('c0c5445c-5187-405e-9bbb-6837497a6403', 'OpenSSL', '1.1.0h', 'CVE-2018-0734'),
('c0ef6384-64ba-4ce0-9d17-df2291c1da6a', 'OpenSSL', '1.0.2j', 'CVE-2018-5407 '),
('c10a5f37-3221-4d28-834c-e95f8895ee28', 'OpenSSL', '1.0.2f', 'CVE-2016-0799 '),
('c12e3119-d580-4a6d-a9b4-eca56a45bfdd', 'OpenSSL', '1.1.0b', 'CVE-2018-0732 '),
('c27810ac-8481-486d-853d-310e2aede6d3', 'OpenSSL', '1.0.2l', 'CVE-2018-5407 '),
('c46adae5-8f0b-44e6-9d1c-e1575ec5f116', 'OpenSSL', '1.0.2i', 'CVE-2018-5407 '),
('c4f4c341-d1b6-4772-9c5d-f79c34bf97ab', 'OpenSSL', '1.0.2p', 'CVE-2018-0734'),
('c50dd974-a8fd-491c-a0a2-261d769cc6a1', 'OpenSSL', '1.0.2b', 'CVE-2015-1793'),
('c535076f-e06a-4e21-a18e-062e39634dca', 'OpenSSL', '1.1.0e', 'CVE-2018-0739 '),
('c6320406-9c6e-4522-94b4-bb7bceac6b1c', 'OpenSSL', '1.1.0a', 'CVE-2018-0739 '),
('c6896f01-9cf6-4f09-b03d-4f6dce8e8ee7', 'OpenSSL', '1.0.2m', 'CVE-2018-0739 '),
('c6b87d52-6b0a-4a4f-b675-19776e40e04c', 'OpenSSL', '1.0.2', 'CVE-2015-1792'),
('c6d3b5f2-30da-48f2-8dec-f983eed08ada', 'OpenSSL', '1.1.0', 'CVE-2018-0733 '),
('c77f0e18-c9a3-40c5-9f59-e20ca18a74fd', 'OpenSSL', '1.0.2h', 'CVE-2017-3735'),
('c7d411b9-840b-4ec3-adf6-4c26a40efbaf', 'OpenSSL', '1.1.0a', 'CVE-2017-3736'),
('c84805d0-268f-4f83-95df-83b366590c25', 'OpenSSL', '1.0.2n', 'CVE-2018-0734'),
('c89b7dca-9885-448e-ac5f-9d02a4fef938', 'OpenSSL', '1.0.2e', 'CVE-2016-2106'),
('c91149d7-78b2-4e69-b83b-20cfdc61a31a', 'OpenSSL', '1.0.2b', 'CVE-2016-2177 '),
('c920bba5-9e09-498d-9034-3bc427f8ab9d', 'OpenSSL', '1.0.2g', 'CVE-2017-3735'),
('c9d44a29-57d0-482c-adf1-6e8f26fbc475', 'OpenSSL', '1.0.2a', 'CVE-2016-0800'),
('ca1ab5ca-201d-413c-a9eb-eab39543f056', 'OpenSSL', '1.0.2e', 'CVE-2017-3736'),
('cad5941d-f695-479f-b9a3-eecdcc6a8d7c', 'OpenSSL', '1.0.2b', 'CVE-2016-0797'),
('cb8ff090-f96f-438c-82b4-bc9aab75071f', 'OpenSSL', '1.0.2f', 'CVE-2016-2107'),
('cc1911b2-b2ff-44a1-8337-72f316cdd766', 'OpenSSL', '1.0.2f', 'CVE-2016-2105'),
('ce5b06b5-b6ff-4344-8212-005a1b8cb277', 'OpenSSL', '1.0.2a', 'CVE-2017-3731'),
('cede8927-d59a-45ef-8505-1745ddaa9dac', 'OpenSSL', '1.0.2d', 'CVE-2017-3736'),
('cf289627-1814-4953-b48d-9d261e35ec3b', 'OpenSSL', '1.0.2e', 'CVE-2016-2109'),
('cfab105d-b67b-40d6-80e9-de86b42427e6', 'OpenSSL', '1.0.2b', 'CVE-2016-2105'),
('d0556139-da66-480a-943b-90d8cd8ff794', 'OpenSSL', '1.1.0b', 'CVE-2017-3738 '),
('d1c2b600-a585-4730-8eb5-43647d68176c', 'OpenSSL', '1.0.2b', 'CVE-2018-5407 '),
('d2324f1a-38c0-4bd8-8223-9ddfa14a08cd', 'OpenSSL', '1.0.2', 'CVE-2015-1788'),
('d2fe9699-015f-4082-98d9-0f4b14f614dd', 'OpenSSL', '1.0.2i', 'CVE-2018-0739 '),
('d30679a6-733c-4220-9294-fc316ad1e5c5', 'OpenSSL', '1.0.2', 'CVE-2016-2179'),
('d5280c29-026b-4c32-a03e-f77e374a1809', 'OpenSSL', '1.1.0b', 'CVE-2017-3733'),
('d5e3ab2e-d938-456d-8892-0c6bad9d4555', 'OpenSSL', '1.1.0b', 'CVE-2017-3730 '),
('d81fe3fc-ebf2-41df-99e3-9be0bbe823a0', 'OpenSSL', '1.0.2d', 'CVE-2017-3737'),
('d8ebdda6-f9dc-4b84-b3eb-07135d3350d5', 'OpenSSL', '1.0.2', 'CVE-2016-2177 '),
('d93a1080-7f5a-4dda-8e34-bcedbb5277d3', 'OpenSSL', '1.0.2d', 'CVE-2015-3194'),
('d94e12a0-b71a-451a-9477-b181e544a91a', 'OpenSSL', '1.1.0b', 'CVE-2017-3731'),
('dafc04c9-8136-4228-bdae-5f5c5a36584d', 'OpenSSL', '1.0.2q', 'CVE-2019-1559'),
('db55e061-c412-4e98-aa90-9e90742485fb', 'OpenSSL', '1.0.2b', 'CVE-2016-2108 '),
('dbbe3c42-4c49-49ab-ac4c-00d5f345fdcf', 'OpenSSL', '1.0.2k', 'CVE-2018-5407 '),
('dbc62c74-90a2-43ec-b9d0-1f56213566d8', 'OpenSSL', '1.1.0', 'CVE-2017-3736'),
('dcbfbec3-226d-4ab9-aa19-676359675e8b', 'OpenSSL', '1.0.2c', 'CVE-2015-1794  '),
('dd7baa1a-1bbc-4b2b-b2d9-ffdf150c64d1', 'OpenSSL', '1.1.0a', 'CVE-2017-3730 '),
('ddb7e2c7-d713-4792-ae70-6f5795534b4a', 'OpenSSL', '1.0.2f', 'CVE-2016-6306 '),
('de4f159b-66b2-4977-8d06-ee425c12821f', 'OpenSSL', '1.0.2e', 'CVE-2018-0739 '),
('df027722-4d42-4a63-af52-48e3057d5926', 'OpenSSL', '1.0.2i', 'CVE-2017-3735'),
('df3f1579-ee3d-429c-b8d2-623162d8f7f8', 'OpenSSL', '1.0.2a', 'CVE-2015-1789'),
('e025ce16-c77f-4d51-b2fe-22500c8e9d1b', 'OpenSSL', '1.0.2d', 'CVE-2019-1559'),
('e02ada50-1345-4dc8-bd61-81ba157c3669', 'OpenSSL', '1.1.0a', 'CVE-2016-7053 '),
('e0322b83-8393-4c82-9fe8-aa80f8cc7746', 'OpenSSL', '1.0.2', 'CVE-2015-1790 '),
('e0d282c5-2232-422d-8deb-73e66cc99d4e', 'OpenSSL', '1.1.0', 'CVE-2017-3732 '),
('e33a2c47-5a2a-479b-8a88-5e1a74f6281e', 'OpenSSL', '1.1.0c', 'CVE-2018-0733 '),
('e3cb3b7e-495c-4cb1-848f-383ae7bbb1c2', 'OpenSSL', '1.0.2f', 'CVE-2016-2178'),
('e3f06afe-bf18-4624-b8b8-b5a100057389', 'OpenSSL', '1.0.2', 'CVE-2016-0797'),
('e47bbdb9-6ab1-4aae-9dc0-2e618b2acfb9', 'OpenSSL', '1.0.2h', 'CVE-2018-0734'),
('e4ad8712-faa6-4e5f-8050-294dd4b4d825', 'OpenSSL', '1.0.2c', 'CVE-2019-1559'),
('e5263acd-6d65-4631-b378-f5fcc3002860', 'OpenSSL', '1.0.2a', 'CVE-2018-0737'),
('e5291ecd-0dca-48e5-849e-e7c2bd7616e7', 'OpenSSL', '1.0.2e', 'CVE-2016-6304 '),
('e5c0187c-4f6d-412f-9d3d-56fe8f4e9957', 'OpenSSL', '1.0.2a', 'CVE-2018-0732 '),
('e5e198bd-7546-4aca-b72f-25ca7816990a', 'OpenSSL', '1.1.0a', 'CVE-2017-3733'),
('e63678ee-04df-4326-9de0-0de2248c0bbc', 'OpenSSL', '1.1.0d', 'CVE-2017-3735'),
('e6acc599-022a-4b69-aaf4-9909c0577fe7', 'OpenSSL', '1.0.2c', 'CVE-2017-3736'),
('e6ea1f93-3e57-410f-84bf-fdf8ab5d60da', 'OpenSSL', '1.0.2', 'CVE-2015-3194'),
('e6fae59f-3196-421b-8b02-8d9703c02278', 'OpenSSL', '1.1.0c', 'CVE-2017-3738 '),
('e70430fb-ec6a-4286-b28a-9cf288c5751d', 'OpenSSL', '1.0.2b', 'CVE-2016-0799 '),
('e727f121-cac5-4ab0-ba77-ffee2b820bc8', 'OpenSSL', '1.0.2k', 'CVE-2019-1559'),
('e8f7d9a1-cb85-4d79-b355-060c6638c7a1', 'OpenSSL', '1.1.0e', 'CVE-2018-0735'),
('ea21aa1d-dd44-4073-bc2a-80bceb554a5b', 'OpenSSL', '1.1.0a', 'CVE-2018-0735'),
('ea3d264a-66d0-4d3a-82cd-3b4dd8de28d0', 'OpenSSL', '1.0.2d', 'CVE-2016-2181'),
('ea42f931-d9aa-4a28-9d97-5a4d1352a021', 'OpenSSL', '1.0.2d', 'CVE-2016-2105'),
('ea5b1f9d-62a1-43f6-9928-6e307cee9431', 'OpenSSL', '1.0.2b', 'CVE-2016-2180'),
('eafdb9e8-d5c3-456c-a41d-0ac6347af3a6', 'OpenSSL', '1.0.2f', 'CVE-2018-0737'),
('eb853199-4b6e-4ae9-8f63-05a49634c42e', 'OpenSSL', '1.1.0d', 'CVE-2018-0734'),
('eb9a31e5-9401-4ffd-b72c-39dc2ba98640', 'OpenSSL', '1.0.2d', 'CVE-2018-0737'),
('ec099e33-f57e-4d0d-816c-e17be0524611', 'OpenSSL', '1.0.2h', 'CVE-2018-0737'),
('ec28fcb2-6dcb-4e92-a7ec-f28bcbe1de51', 'OpenSSL', '1.0.2g', 'CVE-2016-2106'),
('ecb3c777-b294-4ca0-8893-fc6557893d44', 'OpenSSL', '1.0.2f', 'CVE-2016-2180'),
('ecdb13dd-cf74-4a60-94d3-f7f7cf801723', 'OpenSSL', '1.0.2a', 'CVE-2016-0798 '),
('ece42c00-699d-423e-9a48-917276707989', 'OpenSSL', '1.0.2k', 'CVE-2018-0737'),
('ecf6ccd6-589a-4e21-93fe-5b3194ae2d30', 'OpenSSL', '1.1.0a', 'CVE-2017-3732 '),
('ed8d0632-9918-4dc5-a8ee-e8da6b32f18b', 'OpenSSL', '1.0.2a', 'CVE-2016-0705'),
('ee456389-b026-42b7-b60b-e23b26b4aafd', 'OpenSSL', '1.0.2', 'CVE-2015-0289'),
('ef221e71-2c04-4ab8-836e-5a7424c96c9a', 'OpenSSL', '1.0.2b', 'CVE-2015-3195'),
('ef2fe64a-3a1e-4c13-96f7-0ff4e9fc5dea', 'OpenSSL', '1.1.0', 'CVE-2016-6307 '),
('efc997de-443b-43a6-aa24-db41150734ee', 'OpenSSL', '1.0.2e', 'CVE-2016-0705'),
('efe1099a-773d-4e4e-8d96-5c19fdebab3a', 'OpenSSL', '1.0.2', 'CVE-2016-7055 '),
('eff63517-f081-4b33-91c2-45b78722949c', 'OpenSSL', '1.0.2c', 'CVE-2016-0797'),
('f2be73ed-9c57-48e8-9271-5df2bd7d55cc', 'OpenSSL', '1.1.0d', 'CVE-2018-0737'),
('f3da89c2-5b64-4841-9ec2-b9c83507b48a', 'OpenSSL', '1.1.0d', 'CVE-2017-3738 '),
('f4931bb4-9d5f-47e0-a5dd-44e8bd349690', 'OpenSSL', '1.0.2d', 'CVE-2018-0732 '),
('f5137a0f-a93d-448b-ab7e-5908957d88a8', 'OpenSSL', '1.1.0', 'CVE-2018-0732 '),
('f59562c9-f550-4693-a57d-beea59258da5', 'OpenSSL', '1.0.2h', 'CVE-2016-6304 '),
('f5c4f6e1-83bc-4b3b-9905-589a1df0c3e7', 'OpenSSL', '1.0.2f', 'CVE-2016-7055 '),
('f7343985-8012-45fd-9c4f-98154b7e09a6', 'OpenSSL', '1.0.2b', 'CVE-2018-0739 '),
('f85f11a8-0859-48ce-b424-b3dea75540b6', 'OpenSSL', '1.0.2d', 'CVE-2016-6303 '),
('f8641554-0e69-4c5c-bc60-cb0712c672aa', 'OpenSSL', '1.0.2d', 'CVE-2016-0798 '),
('f985b147-6ad1-4a5b-be38-e816feadcb4f', 'OpenSSL', '1.1.0b', 'CVE-2017-3732 '),
('f9b9cc87-0642-49c5-bd11-4c04e680ac34', 'OpenSSL', '1.0.2f', 'CVE-2016-6302 '),
('fa27f4e8-de29-48cd-9100-b449d6e820f0', 'OpenSSL', '1.1.0a', 'CVE-2016-6309'),
('fa6725a4-a664-4a8a-93b2-c4568ccd1de4', 'OpenSSL', '1.0.2e', 'CVE-2016-0797'),
('fb0344bf-0060-494c-b7be-dd1944f7c696', 'OpenSSL', '1.0.2e', 'CVE-2016-2107'),
('fb5b037f-2475-4804-a305-a326fae14233', 'OpenSSL', '1.1.0h', 'CVE-2018-5407 '),
('fbb37d53-0927-435f-be98-8f111403caa7', 'OpenSSL', '1.0.2a', 'CVE-2015-1792'),
('fc1c2f9c-0b0c-4eb8-b66d-3a76551901fb', 'OpenSSL', '1.1.0c', 'CVE-2017-3736'),
('fc6007f6-f915-47df-b1bb-7016c28e034b', 'OpenSSL', '1.0.2n', 'CVE-2019-1559'),
('fc7f384c-99dd-4274-8b3d-3a9dcfe75a3a', 'OpenSSL', '1.0.2g', 'CVE-2018-5407 '),
('fd34000c-5b02-4a49-b409-f5551b5befd2', 'OpenSSL', '1.0.2g', 'CVE-2016-6303 '),
('ffb6f6aa-99b2-44af-bcfb-173d30185617', 'OpenSSL', '1.1.0e', 'CVE-2017-3735'),
('ffc96237-46e0-4072-a13e-898c5c8f5c19', 'OpenSSL', '1.1.0d', 'CVE-2018-0735');

-- --------------------------------------------------------

--
-- 表的结构 `platform_risk`
--

CREATE TABLE `platform_risk` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `payload` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存平台和代码安全风险定义';

--
-- 转存表中的数据 `platform_risk`
--

INSERT INTO `platform_risk` (`id`, `name`, `description`, `level`, `platform`, `payload`) VALUES
('0c89a975-60dd-11e9-95d2-eb78988f066e', 'Android SSL弱校验风险', 'App在进行SSL通信时，未对服务器证书进行校验，可导致中间人攻击并泄漏传输的敏感数据。', 'High', 'Android', 'AndroidService.ssl_pinning'),
('48c0099d-60dd-11e9-95d2-eb78988f066e', 'Linux用户帐户风险', '固件中存在可登录的Linux用户，可能导致攻击者从本地接口或远程方式取得系统权限。', 'Low', 'Linux', 'FwService.linux_shadow'),
('f647b3ba-60dc-11e9-95d2-eb78988f066e', 'Android组件暴露风险', 'App在AndroidManifest.xml中没有正确设置四大组件的权限，暴露不必要的组件可能导致隐私信息泄漏给第三方App。', 'Medium', 'Android', 'AndroidService.exported');

-- --------------------------------------------------------

--
-- 表的结构 `platform_risk_category`
--

CREATE TABLE `platform_risk_category` (
  `id` varchar(36) NOT NULL,
  `category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关联表platform_risk和分析类型';

--
-- 转存表中的数据 `platform_risk_category`
--

INSERT INTO `platform_risk_category` (`id`, `category`) VALUES
('0c89a975-60dd-11e9-95d2-eb78988f066e', 'Android'),
('48c0099d-60dd-11e9-95d2-eb78988f066e', 'Firmware'),
('f647b3ba-60dc-11e9-95d2-eb78988f066e', 'Android');

-- --------------------------------------------------------

--
-- 表的结构 `protocol`
--

CREATE TABLE `protocol` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `layer` int(11) NOT NULL COMMENT '2-5',
  `description` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 COMMENT='保存各种类型的协议';

-- --------------------------------------------------------

--
-- 表的结构 `protocol_risk`
--

CREATE TABLE `protocol_risk` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `protocol` varchar(20) DEFAULT NULL,
  `payload` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存协议安全风险定义';

-- --------------------------------------------------------

--
-- 表的结构 `third_library`
--

CREATE TABLE `third_library` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `latest_version` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存第三方库的种类';

--
-- 转存表中的数据 `third_library`
--

INSERT INTO `third_library` (`id`, `name`, `description`, `latest_version`) VALUES
('22db7316-be37-4728-b8f8-47f30a8b4fff', 'OpenSSL', 'OpenSSL是一个用于加密、数字签名和实现PKI体系的库，是网络安全最基础的第三方库。', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('root', 'root', 1);

--
-- 转储表的索引
--

--
-- 表的索引 `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `ix_auth_username` (`username`,`authority`) USING BTREE;

--
-- 表的索引 `cve`
--
ALTER TABLE `cve`
  ADD PRIMARY KEY (`cve_num`);

--
-- 表的索引 `cve_category`
--
ALTER TABLE `cve_category`
  ADD PRIMARY KEY (`cve_num`);

--
-- 表的索引 `library_risk`
--
ALTER TABLE `library_risk`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_library_risk_cve` (`cve_num`),
  ADD KEY `FK_library_risk_third_part_library` (`name`);

--
-- 表的索引 `platform_risk`
--
ALTER TABLE `platform_risk`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `platform_risk_category`
--
ALTER TABLE `platform_risk_category`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `third_library`
--
ALTER TABLE `third_library`
  ADD PRIMARY KEY (`id`),
  ADD KEY `name` (`name`);

--
-- 表的索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`) USING BTREE;

--
-- 限制导出的表
--

--
-- 限制表 `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- 限制表 `cve_category`
--
ALTER TABLE `cve_category`
  ADD CONSTRAINT `FK_cve_category_cve` FOREIGN KEY (`cve_num`) REFERENCES `cve` (`cve_num`);

--
-- 限制表 `library_risk`
--
ALTER TABLE `library_risk`
  ADD CONSTRAINT `FK_library_risk_cve` FOREIGN KEY (`cve_num`) REFERENCES `cve` (`cve_num`),
  ADD CONSTRAINT `FK_library_risk_third_part_library` FOREIGN KEY (`name`) REFERENCES `third_library` (`name`);

--
-- 限制表 `platform_risk_category`
--
ALTER TABLE `platform_risk_category`
  ADD CONSTRAINT `FK__platform_risk` FOREIGN KEY (`id`) REFERENCES `platform_risk` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
