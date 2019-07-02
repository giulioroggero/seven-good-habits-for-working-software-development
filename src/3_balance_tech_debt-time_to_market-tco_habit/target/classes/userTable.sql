
CREATE TABLE users ( name varchar2(50) NOT NULL, age number(2) NOT NULL, gender char(1));

INSERT INTO users (name, age, gender) VALUES ('primo utente', 20, 'M');
INSERT INTO users (name, age, gender) VALUES ('seconda utente', 20, 'F');
INSERT INTO users (name, age, gender) VALUES ('terzo utente', 20, 'M');

grant select, insert, update, delete on users to system;