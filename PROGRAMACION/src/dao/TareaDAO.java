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

public class TareaDAO {

    // TODAS LAS TAREAS
    public List<Tarea> obtenerTareas(Connection conex) {

        List<Tarea> tareas = new ArrayList<>();

        String consulta = "SELECT id_tarea, titulo, descripcion, fecha_limite, estado, prioridad FROM tarea";

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

    // TAREAS POR USUARIO ORDENADAS
    public List<Tarea> obtenerTareasPorUsuario(Connection conex, int idUsuario) {

        List<Tarea> tareas = new ArrayList<>();

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

    public void insertarTarea(Connection conex, Tarea t, int idUsuario) {

        String sql = "INSERT INTO tarea (titulo, descripcion, fecha_limite, estado, prioridad, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescripcion());
            ps.setDate(3, new java.sql.Date(t.getFechaLimite().getTime()));//
            ps.setString(4, t.getEstado());
            ps.setString(5, t.getPrioridad());
            ps.setInt(6, idUsuario);

            ps.executeUpdate();

            System.out.println("Tarea creada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarTarea(Connection conex, int idTarea) {

        String sql = "DELETE FROM tarea WHERE id_tarea = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            ps.setInt(1, idTarea);
            ps.executeUpdate();

            System.out.println("Tarea eliminada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
