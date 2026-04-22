package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import model.Evento;

public class EventoDAO {

    // TODOS LOS EVENTOS
    public List<Evento> obtenerEventos(Connection conex) {

        List<Evento> eventos = new ArrayList<>();

        String consulta = "SELECT id_evento, titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad FROM evento";

        try (Statement stmt = conex.createStatement();
             ResultSet resultado = stmt.executeQuery(consulta)) {

            while (resultado.next()) {

                Evento evento = new Evento(
                        resultado.getInt("id_evento"),
                        resultado.getString("titulo"),
                        resultado.getString("descripcion"),
                        resultado.getTimestamp("fecha_inicio").toLocalDateTime(),
                        resultado.getTimestamp("fecha_fin").toLocalDateTime(),
                        resultado.getString("ubicacion"),
                        resultado.getString("prioridad")
                );

                eventos.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // NUEVO → EVENTOS POR USUARIO ORDENADOS
    public List<Evento> obtenerEventosPorUsuario(Connection conex, int idUsuario) {

        List<Evento> eventos = new ArrayList<>();

        String consulta = "SELECT id_evento, titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad " +
                          "FROM evento WHERE id_usuario = " + idUsuario + " ORDER BY fecha_inicio";

        try (Statement stmt = conex.createStatement();
             ResultSet resultado = stmt.executeQuery(consulta)) {

            while (resultado.next()) {

                Evento evento = new Evento(
                        resultado.getInt("id_evento"),
                        resultado.getString("titulo"),
                        resultado.getString("descripcion"),
                        resultado.getTimestamp("fecha_inicio").toLocalDateTime(),
                        resultado.getTimestamp("fecha_fin").toLocalDateTime(),
                        resultado.getString("ubicacion"),
                        resultado.getString("prioridad")
                );

                eventos.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }
}
