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
                System.out.println("0. Salir");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

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
                                System.out.println("0. Volver");

                                subop = sc.nextInt();
                                sc.nextLine();

                                switch (subop) {

                                    case 1:
                                        List<Evento> eventos = eventoDAO.obtenerEventosPorUsuario(conexion, idUsuario);

                                        for (Evento e : eventos) {
                                            System.out.printf("- Título: %s | Fecha: %s | Descripción: %s%n",
                                                    e.getTitulo(),
                                                    e.getFechaInicio(),
                                                    e.getDescripcion());
                                        }
                                        break;

                                    case 2:
                                        List<Tarea> tareas = tareaDAO.obtenerTareasPorUsuario(conexion, idUsuario);

                                        for (Tarea t : tareas) {
                                            System.out.printf("- Tarea: %s | Fecha límite: %s | Estado: %s%n",
                                                    t.getTitulo(),
                                                    t.getFechaLimite(),
                                                    t.getEstado());
                                        }
                                        break;
                                }

                            } while (subop != 0);

                        } else {
                            System.out.println("Login incorrecto");
                        }
                        break;

                    case 2:
                        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios(conexion);

                        for (Usuario u : usuarios) {
                            System.out.printf("Nombre: %s %s | Email: %s%n",
                                    u.getNombre(),
                                    u.getApellidos(),
                                    u.getEmail());
                        }
                        break;
                }

            } while (opcion != 0);

            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
