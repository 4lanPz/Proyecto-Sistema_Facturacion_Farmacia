# Proyecto_Casillas-Catani-Perez-Revelo.
Casillas
Catani
Perez
Revelo

En los requerimientos que se pide para la farmacia FARMACET:

El sistema debe tener un login que me permita ingresar como administrador o como cajero.
![image](https://github.com/Alan-Perez02/Proyecto_Casillas-Catani-Perez-Revelo/assets/117744029/effafdb9-e743-4152-8f01-ce870c3a4c51)

El cajero deberà:
- Realizar una transacción de compra, cada transacción debe ser guardada en conjunto con el cajero que hizo la transacción.
- Al final de la transacción de compra deberá generar una nota de venta (similar a una factura) en pdf.
- Cuando compre determinado producto se deberá reducir del stock.

![image](https://github.com/Alan-Perez02/Proyecto_Casillas-Catani-Perez-Revelo/assets/117744029/1a596db1-8758-4dfc-be69-a6604b3bab04)

El administrador deberà:
- Ingresar productos a stock.
- Revisar las ventas realizadas por todos los vendedores e individualmente.
- Agregar usuarios cajeros.
  
![image](https://github.com/Alan-Perez02/Proyecto_Casillas-Catani-Perez-Revelo/assets/117744029/b312821f-fad3-4ebc-a025-b5c3d24316ca)

Base de Datos

DROP DATABASE PROYECTO2023A;
Create database PROYECTO2023A;
use PROYECTO2023A;

Create Table Administrador(
ID int primary key,
Correo varchar(25),
Nombre varchar(15),
Apellido varchar(15),
Contrasenia varchar(30));

Create Table Cajero(
ID int primary key,
Nombre varchar(15),
Apellido varchar(15),
Correo varchar(25),
Contrasenia varchar(30));

Create Table Producto(
Cod int primary key,
Nom varchar(35),
Precio double,
Stock int);

Create Table Cliente(
Cedula int primary key,
Nombrecli varchar(35),
Apelcli varchar(35),
Correocli varchar(35),
Telef int);

Create Table Factura(
numfac int,
cantidad int,
IDCaj int,
CedulaCli int,
Codprod int,
FOREIGN KEY (Codprod) REFERENCES Producto(Cod),
FOREIGN KEY (CedulaCli) REFERENCES Cliente(Cedula),
FOREIGN KEY (IDCaj) REFERENCES Cajero(ID));

insert into producto
values(1,'Buprex',1.20,100),
(2,'Paracetamol',1.30,100),
(3,'Protector solar',1.50,100),
(4,'Lemonflu',1.80,100),
(5,'Aspirina',2.90,100),
(6,'Alcohol',1.50,100),
(7,'Gel',0.90,100),
(8,'Vendas',1.00,100),
(9,'Ibuprofeno',3.50,100),
(10,'Tenciflex',1.40,100);
insert into cajero
values(1,'Kevin','Quishpe','correo@gmail.com','contra123');
insert into Administrador
values(1,'correo@gmail.com','Kevin','Quishpe','contra123');
