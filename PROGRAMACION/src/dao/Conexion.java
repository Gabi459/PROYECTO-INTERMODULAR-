package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/BDAgenda";
        String user = "root";
        String pwd = "mysql";

        return DriverManager.getConnection(url,user, pwd);

    }
}
