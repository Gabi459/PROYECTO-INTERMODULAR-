package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import model.Usuario;

//USUARIO DAO, encargado de gestionar las operaciones sobre la tabla usuario
public class UsuarioDAO {

    // Obytener todos los usuarios de la base de datos
    public List<Usuario> obtenerUsuarios(Connection conex) {

        // Crea una lista vacía donde se guardarán los usuarios
        List<Usuario> usuarios = new ArrayList<>();

        // Consulta SQL para obtener todos los usuarios
        String consulta = "SELECT id_usuario, nombre, apellidos, email, password, fecha_registro FROM usuario";

        // try-with-resources: cierra automáticamente Statement y ResultSet
        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)) {

            // Recorre cada fila del resultado
            while (resultado.next()) {

                // Crea un objeto Usuario con los datos de la fila actual
                Usuario usuario = new Usuario(
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre"),
                        resultado.getString("apellidos"),
                        resultado.getString("email"),
                        resultado.getString("password"),
                        resultado.getDate("fecha_registro"));

                // Añade el usuario a la lista
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            // Manejo de errores SQL
            e.printStackTrace();
        }

        // Devuelve la lista de usuarios
        return usuarios;
    }

    // Login de usuario: devuelve el ID si el login es correcto, -1 si es incorrecto
    public int login(Connection conex, String email, String password) {

        // Consulta parametrizada (evita SQL Injection)
        String sql = "SELECT id_usuario, password FROM usuario WHERE email = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // Asigna el email al parámetro
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                // Si existe el usuario
                if (rs.next()) {

                    String passwordBD = rs.getString("password");

                    // Compara contraseñas (versión simple)
                    if (passwordBD.equals(password)) {
                        return rs.getInt("id_usuario");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // cuando el log in es incorrecto
    }

    // AÑADIR UN NUEVO USUARIO, en la base de datos
    public void insertarUsuario(Connection conex, Usuario usuario) {

        // Consulta SQL con parámetros para insertar un nuevo usuario
        String sql = "INSERT INTO usuario (nombre, apellidos, email, password, fecha_registro) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // Asigna valores a los parámetros de la consulta
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPassword());

            // Convierte java.util.Date a java.sql.Date
            ps.setDate(5, new java.sql.Date(usuario.getFecha_registro().getTime()));

            // Ejecuta la inserción
            ps.executeUpdate();

            // Mensaje de confirmación
            System.out.println("Usuario creado correctamente");

        } catch (Exception e) {
            // Manejo de errores
            e.printStackTrace();
        }
    }

    // Eliminar un usuario de la base de datos por su ID
    public void eliminarUsuario(Connection conex, int idUsuario) {

        // Consulta SQL para eliminar un usuario por ID
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            // Asigna el ID al parámetro
            ps.setInt(1, idUsuario);

            // Ejecuta la eliminación
            ps.executeUpdate();

            // Mensaje de confirmación
            System.out.println("Usuario eliminado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}