create database bcprogramadores;

use bcprogramadores;



create table Usuarios(
id int auto_increment primary key not null,
ingresoUsuarios varchar(50),
ingresoContrasenia varchar(65)
);

INSERT INTO usuarios ( ingresoUsuarios, ingresoContrasenia )
VALUES ('admin', 'contra12345');


create table conclientes(
N0 int auto_increment primary key not null,
DPI varchar(150),
NomCliente varchar(150),
ApellCliente varchar(150),
DireCliente varchar(150),
N0Telefono varchar(150),
Correo varchar(150),
OcupCliente varchar(150),
IngreMensual varchar(150),
TipCuenta varchar(50),
SaldCuenta DOUBLE
);


