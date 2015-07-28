-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Računalo: localhost
-- Vrijeme generiranja: Srp 28, 2015 u 12:40 PM
-- Verzija poslužitelja: 5.1.73-cll
-- PHP verzija: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza podataka: `dancehr_sidrun`
--
CREATE DATABASE IF NOT EXISTS `dancehr_sidrun` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dancehr_sidrun`;

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `STADION`
--

DROP TABLE IF EXISTS `STADION`;
CREATE TABLE IF NOT EXISTS `STADION` (
  `ID` int(11) NOT NULL,
  `DRZAVA` varchar(50) COLLATE cp1250_croatian_ci NOT NULL,
  `GRAD` varchar(80) COLLATE cp1250_croatian_ci NOT NULL,
  `PTT` varchar(15) COLLATE cp1250_croatian_ci NOT NULL,
  `ADRESA` varchar(80) COLLATE cp1250_croatian_ci NOT NULL,
  `NAZIV` varchar(100) COLLATE cp1250_croatian_ci NOT NULL,
  `KOMENTAR` varchar(200) COLLATE cp1250_croatian_ci NOT NULL,
  `LATT` double NOT NULL,
  `LONG` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1250 COLLATE=cp1250_croatian_ci COMMENT='Podaci o stadionu - mjestu odigravanja';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
