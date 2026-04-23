import dao.*;

import db.Conexion;

import model.*;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import java.util.Scanner;

// Clase principal del programa
public class main {

    // Método principal (punto de entrada del programa)
    public static void main(String[] args) {

        // Crea un objeto Scanner para leer entrada del usuario
        Scanner sc = new Scanner(System.in);

        try {
            // Obtiene la conexión a la base de datos
            Connection conexion = Conexion.getConexion();

            // Crea instancias de los DAOs (acceso a datos)
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            EventoDAO eventoDAO = new EventoDAO();
            TareaDAO tareaDAO = new TareaDAO();

            int opcion; // Variable para el menú principal

            // Bucle principal del programa
            do {
                // Muestra el menú principal
                System.out.println("\n===== AGENDA PRO =====");
                System.out.println("1. Usuario (Login)");
                System.out.println("2. Mostrar usuarios");
                System.out.println("3. Crear usuario");
                System.out.println("4. Eliminar usuario");
                System.out.println("0. Salir");

                // Lee la opción elegida
                opcion = sc.nextInt();
                sc.nextLine(); // Limpia el buffer

                // Evalúa la opción elegida
                switch (opcion) {

                    // login de usuario
                    case 1:
                        // Pide email al usuario
                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        // Pide contraseña
                        System.out.print("Password: ");
                        String pass = sc.nextLine();

                        // Intenta hacer login y devuelve el id del usuario si es correcto
                        int idUsuario = usuarioDAO.login(conexion, email, pass);

                        // Si el login es correcto
                        if (idUsuario != -1) {
                            System.out.println("Login correcto");

                            int subop; // Submenú usuario

                            // Bucle del menú de usuario
                            do {
                                System.out.println("\n--- MENÚ USUARIO ---");
                                System.out.println("1. Mostrar eventos");
                                System.out.println("2. Mostrar tareas");
                                System.out.println("3. Crear evento");
                                System.out.println("4. Crear tarea");
                                System.out.println("5. Eliminar evento");
                                System.out.println("6. Eliminar tarea");
                                System.out.println("0. Volver");

                                // Lee opción del submenú
                                subop = sc.nextInt();
                                sc.nextLine();

                                switch (subop) {

                                    // ver eventos
                                    case 1:
                                        // Obtiene eventos del usuario
                                        List<Evento> eventos = eventoDAO.obtenerEventosPorUsuario(conexion, idUsuario);

                                        // Recorre la lista y los muestra
                                        for (Evento e : eventos) {
                                            System.out.printf("ID: %d | %s | Inicio: %s%n",
                                                    e.getIdEvento(),
                                                    e.getTitulo(),
                                                    e.getFechaInicio());
                                        }
                                        break;

                                    // ver tareas
                                    case 2:
                                        // Obtiene tareas del usuario
                                        List<Tarea> tareas = tareaDAO.obtenerTareasPorUsuario(conexion, idUsuario);

                                        // Muestra cada tarea
                                        for (Tarea t : tareas) {
                                            System.out.printf("ID: %d | %s | Fecha límite: %s | Estado: %s%n",
                                                    t.getId_tarea(),
                                                    t.getTitulo(),
                                                    t.getFechaLimite(),
                                                    t.getEstado());
                                        }
                                        break;

                                    // crear evento
                                    case 3:
                                        // Solicita datos del evento
                                        System.out.print("Título: ");
                                        String titulo = sc.nextLine();

                                        System.out.print("Descripción: ");
                                        String desc = sc.nextLine();

                                        System.out.print("Fecha inicio (yyyy-MM-ddTHH:mm): ");
                                        String fi = sc.nextLine();

                                        System.out.print("Fecha fin (yyyy-MM-ddTHH:mm): ");
                                        String ff = sc.nextLine();

                                        System.out.print("Ubicación: ");
                                        String ubi = sc.nextLine();

                                        System.out.print("Prioridad: ");
                                        String prio = sc.nextLine();

                                        // Crea objeto Evento
                                        Evento evento = new Evento(
                                                0,
                                                titulo,
                                                desc,
                                                java.time.LocalDateTime.parse(fi),
                                                java.time.LocalDateTime.parse(ff),
                                                ubi,
                                                prio);

                                        // Inserta el evento en la base de datos
                                        eventoDAO.insertarEvento(conexion, evento, idUsuario);
                                        break;

                                    // crear tarea
                                    case 4:
                                        // Solicita datos de la tarea
                                        System.out.print("Título: ");
                                        String t = sc.nextLine();

                                        System.out.print("Descripción: ");
                                        String d = sc.nextLine();

                                        System.out.print("Fecha límite (yyyy-MM-dd): ");
                                        String fl = sc.nextLine();

                                        System.out.print("Prioridad: ");
                                        String pr = sc.nextLine();

                                        // Crea objeto Tarea
                                        Tarea tarea = new Tarea(
                                                0,
                                                t,
                                                d,
                                                java.sql.Date.valueOf(fl),
                                                "pendiente",
                                                pr);

                                        // Inserta la tarea
                                        tareaDAO.insertarTarea(conexion, tarea, idUsuario);
                                        break;

                                    // eliminar evento
                                    case 5:
                                        // Pide ID del evento
                                        System.out.print("ID del evento a eliminar: ");
                                        int idEvento = sc.nextInt();
                                        sc.nextLine();

                                        // Elimina el evento
                                        eventoDAO.eliminarEvento(conexion, idEvento);
                                        break;

                                    // eliminar tarea
                                    case 6:
                                        // Pide ID de la tarea
                                        System.out.print("ID de la tarea a eliminar: ");
                                        int idTarea = sc.nextInt();
                                        sc.nextLine();

                                        // Elimina la tarea
                                        tareaDAO.eliminarTarea(conexion, idTarea);
                                        break;
                                }

                            } while (subop != 0); // Repite hasta volver

                        } else {
                            // Si el login falla
                            System.out.println("Login incorrecto");
                        }
                        break;

                    // mostrar usuarios
                    case 2:
                        // Obtiene todos los usuarios
                        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios(conexion);

                        // Muestra cada usuario
                        for (Usuario u : usuarios) {
                            System.out.printf("ID: %d | %s %s | Email: %s%n",
                                    u.getId_usuario(),
                                    u.getNombre(),
                                    u.getApellidos(),
                                    u.getEmail());
                        }
                        break;

                    // CREAR USUARIO
                    case 3:
                        // Solicita datos
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Apellidos: ");
                        String a = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Password: ");
                        String p = sc.nextLine();

                        // Crea objeto Usuario
                        Usuario nuevo = new Usuario(0, n, a, e, p, new java.util.Date());

                        // Inserta usuario
                        usuarioDAO.insertarUsuario(conexion, nuevo);
                        break;

                    // ELIMINAR USUARIO
                    case 4:
                        System.out.print("ID del usuario a eliminar: ");
                        int idEliminar = sc.nextInt();
                        sc.nextLine();

                        // Elimina usuario
                        usuarioDAO.eliminarUsuario(conexion, idEliminar);
                        break;

                    case 0:
                        // Salir del programa
                        System.out.println("Saliendo...");
                        break;

                    default:
                        // Opción inválida
                        System.out.println("Opción no válida");
                }

            } while (opcion != 0); // Repite hasta salir

            // Cierra la conexión a la base de datos
            conexion.close();

        } catch (SQLException e) {
            // Manejo de errores SQL
            e.printStackTrace();
        }

        // Cierra el Scanner
        sc.close();
    }
}