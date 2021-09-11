CREATE SCHEMA IF NOT EXISTS fabrica;
USE fabrica;

CREATE TABLE IF NOT EXISTS mueble(
    nombre VARCHAR(45) NOT NULL,
    precio DOUBLE NOT NULL,
    PRIMARY KEY(nombre)
);

CREATE TABLE IF NOT EXISTS pieza(
    tipo VARCHAR(45) NOT NULL,
    PRIMARY KEY(tipo)
);

CREATE TABLE IF NOT EXISTS precio_pieza(
    pieza VARCHAR(45) NOT NULL,
    costo DOUBLE NOT NULL,
    cantidad INT NOT NULL,
    estado BOOLEAN NOT NULL,
    PRIMARY KEY(pieza, costo),
    FOREIGN KEY(pieza) REFERENCES pieza(tipo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS mueble_pieza(
    cantidad_piezas INT NOT NULL,
    pieza VARCHAR(45) NOT NULL,
    mueble VARCHAR(45) NOT NULL,
    PRIMARY KEY(pieza, mueble),
    FOREIGN KEY(pieza) REFERENCES pieza(tipo) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(mueble) REFERENCES mueble(nombre) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS usuario(
    nombre VARCHAR(45) NOT NULL,
    pass VARCHAR(45) NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    estado boolean,
    PRIMARY KEY(nombre)
);

CREATE TABLE IF NOT EXISTS ensamblaje(
    id INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    costo DOUBLE NOT NULL,
    estado BOOLEAN,
    nombre_mueble VARCHAR(45) NOT NULL,
    nombre_usuario VARCHAR(45) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(nombre_mueble) REFERENCES mueble(nombre) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(nombre_usuario) REFERENCES usuario(nombre)ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS detalle_ensamblaje(
    id INT NOT NULL AUTO_INCREMENT,
    id_ensamblaje INT NOT NULL,
    costo_pieza DOUBLE NOT NULL,
    tipo_pieza VARCHAR(45) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_ensamblaje) REFERENCES ensamblaje(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS cliente(
    nit VARCHAR(45) NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    municipio VARCHAR(45),
    departamento VARCHAR(45),
    PRIMARY KEY(nit)
);

CREATE TABLE IF NOT EXISTS producto(
    id_ensamblaje INT NOT NULL,
    estado BOOLEAN NOT NULL,
    PRIMARY KEY(id_ensamblaje),
    FOREIGN KEY(id_ensamblaje) REFERENCES ensamblaje(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS venta(
    id INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    costo DOUBLE NOT NULL,
    nit_cliente VARCHAR(45) NOT NULL,
    nombre_usuario VARCHAR(45),
    PRIMARY KEY(id),
    FOREIGN KEY(nit_cliente) REFERENCES cliente(nit) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(nombre_usuario) REFERENCES usuario(nombre) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS detalle_venta(
    id INT NOT NULL AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_venta) REFERENCES venta(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_producto) REFERENCES producto(id_ensamblaje) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS devolucion(
    id INT NOT NULL AUTO_INCREMENT,
    perdida DOUBLE NOT NULL,
    fecha DATE NOT NULL,
    nombre_cliente VARCHAR(45) NOT NULL,
    id_producto INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(nombre_cliente) REFERENCES cliente(nit) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(id_producto) REFERENCES producto(id_ensamblaje) ON DELETE CASCADE ON UPDATE CASCADE
);
