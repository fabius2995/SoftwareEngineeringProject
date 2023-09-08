-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Creato il: Feb 03, 2018 alle 18:11
-- Versione del server: 5.6.30
-- Versione PHP: 5.5.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `concessionaria`
--
CREATE DATABASE IF NOT EXISTS `concessionaria` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `concessionaria`;

-- --------------------------------------------------------

--
-- Struttura della tabella `auto_nuove`
--

DROP TABLE IF EXISTS `auto_nuove`;
CREATE TABLE IF NOT EXISTS `auto_nuove` (
  `id` int(10) NOT NULL,
  `Marca` varchar(20) DEFAULT NULL,
  `Modello` varchar(20) DEFAULT NULL,
  `Path` varchar(80) NOT NULL,
  `Prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `auto_nuove`
--

INSERT INTO `auto_nuove` (`id`, `Marca`, `Modello`, `Path`, `Prezzo`) VALUES
(1, 'Audi', 'A1', 'Immagini/Auto Nuove/Audi/A1.jpg', 0),
(2, 'Hyundai', 'ix35', 'Immagini/Auto Nuove/Hyundai/hyundai ix35.jpg', 0),
(3, 'Audi', 'Q2', 'Immagini/Auto Nuove/Audi/Q2.jpg', 0),
(4, 'Audi', 'R8', 'Immagini/Auto Nuove/Audi/R8.jpg', 0),
(5, 'Audi', 'TT', 'Immagini/Auto Nuove/Audi/TT.jpg', 0),
(6, 'Opel', 'Mokka', 'Immagini/Auto Nuove/Opel/opel mokka.jpg', 0),
(7, 'BMW', 'serie 1', 'Immagini/Auto Nuove/BMW/bmw serie 1.jpg', 0),
(8, 'Mercedes', 'Classe A', 'Immagini/Auto Nuove/Mercedes/Mercedes Classe A.jpg', 0),
(9, 'Renault', 'Talisman', 'Immagini/Auto Nuove/Renault/Talisman.jpg', 0),
(10, 'Alfa Romeo', 'Giulietta', 'Immagini/Auto Nuove/Alfa Romeo/Giulietta.jpg', 0),
(11, 'Fiat', '124 Abarth', 'Immagini/Auto Nuove/Fiat/Fiat.jpg', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello`
--

DROP TABLE IF EXISTS `carrello`;
CREATE TABLE IF NOT EXISTS `carrello` (
  `userClient` varchar(30) NOT NULL,
  `id_ricambio` int(11) NOT NULL,
  `qnt` int(11) NOT NULL,
  `prezzo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `carrello`
--

INSERT INTO `carrello` (`userClient`, `id_ricambio`, `qnt`, `prezzo`) VALUES
('pippo', 6, 1, 122.2);

-- --------------------------------------------------------

--
-- Struttura della tabella `dipendenti`
--

DROP TABLE IF EXISTS `dipendenti`;
CREATE TABLE IF NOT EXISTS `dipendenti` (
  `uname` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `dipendenti`
--

INSERT INTO `dipendenti` (`uname`, `pass`, `nome`, `cognome`) VALUES
('alfredoRossi@hotmail.it', 'aaaaaaaa', 'Alfredo', 'Rossi');

-- --------------------------------------------------------

--
-- Struttura della tabella `members`
--

DROP TABLE IF EXISTS `members`;
CREATE TABLE IF NOT EXISTS `members` (
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `via` varchar(30) NOT NULL,
  `civico` varchar(10) NOT NULL,
  `cap` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `members`
--

INSERT INTO `members` (`Nome`, `Cognome`, `uname`, `pass`, `data`, `via`, `civico`, `cap`) VALUES
('Alfredo', 'Corrado Mancino', 'alfredo@hotmail.it', 'aaaaaaaa', '2018-02-03', 'Via eccomi', '1', '84014');

-- --------------------------------------------------------

--
-- Struttura della tabella `ordine`
--

DROP TABLE IF EXISTS `ordine`;
CREATE TABLE IF NOT EXISTS `ordine` (
  `via` varchar(30) NOT NULL,
  `cap` varchar(10) NOT NULL,
  `civico` varchar(10) NOT NULL,
  `qnt` int(11) NOT NULL,
  `totale` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `user` varchar(30) NOT NULL,
  `id_ricambio` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ordine`
--

INSERT INTO `ordine` (`via`, `cap`, `civico`, `qnt`, `totale`, `id`, `user`, `id_ricambio`) VALUES
('Via eccomi', '84014', '1', 1, 40, 4, 'alfredo@hotmail.it', 1),
('Via eccomi', '84014', '1', 6, 733, 5, 'alfredo@hotmail.it', 6);

-- --------------------------------------------------------

--
-- Struttura della tabella `preventivo`
--

DROP TABLE IF EXISTS `preventivo`;
CREATE TABLE IF NOT EXISTS `preventivo` (
  `id_auto` int(11) NOT NULL,
  `userClient` varchar(30) NOT NULL,
  `data` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `preventivo`
--

INSERT INTO `preventivo` (`id_auto`, `userClient`, `data`) VALUES
(4, 'alfredo@hotmail.it', '2018-2-3');

-- --------------------------------------------------------

--
-- Struttura della tabella `ricambi`
--

DROP TABLE IF EXISTS `ricambi`;
CREATE TABLE IF NOT EXISTS `ricambi` (
  `id` int(11) NOT NULL,
  `prezzo` double NOT NULL,
  `path` varchar(80) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `qnt` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ricambi`
--

INSERT INTO `ricambi` (`id`, `prezzo`, `path`, `tipo`, `marca`, `qnt`) VALUES
(1, 39.99, 'Immagini/Ricambi/Bosh-Filtro olio.jpg', 'Filtro olio', 'Bosh ', 448),
(6, 122.2, 'Immagini/Ricambi/Merchedes-Disco freno .jpg', 'Freno', 'Mercedes', 99);

-- --------------------------------------------------------

--
-- Struttura della tabella `rifornimento`
--

DROP TABLE IF EXISTS `rifornimento`;
CREATE TABLE IF NOT EXISTS `rifornimento` (
  `d_consegna` date NOT NULL,
  `n_pezzi` int(11) NOT NULL,
  `userDip` varchar(20) NOT NULL,
  `id_ricambio` int(11) NOT NULL,
  `d_richiesta` date NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `auto_nuove`
--
ALTER TABLE `auto_nuove`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `carrello`
--
ALTER TABLE `carrello`
  ADD PRIMARY KEY (`userClient`,`id_ricambio`);

--
-- Indici per le tabelle `dipendenti`
--
ALTER TABLE `dipendenti`
  ADD PRIMARY KEY (`uname`);

--
-- Indici per le tabelle `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`uname`);

--
-- Indici per le tabelle `ordine`
--
ALTER TABLE `ordine`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_1` (`user`),
  ADD KEY `index_2` (`id_ricambio`);

--
-- Indici per le tabelle `preventivo`
--
ALTER TABLE `preventivo`
  ADD PRIMARY KEY (`id_auto`,`userClient`),
  ADD KEY `fk2` (`userClient`);

--
-- Indici per le tabelle `ricambi`
--
ALTER TABLE `ricambi`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `rifornimento`
--
ALTER TABLE `rifornimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_1` (`userDip`) USING BTREE;

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `ordine`
--
ALTER TABLE `ordine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT per la tabella `ricambi`
--
ALTER TABLE `ricambi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT per la tabella `rifornimento`
--
ALTER TABLE `rifornimento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `ordine`
--
ALTER TABLE `ordine`
  ADD CONSTRAINT `fk_2` FOREIGN KEY (`user`) REFERENCES `members` (`uname`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_3` FOREIGN KEY (`id_ricambio`) REFERENCES `ricambi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `preventivo`
--
ALTER TABLE `preventivo`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`id_auto`) REFERENCES `auto_nuove` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`userClient`) REFERENCES `members` (`uname`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `rifornimento`
--
ALTER TABLE `rifornimento`
  ADD CONSTRAINT `fk_1` FOREIGN KEY (`userDip`) REFERENCES `dipendenti` (`uname`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
