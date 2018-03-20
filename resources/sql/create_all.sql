# Erstellen Sie dazu eine Datenbank „ATNxx_JavaTest“ in Ihrem Lokalen MySQL-Server
create database if not exists ATN97_JavaTest;
use ATN97_JavaTest;

drop table if exists Bestellungen;
drop table if exists Kunden;
drop table if exists Artikel;

# Erstellen Sie eine Tabelle „Kunden“ mit den Feldern: 
# ID (PK), Kundenname und Adresse. 
# Legen Sie einige Test-Datensätze bei der Erstellung der Tabelle mit dem „MySQL Workbench“ an.
create table Kunden (
ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
Kundenname VARCHAR(30) NOT NULL,
Adresse VARCHAR(30)
) ENGINE=INNODB;

# Erstellen Sie eine Tabelle „Artikel“ mit den Feldern: 
# ID (PK), Bezeichnung, Einheit und dem Verkaufspreis. 

create table Artikel (
ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
Bezeichnung VARCHAR(30) NOT NULL,
Einheit VARCHAR(10),
Verkaufspreis DECIMAL(10,2) NOT NULL
) ENGINE=INNODB;

# Erstellen Sie nun eine Tabelle „Bestellungen“ mit den Spalten: 
# Rechnungsnummer (PK), ArtikelID (FK), KundenID (FK), Stückzahl, Bestelldatum und Lieferdatum
create table Bestellungen ( 
Rechnungsnummer INT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
ArtikelID INT UNSIGNED NOT NULL,
KundenID INT UNSIGNED NOT NULL,
Stueckzahl INT UNSIGNED NOT NULL,
Bestelldatum date NOT NULL,
Lieferdatum date,
foreign key ( ArtikelID ) references Artikel(ID)
	on delete cascade
    on update cascade,
foreign key ( KundenID ) references Kunden(ID)
	on delete cascade
    on update cascade
) ENGINE=INNODB;

# benutzer anlegen
#drop user 'atn97'@'localhost';
#create user 'atn97'@'localhost' identified by 'abcd';
GRANT ALL PRIVILEGES ON ATN97_JavaTest.* TO 'atn97'@'localhost' WITH GRANT OPTION;
SET PASSWORD FOR 'atn97'@'localhost' = PASSWORD('abcd');

# Legen Sie einige Test-Datensätze bei der Erstellung der Tabelle mit dem „MySQL Workbench“ an.
insert into Kunden (Kundenname, Adresse) values ("Heinz", "Teichgasse 1, 8020 Graz");
insert into Kunden (Kundenname, Adresse) values ("Peter", "Keplerstraße 13, 1010 Wien");
insert into Kunden (Kundenname, Adresse) values ("Eva-Maria", "Dorfgasse 3, 6000 Oberhausen");

insert into Artikel (Bezeichnung, Einheit, Verkaufspreis) values ("Akku", "Stk", 10.99);
insert into Artikel (Bezeichnung, Einheit, Verkaufspreis) values ("Segel", "Stk", 24.90);
insert into Artikel (Bezeichnung, Einheit, Verkaufspreis) values ("Gehäuse", "Stk", 59.0);
insert into Artikel (Bezeichnung, Einheit, Verkaufspreis) values ("Getriebeöl", "ml", 29.99);

insert into Bestellungen (ArtikelID, KundenID, Stueckzahl, Bestelldatum, Lieferdatum) values (1, 1, 1, "2015-09-17", "2015-09-18");
insert into Bestellungen (ArtikelID, KundenID, Stueckzahl, Bestelldatum, Lieferdatum) values (2, 2, 10, "2015-09-15", "2015-09-14");
insert into Bestellungen (ArtikelID, KundenID, Stueckzahl, Bestelldatum, Lieferdatum) values (3, 2, 4, "2015-09-15", "2015-09-14");
insert into Bestellungen (ArtikelID, KundenID, Stueckzahl, Bestelldatum, Lieferdatum) values (4, 3, 1000, "2015-09-10", null);
insert into Bestellungen (ArtikelID, KundenID, Stueckzahl, Bestelldatum, Lieferdatum) values (3, 1, 1, "2015-09-10", null);

# gesp. prozeduren

#### Kunden
DROP PROCEDURE IF EXISTS showKunden;
DROP PROCEDURE IF EXISTS addKunde;
DROP PROCEDURE IF EXISTS changeKunde;
DROP PROCEDURE IF EXISTS delKunde;

delimiter //
create procedure showKunden ()
BEGIN
	select * from Kunden;
END //
delimiter ;

delimiter //
create procedure addKunde (IN p_kundenname VARCHAR(30), IN p_adresse VARCHAR(30))
BEGIN
	insert into Kunden (Kundenname, Adresse) 
    values (p_kundenname, p_adresse);
END //
delimiter ;

delimiter //
create procedure changeKunde (IN p_id INT unsigned, IN p_kundenname VARCHAR(30), IN p_adresse VARCHAR(30))
BEGIN
	update Kunden
    set kundenname=p_kundenname, adresse=p_adresse
    where id=p_id;
END //
delimiter ;

delimiter //
create procedure delKunde (IN p_id INT unsigned)
BEGIN
	delete from Kunden 
    where id=p_id;
END //
delimiter ;

##### Bestellungen
DROP PROCEDURE IF EXISTS showBestellungen;
DROP PROCEDURE IF EXISTS showBestellungenKunde;
DROP PROCEDURE IF EXISTS showOffeneBestellungenKunde;
DROP PROCEDURE IF EXISTS addBestellung;
DROP PROCEDURE IF EXISTS changeBestellung;
DROP PROCEDURE IF EXISTS delBestellung;

delimiter //
create procedure showBestellungen ()
BEGIN
	select kundenname, Bezeichnung, Stueckzahl, Bestelldatum
    from bestellungen
    left join kunden
    on bestellungen.KundenID=kunden.ID
    order by kundenname, Bezeichnung, Stueckzahl, Bestelldatum;
END //
delimiter ;

delimiter //
create procedure showBestellungenKunde (in p_kundenid int unsigned)
BEGIN
	select kundenname, Bezeichnung, Stueckzahl, Bestelldatum
    from bestellungen
    left join kunden
    on bestellungen.KundenID=kunden.ID
    where KundenID=p_kundenid
    order by kundenname, Bezeichnung, Stueckzahl, Bestelldatum;
END //
delimiter ;

delimiter //
create procedure showOffeneBestellungenKunde (in p_kundenid int unsigned)
BEGIN
	select kundenname, Bezeichnung, Stueckzahl, Bestelldatum
    from bestellungen
    left join kunden
    on bestellungen.KundenID=kunden.ID
    where KundenID=p_kundenid and bestellungen.Lieferdatum=null
    order by kundenname, Bezeichnung, Stueckzahl, Bestelldatum;
END //
delimiter ;

delimiter //
create procedure addBestellung 
(IN p_artikelid int unsigned, IN p_kundenid int unsigned, IN p_bestelldatum date, IN p_lieferdatum date)
BEGIN
	insert into Kunden (artikelid, kundenid, bestelldatum, Lieferdatum) 
    values (p_artikelid, p_kundenid, p_bestelldatum, p_lieferdatum);
END //
delimiter ;

delimiter //
create procedure changeBestellung 
(IN p_id INT, IN p_artikelid int unsigned, IN p_kundenid int unsigned, IN p_bestelldatum date, IN p_lieferdatum date)
BEGIN
	update bestellungen
    set ArtikelID=p_artikelid, kundenid=p_kundenid, bestelldatum=p_bestelldatum, Lieferdatum=p_lieferdatum
    where id=p_id;
END //
delimiter ;

delimiter //
create procedure delBestellung (IN p_id INT)
BEGIN
	delete from bestellungen 
    where id=p_id;
END //
delimiter ;
    

##### Artikel
DROP PROCEDURE IF EXISTS showArtikel;
DROP PROCEDURE IF EXISTS addArtikel;
DROP PROCEDURE IF EXISTS changeArtikel;
DROP PROCEDURE IF EXISTS delArtikel;

delimiter //
create procedure showArtikel ()
BEGIN
	select * from artikel;
END //
delimiter ;

delimiter //
create procedure addArtikel 
(in p_bezeichnung varchar(30), in p_einheit varchar(10), in p_verkaufspreis DECIMAL(10,2) )
BEGIN
	insert into artikel (Bezeichnung, einheit, Verkaufspreis) 
    values (p_bezeichnung, p_einheit, p_verkaufspreis);
END //
delimiter ;

delimiter //
create procedure changeArtikel 
(in p_id int unsigned, in p_bezeichnung varchar(30), in p_einheit varchar(10), in p_verkaufspreis DECIMAL(10,2))
BEGIN
	update artikel
    set bezeichnung=p_bezeichnung, einheit=p_einheit, verkaufspreis=p_verkaufspreis
    where id=p_id;
END //
delimiter ;

delimiter //
create procedure delArtikel (IN p_id INT)
BEGIN
	delete from artikel 
    where id=p_id;
END //
delimiter ;
