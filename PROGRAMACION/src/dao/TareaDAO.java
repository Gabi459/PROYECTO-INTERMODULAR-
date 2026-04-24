package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.PreparedStatement;
import model.Tarea;

//TAREA DAO, encargada de gestionar el acceso a datos de la entidad Tarea
public class TareaDAO {

    // OBTIENE TODAS LAS TAREAS, de la base de datos
    public List<Tarea> obtenerTareas(Connection conex) {

        // lista donde se guardarán las tareas obtenidas
        List<Tarea> tareas = new ArrayList<>();

        // consulta SQL para obtener todas las tareas
        String consulta = "SELECT id_tarea, titulo, descripcion, fecha_limite, estado, prioridad FROM tarea";

        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)) {

            // recorre cada fila del resultado
            while (resultado.next()) {

                // crea un objeto Tarea con los datos de la base de datos
                Tarea tarea = new Tarea(
                        resultado.getInt("id_tarea"),
                        resultado.getString("titulo"),
                        resultado.getString("descripcion"),
                        // Se obtiene la fecha como java.sql.Date
                        resultado.getDate("fecha_limite"),
                        resultado.getString("estado"),
                        resultado.getString("prioridad"));

                // añade la tarea a la lista
                tareas.add(tarea);
            }

        } catch (SQLException e) {
            // Manejo de errores
            e.printStackTrace();
        }

        // Devuelve la lista de tareas
        return tareas;
    }

    // OBTIENE LAS TAREAS DE UN USUARIO CONCRETO ORDENADAS POR LA FECHA LÍMITE
    public List<Tarea> obtenerTareasPorUsuario(Connection conex, int idUsuario) {

        List<Tarea> tareas = new ArrayList<>();

        // Consulta con concatenación directa
        String consulta = "SELECT id_tarea, titulo, descripcion, fecha_limite, estado, prioridad " +
                "FROM tarea WHERE id_usuario = " + idUsuario + " ORDER BY fecha_limite";

        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)) {

            while (resultado.next()) {

                Tarea tarea = new Tarea(
                        resultado.getInt("id_tarea"),
                        resultado.getString("titulo"),
                        resultado.getString("descripcion"),
                        resultado.getDate("fecha_limite"),
                        resultado.getString("estado"),
                        resultado.getString("prioridad"));

                tareas.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }

    // AÑADIR UNA NUEVA TAREA, en la base de datos
    public void insertarTarea(Connection conex, Tarea tarea, int idUsuario) {

        // Consulta SQL con parámetros
        String sql = "INSERT INTO tarea (titulo, descripcion, fecha_limite, estado, prioridad, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // asigna los valores a los parámetros
            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());

            // conversión de java.util.Date a java.sql.Date
            ps.setDate(3, new java.sql.Date(tarea.getFechaLimite().getTime()));

            ps.setString(4, tarea.getEstado());
            ps.setString(5, tarea.getPrioridad());
            ps.setInt(6, idUsuario);

            // se añade la nueva tarea
            ps.executeUpdate();

            System.out.println("Tarea creada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ELIMINA UNA TAREA, por su ID
    public void eliminarTarea(Connection conex, int idTarea) {

        // Consulta SQL con parámetro
        String sql = "DELETE FROM tarea WHERE id_tarea = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // decimos el ID de la tarea que queremos eliminar
            ps.setInt(1, idTarea);

            // se elimina la tarea
            ps.executeUpdate();

            System.out.println("Tarea eliminada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
