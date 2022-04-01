create table users
(
	id int auto_increment,
	name varchar(255) null,
	constraint users_pk
		primary key (id)
);

INSERT INTO users VALUES (1, 'dipanjal'), (2, 'shohan');

