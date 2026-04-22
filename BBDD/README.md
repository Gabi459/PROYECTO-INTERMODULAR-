# Diseño de la Base de Datos — Agenda Digital

## Introducción

La base de datos desarrollada en este proyecto tiene como objetivo modelar el funcionamiento interno de una **agenda digital**, permitiendo gestionar usuarios, eventos, tareas, contactos y recordatorios de forma estructurada.

El diseño se ha realizado siguiendo el modelo Entidad–Relación y su posterior transformación al modelo relacional.

---

## Análisis del sistema

La aplicación simula una agenda digital en la que un usuario puede:

* Gestionar sus contactos
* Crear eventos y tareas
* Organizar información mediante categorías
* Asociar etiquetas a eventos y tareas
* Programar recordatorios

---

## Entidades principales

### Usuario

Entidad central del sistema. Representa a cada persona que utiliza la agenda.

### Evento

Almacena citas o reuniones con fecha y hora.

### Tarea

Permite gestionar actividades pendientes.

### Contacto

Información de personas asociadas a un usuario.

---

## Entidades secundarias

### Categoría_Evento y Categoría_Tarea

Permiten clasificar eventos y tareas.

### Etiqueta

Sistema flexible de organización basado en etiquetas.

### Recordatorio

Permite generar avisos asociados a eventos o tareas.

### Sesión

Gestiona el acceso de los usuarios al sistema.

---

## Relaciones

* Un **usuario** puede tener múltiples:

  * contactos
  * eventos
  * tareas
  * sesiones

* Un **evento** pertenece a un usuario y a una categoría

* Una **tarea** pertenece a un usuario y a una categoría

* Los **recordatorios** se asocian a eventos o tareas

* Existe una relación **N:M** entre:

  * Evento ↔ Etiqueta
  * Tarea ↔ Etiqueta

---

## Modelo relacional

El modelo relacional se ha diseñado aplicando:

* Claves primarias en todas las tablas
* Claves foráneas para mantener la integridad referencial
* Tablas intermedias para relaciones N:M

---

## Integridad y normalización

La base de datos cumple con principios de normalización (hasta 3FN):

* Eliminación de redundancias
* Separación de entidades independientes
* Uso correcto de relaciones

Se han definido restricciones como:

* `PRIMARY KEY`
* `FOREIGN KEY`
* `UNIQUE` (en el email del usuario)

---

## Justificación del diseño

El diseño busca representar de forma realista el funcionamiento de una agenda digital moderna, permitiendo:

* Escalabilidad
* Flexibilidad en la organización (etiquetas y categorías)
* Integridad de los datos
* Facilidad de consulta mediante SQL

---

## Conclusión

La base de datos desarrollada permite gestionar de forma eficiente toda la información necesaria en una agenda digital, siendo coherente con el funcionamiento de aplicaciones reales y cumpliendo los requisitos del proyecto intermodular.


