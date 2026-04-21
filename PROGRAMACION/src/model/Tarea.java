package model;

public class Tarea {

    private String titulo;
    private String descripcion;
    private String fechalimite;
    private String estado;
    private String prioridad;

    public Tarea(String titulo, String descripcion, String fechalimite, String estado, String prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechalimite = fechalimite;
        this.estado = estado;
        this.prioridad = prioridad;
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

    public String getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(String fechalimite) {
        this.fechalimite = fechalimite;
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
