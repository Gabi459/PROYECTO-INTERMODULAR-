create database BDAgenda;
use bdagenda;

CREATE TABLE USUARIO (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    fecha_registro DATE
);

CREATE TABLE CONTACTO (
    id_contacto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(150),
    notas TEXT,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE TABLE CATEGORIA_EVENTO (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    color VARCHAR(20)
);

CREATE TABLE EVENTO (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATETIME,
    fecha_fin DATETIME,
    ubicacion VARCHAR(150),
    prioridad VARCHAR(20),
    id_usuario INT,
    id_categoria INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA_EVENTO(id_categoria)
);

CREATE TABLE TAREA (
    id_tarea INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    descripcion TEXT,
    fecha_limite DATE,
    estado VARCHAR(20),
    prioridad VARCHAR(20),
    id_usuario INT,
    id_categoria_tarea INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_categoria_tarea) REFERENCES CATEGORIA_TAREA(id_categoria_tarea)
);

CREATE TABLE RECORDATORIO (
    id_recordatorio INT AUTO_INCREMENT PRIMARY KEY,
    fecha_aviso DATETIME,
    tipo VARCHAR(20),
    estado VARCHAR(20),
    id_evento INT NULL,
    id_tarea INT NULL,
    FOREIGN KEY (id_evento) REFERENCES EVENTO(id_evento),
    FOREIGN KEY (id_tarea) REFERENCES TAREA(id_tarea)
);

CREATE TABLE CATEGORIA_TAREA (
    id_categoria_tarea INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    color VARCHAR(20)
);

CREATE TABLE ETIQUETA (
    id_etiqueta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE EVENTO_ETIQUETA (
    id_evento INT,
    id_etiqueta INT,
    PRIMARY KEY (id_evento, id_etiqueta),
    FOREIGN KEY (id_evento) REFERENCES EVENTO(id_evento),
    FOREIGN KEY (id_etiqueta) REFERENCES ETIQUETA(id_etiqueta)
);

CREATE TABLE TAREA_ETIQUETA (
    id_tarea INT,
    id_etiqueta INT,
    PRIMARY KEY (id_tarea, id_etiqueta),
    FOREIGN KEY (id_tarea) REFERENCES TAREA(id_tarea),
    FOREIGN KEY (id_etiqueta) REFERENCES ETIQUETA(id_etiqueta)
);

CREATE TABLE SESION (
    id_sesion INT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_expiracion DATETIME NOT NULL,
    id_usuario INT NOT NULL,
    
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);