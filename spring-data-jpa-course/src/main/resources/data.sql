create table person(
id integer not null,
name varchar(255) not null,
location varchar(255),
date_of_birth timestamp,
primary key(id)
);

insert into person values(10001, 'Prashant', 'Gurgaon', sysdate());
insert into person values(10002, 'Peter', 'New York', sysdate());
insert into person values(10003, 'James', 'London', sysdate());
