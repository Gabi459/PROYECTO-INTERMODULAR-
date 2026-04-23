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

public class UsuarioDAO {

    // OBTENER TODOS LOS USUARIOS
    public List<Usuario> obtenerUsuarios(Connection conex) {

        List<Usuario> usuarios = new ArrayList<>();

        String consulta = "SELECT id_usuario, nombre, apellidos, email, password, fecha_registro FROM usuario";

        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)) {

            while (resultado.next()) {

                Usuario usuario = new Usuario(
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre"),
                        resultado.getString("apellidos"),
                        resultado.getString("email"),
                        resultado.getString("password"),
                        resultado.getDate("fecha_registro"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // LOGIN → DEVUELVE ID DEL USUARIO
    public int login(Connection conex, String email, String password) {

        String consulta = "SELECT id_usuario FROM usuario WHERE email = '"
                + email + "' AND password = '" + password + "'";

        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)) {

            if (resultado.next()) {
                return resultado.getInt("id_usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // login incorrecto
    }

    public void insertarUsuario(Connection conex, Usuario u) {

        String sql = "INSERT INTO usuario (nombre, apellidos, email, password, fecha_registro) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setDate(5, new java.sql.Date(u.getFecha_registro().getTime()));//

            ps.executeUpdate();

            System.out.println("Usuario creado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Connection conex, int idUsuario) {

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();

            System.out.println("Usuario eliminado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}