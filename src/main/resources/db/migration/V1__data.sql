CREATE TABLE categories(
	id	SERIAL PRIMARY KEY NOT NULL ,
	name	VARCHAR(35) NOT NULL
);

CREATE TABLE jokes (
	id SERIAL PRIMARY KEY NOT NULL ,
	categoryid INTEGER REFERENCES CATEGORIES(id),
	content TEXT not null ,
	likes INTEGER not null DEFAULT 0 ,
	dislikes INTEGER not null DEFAULT 0
);

INSERT INTO categories(name) VALUES ('Crni humor');
INSERT INTO categories(name) VALUES ('Mujo i Haso');
INSERT INTO categories(name) VALUES ('Ivica');
INSERT INTO categories(name) VALUES ('Plavuše');

INSERT INTO jokes(categoryid, content) VALUES (2, 'Pita Mujo svog sina:
- Sine, šta bi ti voleo da budeš kad porasteš?
- Pa, voleo bih da budem čistač bazena!
- Čistač bazena? Stvarno?
- Pa dobro, može baštovan, vodoinstaler, raznosač pica, Deda Mraz...
- Fatooo, mali nam je pronašao porniće!');
INSERT INTO jokes(categoryid, content) VALUES (1, 'Ulazi čovjek bez jedne ruke u prodavaonicu i pita:
Je li ovo second hand shop?');
INSERT INTO jokes(categoryid, content) values (3, 'Kaže Ivica Perici:
E Perice, ja mislim da Englezi baš ne vjeruju u Boga.
Zašto to misliš? - kaže Perica.
Zato što ga zovu Gad!');
INSERT INTO jokes(categoryid, content) VALUES (4, 'Dolazi plavuša u kino i kupi jednu kartu za film. Poslije dvije minute dolazi i kupuje još jednu i tako nekoliko puta. Radnik za blagajnom je pita:
Pa šta će Vam toliko karata za jedan te isti film?
Ona odgovori:
Znam ja da je dovoljna samo jedna, ali onaj kreten na ulazu mi je uvijek podere.');
