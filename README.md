# PROYECTO-INTERMODULAR-

AgendaPro es una aplicación web desarrollada como proyecto intermodular del grado superior de Desarrollo de Aplicaciones Web. Permite que los usuarios organizen su tiempo de forma eficiente mediante la gestión de tareas, eventos y recordatorios.

En la actualidad las personas tienen dificulated para organizar su tiempo y recordar tareas importantes. Con esta aplicación web nuestra intención es que los usuarios puedas gestionar sus tareas diarias, visualizar eventos en un calendario, establecer recordatorios, priorizar actividades y todo esto en una única plataforma fácil de usar.

Hemos utilizado estas tecnologías:
· HTML para la estructura de la web.
· CSS para el diseño y los estilos.
· MySQL para la base de datos.



Estructura del repositorio:

ProyectoIntermodular/
|
|---.vscode/
|    |-settings.json
|
|---BBDD/
|   |-Scripts/
|   | |-BDAgenda.sql
|   | |-Consultas.sql
|   | |-Inserts.sql
|   |
|   |-Diagrama Agenda_digital.drawio.png
|   |-Modelo Relacional Agenda_digital.pdf       
|   |-README.md
|
|---PROGRAMACION/
|   |-lib/
|   | |-mysql-connector-j-9.6.0.jar
|   |
|   |-src/
|   | |-dao/
|   | | |-EventoDAO.java
|   | | |-TareaDAO.java
|   | | |-UsuarioDAO.java
|   | |
|   | |-db/
|   | | |-Conexion.java
|   | |
|   | |-model/
|   | | |-Evento.java
|   | | |-Tarea.java
|   | | |-Usuario.java
|   | 
|   |-main.java
|   |
|   |-README.md
|
|---web/
|   |-js/
|   | |-app.js
|   |
|   |-styles/
|     |-calendario.html
|     |-contacto.html
|     |-index.html
|     |-login.html
|     |-sobre.html
|     |-tareas.html
|
|---README.md


AUTORES: Jeremy González, Diego Garrido y Gabriel Martín