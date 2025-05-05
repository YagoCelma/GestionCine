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
    id_sala_pelicula INT NOT NULL,
    precio DECIMAL(3, 2) NOT NULL,
    asiento INT NOT NULL,
    FOREIGN KEY (id_sala_pelicula) REFERENCES salas_peliculas(id)
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
    hora_inicio TIME NOT NULL,  
    hora_fin TIME NOT NULL, --Establecer un margen 15 minutos antes de empezar una pelicula
    precio_base DEC(2,2),
    id_sala INT NOT NULL, 
    id_pelicula INT NOT NULL, 
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala),  
    FOREIGN KEY (id_pelicula) REFERENCES peliculas(id_pelicula)
); 

CREATE TABLE IF NOT EXISTS cartelera(
    titulo VARCHAR(50) PRIMARY KEY
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

-- Validación de fechas en salas_peliculas
DELIMITER //
CREATE TRIGGER validar_fechas_salas_peliculas
BEFORE INSERT ON salas_peliculas
FOR EACH ROW
BEGIN
    IF NEW.fecha_fin_emision < NEW.fecha_incio_emision THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de fin debe ser posterior a la fecha de inicio';
    END IF;
    
    IF NEW.hora_fin <= NEW.hora_inicio THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La hora de fin debe ser posterior a la hora de inicio';
    END IF;
END//
DELIMITER ;

-- Actualización de flujo de caja
DELIMITER //
CREATE TRIGGER actualizar_flujo_caja_pedido
AFTER INSERT ON pedidos
FOR EACH ROW
BEGIN
    IF NEW.estado = 'PAGADO' THEN
        INSERT INTO flujo_caja (tipo, descripcion, cantidad, fecha, categoria)
        VALUES ('INGRESO', CONCAT('Pedido #', NEW.id_pedido), NEW.precio_total, CURDATE(), 'VENTA');
    END IF;
END//
DELIMITER ;


-- Actualización de cartelera
DELIMITER //
CREATE TRIGGER actualizar_cartelera
AFTER INSERT ON salas_peliculas
FOR EACH ROW
BEGIN
    INSERT IGNORE INTO cartelera (titulo)
    VALUES (NEW.nombre_pelicula);
END//
DELIMITER ;

-- Validación de horarios 
DELIMITER //
CREATE TRIGGER validar_horarios_peliculas
BEFORE INSERT ON salas_peliculas
FOR EACH ROW
BEGIN
    DECLARE hay_solapamiento INT;
    
    SELECT COUNT(*) INTO hay_solapamiento
    FROM salas_peliculas
    WHERE id_sala = NEW.id_sala
    AND (
        (NEW.fecha_incio_emision BETWEEN fecha_incio_emision AND fecha_fin_emision
        OR NEW.fecha_fin_emision BETWEEN fecha_incio_emision AND fecha_fin_emision)
        AND (
            (NEW.hora_inicio BETWEEN hora_inicio AND hora_fin)
            OR (NEW.hora_fin BETWEEN hora_inicio AND hora_fin)
        )
    );
    
    IF hay_solapamiento > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Existe un solapamiento en la sala';
    END IF;
END//
DELIMITER ;