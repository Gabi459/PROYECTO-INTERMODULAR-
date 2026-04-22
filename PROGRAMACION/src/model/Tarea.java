package model;

import java.util.Date;

public class Tarea {

    //ATRIBUTOS
    private int id_tarea;
    private String titulo;
    private String descripcion;
    private Date fecha_limite;
    private String estado;
    private String prioridad;

    //CONSTRUCTOR
    public Tarea(int id_tarea, String titulo, String descripcion, Date fecha_limite, String estado, String prioridad) {
        this.id_tarea = id_tarea;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_limite = fecha_limite;
        this.estado = estado;
        this.prioridad = prioridad;
    }

    //GETTERS Y SETTERS
    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(){
        this.id_tarea = id_tarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLimite() {
        return fecha_limite;
    }

    public void setFechaLimite(Date fechalimite) {
        this.fecha_limite = fecha_limite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }  
}
