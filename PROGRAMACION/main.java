import dao.*;
import model.*;
import db.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        try {
            //  Conexión usando tu método
            Connection conexion = Conexion.getConexion();

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            EventoDAO eventoDAO = new EventoDAO();
            TareaDAO tareaDAO = new TareaDAO();

            do {
                System.out.println("======================= AGENDA PRO =======================");
                System.out.println("|   1. Mostrar usuarios                                  |");
                System.out.println("|   2. Mostrar eventos                                   |");
                System.out.println("|   3. Mostrar tareas                                    |");
                System.out.println("|   0. Salir                                             |");
                System.out.println("==========================================================");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1:
                        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios(conexion);

                        System.out.println("------------------------- USUARIOS -----------------------");
                        for (Usuario u : usuarios) {
                            System.out.printf("Nombre: %s %s | Email: %s%n",
                                    u.getNombre(),
                                    u.getApellidos(),
                                    u.getEmail());
                        }
                        break;

                    case 2:
                        List<Evento> eventos = eventoDAO.obtenerEventos(conexion);

                        System.out.println("------------------------- EVENTOS ------------------------");
                        for (Evento e : eventos) {
                            System.out.printf("Título: %s%nDescripción: %s%nInicio: %s | Fin: %s%nUbicación: %s | Prioridad: %s%n",
                                    e.getTitulo(),
                                    e.getDescripcion(),
                                    e.getFechaInicio(),
                                    e.getFechaFin(),
                                    e.getUbicacion(),
                                    e.getPrioridad());
                            System.out.println("----------------------------------------------------------");
                        }
                        break;

                    case 3:
                        List<Tarea> tareas = tareaDAO.obtenerTareas(conexion);

                        System.out.println("------------------------- TAREAS -------------------------");
                        for (Tarea t : tareas) {
                            System.out.printf("Título: %s%nDescripción: %s%nFecha límite: %s%nEstado: %s | Prioridad: %s%n",
                                    t.getTitulo(),
                                    t.getDescripcion(),
                                    t.getFechaLimite(),
                                    t.getEstado(),
                                    t.getPrioridad());
                            System.out.println("----------------------------------------------------------");
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }

            } while (opcion != 0);

            // Cerrar conexión
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos");
            e.printStackTrace();
        }
        sc.close();
    }
}
