-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 03 Novembre 2016 à 15:19
-- Version du serveur :  5.7.9
-- Version de PHP :  5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `beerfinder`
--

-- --------------------------------------------------------

--
-- Structure de la table `bar`
--

DROP TABLE IF EXISTS `bar`;
CREATE TABLE IF NOT EXISTS `bar` (
  `idbar` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `terrasse` tinyint(1) DEFAULT NULL,
  `open_hours` varchar(25) DEFAULT NULL,
  `happy_hours` varchar(25) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `baisseprixhh` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bar`
--

INSERT INTO `bar` (`idbar`, `name`, `address`, `terrasse`, `open_hours`, `happy_hours`, `longitude`, `latitude`, `baisseprixhh`) VALUES
('Bar1', 'BarTest', 'lskdhg', 1, '15-20', '18-19', 64, 4213, 1),
('Bar2', 'chips', 'sfsgshsgdh2', 0, '15-23', '18-19', 1, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `beer`
--

DROP TABLE IF EXISTS `beer`;
CREATE TABLE IF NOT EXISTS `beer` (
  `idbeer` varchar(50) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `idbeertype` int(11) DEFAULT NULL,
  `idorigin` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbeer`),
  KEY `FK_beer_idbeertype` (`idbeertype`),
  KEY `FK_beer_idorigin` (`idorigin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `beer`
--

INSERT INTO `beer` (`idbeer`, `name`, `price`, `description`, `idbeertype`, `idorigin`) VALUES
('beer1', 'Labierenumber1', 5, 'deliciousbeer', 1, 1),
('beer2', 'Labierenumber2', 5.5, 'Delicious beer from another ocuntry', 1, 2),
('beer3', 'Chouffe', 5.5, 'La choufffe', 4, 1),
('beer4', 'Leffe ambrée', 6.5, 'leffe', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `origin`
--

DROP TABLE IF EXISTS `origin`;
CREATE TABLE IF NOT EXISTS `origin` (
  `idorigin` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idorigin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `origin`
--

INSERT INTO `origin` (`idorigin`, `name`) VALUES
(1, 'France'),
(2, 'Belgique');

-- --------------------------------------------------------

--
-- Structure de la table `serve`
--

DROP TABLE IF EXISTS `serve`;
CREATE TABLE IF NOT EXISTS `serve` (
  `idbeer` varchar(50) NOT NULL,
  `idbar` varchar(50) NOT NULL,
  PRIMARY KEY (`idbeer`,`idbar`),
  KEY `FK_serve_idbar` (`idbar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `serve`
--

INSERT INTO `serve` (`idbeer`, `idbar`) VALUES
('beer1', 'Bar1'),
('beer4', 'Bar1'),
('beer1', 'Bar2'),
('beer2', 'Bar2'),
('beer4', 'Bar2');

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `idbeertype` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idbeertype`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `type`
--

INSERT INTO `type` (`idbeertype`, `name`) VALUES
(1, 'Blonde'),
(2, 'Ambrée'),
(3, 'Blanche'),
(4, 'IPA');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `beer`
--
ALTER TABLE `beer`
  ADD CONSTRAINT `FK_beer_idbeertype` FOREIGN KEY (`idbeertype`) REFERENCES `type` (`idbeertype`),
  ADD CONSTRAINT `FK_beer_idorigin` FOREIGN KEY (`idorigin`) REFERENCES `origin` (`idorigin`);

--
-- Contraintes pour la table `serve`
--
ALTER TABLE `serve`
  ADD CONSTRAINT `FK_serve_idbar` FOREIGN KEY (`idbar`) REFERENCES `bar` (`idbar`),
  ADD CONSTRAINT `FK_serve_idbeer` FOREIGN KEY (`idbeer`) REFERENCES `beer` (`idbeer`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
