package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import model.Evento;

public class EventoDAO {

    public List<Evento> obtenerEventos(Connection conex) {

        List<Evento> eventos = new ArrayList<>();

        String consulta = "SELECT id_evento, titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad FROM evento";

        //preparamos la consulta y la ejecutamos, con un try-con-recursos
        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)){
                
                //se recorre el conjunto de registros que devuelve la SELECT
                while (resultado.next()) {
                    //mientras haya registros, se guarda en las variables temporales cada columna
                    int id_evento = resultado.getInt("id_evento");
                    String titulo = resultado.getString("titulo");
                    String descripcion = resultado.getString("descripcion");
                    LocalDateTime fecha_inicio = resultado.getTimestamp("fecha_inicio").toLocalDateTime();
                    LocalDateTime fecha_fin = resultado.getTimestamp("fecha_fin").toLocalDateTime();
                    String ubicacion = resultado.getString("ubicacion");
                    String prioridad = resultado.getString("prioridad");

                    //con los datos obtenidos de la BD, crea un objeto evento
                    Evento evento = new Evento (id_evento, titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad);
                    
                    //añade el usuario a la lista que se quiere retornar con este método
                    eventos.add(evento);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
}
