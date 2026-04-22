package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
                        resultado.getString("prioridad")
                );

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
                        resultado.getString("prioridad")
                );

                tareas.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }
}
