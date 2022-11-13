package com.example.libreria.entidades;

public class Libros {
    private int id;
    private String nombreLibro;
    private String autorLibro;
    private String cantidadLibro;
    private String urlLibro;
    private String imagenLibro;
    private String descripcionLibro;




    public int getId() {
        return id;
    }

    public void  setId(int id) {
        this.id = id;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public String getCantidadLibro() {
        return cantidadLibro;
    }

    public void setCantidadLibro(String cantidadLibro) {
        this.cantidadLibro = cantidadLibro;
    }

    public String getUrlLibro() {
        return urlLibro;
    }

    public void setUrlLibro(String urlLibro) {
        this.urlLibro = urlLibro;
    }

    public String getImagenLibro() {
        return imagenLibro;
    }

    public void setImagenLibro(String imagenLibro) {
        this.imagenLibro = imagenLibro;
    }

    public String getDescripcionLibro() {
        return descripcionLibro;
    }

    public void setDescripcionLibro(String descripcionLibro) {
        this.descripcionLibro = descripcionLibro;
    }
}
