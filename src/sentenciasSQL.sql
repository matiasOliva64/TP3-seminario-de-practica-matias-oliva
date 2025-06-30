CREATE TABLE Marca(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE CategoriaProducto(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Proveedor(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    contacto VARCHAR(100),
    direccion VARCHAR(100)
);

CREATE TABLE Producto(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10,2) NOT NULL,
    stockMinimo INT NOT NULL,
    idMarca INT,
    idCategoria INT,
    idProveedor INT,
    FOREIGN KEY (idMarca) REFERENCES Marca(id),
    FOREIGN KEY (idCategoria) REFERENCES CategoriaProducto(id),
    FOREIGN KEY (idProveedor) REFERENCES Proveedor(id)
);

CREATE TABLE Stock(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idProducto INT UNIQUE,
    cantidadDisponible INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(id)
);

CREATE TABLE Cliente(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    direccion VARCHAR(150),
    telefono VARCHAR(30)
);

CREATE TABLE Factura(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    total DECIMAL(12,2) NOT NULL,
    estado VARCHAR(20),
    idCliente INT,
    FOREIGN KEY (idCliente) REFERENCES Cliente(id)
);

CREATE TABLE ItemFactura(
    id INT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10,2) NOT NULL,
    idProducto INT,
    idFactura INT,
    FOREIGN KEY (idProducto) REFERENCES Producto(id),
    FOREIGN KEY (idFactura) REFERENCES Factura(id)
);

CREATE TABLE Rol(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Usuario(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombreUsuario VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    idRol INT,
    FOREIGN KEY (idRol) REFERENCES Rol(id)
);
