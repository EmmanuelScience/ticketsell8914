DROP DATABASE IF EXISTS `ticketselldb`;
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
  `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL
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
  `ticketID` int auto_increment PRIMARY KEY,
  `ticketCode` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `user` int NOT NULL,
  `event` int NOT NULL,
  `ticketOwnerName` varchar(45) NOT NULL,
  CONSTRAINT `FK_user_1` FOREIGN KEY (`user`) REFERENCES `Users` (`userID`),
  CONSTRAINT `FK_event_1` FOREIGN KEY (`event`) REFERENCES `Events` (`eventID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into Tickets (ticketCode, category, price, user, event, ticketOwnerName) values ('123456789', 'VIP', 100, 1, 1, 'John Smith');

DELETE FROM events WHERE eventID = 1;
insert into Users(name, surname, address, alias, phone, email, password)
    value ('John', 'Doe', 'Su Casa', 'johnny', '+341234567', 'john@email.com', '1234');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Fabrik', 'Madrid', 'Spain', '2022-11-06 23:59:59', 'musical');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'O2', 'Paris', 'France', '2022-11-06 23:59:59', 'theater');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue Cologne', 'Cologne', 'Germany', '2022-11-06 23:59:59', 'Concert');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Fabrik', 'Madrid', 'Spain', '2022-11-06 23:59:59', 'musical');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'O2', 'Paris', 'France', '2022-12-31 23:59:59', 'theater');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue Cologne', 'Cologne', 'Germany', '2022-12-31 23:59:59', 'Concert');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Eko hotel', 'Lagos', 'Nigeria', '2022-12-31 23:59:59', 'Festival');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Casablanca Venue', 'Casablanca', 'Morocco', '2022-12-31 23:59:59', 'Drugs');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Delhi Venue', 'Delhi', 'India', '2022-12-31 23:59:59', 'Rock');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Sevilla Venue', 'Sevilla', 'Spain', '2022-12-31 23:59:59', 'musical');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue', 'Malaga', 'Spain', '2022-12-31 23:59:59', 'Rock');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue', 'Madrid', 'Spain', '2022-12-31 23:59:59', 'musical');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue', 'Madrid', 'Spain', '2022-12-26 23:59:59', 'Drugs');
insert into Events(eventName, venue, city, country, date, category)
    VALUE ('Arena', 'Venue', 'Madrid', 'Spain', '2022-11-05', 'musical');

insert into Tickets(ticketCode, category, price, user, event, ticketOwnerName)
    VALUE ('1234abcd', 'musical', 2.5, 1, 1, 'John Doe');

select * from Users;
select * from Events;
select * from Tickets;


commit;
