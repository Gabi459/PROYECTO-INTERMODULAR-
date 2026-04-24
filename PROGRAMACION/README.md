# AgendaPro – Aplicación de gestión de tareas y eventos

## ¿Qué hace la aplicación?

AgendaPro es una aplicación desarrollada en Java que permite a los usuarios gestionar su agenda personal.
Cada usuario puede iniciar sesión en el sistema y administrar sus propias tareas y eventos.

La aplicación simula el funcionamiento de una agenda digital real, permitiendo organizar actividades diarias de forma sencilla y persistente mediante una base de datos.

---

## ¿Cómo se ejecuta?

1. Requisitos previos:

   * Java (JDK instalado)
   * MySQL u otro gestor de base de datos

2. Base de datos:

   * Ejecutar el script SQL incluido en el proyecto para crear las tablas necesarias (usuarios, tareas, eventos)

3. Configuración:

   * Revisar la clase `Conexion.java` y configurar:

     * URL de conexión
     * Usuario
     * Contraseña

4. Ejecución:

   * Abrir el proyecto en un IDE (IntelliJ, Eclipse, etc.)
   * Ejecutar la clase `Main.java`

5. Uso:

   * El programa mostrará un menú interactivo por consola
   * El usuario podrá registrarse o iniciar sesión
   * Una vez dentro, podrá gestionar sus datos

---

## ¿Qué funcionalidades tiene?

* Registro de usuarios
* Inicio de sesión (login)
* Creación de tareas
* Visualización de tareas
* Eliminación de tareas
* Creación de eventos
* Consulta de eventos
* Eliminación de eventos
* Menú interactivo por consola

Cada usuario gestiona únicamente sus propias tareas y eventos.

---

## ¿Qué entidades gestiona?

La aplicación trabaja con las siguientes entidades:

### Usuario

* id_usuario
* nombre
* apellidos
* email
* password
* fecha_registro

### Tarea

* id_tarea
* título
* descripcion
* fecha_limite
* estado (completada o no)
* prioridad
* id_usuario (relación con usuario)
* id_categoria (relacion con categoria)

### Evento

* id_evento
* título
* descripciónç
* fecha_inicio
* fecha_fin
* ubicacion
* prioridad
* id_usuario (relación con usuario)
* id_categoria (relacion con categoria)

---

## ¿Qué parte usa la base de datos?

La aplicación utiliza una base de datos para almacenar toda la información de forma persistente.

Mediante JDBC se realizan operaciones como:

* **INSERT** → añadir usuarios, tareas y eventos
* **SELECT** → consultar información según el usuario logueado
* **DELETE** → eliminar tareas y eventos

La conexión se gestiona desde la clase `Conexion`, y las operaciones se organizan mediante clases DAO.

---

## Estructura del proyecto

El proyecto está organizado en varias capas:

* `model` → clases que representan las entidades (Usuario, Tarea, Evento)
* `dao` → acceso a datos (consultas SQL mediante JDBC)
* `db` → conexión a la base de datos
* `main` → ejecución y menú interactivo

---

## Tecnologías utilizadas

* Java
* JDBC
* MySQL
* Programación Orientada a Objetos

---

## Objetivo del proyecto

Este proyecto forma parte del Proyecto Intermodular de 1º DAW, y tiene como objetivo integrar conocimientos de:

* Programación
* Bases de Datos
* Lenguajes de Marcas

Simulando el desarrollo de una aplicación real con gestión de usuarios, autenticación y persistencia de datos.

---

## Mejoras futuras

* Interfaz gráfica (GUI)
* Conexión con la web desarrollada en HTML/CSS
* Sistema de notificaciones
* Gestión avanzada de calendario
