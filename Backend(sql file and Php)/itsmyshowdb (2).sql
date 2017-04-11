-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2016 at 07:28 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `itsmyshowdb`
--
CREATE DATABASE IF NOT EXISTS `itsmyshowdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `itsmyshowdb`;

-- --------------------------------------------------------

--
-- Table structure for table `booking_db`
--

DROP TABLE IF EXISTS `booking_db`;
CREATE TABLE `booking_db` (
  `book_id` int(11) NOT NULL,
  `booking_email` varchar(255) NOT NULL,
  `booking_movie` varchar(255) NOT NULL,
  `booking_card` varchar(255) NOT NULL,
  `booking_movietime` varchar(255) NOT NULL,
  `booking_fare` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_db`
--

INSERT INTO `booking_db` (`book_id`, `booking_email`, `booking_movie`, `booking_card`, `booking_movietime`, `booking_fare`) VALUES
(1, 'sathviknarasimha@gmail.com', 'rustom', '1865872569875', '10:00', 50),
(2, 'sathviknarasimha@gmail.com', 'rustom', '7d093ec2da7935103a0602450b2e220f', '10:00', 50),
(3, 'sathviknarasimha@gmail.com', 'rustom', '7d093ec2da7935103a0602450b2e220f', '10:00', 50),
(4, 'a', '', 'aa15bf261b1997e6e630247097a1f69b', '10:00', 0),
(5, 'a', 'Rustom', 'b9424603543b9c17090d28637c349070', '10:00', 0),
(6, 'a', 'Rustom', '9b957da1b735aeb2626392a84496e345', '10:00', 0),
(7, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 0),
(8, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 0),
(9, 'a', 'Rustom', '2230471da5d8bd3f5d4b607bbcb34605', '10:00', 0),
(10, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 0),
(11, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 0),
(12, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 0),
(13, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 120),
(14, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 60),
(15, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 300),
(16, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 450),
(17, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '1:00', 180),
(18, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '5:00', 300),
(19, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 750),
(20, 'a', 'Rustom', '6eceab862211170b17b9d316619df6a9', '10:00', 450),
(21, 'a', 'Premam', '522c88530c38f56f72e6cda1871e04cf', '10:00', 180),
(22, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 180),
(23, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 120),
(24, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 120),
(25, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 450),
(26, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 60),
(27, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '10:00', 60),
(28, 'a', 'Rustom', '522c88530c38f56f72e6cda1871e04cf', '5:00', 300),
(29, 'a', 'Rustom', '9b957da1b735aeb2626392a84496e345', '10:00', 60);

-- --------------------------------------------------------

--
-- Table structure for table `drc_fetch`
--

DROP TABLE IF EXISTS `drc_fetch`;
CREATE TABLE `drc_fetch` (
  `b_id` int(11) NOT NULL,
  `time_1` int(255) NOT NULL DEFAULT '2',
  `time_2` int(11) NOT NULL DEFAULT '2',
  `time_3` int(11) DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drc_fetch`
--

INSERT INTO `drc_fetch` (`b_id`, `time_1`, `time_2`, `time_3`) VALUES
(1, 1, 2, 2),
(2, 1, 2, 2),
(3, 1, 2, 2),
(4, 2, 2, 2),
(5, 2, 2, 2),
(6, 2, 2, 2),
(7, 2, 2, 2),
(8, 2, 2, 2),
(9, 2, 2, 2),
(10, 2, 2, 2),
(11, 1, 2, 2),
(12, 2, 2, 2),
(13, 2, 2, 2),
(14, 2, 2, 2),
(15, 2, 2, 2),
(16, 2, 2, 2),
(17, 2, 2, 2),
(18, 2, 2, 2),
(19, 2, 2, 2),
(20, 1, 2, 2),
(21, 2, 2, 2),
(22, 2, 2, 2),
(23, 2, 2, 2),
(24, 2, 2, 2),
(25, 2, 2, 2),
(26, 2, 2, 2),
(27, 2, 2, 2),
(28, 1, 2, 2),
(29, 1, 2, 2),
(30, 1, 2, 2),
(31, 2, 2, 2),
(32, 2, 2, 2),
(33, 2, 2, 2),
(34, 2, 2, 2),
(35, 2, 2, 2),
(36, 2, 2, 2),
(37, 2, 2, 2),
(38, 2, 2, 2),
(39, 2, 2, 2),
(40, 2, 2, 2),
(41, 2, 2, 2),
(42, 2, 2, 2),
(43, 2, 2, 2),
(44, 2, 2, 2),
(45, 2, 2, 2),
(46, 2, 2, 2),
(47, 2, 2, 2),
(48, 2, 2, 2),
(49, 2, 2, 2),
(50, 2, 2, 2),
(51, 2, 2, 2),
(52, 2, 2, 2),
(53, 2, 2, 2),
(54, 2, 2, 2),
(55, 2, 2, 2),
(56, 2, 2, 2),
(57, 2, 2, 2),
(58, 2, 2, 2),
(59, 2, 2, 2),
(60, 2, 2, 2),
(61, 2, 2, 2),
(62, 2, 2, 2),
(63, 2, 2, 2),
(64, 2, 2, 2),
(65, 2, 2, 2),
(66, 2, 2, 2),
(67, 2, 2, 2),
(68, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `event_fetch`
--

DROP TABLE IF EXISTS `event_fetch`;
CREATE TABLE `event_fetch` (
  `e_id` int(11) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `organizer` varchar(255) NOT NULL,
  `e_place` varchar(255) NOT NULL,
  `e_date` varchar(255) NOT NULL,
  `e_time` varchar(255) NOT NULL,
  `e_fare` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event_fetch`
--

INSERT INTO `event_fetch` (`e_id`, `event_name`, `organizer`, `e_place`, `e_date`, `e_time`, `e_fare`) VALUES
(1, 'comedy nights', 'kapil sharma', 'mysore', '23/11/16', '8:00 PM', 50),
(2, 'infotsav', 'SJCE MCA', 'MYSORE SJCE Campus', '11/11/2016', '9:00 AM', 60);

-- --------------------------------------------------------

--
-- Table structure for table `inox_fetch`
--

DROP TABLE IF EXISTS `inox_fetch`;
CREATE TABLE `inox_fetch` (
  `b_id` int(11) NOT NULL,
  `time_1` int(11) NOT NULL DEFAULT '2',
  `time_2` int(11) NOT NULL DEFAULT '2',
  `time_3` int(11) NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inox_fetch`
--

INSERT INTO `inox_fetch` (`b_id`, `time_1`, `time_2`, `time_3`) VALUES
(1, 1, 2, 2),
(2, 1, 2, 2),
(3, 1, 2, 2),
(4, 1, 2, 2),
(5, 1, 2, 2),
(6, 1, 2, 2),
(7, 2, 2, 2),
(8, 2, 2, 2),
(9, 2, 2, 2),
(10, 2, 2, 2),
(11, 2, 2, 2),
(12, 2, 2, 2),
(13, 2, 2, 2),
(14, 2, 2, 2),
(15, 2, 2, 2),
(16, 2, 2, 2),
(17, 2, 2, 2),
(18, 2, 2, 2),
(19, 2, 2, 2),
(20, 2, 2, 2),
(21, 2, 2, 2),
(22, 2, 2, 2),
(23, 2, 2, 2),
(24, 2, 2, 2),
(25, 2, 2, 2),
(26, 2, 2, 2),
(27, 2, 2, 2),
(28, 2, 2, 2),
(29, 2, 2, 2),
(30, 2, 2, 2),
(31, 2, 2, 2),
(32, 2, 2, 2),
(33, 2, 2, 2),
(34, 2, 2, 2),
(35, 2, 2, 2),
(36, 2, 2, 2),
(37, 2, 2, 2),
(38, 2, 2, 2),
(39, 2, 2, 2),
(40, 2, 2, 2),
(41, 2, 2, 2),
(42, 2, 2, 2),
(43, 2, 2, 2),
(44, 2, 2, 2),
(45, 2, 2, 2),
(46, 2, 2, 2),
(47, 2, 2, 2),
(48, 2, 2, 2),
(49, 2, 2, 2),
(50, 2, 2, 2),
(51, 2, 2, 2),
(52, 2, 2, 2),
(53, 2, 2, 2),
(54, 2, 2, 2),
(55, 2, 2, 2),
(56, 2, 2, 2),
(57, 2, 2, 2),
(58, 2, 2, 2),
(59, 2, 2, 2),
(60, 2, 2, 2),
(61, 2, 2, 2),
(62, 2, 2, 2),
(63, 2, 2, 2),
(64, 2, 2, 2),
(65, 2, 2, 2),
(66, 2, 2, 2),
(67, 2, 2, 2),
(68, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `login_db`
--

DROP TABLE IF EXISTS `login_db`;
CREATE TABLE `login_db` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(200) NOT NULL,
  `points` int(11) NOT NULL DEFAULT '100'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_db`
--

INSERT INTO `login_db` (`id`, `username`, `password`, `email`, `points`) VALUES
(1, 'admin', 'admin', 'admin@itsmyshow.com', 100),
(2, 'sathvik', 'sathvik', 'sathviknaraimha@gmail.com', 100),
(3, 'pasha', 'pasha', 'pasha@gmail.com', 100),
(9, 'lol', 'lol', 'lol@gmail.com', 100),
(10, 'sathv', 'sathv', 'sathviksad@gmail.com', 100),
(11, 'sathvik', 'great', 'sad@gmail.com', 100),
(12, 'sathvik', 'great', 'sad@gmail.com', 100),
(13, 'sathvik', 'sathvik', 'sathviknarasimha@gmail.com', 100),
(14, 'ajkslljk', 'sad', 'klsjal', 100),
(15, 'sdnklfns', '123', 'sadfas', 100),
(16, 'sdfasd', '11', 'sdfsa', 100),
(17, 'sdfs', '11', 'sdasdf', 100),
(18, 'dsfsd', '11', 'sdfsd', 100),
(19, 'dsfas', '44', 'sdaf', 100),
(20, 'dsfas', '44', 'sdaf', 100),
(21, 'dsfas', '44', 'sdaf', 100),
(22, 'dsfas', '44', 'sdaf', 100),
(23, 'sh', '123', 'sasa', 100),
(24, 'gfdjk', '123', 'ghfhg', 100),
(25, 'jggfhg', '1', 'hk', 100),
(26, 'sas', '123', 'sas', 100),
(27, 'shreesha', '1222', 'kl.shreesha@gmail.com', 100),
(28, 'kls', '45', 'kls', 100),
(29, 'sas', '1', 'asdas', 100),
(30, 'shreesha', '12345', 'shreesha.kls@gmail.com', 100),
(31, 'pasha', 'saba', 'pasha@google.com', 100),
(32, 'a', 'a', 'a', 100);

-- --------------------------------------------------------

--
-- Table structure for table `movie_db`
--

DROP TABLE IF EXISTS `movie_db`;
CREATE TABLE `movie_db` (
  `m_id` int(11) NOT NULL,
  `m_name` varchar(40) NOT NULL,
  `m_lang` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_db`
--

INSERT INTO `movie_db` (`m_id`, `m_name`, `m_lang`) VALUES
(1, 'Rustom', 'Hindi'),
(2, 'Kotigobba', 'Kannada'),
(3, 'Premam', 'Malayalam');

-- --------------------------------------------------------

--
-- Table structure for table `pvr_fetch`
--

DROP TABLE IF EXISTS `pvr_fetch`;
CREATE TABLE `pvr_fetch` (
  `b_id` int(11) NOT NULL,
  `time_1` int(11) DEFAULT '2',
  `time_2` int(11) NOT NULL DEFAULT '2',
  `time_3` int(11) NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pvr_fetch`
--

INSERT INTO `pvr_fetch` (`b_id`, `time_1`, `time_2`, `time_3`) VALUES
(1, 2, 2, 2),
(2, 2, 2, 2),
(3, 2, 2, 2),
(4, 2, 2, 2),
(5, 2, 2, 2),
(6, 2, 2, 2),
(7, 2, 2, 2),
(8, 2, 2, 2),
(9, 2, 2, 2),
(10, 2, 2, 2),
(11, 2, 2, 2),
(12, 2, 2, 2),
(13, 2, 2, 2),
(14, 2, 2, 2),
(15, 2, 2, 2),
(16, 2, 2, 2),
(17, 2, 2, 2),
(18, 2, 2, 2),
(19, 2, 2, 2),
(20, 2, 2, 2),
(21, 2, 2, 2),
(22, 2, 2, 2),
(23, 2, 2, 2),
(24, 2, 2, 2),
(25, 2, 2, 2),
(26, 2, 2, 2),
(27, 2, 2, 2),
(28, 2, 2, 2),
(29, 2, 2, 2),
(30, 2, 2, 2),
(31, 2, 2, 2),
(32, 2, 2, 2),
(33, 2, 2, 2),
(34, 2, 2, 2),
(35, 2, 2, 2),
(36, 2, 2, 2),
(37, 2, 2, 2),
(38, 2, 2, 2),
(39, 2, 2, 2),
(40, 2, 2, 2),
(41, 2, 2, 2),
(42, 2, 2, 2),
(43, 2, 2, 2),
(44, 2, 2, 2),
(45, 2, 2, 2),
(46, 2, 2, 2),
(47, 2, 2, 2),
(48, 2, 2, 2),
(49, 2, 2, 2),
(50, 2, 2, 2),
(51, 2, 2, 2),
(52, 2, 2, 2),
(53, 2, 2, 2),
(54, 2, 2, 2),
(55, 2, 2, 2),
(56, 2, 2, 2),
(57, 2, 2, 2),
(58, 2, 2, 2),
(59, 2, 2, 2),
(60, 2, 2, 2),
(61, 2, 2, 2),
(62, 2, 2, 2),
(63, 2, 2, 2),
(64, 2, 2, 2),
(65, 2, 2, 2),
(66, 2, 2, 2),
(67, 2, 2, 2),
(68, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `sterling_fetch`
--

DROP TABLE IF EXISTS `sterling_fetch`;
CREATE TABLE `sterling_fetch` (
  `b_id` int(11) NOT NULL,
  `time_1` int(11) NOT NULL DEFAULT '2',
  `time_2` int(11) NOT NULL DEFAULT '2',
  `time_3` int(11) NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sterling_fetch`
--

INSERT INTO `sterling_fetch` (`b_id`, `time_1`, `time_2`, `time_3`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 1),
(3, 2, 1, 1),
(4, 2, 2, 2),
(5, 2, 2, 2),
(6, 2, 2, 2),
(7, 2, 2, 2),
(8, 2, 2, 2),
(9, 2, 2, 2),
(10, 2, 2, 1),
(11, 1, 2, 2),
(12, 1, 2, 1),
(13, 2, 2, 1),
(14, 2, 2, 1),
(15, 2, 2, 1),
(16, 2, 2, 1),
(17, 2, 2, 2),
(18, 2, 2, 2),
(19, 1, 2, 1),
(20, 2, 2, 2),
(21, 2, 2, 2),
(22, 2, 2, 2),
(23, 2, 2, 2),
(24, 2, 2, 2),
(25, 2, 2, 2),
(26, 2, 2, 2),
(27, 2, 2, 2),
(28, 2, 2, 2),
(29, 2, 2, 2),
(30, 2, 2, 2),
(31, 2, 2, 2),
(32, 1, 2, 2),
(33, 2, 2, 2),
(34, 2, 2, 2),
(35, 2, 2, 2),
(36, 2, 2, 2),
(37, 2, 2, 2),
(38, 2, 2, 2),
(39, 2, 2, 2),
(40, 2, 2, 2),
(41, 2, 2, 2),
(42, 2, 2, 2),
(43, 2, 2, 2),
(44, 2, 2, 2),
(45, 2, 2, 2),
(46, 2, 2, 2),
(47, 2, 2, 2),
(48, 2, 2, 2),
(49, 2, 2, 2),
(50, 2, 2, 2),
(51, 2, 2, 2),
(52, 2, 2, 2),
(53, 2, 2, 2),
(54, 2, 2, 2),
(55, 2, 2, 2),
(56, 2, 2, 2),
(57, 2, 2, 2),
(58, 2, 2, 2),
(59, 2, 2, 2),
(60, 2, 2, 2),
(61, 2, 2, 2),
(62, 2, 2, 2),
(63, 2, 2, 2),
(64, 2, 2, 2),
(65, 2, 2, 2),
(66, 2, 2, 2),
(67, 2, 2, 2),
(68, 2, 2, 2),
(69, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `theatre_fetch`
--

DROP TABLE IF EXISTS `theatre_fetch`;
CREATE TABLE `theatre_fetch` (
  `t_id` int(11) NOT NULL,
  `t_name` varchar(255) NOT NULL,
  `mov_1` varchar(255) DEFAULT NULL,
  `mov_2` varchar(255) DEFAULT NULL,
  `mov_3` varchar(255) DEFAULT NULL,
  `time_1` varchar(255) DEFAULT NULL,
  `time_2` varchar(255) DEFAULT NULL,
  `time_3` varchar(255) DEFAULT NULL,
  `m_fare` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `theatre_fetch`
--

INSERT INTO `theatre_fetch` (`t_id`, `t_name`, `mov_1`, `mov_2`, `mov_3`, `time_1`, `time_2`, `time_3`, `m_fare`) VALUES
(1, 'thiba', 'premam', NULL, NULL, '10:00', '1:00', '5:00', 60),
(2, 'sterling', 'rustom', NULL, NULL, '10:00', '1:00', '5:00', 60),
(3, 'inox', NULL, 'rustom', NULL, '10:00', '1:00', '5:00', 150),
(4, 'DRC', 'kottigobba', NULL, 'rustom', '10:00', '1:00', '5:00', 150),
(5, 'PVR', 'kotigobba', NULL, NULL, '10:00', '1:00', '5:00', 150);

-- --------------------------------------------------------

--
-- Table structure for table `thiba_fetch`
--

DROP TABLE IF EXISTS `thiba_fetch`;
CREATE TABLE `thiba_fetch` (
  `b_id` int(11) NOT NULL,
  `time_1` int(11) NOT NULL DEFAULT '2',
  `time_2` int(11) NOT NULL DEFAULT '2',
  `time_3` int(11) NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `thiba_fetch`
--

INSERT INTO `thiba_fetch` (`b_id`, `time_1`, `time_2`, `time_3`) VALUES
(1, 1, 2, 2),
(2, 1, 2, 2),
(3, 1, 2, 2),
(4, 2, 2, 2),
(5, 2, 2, 2),
(6, 2, 2, 2),
(7, 2, 2, 2),
(8, 2, 2, 2),
(9, 2, 2, 2),
(10, 2, 2, 2),
(11, 2, 2, 2),
(12, 2, 2, 2),
(13, 2, 2, 2),
(14, 2, 2, 2),
(15, 2, 2, 2),
(16, 2, 2, 2),
(17, 2, 2, 2),
(18, 2, 2, 2),
(19, 2, 2, 2),
(20, 2, 2, 2),
(21, 2, 2, 2),
(22, 2, 2, 2),
(23, 2, 2, 2),
(24, 2, 2, 2),
(25, 2, 2, 2),
(26, 2, 2, 2),
(27, 2, 2, 2),
(28, 2, 2, 2),
(29, 2, 2, 2),
(30, 2, 2, 2),
(31, 2, 2, 2),
(32, 2, 2, 2),
(33, 2, 2, 2),
(34, 2, 2, 2),
(35, 2, 2, 2),
(36, 2, 2, 2),
(37, 2, 2, 2),
(38, 2, 2, 2),
(39, 2, 2, 2),
(40, 2, 2, 2),
(41, 2, 2, 2),
(42, 2, 2, 2),
(43, 2, 2, 2),
(44, 2, 2, 2),
(45, 2, 2, 2),
(46, 2, 2, 2),
(47, 2, 2, 2),
(48, 2, 2, 2),
(49, 2, 2, 2),
(50, 2, 2, 2),
(51, 2, 2, 2),
(52, 2, 2, 2),
(53, 2, 2, 2),
(54, 2, 2, 2),
(55, 2, 2, 2),
(56, 2, 2, 2),
(57, 2, 2, 2),
(58, 2, 2, 2),
(59, 2, 2, 2),
(60, 2, 2, 2),
(61, 2, 2, 2),
(62, 2, 2, 2),
(63, 2, 2, 2),
(64, 2, 2, 2),
(65, 2, 2, 2),
(66, 2, 2, 2),
(67, 2, 2, 2),
(68, 2, 2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_db`
--
ALTER TABLE `booking_db`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `drc_fetch`
--
ALTER TABLE `drc_fetch`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `event_fetch`
--
ALTER TABLE `event_fetch`
  ADD PRIMARY KEY (`e_id`);

--
-- Indexes for table `inox_fetch`
--
ALTER TABLE `inox_fetch`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `login_db`
--
ALTER TABLE `login_db`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movie_db`
--
ALTER TABLE `movie_db`
  ADD PRIMARY KEY (`m_id`);

--
-- Indexes for table `pvr_fetch`
--
ALTER TABLE `pvr_fetch`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `sterling_fetch`
--
ALTER TABLE `sterling_fetch`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `theatre_fetch`
--
ALTER TABLE `theatre_fetch`
  ADD PRIMARY KEY (`t_id`);

--
-- Indexes for table `thiba_fetch`
--
ALTER TABLE `thiba_fetch`
  ADD PRIMARY KEY (`b_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking_db`
--
ALTER TABLE `booking_db`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `drc_fetch`
--
ALTER TABLE `drc_fetch`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT for table `event_fetch`
--
ALTER TABLE `event_fetch`
  MODIFY `e_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `inox_fetch`
--
ALTER TABLE `inox_fetch`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT for table `login_db`
--
ALTER TABLE `login_db`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `movie_db`
--
ALTER TABLE `movie_db`
  MODIFY `m_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `pvr_fetch`
--
ALTER TABLE `pvr_fetch`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT for table `sterling_fetch`
--
ALTER TABLE `sterling_fetch`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;
--
-- AUTO_INCREMENT for table `theatre_fetch`
--
ALTER TABLE `theatre_fetch`
  MODIFY `t_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `thiba_fetch`
--
ALTER TABLE `thiba_fetch`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
