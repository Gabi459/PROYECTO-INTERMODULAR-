package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import model.Evento;

//EVENTO DAO (Data Access Object), encargadO de gestionar las operaciones
// de base de datos relacionadas con la entidad Evento
public class EventoDAO {

    // OBTIENE TODOS LOS EVENTOS, de la base de datos
    public List<Evento> obtenerEventos(Connection conex) {

        // lista donde se almacenarán los eventos obtenidos
        List<Evento> eventos = new ArrayList<>();

        // Consulta SQL para obtener todos los eventos
        String consulta = "SELECT id_evento, titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad FROM evento";

        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)) {

            // Recorremos cada fila del resultado
            while (resultado.next()) {

                // Creamos un objeto Evento con los datos de la fila
                Evento evento = new Evento(
                        resultado.getInt("id_evento"),
                        resultado.getString("titulo"),
                        resultado.getString("descripcion"),
                        // Convertimos Timestamp a LocalDateTime
                        resultado.getTimestamp("fecha_inicio").toLocalDateTime(),
                        resultado.getTimestamp("fecha_fin").toLocalDateTime(),
                        resultado.getString("ubicacion"),
                        resultado.getString("prioridad"));

                // Añadimos el evento a la lista
                eventos.add(evento);
            }

        } catch (SQLException e) {
            // Manejo de errores
            e.printStackTrace();
        }

        // Devolvemos la lista de eventos
        return eventos;
    }

    // MÉTODO PARA QUE NOS DIGA LOS EVENTOS DE UN USUARIO CONCRETO ORDENADOS POR
    // FECHA
    public List<Evento> obtenerEventosPorUsuario(Connection conex, int idUsuario) {

        List<Evento> eventos = new ArrayList<>();

        // Esta consulta concatena el idUsuario directamente (no recomendable)
        // Se debería usar PreparedStatement para evitar SQL Injection
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
                        resultado.getString("prioridad"));

                eventos.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // AÑADIR UN EVENTO en la base de datos
    public void insertarEvento(Connection conex, Evento e, int idUsuario) {

        // consulta SQL con parámetros (uso seguro de PreparedStatement)
        String sql = "INSERT INTO evento (titulo, descripcion, fecha_inicio, fecha_fin, ubicacion, prioridad, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // Asignamos los valores a los parámetros
            ps.setString(1, e.getTitulo());
            ps.setString(2, e.getDescripcion());

            // Convertimos LocalDateTime a Timestamp para SQL
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(e.getFechaInicio()));
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(e.getFechaFin()));

            ps.setString(5, e.getUbicacion());
            ps.setString(6, e.getPrioridad());
            ps.setInt(7, idUsuario);

            // se ejecuta añadir el evento a la base de datos
            ps.executeUpdate();

            System.out.println("Evento creado correctamente");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ELIMIAR EL EVENTO POR ID
    public void eliminarEvento(Connection conex, int idEvento) {

        // consulta SQL con parámetro
        String sql = "DELETE FROM evento WHERE id_evento = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // decimos el id del evento que queremos eliminar
            ps.setInt(1, idEvento);

            // se ejecuta eliminar el evento
            ps.executeUpdate();

            System.out.println("Evento eliminado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
