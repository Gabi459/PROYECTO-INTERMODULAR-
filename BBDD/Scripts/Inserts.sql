-- USUARIOS
INSERT INTO USUARIO (nombre, apellidos, email, password, fecha_registro)
VALUES
('Diego', 'Couto Cabezas', 'diegocc@mail.com', '8234', '2023-07-10'),
('Carlos', 'Molina Berenger', 'carlosmb@mail.com', '0834', '2026-07-30'),
('Marcos', 'Padero Cambra', 'marcospc@mail.com', '1234', '2021-06-25');

-- CONTACTOS
INSERT INTO CONTACTO (nombre, apellidos, telefono, email, direccion, notas, id_usuario)
VALUES
('Pedro', 'Sánchez', '640111222', 'pedro@mail.com', 'Madrid', 'Amigo trabajo', 19),
('Lucía', 'Fernández', '620333444', 'lucia@mail.com', 'Madrid', 'Cliente', 20),
('Diego', 'Garrido', '640933401', 'diego@mail.com', 'Perú', 'Amigo', 21),
('Jeremy', 'González', '680753901', 'jeremy@mail.com', 'RD', 'Primo', 19),
('Gabriel', 'Martín', '661363425', 'gabriel@mail.com', 'Madrid', 'Jefe', 21),
('Manolito', 'Lopez', '605545678', 'manolo@mail.com', 'Alicante', 'Cocinero', 20),
('Marcela', 'Martin', '613094783', 'marcelita@mail.com', 'Madrid', 'Profesora', 19),
('Álvaro', 'Peralta García', '647694837', 'alavarito2mail.com', 'Valencia',"Obrero",20),
('Esmel', 'Berejil Benasi', '693827523', 'esmel@mail.com', 'Madrid', 'Amigo Uni', 21),
('Antonio', 'Asencio Luna', '675837232', 'tony2@mail.com', 'Madrid', 'Mecánico', 19);

-- CATEGORIAS EVENTO
INSERT INTO CATEGORIA_EVENTO (nombre, color)
VALUES
('Trabajo', 'azul'),
('Amigo', 'verde'),
('Personal', 'morado'),
('Estudios', 'rojo');

-- EVENTOS
INSERT INTO EVENTO (titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad, id_usuario, id_categoria)
VALUES
('Reunión proyecto', 'Reunión con el equipo', '2026-04-15 10:00:00', '2026-04-15 11:00:00', 'Oficina', 'alta', 1, 1),
('Examen', 'Examen de programación', '2026-04-20 13:00:00', '2026-04-20 14:00:00', 'Universidad', 'alta', 2, 2),
('Fiesta', 'Fiesta de Manolito', '2026-06-18 20:00:00', '2026-06-19 1:00:00', 'Casa de Manolito', 'media', 3, 3),
('Quedada', 'Quedar con Karima', '2026-07-08 14:00:00', '2026-07-08 17:00:00', 'Restaurante Tony', 'muy alta', 1, 4),
('Partido', 'Partido de fútbol', '2026-04-17 09:00:00', '2026-04-17 10:30:00', 'Estadio Metropolitano', 'baja', 2, 1),
('Cine', 'Ir al cine con Diego', '2026-04-25 18:00:00', '2026-04-15 20:00:00', 'Yelmo cines', 'media', 2, 2),
('Cita médica', 'Revisión anual', '2026-04-18 09:00:00', '2026-04-18 09:30:00', 'Centro salud', 'media', 3, 3);

-- CATEGORIAS TAREA
INSERT INTO CATEGORIA_TAREA (nombre, color)
VALUES 
('Estudio', 'amarillo'),
('Trabajo', 'azul'),
('Deberes', 'verde');

-- TAREAS
INSERT INTO TAREA (titulo, descripcion, fecha_limite, estado, prioridad, id_usuario, id_categoria_tarea)
VALUES
('Hacer proyecto de programación', 'Terminar proyecto de programación', '2026-04-20', 'pendiente', 'alta', 1, 1),
('Ir a comprar comida', 'Ir a comprar comida al Mercadona', '2026-05-05', 'pendiente', 'media', 2, 2),
('Clase de conducir', 'Ir a la autoescuela a la clase de conducir', '2026-04-28', 'pendiente', 'alta', 3, 3),
('Estudiar sistemas informáticos', 'estudiar para el examen de sistemas', '2026-04-22', 'pendiente', 'alta', 1, 1),
('Pasear al perro', 'Dar paseo por el parque con el perro', '2026-04-20', 'pendiente', 'baja', 2, 2),
('Limpiar', 'Limpiar la casa', '2026-04-24', 'pendiente', 'baja', 3, 3),
('Recoger paquete', 'Ir a recoger el paquete', '2026-04-20', 'terminada', 'alta', 1, 1);

-- ETIQUETAS                                                                                                                                                                             
INSERT INTO ETIQUETA (nombre)
VALUES
('Urgente'), ('Importante'), ('Poco importante'), ('Nada importante');

-- EVENTO_ETIQUETA
INSERT INTO EVENTO_ETIQUETA VALUES (1, 2);

-- TAREA_ETIQUETA
INSERT INTO TAREA_ETIQUETA VALUES (1, 1);