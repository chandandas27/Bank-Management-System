-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 06, 2018 at 08:58 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounttable`
--

CREATE TABLE `accounttable` (
  `Id` int(11) NOT NULL,
  `Account_Number` varchar(15) NOT NULL,
  `Account_Type` varchar(15) NOT NULL,
  `BCode` varchar(15) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `DOB` date DEFAULT NULL,
  `Address` varchar(50) NOT NULL,
  `Aadhar` varchar(12) NOT NULL,
  `Balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounttable`
--

INSERT INTO `accounttable` (`Id`, `Account_Number`, `Account_Type`, `BCode`, `Name`, `Gender`, `DOB`, `Address`, `Aadhar`, `Balance`) VALUES
(1, 'SBI23432310001', 'Savings', 'SBI234323', 'chandan', 'M', '2018-09-06', 'xyz xyz', '234432234', 20500);

-- --------------------------------------------------------

--
-- Table structure for table `branchtable`
--

CREATE TABLE `branchtable` (
  `Id` int(11) NOT NULL,
  `Name` varchar(120) NOT NULL,
  `BCode` varchar(15) NOT NULL,
  `Address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branchtable`
--

INSERT INTO `branchtable` (`Id`, `Name`, `BCode`, `Address`) VALUES
(1, 'newjersy', 'SBI234323', 'xyz');

-- --------------------------------------------------------

--
-- Table structure for table `employeetable`
--

CREATE TABLE `employeetable` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Branch` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employeetable`
--

INSERT INTO `employeetable` (`Id`, `Name`, `Branch`) VALUES
(1, 'arun', 'SBI234323');

-- --------------------------------------------------------

--
-- Table structure for table `servicetable`
--

CREATE TABLE `servicetable` (
  `Date` date NOT NULL,
  `Account_Num` varchar(15) DEFAULT NULL,
  `ServiceName` varchar(100) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `TransactionId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servicetable`
--

INSERT INTO `servicetable` (`Date`, `Account_Num`, `ServiceName`, `Description`, `Amount`, `TransactionId`) VALUES
('2018-09-06', 'SBI23432310001', 'online banking', 'done', 500, 2);

-- --------------------------------------------------------

--
-- Table structure for table `transactiontable`
--

CREATE TABLE `transactiontable` (
  `Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Account_Num` varchar(15) DEFAULT NULL,
  `Transaction_Type` varchar(15) DEFAULT NULL,
  `Amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactiontable`
--

INSERT INTO `transactiontable` (`Id`, `Date`, `Account_Num`, `Transaction_Type`, `Amount`) VALUES
(1, '2018-09-06', 'SBI23432310001', 'Credit', 21000),
(2, '2018-09-06', 'SBI23432310001', 'Debit', 500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounttable`
--
ALTER TABLE `accounttable`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `branchtable`
--
ALTER TABLE `branchtable`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `employeetable`
--
ALTER TABLE `employeetable`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `servicetable`
--
ALTER TABLE `servicetable`
  ADD KEY `par_ind` (`TransactionId`);

--
-- Indexes for table `transactiontable`
--
ALTER TABLE `transactiontable`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounttable`
--
ALTER TABLE `accounttable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `branchtable`
--
ALTER TABLE `branchtable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employeetable`
--
ALTER TABLE `employeetable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transactiontable`
--
ALTER TABLE `transactiontable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `servicetable`
--
ALTER TABLE `servicetable`
  ADD CONSTRAINT `fk_tranTable` FOREIGN KEY (`TransactionId`) REFERENCES `transactiontable` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
