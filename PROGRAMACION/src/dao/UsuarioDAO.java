package dao;

import java.sql.Connection;
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
                        resultado.getDate("fecha_registro")
                );

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
}
