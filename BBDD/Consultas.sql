USE agenda_digital;

-- 1. Ver todos los eventos de un usuario
SELECT e.titulo, e.fecha_inicio, c.nombre AS categoria
FROM EVENTO e
JOIN CATEGORIA_EVENTO c ON e.id_categoria = c.id_categoria
WHERE e.id_usuario = 1;

-- 2. Ver contactos de un usuario
SELECT nombre, telefono, email
FROM CONTACTO
WHERE id_usuario = 1;

-- 3. Tareas pendientes
SELECT titulo, fecha_limite, prioridad
FROM TAREA
WHERE estado = 'pendiente';

-- 4. Eventos con etiquetas
SELECT e.titulo, et.nombre
FROM EVENTO e
JOIN EVENTO_ETIQUETA ee ON e.id_evento = ee.id_evento
JOIN ETIQUETA et ON ee.id_etiqueta = et.id_etiqueta;

-- 5. Número de eventos por usuario
SELECT id_usuario, COUNT(*) AS total_eventos
FROM EVENTO
GROUP BY id_usuario;