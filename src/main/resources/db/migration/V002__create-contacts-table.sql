CREATE TABLE contacts (
	id BIGSERIAL,
	name VARCHAR(45) NOT NULL,
	email VARCHAR(45) NOT NULL,
	cellphone VARCHAR(45) NOT NULL,
	user_id BIGINT,
	
	PRIMARY KEY(id),
	FOREIGN KEY (user_id) REFERENCES users (id)
);