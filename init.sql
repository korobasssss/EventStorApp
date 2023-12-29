CREATE TYPE event_type AS ENUM ('BIRTHDAY', 'MEET');

CREATE TABLE event (
	id INT NOT NULL PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
	type event_type,
	title VARCHAR(50) NOT NULL,
	data DATE
);

CREATE TABLE user_event (
	id INT NOT NULL PRIMARY KEY UNIQUE GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(50) NOT NULL,
	login VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	eventsindex VARCHAR(300) NOT NULL
)