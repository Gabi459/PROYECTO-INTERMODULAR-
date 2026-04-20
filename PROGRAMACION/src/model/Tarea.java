package model;

public class Tarea {

    private String titulo;
    private String estado;

    public Tarea(String titulo, String estado) {
        this.titulo = titulo;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
