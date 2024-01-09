-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 06, 2023 at 06:00 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carrental`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookedcar`
--

CREATE TABLE `bookedcar` (
  `id` int(100) NOT NULL,
  `c_id` int(100) NOT NULL,
  `c_name` varchar(255) NOT NULL,
  `c_number` varchar(255) NOT NULL,
  `source` varchar(255) NOT NULL,
  `destination` varchar(255) NOT NULL,
  `km` int(100) NOT NULL,
  `date` varchar(255) NOT NULL,
  `sc_id` int(100) NOT NULL,
  `sc_name` varchar(255) NOT NULL,
  `sc_seat` int(100) NOT NULL,
  `sc_ppkm` double NOT NULL,
  `bill` double NOT NULL,
  `bill_paid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookedcar`
--

INSERT INTO `bookedcar` (`id`, `c_id`, `c_name`, `c_number`, `source`, `destination`, `km`, `date`, `sc_id`, `sc_name`, `sc_seat`, `sc_ppkm`, `bill`, `bill_paid`) VALUES
(1, 1, 'Prit', '9426738971', 'surat', 'ahmedabad', 300, '2024/10/08', 1, 'Fortuner', 7, 30, 10620, 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `id` int(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `seat` int(100) NOT NULL,
  `ppkm` double NOT NULL,
  `noOfcar` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`id`, `name`, `seat`, `ppkm`, `noOfcar`) VALUES
(1, 'Fortuner', 7, 30, 0),
(2, 'Verna', 5, 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `number`, `email`, `password`) VALUES
(1, 'Prit', '9426738971', 'pritpatel@gmail.com', 'Pri99742$');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookedcar`
--
ALTER TABLE `bookedcar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookedcar`
--
ALTER TABLE `bookedcar`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
