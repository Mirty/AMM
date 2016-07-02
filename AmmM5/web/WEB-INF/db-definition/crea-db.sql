/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mirty
 * Created: 2-lug-2016
 */


CREATE TABLE conto (
	idConto INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	saldo FLOAT
);



CREATE TABLE Utente (
	email VARCHAR (32),
	nome VARCHAR (32),
	cognome VARCHAR (32),
	password VARCHAR (32),
	conto INTEGER,
	PRIMARY KEY(email),
	FOREIGN KEY (conto) REFERENCES conto (idConto)
);



CREATE TABLE acquirente (
	email VARCHAR (32),
	PRIMARY KEY (email),
	FOREIGN KEY (email) REFERENCES  utente (email)
);



CREATE TABLE venditore (
	email VARCHAR (32),
	marchio VARCHAR (32),
	PRIMARY KEY(email),
	FOREIGN KEY (email) REFERENCES  utente (email)
);



CREATE TABLE categoria (
	idCategoria  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	nome VARCHAR (16),
	descrizione VARCHAR (256)
);



CREATE TABLE oggetto (
	idOggetto  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	nome VARCHAR (32),
	prezzo FLOAT,
	descrizione VARCHAR (128),
	peso FLOAT,
	urlImg VARCHAR (256),
	categoria INTEGER,
	vendutoda VARCHAR (32),
	FOREIGN KEY (categoria) REFERENCES categoria (idCategoria),
	FOREIGN KEY (vendutoda) REFERENCES venditore (email)
);



CREATE TABLE istanza (
	idOggetto  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	disponibili INTEGER,
	venduti INTEGER,
	FOREIGN KEY (idOggetto) REFERENCES oggetto (idOggetto)
);




CREATE TABLE opinione (
	idOpinione INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	titolo VARCHAR (32),
	descrizione VARCHAR (256),
	valutazione FLOAT,
	lascataDa VARCHAR (32),
	FOREIGN KEY (lascataDa) REFERENCES acquirente (email)
);



CREATE TABLE opinione_istanza (
	idOpinione INTEGER,
	idIstanza INTEGER,
	PRIMARY KEY (idOpinione, idIstanza),
	FOREIGN KEY (idOpinione) REFERENCES opinione (idOpinione),
	FOREIGN KEY (idIstanza) REFERENCES istanza (idOggetto)
);



CREATE TABLE carrello (
	possessore VARCHAR (32),
	PRIMARY KEY (possessore),
	FOREIGN KEY (possessore) REFERENCES acquirente (email)
);



CREATE TABLE carrello_istanza (
	idCarrello varchar (32),
	idIstanza INTEGER,
	PRIMARY KEY (idCarrello, idIstanza),
	FOREIGN KEY (idCarrello) REFERENCES carrello (possessore),
	FOREIGN KEY (idIstanza) REFERENCES istanza (idOggetto)
);



CREATE TABLE ordine (
	idOrdine INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	dataOrdine DATE,
	totaleSpesa FLOAT,
	fattoDa VARCHAR (32),
	FOREIGN KEY (fattoDa) REFERENCES acquirente (email)
);



CREATE TABLE ordine_istanza (
	idOrdine INTEGER,
	idIstanza INTEGER,
	PRIMARY KEY (idOrdine, idIstanza),
	FOREIGN KEY (idOrdine) REFERENCES ordine (idOrdine),
	FOREIGN KEY (idIstanza) REFERENCES istanza (idOggetto)
);



INSERT INTO conto VALUES 
(default, 15.7), (default, 98), (default, 345), (default, 214), (default, 765.12), (default, 11), (default, 85), (default, 590), (default, 12.3), (default, 3.47), (default, 87.5), (default, 15.12), (default, 119.12), (default, 27);

INSERT INTO utente VALUES
('federico_botta@gmail.com', 'Federico', 'Botta', 'pass', 9), ('cecilia_di francesco@gmail.com', 'Cecilia', 'Di Francesco', 'pass', 5), ('manuela_boi@gmail.com', 'Manuela', 'Boi', 'pass', 5), ('emma_rossi@gmail.com', 'Emma', 'Rossi', 'pass', 4), ('carla_vargiu@gmail.com', 'Carla', 'Vargiu', 'pass', 12), ('roberta_mussolini@gmail.com', 'Roberta', 'Mussolini', 'pass', 1), ('stefano_boi@gmail.com', 'Stefano', 'Boi', 'pass', 12), ('roberta_verdi@gmail.com', 'Roberta', 'Verdi', 'pass', 11), ('manuela_carta@gmail.com', 'Manuela', 'Carta', 'pass', 3), ('giulietta_pellegrini@gmail.com', 'Giulietta', 'Pellegrini', 'pass', 8), ('marisa_botta@gmail.com', 'Marisa', 'Botta', 'pass', 7), ('manuela_pes@gmail.com', 'Manuela', 'Pes', 'pass', 5), ('cecilia_boi@gmail.com', 'Cecilia', 'Boi', 'pass', 1), ('stefano_carta@gmail.com', 'Stefano', 'Carta', 'pass', 9), ('federico_porru@gmail.com', 'Federico', 'Porru', 'pass', 7), ('michael_atzori@gmail.com', 'Michael', 'Atzori', 'pass', 12), ('salvatore_vargiu@gmail.com', 'Salvatore', 'Vargiu', 'pass', 1), ('genoveffa_mostallino@gmail.com', 'Genoveffa', 'Mostallino', 'pass', 6), ('cecilia_vargiu@gmail.com', 'Cecilia', 'Vargiu', 'pass', 4), ('romilda_murgia@gmail.com', 'Romilda', 'Murgia', 'pass', 5), ('andrea_planu@gmail.com', 'Andrea', 'Planu', 'pass', 8), ('filippo_mascia@gmail.com', 'Filippo', 'Mascia', 'pass', 13), ('carla_verdi@gmail.com', 'Carla', 'Verdi', 'pass', 1), ('gioia_mussolini@gmail.com', 'Gioia', 'Mussolini', 'pass', 3), ('manuela_atzeni@gmail.com', 'Manuela', 'Atzeni', 'pass', 4), ('michael_murgia@gmail.com', 'Michael', 'Murgia', 'pass', 9), ('romilda_picciau@gmail.com', 'Romilda', 'Picciau', 'pass', 4), ('federico_pellegrini@gmail.com', 'Federico', 'Pellegrini', 'pass', 1), ('roberta_carcangiu@gmail.com', 'Roberta', 'Carcangiu', 'pass', 4);

INSERT INTO acquirente VALUES 
('federico_botta@gmail.com'), ('cecilia_di francesco@gmail.com'), ('manuela_boi@gmail.com'), ('emma_rossi@gmail.com'), ('carla_vargiu@gmail.com'), ('roberta_mussolini@gmail.com'), ('stefano_boi@gmail.com'), ('roberta_verdi@gmail.com'), ('manuela_carta@gmail.com'), ('giulietta_pellegrini@gmail.com'), ('marisa_botta@gmail.com'), ('manuela_pes@gmail.com'), ('cecilia_boi@gmail.com'), ('stefano_carta@gmail.com'), ('federico_porru@gmail.com'), ('michael_atzori@gmail.com'), ('salvatore_vargiu@gmail.com'), ('genoveffa_mostallino@gmail.com'), ('cecilia_vargiu@gmail.com'), ('romilda_murgia@gmail.com');

INSERT INTO venditore VALUES
('andrea_planu@gmail.com','Barilla'), ('filippo_mascia@gmail.com','Voiello'), ('carla_verdi@gmail.com','Galbani'), ('gioia_mussolini@gmail.com','San Carlo'), ('manuela_atzeni@gmail.com','Bertolli'), ('michael_murgia@gmail.com','Arborea'), ('romilda_picciau@gmail.com','Perugina'), ('federico_pellegrini@gmail.com','Ferrero');

INSERT INTO opinione VALUES
(default,'Super consigliato!', 'Ne ho comprato una confezione giusto per provare, ma devo dire di essere rimasto sorpreso! Consiglio il prodotto.', 8, 'federico_botta@gmail.com'), (default,'Pensavo meglio', 'Ne ho comprato una confezione giusto per provare, ma devo dire di esserne rimasta deluso!', 5, 'federico_botta@gmail.com'),(default,'È già la terza confezione!', 'Non riesco più a fare a meno di questo prodotto. I miei bambini ne vanno matti.', 10, 'manuela_pes@gmail.com'),(default,'È stato un affarone!!', 'Ho acquistato questo prodotto per una cena a lume di candela. Il risultato è stato impeccabile! Assolutamente consigliato!!!1', 10, 'roberta_verdi@gmail.com'), (default,'BEST PRODUCT EVER!', 'I eat this for the first time when I was in Italy, it absolutely blowed my mind, it was so tasty that I couldt stop thinking of it. Thanks to Eataly I am now able to buy this amazing product! Im feeling like Marcel Proust right now! Thank you Eataly!', 9, 'romilda_murgia@gmail.com');

INSERT INTO categoria VALUES (default, 'Integrale', ''),(default, 'Formaggi', ''),(default, 'Senza glutine', ''),(default, 'Salumi', ''),(default, 'Classico', '');

INSERT INTO oggetto (idOggetto, prezzo, nome, vendutoda, categoria, descrizione, urlimg, peso) VALUES 
(default, 2.5, 'Spaghetti integrali', 'andrea_planu@gmail.com', 1, 'Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.','barillaInSpaghetti.jpg', 2),
(default, 2.7, 'Pennette rigate integrali', 'andrea_planu@gmail.com', 1, 'Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.','barillaInSpaghetti.jpg',2),
(default, 3.1, 'Mezze Penne rigate integrali', 'andrea_planu@gmail.com', 1 , 'Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.','barillaInMezzePenneRigate.jpg', 2);

INSERT INTO istanza VALUES (1,75,90), (2,100,94), (3,12,45);


