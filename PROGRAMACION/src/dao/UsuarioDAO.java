package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {
    public List<Usuario> obtenerUsuarios(Connection conex) {

        List<Usuario> usuarios = new ArrayList<>();

        String consulta = "SELECT nombre, apellidos, email, password FROM usuario";

        //preparamos la consulta y la ejecutamos, con un try-con-recursos
        try (Statement stmt = conex.createStatement();
                ResultSet resultado = stmt.executeQuery(consulta)){
                
                //se recorre el conjunto de registros que devuelve la SELECT
                while (resultado.next()) {
                    //mientras haya registros, se guarda en las variables temporales cada columna
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String email = resultado.getString("email");
                    String password = resultado.getString("password");

                    //con los datos obtenidos de la BD, crea un objeto Alumno
                    Usuario usuario = new Usuario(nombre, apellidos, email, password);
                    
                    //añade el usuario a la lista que se quiere retornar con este método
                    usuarios.add(usuario);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
