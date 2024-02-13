INSERT INTO customers(nombre, apellido,email,create_at) VALUES ('Rashel', 'Alvarez', 'rashelalvarez21@gmail.com','2023-06-25'); 
INSERT INTO customers(nombre, apellido,email,create_at) VALUES ('Mariana', 'Alvarez', 'mariana@gmail.com','2023-06-28'); 
INSERT INTO customers(nombre, apellido,email,create_at) VALUES ('Jose', 'Perez', 'josep@gmail.com','2023-06-25'); 
INSERT INTO customers(nombre, apellido,email,create_at) VALUES ('Carlos', 'Abreu', 'carlosabre@gmail.com','2023-06-13'); 
INSERT INTO customers(nombre, apellido,email,create_at) VALUES ('Eugenio', 'Lopez', 'eugenil@gmail.com','2023-06-14'); 

INSERT INTO users(username, password, enabled, email) values ('rashel','$2a$10$08DfDHUJSdYoENisNdQnxOIbuoySxtPkR1HBwCeoteQ5JFrel66HO',1, 'rashelalvarez21@gmail.com');
INSERT INTO users(username, password, enabled, email) values ('jose','$2a$10$ypGA8VzUFOnu5bPLExzrVOHtwffoKrJRYS/.f84mOD9b/h.iI5/xC',1, 'pedro@gmail.com');

INSERT INTO roles(nombre) values ('ROLE_USER');
INSERT INTO roles(nombre) values ('ROLE_ADMIN');

INSERT INTO users_roles(user_id,roles_id) values (1,1);
INSERT INTO users_roles(user_id,roles_id) values (2,2);
INSERT INTO users_roles(user_id,roles_id) values (2,1);