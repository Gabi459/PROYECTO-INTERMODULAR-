package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.Tarea;

public class TareaDAO {
    
    public List<Tarea> obtenerTareas(Connection conex) {

        List<Tarea> tareas = new ArrayList<>();

        String consulta = "SELECT id_tarea, titulo, descripcion, fecha_limite, estado, prioridad FROM tarea";

        //preparamos la consulta y la ejecutamos, con un try-con-recursos
        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)){
                
                //se recorre el conjunto de registros que devuelve la SELECT
                while (resultado.next()) {
                    //mientras haya registros, se guarda en las variables temporales cada columna
                    int id_tarea = resultado.getInt("id_tarea");
                    String titulo = resultado.getString("titulo");
                    String descripcion = resultado.getString("descripcion");
                    Date fecha_limite = resultado.getDate("fecha_limite");
                    String estado = resultado.getString("estado");
                    String prioridad = resultado.getString("prioridad");
                     
                    //con los datos obtenidos de la BD, crea un objeto tarea
                    Tarea tarea = new Tarea(id_tarea, titulo, descripcion, fecha_limite, estado, prioridad);
                    
                    //añade el alumno a la lista que se quiere retornar con este método
                    tareas.add(tarea);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }
}
