CREATE DATABASE IF NOT EXISTS DecathlonTransportDatabase;

USE DecathlonTransportDatabase;

CREATE TABLE IF NOT EXISTS `Orders` (
    `orderId` bigint(20) NOT NULL AUTO_INCREMENT,
    `address` varchar(255) NOT NULL,
    `city` varchar(255) NOT NULL,
    `firstName` varchar(255) NOT NULL,
    `lastName` varchar(255) NOT NULL,
    `postalCode` int(11) NOT NULL,
    `telephone` varchar(255) NOT NULL,
    PRIMARY KEY (`orderId`)
);


CREATE TABLE IF NOT EXISTS `Status` (
    `statusId` bigint(20) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`statusId`)
);

CREATE TABLE IF NOT EXISTS `Transports` (
    `transportId` bigint(20) NOT NULL AUTO_INCREMENT,
    `deliveryDate` datetime(6) NOT NULL,
    `issueDate` datetime(6) NOT NULL,
    `fk_orderId` bigint(20) DEFAULT NULL,
    `fk_statusId` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`transportId`),
    KEY `fk_statusId_reference` (`fk_statusId`),
    KEY `fk_orderId_reference` (`fk_orderId`),
    CONSTRAINT `fk_statusId_reference` FOREIGN KEY (`fk_statusId`) REFERENCES `Status` (`statusId`),
    CONSTRAINT `fk_orderId_reference` FOREIGN KEY (`fk_orderId`) REFERENCES `Orders` (`orderId`)
);

CREATE TABLE IF NOT EXISTS `Products` (
    `productId` bigint(20) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    `price` int(11) DEFAULT NULL,
    PRIMARY KEY (`productId`)
);

INSERT INTO Transports(deliveryDate, issueDate, fk_orderId, fk_statusId) VALUES ('2019-06-28', '2019-06-29', 1, 1);