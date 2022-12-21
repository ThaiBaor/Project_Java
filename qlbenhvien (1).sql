-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 21, 2022 at 05:16 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlbenhvien`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_benhnhan`
--

DROP TABLE IF EXISTS `tbl_benhnhan`;
CREATE TABLE IF NOT EXISTS `tbl_benhnhan` (
  `hoten` varchar(50) NOT NULL,
  `namsinh` int(11) NOT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `mabn` varchar(20) NOT NULL,
  `bhyt` varchar(20) NOT NULL,
  `nhanvien` varchar(20) NOT NULL,
  PRIMARY KEY (`mabn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_benhnhan`
--

INSERT INTO `tbl_benhnhan` (`hoten`, `namsinh`, `gioitinh`, `mabn`, `bhyt`, `nhanvien`) VALUES
('Nguyễn Anh ', 1999, 1, 'BN111', '984960342234', 'KB002'),
('Lê Văn Bình', 1967, 1, 'BN112', '675938204725', 'CC001'),
('Trần Cao Hải', 2018, 1, 'BN004', '987364334768', 'NTH002'),
('Lê Xuân Tiến', 1971, 1, 'BN345', '093748594312', 'NGTH001'),
('Lê Hải Yến', 2007, 0, 'BN774', '986374839482', 'NTH001'),
('Đào Việt Hùng', 1996, 1, 'BN743', '985637483032', 'NTH003'),
('Nguyễn Gia Hân', 1998, 0, 'BN423', '905635363434', 'KB001'),
('Lê Khánh Hân', 2017, 0, 'BN203', '345675789054', 'CC002'),
('Hồ Ngọc Khả Như', 2002, 0, 'BN006', '837539453932', 'NTH001'),
('Lê Hải Yến', 1997, 0, 'BN325', '904578456844', 'NTH003');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_khoa`
--

DROP TABLE IF EXISTS `tbl_khoa`;
CREATE TABLE IF NOT EXISTS `tbl_khoa` (
  `makhoa` varchar(20) NOT NULL,
  `tenkhoa` varchar(20) NOT NULL,
  PRIMARY KEY (`makhoa`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_khoa`
--

INSERT INTO `tbl_khoa` (`makhoa`, `tenkhoa`) VALUES
('KB', 'Khám Bệnh'),
('NTH', 'Nội tổng hợp'),
('CC', 'Cấp cứu'),
('NGTH', 'Ngoại tổng hợp');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_nhanvien`
--

DROP TABLE IF EXISTS `tbl_nhanvien`;
CREATE TABLE IF NOT EXISTS `tbl_nhanvien` (
  `hoten` varchar(50) NOT NULL,
  `namsinh` int(11) NOT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `manv` varchar(20) NOT NULL,
  `luong` int(11) NOT NULL,
  `chucdanh` varchar(20) NOT NULL,
  `Khoa` varchar(20) NOT NULL,
  PRIMARY KEY (`manv`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_nhanvien`
--

INSERT INTO `tbl_nhanvien` (`hoten`, `namsinh`, `gioitinh`, `manv`, `luong`, `chucdanh`, `Khoa`) VALUES
('Nguyễn Văn Kiên', 1990, 1, 'KB001', 10000000, 'Bác sĩ', 'Khám bệnh'),
('Nguyễn Thị Mai', 1972, 0, 'CC001', 10000000, 'Bác sĩ', 'Cấp cứu'),
('Nguyễn Thị Tuyết', 1995, 0, 'CC003', 7000000, 'Y tá', 'Cấp cứu'),
('Trần Tuấn Sơn', 1988, 1, 'CC002', 15000000, 'Dược sĩ', 'Cấp cứu'),
('Trần Anh Thư', 1991, 0, 'KB002', 5000000, 'Dược sĩ', 'Khám Bệnh'),
('Trương Thị Mai Anh', 1994, 0, 'KB003', 6000000, 'Điều dưỡng', 'Khám Bệnh'),
('Đào Thanh Hải', 1969, 1, 'NTH001', 12000000, 'Bác sĩ', 'Nội tổng hợp'),
('Nguyễn Bích Trân', 1990, 0, 'NTH002', 6000000, 'Y tá', 'Nội tổng hợp'),
('Lưu Ngọc Mai', 1992, 0, 'NTH003', 7000000, 'Y tá', 'Nội tổng hợp'),
('Đỗ Duy Thắng', 1976, 1, 'NGTH001', 13000000, 'Bác sĩ', 'Ngoại tổng hợp');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
