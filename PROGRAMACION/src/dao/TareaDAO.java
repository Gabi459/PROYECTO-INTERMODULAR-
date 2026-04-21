package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import model.Tarea;

public class TareaDAO {
    
    public List<Tarea> obtenerTareas(Connection conex) {

        List<Tarea> tareas = new ArrayList<>();

        String consulta = "SELECT titulo, descripcion, fechalimite, estado, prioridad estado FROM tarea";

        //preparamos la consulta y la ejecutamos, con un try-con-recursos
        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)){
                
                //se recorre el conjunto de registros que devuelve la SELECT
                while (resultado.next()) {
                    //mientras haya registros, se guarda en las variables temporales cada columna
                    String titulo = resultado.getString("titulo");
                    String descripcion = resultado.getString("descripcion");
                    String fechalimite = resultado.getString("fecha-limite");
                    String estado = resultado.getString("estado");
                    String prioridad = resultado.getString("prioridad");
                     
                    //con los datos obtenidos de la BD, crea un objeto Alumno
                    Tarea tarea = new Tarea(titulo, descripcion, fechalimite, estado, prioridad);
                    
                    //añade el alumno a la lista que se quiere retornar con este método
                    tareas.add(tarea);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }
}
