-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Računalo: localhost
-- Vrijeme generiranja: Kol 16, 2015 u 06:52 PM
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

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `IGRAC`
--

CREATE TABLE IF NOT EXISTS `IGRAC` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FID_TIM` int(11) NOT NULL,
  `IME` varchar(30) CHARACTER SET cp1250 COLLATE cp1250_croatian_ci NOT NULL,
  `PREZIME` varchar(40) CHARACTER SET cp1250 COLLATE cp1250_croatian_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Izbacivanje podataka za tablicu `IGRAC`
--

INSERT INTO `IGRAC` (`ID`, `FID_TIM`, `IME`, `PREZIME`) VALUES
(1, 1, 'VEDRAN', 'POROPAT'),
(2, 1, 'PERO', 'ZUCALO'),
(3, 1, 'DARJAN', 'BUKOVIĆ'),
(4, 1, 'GERI', 'SMOKVINA'),
(5, 2, 'BRUNO', 'PAVLEKOVIĆ'),
(6, 2, 'TEO', 'BJELOPERA'),
(7, 2, 'NIKŠA', 'KARAČ'),
(8, 2, 'VLAHO', 'PRIMORAC'),
(9, 3, 'VLATKO', 'VUKOVIĆ'),
(10, 3, 'LUKA', 'VUKŠIĆ'),
(11, 3, 'NIKOLA', 'NINIĆ'),
(12, 3, 'LUKA', 'MARČIĆ'),
(13, 4, 'KRSTO', 'KNEZ'),
(14, 4, 'ALDO', 'KATIĆ'),
(15, 4, 'ANTE', 'ŠOSA'),
(16, 4, 'MARKO', 'DENKOVIĆ KRALJEV');

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `STADION`
--

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
  `UUID` varchar(50) COLLATE cp1250_croatian_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=cp1250 COLLATE=cp1250_croatian_ci COMMENT='Podaci o stadionu - mjestu odigravanja';

--
-- Izbacivanje podataka za tablicu `STADION`
--

INSERT INTO `STADION` (`ID`, `DRZAVA`, `GRAD`, `PTT`, `ADRESA`, `NAZIV`, `KOMENTAR`, `LATT`, `LONG`, `UUID`) VALUES
(1, 'HRVATSKA', 'DUBROVNIK', '20000', 'DON FRANA BULIĆA 1', 'DANČE', 'U JUGA', 0, 0, NULL),
(2, 'HRVATSKA', 'DUBROVNIK', '20000', 'NA PONTI BB', 'PORAT', 'STARI GRAD', 0, 0, NULL),
(3, 'HRVATSKA', 'DUBROVNIK', '20000', 'OD TABAKERIJE BB', 'KOLORINA', 'Pazi na šiloku.', 0, 0, NULL),
(4, 'HRVATSKA', 'DUBROVNIK', '20000', 'PORAT BB', 'PORPORELA', 'Pazi na šiloku.', 0, 0, NULL),
(5, 'HRVATSKA', 'DUBROVNIK', '20000', 'PERA ČINGRIJE 6', 'BELLEVUE', '', 0, 0, NULL);

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `TIM`
--

CREATE TABLE IF NOT EXISTS `TIM` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAZIV` varchar(100) CHARACTER SET cp1250 COLLATE cp1250_croatian_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Izbacivanje podataka za tablicu `TIM`
--

INSERT INTO `TIM` (`ID`, `NAZIV`) VALUES
(1, 'KVARNERČIĆ'),
(2, 'PODRUM'),
(3, 'POWERPUFF GIRLS'),
(4, 'KOLPOMORTO');

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `UTAKMICA`
--

CREATE TABLE IF NOT EXISTS `UTAKMICA` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FID_TIM1` int(11) NOT NULL,
  `FID_TIM2` int(11) NOT NULL,
  `FID_STADION` int(11) NOT NULL,
  `GOL_TIM1` int(11) NOT NULL DEFAULT '0',
  `GOL_TIM2` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Izbacivanje podataka za tablicu `UTAKMICA`
--

INSERT INTO `UTAKMICA` (`ID`, `FID_TIM1`, `FID_TIM2`, `FID_STADION`, `GOL_TIM1`, `GOL_TIM2`) VALUES
(1, 1, 2, 3, 6, 11),
(2, 3, 4, 3, 11, 5);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
