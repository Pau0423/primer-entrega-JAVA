
CREATE DATABASE IF NOT EXISTS facturacion
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE facturacion;


DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(50) NOT NULL
);


DROP TABLE IF EXISTS productos;

CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(38,2) NOT NULL,
    stock INT NOT NULL
);


DROP TABLE IF EXISTS facturas;

CREATE TABLE facturas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    fecha DATETIME NOT NULL,
    cliente_id BIGINT NOT NULL,
    cantidad_productos INT NOT NULL,
    total DECIMAL(38,2) NOT NULL,

    CONSTRAINT fk_factura_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
        ON DELETE CASCADE
);


DROP TABLE IF EXISTS detalle_factura;

CREATE TABLE detalle_factura (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(38,2) NOT NULL,

    factura_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,

    CONSTRAINT fk_detalle_factura
        FOREIGN KEY (factura_id)
        REFERENCES facturas(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (producto_id)
        REFERENCES productos(id)
);



INSERT INTO clientes (nombre, email, telefono)
VALUES
('Paula Rodríguez', 'paula@gmail.com', '099123456'),
('Juan Pérez', 'juan@gmail.com', '098888888');

INSERT INTO productos (nombre, precio, stock)
VALUES
('iPhone 16 Pro Max', 450.00, 20),
('PlayStation 5', 650.00, 15),
('SmartTV Samsung 55”', 900.00, 10);


INSERT INTO facturas (codigo, fecha, cliente_id, cantidad_productos, total)
VALUES ('FAC-TEST-001', NOW(), 1, 2, 900.00);


INSERT INTO detalle_factura (cantidad, precio_unitario, factura_id, producto_id)
VALUES
(2, 450.00, 1, 1);
