package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import model.Evento;

public class EventoDAO {

    public List<Evento> obtenerEventos(Connection conex) {

        List<Evento> eventos = new ArrayList<>();

        String consulta = "SELECT titulo, descripcion, fechainicio, fechafin, ubicacion, prioridad FROM evento";

        //preparamos la consulta y la ejecutamos, con un try-con-recursos
        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)){
                
                //se recorre el conjunto de registros que devuelve la SELECT
                while (resultado.next()) {
                    //mientras haya registros, se guarda en las variables temporales cada columna
                    String titulo = resultado.getString("titulo");
                    String descripcion = resultado.getString("descripcion");
                    String fechainicio = resultado.getString("fechainicio");
                    String fechafin = resultado.getString("fechafin");
                    String ubicacion = resultado.getString("ubicacion");
                    String prioridad = resultado.getString("prioridad");

                    //con los datos obtenidos de la BD, crea un objeto Alumno
                    Evento evento = new Evento(titulo, descripcion, fechainicio, fechafin, ubicacion, prioridad);
                    
                    //añade el usuario a la lista que se quiere retornar con este método
                    eventos.add(evento);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
}
