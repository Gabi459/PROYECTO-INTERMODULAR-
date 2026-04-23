import dao.*;
import db.Conexion;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection conexion = Conexion.getConexion();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            EventoDAO eventoDAO = new EventoDAO();
            TareaDAO tareaDAO = new TareaDAO();

            int opcion;

            do {
                System.out.println("\n===== AGENDA PRO =====");
                System.out.println("1. Usuario (Login)");
                System.out.println("2. Mostrar usuarios");
                System.out.println("3. Crear usuario");
                System.out.println("4. Eliminar usuario");
                System.out.println("0. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    // LOGIN
                    case 1:
                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Password: ");
                        String pass = sc.nextLine();

                        int idUsuario = usuarioDAO.login(conexion, email, pass);

                        if (idUsuario != -1) {
                            System.out.println("Login correcto");

                            int subop;
                            do {
                                System.out.println("\n--- MENÚ USUARIO ---");
                                System.out.println("1. Mostrar eventos");
                                System.out.println("2. Mostrar tareas");
                                System.out.println("3. Crear evento");
                                System.out.println("4. Crear tarea");
                                System.out.println("5. Eliminar evento");
                                System.out.println("6. Eliminar tarea");
                                System.out.println("0. Volver");

                                subop = sc.nextInt();
                                sc.nextLine();

                                switch (subop) {

                                    // VER EVENTOS
                                    case 1:
                                        List<Evento> eventos = eventoDAO.obtenerEventosPorUsuario(conexion, idUsuario);

                                        for (Evento e : eventos) {
                                            System.out.printf("ID: %d | %s | Inicio: %s%n",
                                                    e.getIdEvento(),
                                                    e.getTitulo(),
                                                    e.getFechaInicio());
                                        }
                                        break;

                                    // VER TAREAS
                                    case 2:
                                        List<Tarea> tareas = tareaDAO.obtenerTareasPorUsuario(conexion, idUsuario);

                                        for (Tarea t : tareas) {
                                            System.out.printf("ID: %d | %s | Fecha límite: %s | Estado: %s%n",
                                                    t.getId_tarea(),
                                                    t.getTitulo(),
                                                    t.getFechaLimite(),
                                                    t.getEstado());
                                        }
                                        break;

                                    // CREAR EVENTO
                                    case 3:
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

                                        Evento evento = new Evento(
                                                0,
                                                titulo,
                                                desc,
                                                java.time.LocalDateTime.parse(fi),
                                                java.time.LocalDateTime.parse(ff),
                                                ubi,
                                                prio);

                                        eventoDAO.insertarEvento(conexion, evento, idUsuario);
                                        break;

                                    // CREAR TAREA
                                    case 4:
                                        System.out.print("Título: ");
                                        String t = sc.nextLine();

                                        System.out.print("Descripción: ");
                                        String d = sc.nextLine();

                                        System.out.print("Fecha límite (yyyy-MM-dd): ");
                                        String fl = sc.nextLine();

                                        System.out.print("Prioridad: ");
                                        String pr = sc.nextLine();

                                        Tarea tarea = new Tarea(
                                                0,
                                                t,
                                                d,
                                                java.sql.Date.valueOf(fl),
                                                "pendiente",
                                                pr);

                                        tareaDAO.insertarTarea(conexion, tarea, idUsuario);
                                        break;

                                    // ELIMINAR EVENTO
                                    case 5:
                                        System.out.print("ID del evento a eliminar: ");
                                        int idEvento = sc.nextInt();
                                        sc.nextLine();

                                        eventoDAO.eliminarEvento(conexion, idEvento);
                                        break;

                                    // ELIMINAR TAREA
                                    case 6:
                                        System.out.print("ID de la tarea a eliminar: ");
                                        int idTarea = sc.nextInt();
                                        sc.nextLine();

                                        tareaDAO.eliminarTarea(conexion, idTarea);
                                        break;
                                }

                            } while (subop != 0);

                        } else {
                            System.out.println("Login incorrecto");
                        }
                        break;

                    // MOSTRAR USUARIOS
                    case 2:
                        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios(conexion);

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
                        System.out.print("Nombre: ");
                        String n = sc.nextLine();

                        System.out.print("Apellidos: ");
                        String a = sc.nextLine();

                        System.out.print("Email: ");
                        String e = sc.nextLine();

                        System.out.print("Password: ");
                        String p = sc.nextLine();

                        Usuario nuevo = new Usuario(0, n, a, e, p, new java.util.Date());

                        usuarioDAO.insertarUsuario(conexion, nuevo);
                        break;

                    // ELIMINAR USUARIO
                    case 4:
                        System.out.print("ID del usuario a eliminar: ");
                        int idEliminar = sc.nextInt();
                        sc.nextLine();

                        usuarioDAO.eliminarUsuario(conexion, idEliminar);
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }

            } while (opcion != 0);

            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}