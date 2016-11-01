#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: beer
#------------------------------------------------------------

CREATE TABLE beer(
        idbeer      Varchar (50) NOT NULL ,
        name        Varchar (25) ,
        price       Double ,
        description Varchar (100) ,
        idbeertype  Int ,
        idorigin    Int ,
        PRIMARY KEY (idbeer )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: bar
#------------------------------------------------------------

CREATE TABLE bar(
        idbar        Varchar (50) NOT NULL ,
        name         Varchar (50) ,
        address      Varchar (100) ,
        terrasse     Bool ,
        open_hours   Varchar (25) ,
        happy_hours  Varchar (25) ,
        longitude    Double ,
        latitude     Double ,
        baisseprixhh Int ,
        PRIMARY KEY (idbar )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: type
#------------------------------------------------------------

CREATE TABLE type(
        idbeertype int (11) Auto_increment  NOT NULL ,
        name       Varchar (100) ,
        PRIMARY KEY (idbeertype )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: origin
#------------------------------------------------------------

CREATE TABLE origin(
        idorigin int (11) Auto_increment  NOT NULL ,
        name     Varchar (100) ,
        PRIMARY KEY (idorigin )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: serve
#------------------------------------------------------------

CREATE TABLE serve(
        idbeer Varchar (50) NOT NULL ,
        idbar  Varchar (50) NOT NULL ,
        PRIMARY KEY (idbeer ,idbar )
)ENGINE=InnoDB;

ALTER TABLE beer ADD CONSTRAINT FK_beer_idbeertype FOREIGN KEY (idbeertype) REFERENCES type(idbeertype);
ALTER TABLE beer ADD CONSTRAINT FK_beer_idorigin FOREIGN KEY (idorigin) REFERENCES origin(idorigin);
ALTER TABLE serve ADD CONSTRAINT FK_serve_idbeer FOREIGN KEY (idbeer) REFERENCES beer(idbeer);
ALTER TABLE serve ADD CONSTRAINT FK_serve_idbar FOREIGN KEY (idbar) REFERENCES bar(idbar);
