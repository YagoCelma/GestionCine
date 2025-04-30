CREATE TABLE IF NOT EXISTS empleados(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono INT(15) NOT NULL,
    email VARCHAR(100) NOT NULL, --samu 
    puesto VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    fecha_contratacion DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    apellido VARCHAR(100) NOT NULL, --yago
    telefono INT(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS flujo_caja (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('INGRESO', 'GASTO') NOT NULL,
    descripcion TEXT NOT NULL,
    cantidad DECIMAL(10,2) NOT NULL,
    fecha DATE NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS proveedor(
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    telefono INT NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventario_peliculas (
    id_inventario_peliculas INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(50),
    cantidad INT NOT NULL,
    cantidad_minima INT NOT NULL, --hacer un trigger
    id_proveedor INT NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE IF NOT EXISTS inventario_productos (
    id_inventario_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100),
    cantidad INT NOT NULL,
    cantidad_minima INT NOT NULL, --hacer otro trigger
    id_proveedor INT NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE IF NOT EXISTS peliculas (
    id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    duracion INT NOT NULL,
    clasificacion VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    en_cartelera BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS entradas (
    id_entrada INT AUTO_INCREMENT PRIMARY KEY,
    id_pelicula INT NOT NULL,
    tipo ENUM('descuento_nino', 'descuento_jubilado', 'normal'),
    precio DECIMAL(3, 2) NOT NULL,
    hora TIME NOT NULL, --andres
    fecha DATE NOT NULL,
    id_sala INT NOT NULL,
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala),
    FOREIGN KEY (id_pelicula) REFERENCES peliculas(id_pelicula)
);

CREATE TABLE IF NOT EXISTS sala (
    id_sala INT AUTO_INCREMENT PRIMARY KEY, 
    capacidad INT NOT NULL
);

CREATE TABLE IF NOT EXISTS pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_articulo INT NOT NULL,
    cantidad INT NOT NULL,
    precio_total DECIMAL(10, 2) NOT NULL,
    estado ENUM('PENDIENTE', 'PAGADO', 'ENTREGADO', 'CANCELADO') NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS salas_peliculas( 
    id INT AUTO_INCREMENT PRIMARY KEY, 
    nombre_pelicula VARCHAR(50) NOT NULL, 
	fecha_incio_emision DATE NOT NULL,  
	fecha_fin_emision DATE NOT NULL,  
    hora_inicio TIME NOT NULL,  
    hora_fin TIME NOT NULL, --Establecer un margen 15 minutos antes de emepezar una pelicula 
    id_sala INT NOT NULL, 
    id_pelicula INT NOT NULL ,  
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala), 
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula),	  
); 

CREATE TABLE IF NOT EXISTS emision(
    id
    nombre
    duracion
    fecha_inicio_emision
    fecha_fin_emision

)

CREATE TABLE IF NOT EXISTS precio_producto(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL, --isma
    precio DEC(5,2) NOT NULL
); 


CREATE TABLE IF NOT EXISTS inventario_pelicula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pelicula_id INT,
    copias INT,
    copias_disponibles INT,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id)
);