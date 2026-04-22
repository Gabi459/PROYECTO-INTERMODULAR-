package model;

import java.time.LocalDateTime;

public class Evento {

    //ATRIBUTOS
    private int id_evento;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_inicio;    
    private LocalDateTime fecha_fin;
    private String ubicacion;
    private String prioridad;

    //CONSTRUCTOR
    public Evento(int id_evento, String titulo, String descripcion, LocalDateTime fecha_inicio, LocalDateTime fecha_fin, String ubicacion, String prioridad) {
        this.id_evento = id_evento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.ubicacion = ubicacion;
        this.prioridad = prioridad;
    }

    //GETTERS Y SETTERS
    public int getIdEvento() {
        return id_evento;
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

    public LocalDateTime getFechaInicio() {
        return fecha_inicio;
    }

    public void setFechaInicio(LocalDateTime fechainicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFechaFin() {
        return fecha_fin;
    }

    public void setFechaFin (LocalDateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
}
