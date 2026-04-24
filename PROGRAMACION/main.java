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
        Scanner scanner = new Scanner(System.in);

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
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer

                // Evalúa la opción elegida
                switch (opcion) {

                    // login de usuario
                    case 1:
                        // Pide email al usuario
                        System.out.print("Email: ");
                        String emailUsuario = scanner.nextLine();

                        // Pide contraseña
                        System.out.print("Password: ");
                        String passwordUsuario = scanner.nextLine();

                        // Intenta hacer login y devuelve el id del usuario si es correcto
                        int idUsuario = usuarioDAO.login(conexion, emailUsuario, passwordUsuario);

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
                                subop = scanner.nextInt();
                                scanner.nextLine();

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
                                        String tituloEvento = scanner.nextLine();

                                        System.out.print("Descripción: ");
                                        String descripcionEvento = scanner.nextLine();

                                        System.out.print("Fecha inicio (yyyy-MM-ddTHH:mm): ");
                                        String fechaInicioEvento = scanner.nextLine();

                                        System.out.print("Fecha fin (yyyy-MM-ddTHH:mm): ");
                                        String fechaFinEvento = scanner.nextLine();

                                        System.out.print("Ubicación: ");
                                        String ubicacionEvento = scanner.nextLine();

                                        System.out.print("Prioridad: ");
                                        String prioridadEvento = scanner.nextLine();

                                        // Crea objeto Evento
                                        Evento evento = new Evento(
                                                0,
                                                tituloEvento,
                                                descripcionEvento,
                                                java.time.LocalDateTime.parse(fechaInicioEvento),
                                                java.time.LocalDateTime.parse(fechaFinEvento),
                                                ubicacionEvento,
                                                prioridadEvento);

                                        // Inserta el evento en la base de datos
                                        eventoDAO.insertarEvento(conexion, evento, idUsuario);
                                        break;

                                    // crear tarea
                                    case 4:
                                        // Solicita datos de la tarea
                                        System.out.print("Título: ");
                                        String tituloTarea = scanner.nextLine();

                                        System.out.print("Descripción: ");
                                        String descripcionTarea = scanner.nextLine();

                                        System.out.print("Fecha límite (yyyy-MM-dd): ");
                                        String fechaLimiteTarea = scanner.nextLine();

                                        System.out.print("Prioridad: ");
                                        String prioridadTarea = scanner.nextLine();

                                        // Crea objeto Tarea
                                        Tarea tarea = new Tarea(
                                                0,
                                                tituloTarea,
                                                descripcionTarea,
                                                java.sql.Date.valueOf(fechaLimiteTarea),
                                                "pendiente",
                                                prioridadTarea);

                                        // Inserta la tarea
                                        tareaDAO.insertarTarea(conexion, tarea, idUsuario);
                                        break;

                                    // eliminar evento
                                    case 5:
                                        // Pide ID del evento
                                        System.out.print("ID del evento a eliminar: ");
                                        int idEvento = scanner.nextInt();
                                        scanner.nextLine();

                                        // Elimina el evento
                                        eventoDAO.eliminarEvento(conexion, idEvento);
                                        break;

                                    // eliminar tarea
                                    case 6:
                                        // Pide ID de la tarea
                                        System.out.print("ID de la tarea a eliminar: ");
                                        int idTarea = scanner.nextInt();
                                        scanner.nextLine();

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
                        String nombre = scanner.nextLine();

                        System.out.print("Apellidos: ");
                        String apellidos = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        // Crea objeto Usuario
                        Usuario nuevo = new Usuario(0, nombre, apellidos, email, password, new java.util.Date());

                        // Inserta usuario
                        usuarioDAO.insertarUsuario(conexion, nuevo);
                        break;

                    // ELIMINAR USUARIO
                    case 4:
                        System.out.print("ID del usuario a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        scanner.nextLine();

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
        scanner
                .close();
    }
}