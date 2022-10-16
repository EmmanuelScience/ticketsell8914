CREATE DATABASE IF NOT EXISTS `ticketselldb`;
USE `ticketselldb`;
SET foreign_key_checks = 0;
drop table if exists `Users`;
drop table if exists `Tickets`;
drop table if exists `Events`;

CREATE TABLE IF NOT EXISTS `Users` (
  `userID` int auto_increment PRIMARY KEY,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `alias` varchar(45) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `Events` (
  `eventID` int auto_increment PRIMARY KEY,
  `eventName` varchar(45) NOT NULL,
  `venue` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `category` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `Tickets` (
  `eventID` int auto_increment PRIMARY KEY,
  `ticketCode` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `user` int NOT NULL,
  `event` int NOT NULL,
  KEY `FK_user_1` (`user`),
  KEY `FK_event_1` (`event`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into Users(name, surname, address, alias, phone, email)
    value ('John', 'Doe', 'Su Casa', 'johnny', '+341234567', 'john@email.com');

insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue', 'Madrid', 'Spain', '2022-12-31 23:59:59', 'musical');

insert into Tickets(ticketCode, category, price, user, event)
    VALUE ('1234abcd', 'musical', 2.5, 1, 1);

select * from Users;
select * from Events;
select * from Tickets;
commit;
