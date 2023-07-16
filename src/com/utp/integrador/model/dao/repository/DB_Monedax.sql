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
INSERT INTO Pais (idPais,nombre) VALUES ('P002','Estados Unidos');
INSERT INTO Pais (idPais,nombre) VALUES ('P003','España');
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
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M001','Soles','S/.',3.69,'03/05/2023 23:23:48','P001');
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M002','Dolar','$.',2.30,'03/05/2023 23:23:48','P002');
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M003','Soles','$.',3.57,'15/07/2023 15:16:48','P001');
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M004','Euro','€.',3.60,'15/07/2023 08:16:48','P003');
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M005','Dolar','$.',1.69,'15/07/2023 16:08:48','P002');
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M006','Euro','€.',4.60,'15/07/2023 16:19:48','P003');
INSERT INTO Moneda (idMoneda,nombre,simbolo,tipoCambio,fechaHora,idPais) VALUES ('M007','Soles','$.',3.40,'15/07/2023 16:22:48','P001');
SELECT * FROM Moneda;

SELECT MIN(idMoneda) AS id, nombre
FROM Moneda
GROUP BY nombre;

SELECT * FROM Moneda WHERE nombre = 'Soles'
ORDER BY STR_TO_DATE(fechaHora, '%d/%m/%Y %H:%i:%s') DESC
LIMIT 1;

SELECT M.idMoneda, M.nombre, M.simbolo, M.tipoCambio, M.fechaHora, M.idPais
FROM Moneda M
JOIN (
    SELECT nombre, MAX(fechaHora) AS ultimaFechaHora
    FROM Moneda
    GROUP BY nombre
) UltimaFecha ON M.nombre = UltimaFecha.nombre AND M.fechaHora = UltimaFecha.ultimaFechaHora;

-- --Tabla usuario
CREATE TABLE Usuario(
       idUsuario  VARCHAR(50) PRIMARY KEY NOT NULL,
       dni VARCHAR(8) NOT NULL,
       nombres VARCHAR(100) NULL,
       apellidos VARCHAR(100) NULL,
       email VARCHAR(100) NULL,
       password VARBINARY(12)  NOT NULL
);
INSERT INTO Usuario (idUsuario, dni, nombres, apellidos, email, password) VALUES ('U001','admin','admin','admin','admin@gmail.com','admin');
SELECT * FROM Usuario;
select * from usuario where dni ='admin' and password = 'admin';

-- --Tabla cuenta bancaria 
CREATE TABLE CuentaBancaria(
       idCuentaBancaria VARCHAR(50) PRIMARY KEY NOT NULL,
	   idUsuario VARCHAR(50) NOT NULL,
       idMoneda VARCHAR(50) NOT NULL,
       nombreCuenta VARCHAR (50) NULL,
       nroCuenta VARCHAR(20) NULL,
       saldo DECIMAL(10, 2),
       FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

INSERT INTO CuentaBancaria (idCuentaBancaria, idUsuario, idMoneda, nombreCuenta, nroCuenta, saldo) VALUES ('CB001', 'U001','M001', 'Ahorros Soles', '1234-1234-1234-1111', 1000);
INSERT INTO CuentaBancaria (idCuentaBancaria, idUsuario, idMoneda,nombreCuenta, nroCuenta, saldo) VALUES ('CB002', 'U001','M002','Ahorro Dolares', '1234-1234-1234-2222', 500);
SELECT * FROM CuentaBancaria;

UPDATE CuentaBancaria SET saldo=980 WHERE idCuentaBancaria='CB001';

SELECT cb.*
FROM CuentaBancaria cb
JOIN Usuario u ON cb.idUsuario = u.idUsuario
WHERE u.dni = '48136555';

-- Creación de la tabla Transaccion
CREATE TABLE Transaccion (
  idTransaccion VARCHAR(50) PRIMARY KEY NOT NULL,
  idUsuario VARCHAR(50),
  idCuentaOrigen VARCHAR(50),
  idCuentaDestino VARCHAR(50),
  idMoneda VARCHAR(50),
  montoInicial DECIMAL(10, 2),
  montoFinal DECIMAL(10, 2),
  fechaTransaccion VARCHAR(50) NULL,
  FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
  FOREIGN KEY (idMoneda) REFERENCES Moneda(idMoneda)
);
INSERT INTO Transaccion (idTransaccion, idUsuario, idCuentaOrigen, idCuentaDestino, idMoneda, montoInicial, montoFinal, fechaTransaccion) 
VALUES ('T001', 'U001', 'CB001', 'CB002', 'M001',50, 100, '2023-05-15');
INSERT INTO Transaccion (idTransaccion, idUsuario, idCuentaOrigen, idCuentaDestino, idMoneda, montoInicial, montoFinal, fechaTransaccion) 
VALUES ('T002', 'U001', 'CB002', 'CB001', 'M001',200, 278, '2023-05-16');

SELECT * FROM Transaccion;	
	


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

