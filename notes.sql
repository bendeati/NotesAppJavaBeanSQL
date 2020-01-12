-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Gép: localhost:3306
-- Létrehozás ideje: 2020. Jan 12. 20:56
-- Kiszolgáló verziója: 5.7.28-0ubuntu0.18.04.4
-- PHP verzió: 7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `notes`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `felhasznalo`
--

CREATE TABLE `felhasznalo` (
  `id` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8_hungarian_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
  `fullname` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `lastlogin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `felhasznalo`
--

INSERT INTO `felhasznalo` (`id`, `username`, `password`, `fullname`, `lastlogin`) VALUES
(1, 'null', 'null', 'null', NULL),
(2, 'bendeati', 'qwe123', 'Bende Attila', NULL),
(4, 'valaki', 'qwe123', 'Valaki Imre', NULL);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `jegyzet`
--

CREATE TABLE `jegyzet` (
  `id` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `content` text COLLATE utf8_hungarian_ci NOT NULL,
  `date1` date NOT NULL,
  `date2` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `jegyzet`
--

INSERT INTO `jegyzet` (`id`, `userID`, `title`, `content`, `date1`, `date2`) VALUES
(1, 2, 'Hétfőn óra lesz!', 'EL ne felejtsem, a kedves webes hallgatókat...', '2020-01-11', '2020-01-11'),
(2, 2, 'Szóbeli tételek', 'Beszéljünk róla', '2020-01-12', '2020-01-12');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `jegyzet`
--
ALTER TABLE `jegyzet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `userID` (`userID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `felhasznalo`
--
ALTER TABLE `felhasznalo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT a táblához `jegyzet`
--
ALTER TABLE `jegyzet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `jegyzet`
--
ALTER TABLE `jegyzet`
  ADD CONSTRAINT `jegyzet_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `felhasznalo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
