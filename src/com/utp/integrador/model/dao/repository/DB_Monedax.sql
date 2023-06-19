/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Usuario
 * Created: 18/06/2023
 */

CREATE DATABASE sys_monedax;

use sys_monedax;

-- Tabla Pais
CREATE TABLE Pais(
       idPais VARCHAR(50) PRIMARY KEY NOT NULL,
       nombre VARCHAR(100)
);
INSERT INTO Pais (idPais,nombre) VALUES ('P001','Perú');
INSERT INTO Pais (idPais,nombre) VALUES ('P002','Dolar');
SELECT * FROM Pais;

-- Tabla Moneda
CREATE TABLE Moneda(
       idMoneda VARCHAR(50) PRIMARY KEY NOT NULL,
       nombre VARCHAR(100) NULL,
       simbolo VARCHAR(10) NULL,
       tipoCambio DECIMAL(10, 2),       
       fechaHora VARCHAR(50) NULL,
       idPais VARCHAR(50) NULL,
       FOREIGN KEY (idPais) REFERENCES pais(idPais)
);
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M001','Dolar','$.',3.69,'03/05/2023 23:23:48','P001');
SELECT * FROM Moneda;


-- --Tabla usuario
CREATE TABLE Usuario(
       idUsuario  VARCHAR(50) PRIMARY KEY NOT NULL,
       dni VARCHAR(8) NOT NULL,
       nombres VARCHAR(100) NULL,
       apellidos VARCHAR(100) NULL,
       email VARCHAR(100) NULL,
       password VARBINARY(12)  NOT NULL
);
INSERT INTO Usuario (idUsuario, dni, nombres, apellidos, email, password) VALUES ('U001','12345678','admin','admin','admin@gmail.com',123);
SELECT * FROM Usuario;

-- --Tabla cuenta bancaria 
CREATE TABLE CuentaBancaria(
       idCuentaBancaria VARCHAR(50) PRIMARY KEY NOT NULL,
	   idUsuario VARCHAR(50) NOT NULL,
       nombreCuenta VARCHAR (50) NULL,
       nroCuenta VARCHAR(20) NULL,
       saldo DECIMAL(10, 2),
       FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

INSERT INTO CuentaBancaria (idCuentaBancaria, idUsuario, nombreCuenta, nroCuenta, saldo) VALUES ('CB001', 'U001', 'Ahorros Soles', '1234-1234-1234-1111', 1000);
INSERT INTO CuentaBancaria (idCuentaBancaria, idUsuario, nombreCuenta, nroCuenta, saldo) VALUES ('CB002', 'U001', 'Ahorro Casa', '1234-1234-1234-2222', 500);
SELECT * FROM CuentaBancaria;

-- Creación de la tabla Transaccion
CREATE TABLE Transaccion (
  idTransaccion VARCHAR(50) PRIMARY KEY NOT NULL,
  idUsuario VARCHAR(50),
  idCuentaOrigen VARCHAR(50),
  idCuentaDestino VARCHAR(50),
  idMoneda VARCHAR(50),
  montoInicial DECIMAL(10, 2),
  montoFinal DECIMAL(10, 2),
  fechaTransaccion DATE,
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
  FOREIGN KEY (idCuentaOrigen) REFERENCES CuentaBancaria(idCuentaBancaria),
  FOREIGN KEY (idCuentaDestino) REFERENCES CuentaBancaria(idCuentaBancaria),
  FOREIGN KEY (idMoneda) REFERENCES Moneda(idMoneda)
);
INSERT INTO Transaccion (idTransaccion, idUsuario, idCuentaOrigen, idCuentaDestino, idMoneda, montoInicial, montoFinal, fechaTransaccion) 
VALUES ('T001', 'U001', 'CB001', 'CB002', 'M001',50, 100, '2023-05-15');
INSERT INTO Transaccion (idTransaccion, idUsuario, idCuentaOrigen, idCuentaDestino, idMoneda, montoInicial, montoFinal, fechaTransaccion) 
VALUES ('T002', 'U001', 'CB002', 'CB001', 'M001',200, 278, '2023-05-16');

-- Creación de la tabla Historial
CREATE TABLE Historial (
  idHistorial VARCHAR(50) PRIMARY KEY NOT NULL,
  idTransaccion VARCHAR(50),
  fechaHistorial DATE,
   FOREIGN KEY (idTransaccion) REFERENCES Transaccion(idTransaccion)
);
INSERT INTO Historial (idHistorial, idTransaccion, fechaHistorial) 
VALUES ('H001', 'T001', '2023-05-16');
INSERT INTO Historial (idHistorial, idTransaccion, fechaHistorial) 
VALUES ('H002', 'T002', '2023-05-16');
