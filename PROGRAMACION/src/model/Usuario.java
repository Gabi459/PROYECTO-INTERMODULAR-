package model;

import java.util.Date;

public class Usuario {

    //ATRIBUTOS
    private int id_usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private Date fecha_registro;

    //CONSTRUCTOR
    public Usuario(int id_usuario, String nombre, String apellidos, String email, String password, Date fecha_registro) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.fecha_registro = fecha_registro;
    }

    // GETTERS Y SETTERS
    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    public Date getFecha_registro() {
        return fecha_registro;
    }
}
