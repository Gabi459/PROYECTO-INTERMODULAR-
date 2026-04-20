create database BDAgenda;
use bdagenda;

create table usuario(
    id_usuario PK,
    nombre,
    apellidos,
    email UNIQUE,
    password,
    fecha_registro
)

create table contacto(
    id_contacto PK,
    nombre,
    apellidos,
    telefono,
    email,
    direccion,
    notas,
    id_usuario FK → USUARIO(id_usuario)
)

