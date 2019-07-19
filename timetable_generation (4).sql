-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 15, 2019 at 07:41 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `timetable_generation`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `branch_id` int(11) NOT NULL,
  `branch_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`branch_id`, `branch_name`) VALUES
(1, 'INSTRU'),
(2, 'ETRX'),
(3, 'EXTC'),
(4, 'COMS'),
(5, 'IT'),
(6, 'FE'),
(7, 'COMMON');

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `classroom_id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`classroom_id`, `division_id`) VALUES
(514, 27);

-- --------------------------------------------------------

--
-- Table structure for table `division`
--

CREATE TABLE `division` (
  `division_id` int(11) NOT NULL,
  `division_name` varchar(255) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `shift` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `division`
--

INSERT INTO `division` (`division_id`, `division_name`, `branch_id`, `shift`) VALUES
(1, 'D1A', 6, 2),
(2, 'D1B', 6, 2),
(3, 'D2A', 6, 2),
(4, 'D2B', 6, 2),
(5, 'D2C', 6, 3),
(6, 'D3', 6, 2),
(7, 'D4A', 6, 2),
(8, 'D4B', 6, 2),
(9, 'D5', 6, 2),
(10, 'D6A', 1, 1),
(11, 'D6B', 1, 1),
(12, 'D7A', 4, 1),
(13, 'D7B', 4, 1),
(14, 'D7C', 4, 2),
(15, 'D8', 2, 1),
(16, 'D9A', 3, 1),
(17, 'D9B', 3, 1),
(18, 'D10', 5, 1),
(19, 'D11A', 1, 1),
(20, 'D11B', 1, 1),
(21, 'D12A', 4, 1),
(22, 'D12B', 4, 1),
(23, 'D12C', 4, 2),
(24, 'D13', 2, 1),
(25, 'D14A', 3, 1),
(26, 'D14B', 3, 1),
(27, 'D15', 5, 1),
(28, 'D16A', 1, 2),
(29, 'D16B', 1, 2),
(30, 'D17A', 4, 2),
(31, 'D17B', 4, 2),
(32, 'D17C', 4, 3),
(33, 'D18', 2, 2),
(34, 'D19A', 3, 2),
(35, 'D19B', 3, 2),
(36, 'D20', 5, 2),
(37, 'D9C', 3, 3),
(38, 'D14C', 3, 3),
(39, 'D19C', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `lab`
--

CREATE TABLE `lab` (
  `lab_id` int(11) NOT NULL,
  `branch_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lab`
--

INSERT INTO `lab` (`lab_id`, `branch_id`) VALUES
(512, 5),
(513, 5);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL,
  `subject_name` varchar(255) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `sem_id` int(11) NOT NULL,
  `hours_per_week` int(11) NOT NULL,
  `lab_hours_per_week` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subject_id`, `subject_name`, `branch_id`, `sem_id`, `hours_per_week`, `lab_hours_per_week`) VALUES
(1, 'Applied Mathematics III', 5, 3, 5, 0),
(2, 'Logic Design', 5, 3, 4, 0),
(3, 'Data Structures and Analysis', 5, 3, 4, 2),
(4, 'Database Management System', 5, 3, 4, 2),
(5, 'Principle of communication', 5, 3, 3, 0),
(6, 'Java Programming Lab', 5, 3, 2, 2),
(7, 'Applied Mathematics IV', 5, 4, 5, 0),
(8, 'Computer Networks', 5, 4, 4, 2),
(9, 'Operating System', 5, 4, 4, 2),
(10, 'COA', 5, 4, 4, 2),
(11, 'Automata Theory', 5, 4, 4, 0),
(12, 'Python', 5, 4, 2, 2),
(13, 'MPMC', 5, 5, 4, 2),
(14, 'Internet Programming', 5, 5, 4, 2),
(15, 'ADBMS', 5, 5, 4, 2),
(16, 'CNS', 5, 5, 4, 2),
(17, 'E-Commerce', 5, 5, 4, 0),
(18, 'BCE', 5, 5, 2, 2),
(19, 'Software Engineering', 5, 6, 4, 2),
(20, 'Data Mining', 5, 6, 4, 2),
(21, 'Cloud Computing', 5, 6, 4, 2),
(22, 'Wireless Networks', 5, 6, 4, 2),
(23, 'AIP', 5, 6, 4, 0),
(24, 'Mini Project', 5, 6, 0, 4),
(26, 'Applied Mathematics I', 6, 1, 5, 0),
(27, 'BEE', 6, 1, 4, 2),
(28, 'Mechanics', 6, 1, 4, 2),
(29, 'Physics 1', 6, 1, 3, 2),
(30, 'Chemistry', 6, 1, 3, 2),
(31, 'EVS', 6, 1, 2, 0),
(32, 'Applied Mathematics III', 4, 3, 5, 0),
(33, 'DLDA', 4, 3, 4, 2),
(34, 'Discrete Mathematics', 4, 3, 4, 0),
(35, 'ECCF', 4, 3, 4, 2),
(36, 'Data Structures', 4, 3, 4, 2),
(37, 'Java', 4, 3, 2, 2),
(38, 'Workshop 1', 6, 1, 0, 4),
(39, 'Applied Mathematics III', 2, 3, 5, 0),
(40, 'Electronic Devices 1', 2, 3, 4, 2),
(41, 'Digital Circuit', 2, 3, 4, 2),
(42, 'Electrical Network Analysis', 2, 3, 4, 2),
(43, 'Electronic Instruments', 2, 3, 4, 0),
(44, 'Java', 2, 3, 2, 2),
(45, 'Applied Mathematics III', 3, 3, 5, 0),
(46, 'Electronic Devices and Circuits 1', 3, 3, 4, 2),
(47, 'Digital System Design', 3, 3, 4, 2),
(48, 'Circuit Theory', 3, 3, 4, 2),
(49, 'Electronic Instrumentation', 3, 3, 4, 2),
(50, 'Java', 3, 3, 0, 2),
(51, 'Applied Mathematics III', 1, 3, 5, 0),
(52, 'Analog Electronics', 1, 3, 4, 2),
(53, 'Transducers - 1', 1, 3, 4, 2),
(54, 'Digital Electronics', 1, 3, 4, 2),
(55, 'Electrical Networks and measurements', 1, 3, 5, 0),
(56, 'Java', 1, 3, 0, 4),
(57, 'Microprocessor', 4, 5, 4, 2),
(58, 'DBMS', 4, 5, 4, 2),
(59, 'CN', 4, 5, 4, 2),
(60, 'TCS', 4, 5, 4, 0),
(61, 'Advance OS', 4, 5, 4, 0),
(62, 'BCE', 4, 5, 2, 2),
(63, 'Web', 4, 5, 2, 2),
(64, 'Micro-controllers', 2, 5, 4, 2),
(65, 'Digital Communication', 2, 5, 4, 2),
(66, 'Engineering Electromagnetics', 2, 5, 4, 0),
(67, 'Design with Linear integrated cirtuits', 2, 5, 4, 2),
(68, 'Elective', 2, 5, 4, 2),
(69, 'BCE', 2, 5, 2, 2),
(70, 'Microprocessor and Peripherals', 3, 5, 4, 2),
(71, 'Digital Communication', 3, 5, 4, 2),
(72, 'Electromagnetic Engineering', 3, 5, 5, 0),
(73, 'Discrete Time Signal', 3, 5, 4, 2),
(74, 'Elective', 3, 5, 4, 2),
(75, 'BCE', 3, 5, 2, 2),
(76, 'Signals and systems', 1, 5, 4, 0),
(77, 'App of microcontroller', 1, 5, 4, 2),
(78, 'Control system design', 1, 5, 4, 2),
(79, 'Control system components', 1, 5, 4, 2),
(80, 'Elective', 1, 5, 3, 2),
(81, 'BCE', 1, 5, 0, 4),
(82, 'Mini Project', 1, 5, 0, 2),
(83, 'Digital signal and image processing', 4, 7, 4, 2),
(84, 'Mobile Communication', 4, 7, 4, 2),
(85, 'Artificial Intelligence', 4, 7, 4, 2),
(86, 'Elective', 4, 7, 4, 0),
(87, 'Institute course', 4, 7, 3, 0),
(88, 'Computational Lab', 4, 7, 0, 2),
(89, 'Major Project', 4, 7, 0, 6),
(90, 'Instrumentation System esign', 2, 7, 4, 2),
(91, 'Power Electronics', 2, 7, 4, 2),
(92, 'Digital signal processing', 2, 7, 4, 2),
(93, 'Elective', 2, 7, 4, 2),
(94, 'Institute course', 2, 7, 3, 0),
(95, 'Project-1', 2, 7, 0, 6),
(96, 'Microwave Engineering', 3, 7, 4, 2),
(97, 'Mobile communication system', 3, 7, 4, 2),
(98, 'Optical Communication', 3, 7, 4, 2),
(99, 'Elective', 3, 7, 4, 2),
(100, 'Institute Course', 3, 7, 3, 0),
(101, 'Project-1', 3, 7, 0, 6),
(102, 'Industrial Process control', 1, 7, 4, 2),
(103, 'Biomedical Instrumentation', 1, 7, 4, 2),
(104, 'Industrial automation', 1, 7, 4, 2),
(105, 'Elective', 1, 7, 4, 2),
(106, 'Institute course', 1, 7, 3, 0),
(107, 'Project-1', 1, 7, 0, 6),
(108, 'Enterprise Network design', 5, 7, 4, 2),
(109, 'Infrastructure Security', 5, 7, 4, 2),
(110, 'Artificial Intelligence', 5, 7, 4, 2),
(111, 'Elective', 5, 7, 4, 0),
(112, 'Institute course', 5, 7, 3, 0),
(113, 'Andriod App', 5, 7, 0, 2),
(114, 'Project-1', 5, 7, 0, 6);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `teacher_name` varchar(255) NOT NULL,
  `branch_id` int(11) NOT NULL,
  `teacher_shift` int(11) NOT NULL DEFAULT '1',
  `senior` int(11) NOT NULL DEFAULT '0',
  `max_subject_count` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `teacher_name`, `branch_id`, `teacher_shift`, `senior`, `max_subject_count`) VALUES
(1, 'Jayashree Hajgude', 5, 1, 0, 0),
(2, 'Vinita Mishra', 5, 1, 0, 0),
(3, 'Pooja Shety', 5, 1, 0, 0),
(4, 'Amit Singh', 5, 1, 0, 0),
(5, 'Asha Bharambe', 5, 1, 0, 0),
(6, 'Shanta Shondur', 5, 1, 1, 0),
(7, 'Sukanyaroy Chaudri', 5, 2, 0, 0),
(8, 'Shalu Chopra', 5, 2, 1, 0),
(9, 'Asma Praveen', 5, 2, 0, 0),
(10, 'Vidya Pujari', 5, 1, 1, 0),
(11, 'Sandeep Utala', 5, 1, 0, 0),
(12, 'Mahesh Singh', 6, 2, 0, 0),
(13, 'Subash Singh', 6, 2, 0, 0),
(14, 'Vijay Shekhar ', 6, 2, 0, 0),
(15, 'Umbrikar ', 6, 2, 0, 0),
(16, 'Mamta Jain', 6, 2, 0, 0),
(17, 'Soniya', 6, 2, 0, 0),
(18, 'Sachin ', 6, 3, 0, 0),
(19, 'Sunanda Manoj', 6, 3, 0, 0),
(20, 'Vrinda', 6, 2, 0, 0),
(21, 'Chemistry Teacher 2', 6, 3, 0, 0),
(22, 'Prafula', 6, 3, 0, 0),
(23, 'Manisha Tiwari', 6, 2, 0, 0),
(24, 'Physics Teacher 2', 6, 3, 0, 0),
(25, 'Pooja Kundu', 7, 3, 0, 0),
(26, 'Sushil', 7, 2, 0, 0),
(27, 'Pooja Nagdev', 4, 1, 0, 0),
(28, 'Kajal Jevani', 4, 1, 0, 0),
(29, 'Prashant Kanade', 4, 2, 1, 0),
(30, 'Indu Dokre', 4, 1, 0, 0),
(31, 'Yugchaya', 4, 1, 1, 0),
(32, 'Kavita Rathi', 4, 2, 1, 0),
(33, 'Anjoli Yeole', 4, 1, 1, 0),
(34, 'Ruplai Hande', 4, 1, 0, 0),
(35, 'Sunita Sahu', 4, 1, 0, 0),
(36, 'Richsa Sharm', 4, 1, 0, 0),
(37, 'Priya Rl', 4, 1, 0, 0),
(38, 'Sujata Khadekar', 4, 3, 0, 0),
(39, 'Vidya Zope', 4, 2, 0, 0),
(40, 'Lifna ', 4, 1, 1, 0),
(41, 'Coms Teacher', 4, 3, 0, 0),
(42, 'Palavi Saindane', 4, 3, 0, 0),
(43, 'Coms Teacher 2', 4, 3, 0, 0),
(44, 'Coms Teacher 3', 4, 2, 0, 0),
(45, 'Coms Teacher 4', 4, 2, 0, 0),
(46, 'Coms Teacher 5', 4, 2, 0, 0),
(47, 'Coms Teacher 6', 4, 2, 1, 0),
(48, 'Coms Teacher 7', 4, 1, 0, 0),
(49, 'Coms Teacher 8', 4, 2, 1, 0),
(50, 'Coms Teacher 11', 4, 1, 0, 0),
(51, 'Extc Teacher 1', 3, 1, 1, 0),
(52, 'Extc Teacher 2', 3, 1, 0, 0),
(53, 'Extc Teacher 3', 3, 1, 1, 0),
(54, 'Extc Teacher 3', 3, 1, 0, 0),
(55, 'Extc Teacher 4', 3, 1, 0, 0),
(56, 'Extc Teacher 5', 3, 1, 1, 0),
(57, 'Extc Teacher 6', 3, 1, 0, 0),
(58, 'Extc Teacher 7', 3, 1, 0, 0),
(59, 'Extc Teacher 8', 3, 1, 1, 0),
(60, 'Extc Teacher 9', 3, 1, 0, 0),
(61, 'Extc Teacher 10', 3, 1, 0, 0),
(62, 'Extc Teacher 11', 3, 2, 1, 0),
(63, 'Extc Teacher 11', 3, 2, 1, 0),
(64, 'Extc Teacher 12', 3, 2, 0, 0),
(65, 'Extc Teacher 13', 3, 2, 0, 0),
(66, 'Extc Teacher 14', 3, 2, 0, 0),
(67, 'Extc Teacher 15', 3, 2, 0, 0),
(68, 'Extc Teacher 16', 3, 2, 0, 0),
(69, 'Extc Teacher 17', 3, 2, 0, 0),
(70, 'Extc Teacher 18', 3, 3, 1, 0),
(71, 'Extc Teacher 19', 3, 3, 0, 0),
(72, 'Extc Teacher 20', 3, 3, 0, 0),
(73, 'Extc Teacher 21', 3, 3, 0, 0),
(74, 'Instru Teacher 1', 1, 1, 0, 0),
(75, 'Instru Teacher 2', 1, 1, 0, 0),
(76, 'Instru Teacher 3', 1, 1, 1, 0),
(77, 'Instru Teacher 4', 1, 1, 0, 0),
(78, 'Instru Teacher 5', 1, 1, 0, 0),
(79, 'Instru Teacher 6', 1, 1, 1, 0),
(80, 'Instru Teacher 7', 1, 1, 0, 0),
(81, 'Instru Teacher 8', 1, 2, 1, 0),
(82, 'Instru Teacher 9', 1, 2, 0, 0),
(83, 'Instru Teacher 10', 1, 2, 0, 0),
(84, 'Instru Teacher 11', 1, 2, 0, 0),
(85, 'Instru Teacher 12', 1, 1, 1, 0),
(86, 'Instru Teacher 13', 1, 2, 1, 0),
(87, 'Instru Teacher 14', 1, 1, 0, 0),
(88, 'Instru Teacher 15', 1, 1, 0, 0),
(89, 'Etrx Teacher 1', 2, 1, 0, 0),
(90, 'Etrx Teacher 2', 2, 2, 1, 0),
(91, 'Etrx Teacher 3', 2, 2, 0, 0),
(92, 'Etrx Teacher 4', 2, 1, 0, 0),
(93, 'Etrx Teacher 5', 2, 2, 0, 0),
(94, 'Etrx Teacher 6', 2, 1, 1, 0),
(95, 'Etrx Teacher 7', 2, 1, 0, 0),
(96, 'Etrx Teacher 8', 2, 2, 0, 0),
(97, 'Etrx Teacher 9', 2, 1, 1, 0),
(98, 'Etrx Teacher 10', 2, 1, 0, 0),
(99, 'Etrx Teacher 11', 2, 1, 1, 0),
(100, 'Nagananda', 7, 2, 0, 0),
(101, 'Instru Teacher 16', 1, 2, 0, 0),
(102, 'Instru Teacher 17', 1, 1, 0, 0),
(103, 'Netto Martia', 7, 1, 0, 0),
(104, 'Maths Teacher 2', 7, 1, 0, 0),
(105, 'Maths Teacher 3', 7, 1, 0, 0),
(106, 'EXTC Teacher 22', 3, 3, 0, 0),
(107, 'EXTC Teacher 23', 3, 3, 0, 0),
(108, 'Workshop Teacher 1', 6, 2, 0, 0),
(109, 'Workshop Teacher 2', 6, 2, 0, 0),
(110, 'Workshop Teacher 3', 6, 2, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `teacher_subject_preference`
--

CREATE TABLE `teacher_subject_preference` (
  `teacher_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher_subject_preference`
--

INSERT INTO `teacher_subject_preference` (`teacher_id`, `subject_id`) VALUES
(16, 26),
(17, 26),
(18, 26),
(12, 28),
(13, 28),
(14, 28),
(15, 28),
(22, 29),
(23, 29),
(24, 29),
(19, 30),
(20, 30),
(21, 30),
(25, 31),
(26, 31),
(80, 27),
(78, 27),
(89, 27),
(54, 27),
(55, 27),
(103, 51),
(104, 51),
(105, 51),
(74, 52),
(75, 52),
(77, 53),
(78, 53),
(80, 54),
(82, 54),
(83, 55),
(84, 55),
(87, 56),
(88, 56),
(76, 52),
(79, 53),
(81, 54),
(85, 55),
(102, 56),
(103, 51),
(76, 76),
(80, 76),
(83, 76),
(74, 77),
(82, 77),
(84, 77),
(79, 77),
(75, 78),
(81, 78),
(77, 78),
(86, 79),
(85, 79),
(87, 79),
(101, 80),
(88, 80),
(102, 80),
(25, 81),
(26, 81),
(100, 81),
(85, 51),
(86, 51),
(81, 102),
(80, 102),
(82, 103),
(83, 103),
(84, 104),
(86, 104),
(79, 105),
(76, 105),
(101, 106),
(102, 106),
(74, 107),
(75, 107),
(103, 39),
(104, 39),
(105, 39),
(89, 40),
(92, 40),
(94, 41),
(95, 41),
(97, 42),
(98, 42),
(90, 43),
(91, 43),
(93, 44),
(89, 44),
(91, 64),
(92, 64),
(95, 65),
(97, 65),
(98, 67),
(93, 67),
(98, 68),
(99, 68),
(25, 69),
(26, 69),
(100, 69),
(90, 90),
(89, 90),
(91, 91),
(92, 91),
(93, 92),
(94, 92),
(96, 93),
(97, 93),
(98, 94),
(99, 94),
(97, 95),
(92, 95),
(103, 45),
(104, 45),
(104, 45),
(51, 46),
(52, 46),
(53, 46),
(54, 47),
(55, 47),
(56, 47),
(57, 48),
(58, 48),
(59, 48),
(60, 49),
(61, 49),
(62, 49),
(63, 50),
(64, 50),
(65, 50),
(66, 70),
(67, 70),
(68, 70),
(69, 71),
(70, 71),
(71, 71),
(51, 72),
(52, 72),
(53, 72),
(54, 73),
(55, 73),
(57, 73),
(58, 74),
(72, 74),
(73, 74),
(72, 70),
(25, 75),
(26, 75),
(100, 75),
(64, 96),
(65, 96),
(66, 96),
(67, 97),
(68, 97),
(69, 97),
(57, 98),
(58, 98),
(59, 98),
(72, 99),
(73, 99),
(71, 99),
(64, 100),
(69, 100),
(71, 100),
(72, 100),
(65, 100),
(68, 101),
(64, 101),
(65, 101),
(106, 46),
(107, 47),
(106, 70),
(107, 71),
(103, 32),
(104, 32),
(105, 32),
(27, 33),
(28, 33),
(41, 33),
(29, 34),
(30, 34),
(42, 34),
(31, 35),
(32, 35),
(33, 35),
(34, 36),
(35, 36),
(43, 36),
(36, 37),
(37, 37),
(39, 37),
(40, 57),
(44, 57),
(108, 57),
(45, 58),
(46, 58),
(38, 58),
(47, 59),
(48, 59),
(49, 59),
(50, 60),
(27, 60),
(28, 60),
(30, 61),
(31, 61),
(33, 61),
(25, 62),
(26, 62),
(100, 62),
(35, 63),
(42, 63),
(43, 63),
(44, 83),
(45, 83),
(46, 83),
(47, 84),
(48, 84),
(108, 84),
(49, 85),
(50, 85),
(38, 85),
(29, 86),
(30, 86),
(31, 86),
(32, 87),
(33, 87),
(34, 87),
(35, 87),
(37, 88),
(43, 88),
(48, 88),
(49, 89),
(50, 89),
(40, 89),
(103, 1),
(2, 2),
(9, 2),
(1, 3),
(4, 3),
(1, 4),
(5, 4),
(6, 5),
(11, 5),
(3, 6),
(7, 6),
(6, 13),
(8, 13),
(3, 14),
(10, 14),
(1, 15),
(5, 15),
(11, 16),
(9, 16),
(10, 17),
(2, 17),
(25, 18),
(26, 18),
(8, 108),
(10, 108),
(7, 109),
(6, 109),
(9, 110),
(4, 110),
(3, 111),
(11, 111),
(2, 112),
(1, 112),
(5, 113),
(4, 114),
(108, 38),
(109, 38),
(110, 38),
(81, 82),
(85, 82),
(97, 66),
(98, 66);

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
  `primary_key` int(11) NOT NULL,
  `division_name` varchar(255) NOT NULL,
  `slot` varchar(255) NOT NULL,
  `day_id` int(11) NOT NULL DEFAULT '0',
  `subject_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `lecture_hour` int(11) NOT NULL,
  `lab_hour` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_email`, `user_password`) VALUES
(1, 'sahil@gmail.com', 'abc123'),
(2, 'sanjay@gmail.com', 'abc123');

-- --------------------------------------------------------

--
-- Table structure for table `year`
--

CREATE TABLE `year` (
  `year_id` int(11) NOT NULL,
  `year_name` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`branch_id`);

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`classroom_id`);

--
-- Indexes for table `division`
--
ALTER TABLE `division`
  ADD PRIMARY KEY (`division_id`);

--
-- Indexes for table `lab`
--
ALTER TABLE `lab`
  ADD PRIMARY KEY (`lab_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`);

--
-- Indexes for table `timetable`
--
ALTER TABLE `timetable`
  ADD PRIMARY KEY (`primary_key`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `branch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `division`
--
ALTER TABLE `division`
  MODIFY `division_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- AUTO_INCREMENT for table `timetable`
--
ALTER TABLE `timetable`
  MODIFY `primary_key` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1696;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
