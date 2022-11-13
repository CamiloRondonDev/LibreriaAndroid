package com.example.libreria.entidades;

public class LibrosPrestados {

    private int idLibroPrestamo;
    private int idLibroPrestado;
    private String nombreLibroPrestado ;
    private String autorLibroPrestado ;
    private String imagenLibroPrestado;
    private String fehaLibroPrestado;
    private String correoUsuarioPrestoLibro;
    private String cantidadLibros;
    private String telefonoUsuarioPrestamo;
    private String nombreUsuarioPrestamo;

    public String getTelefonoUsuarioPrestamo() {
        return telefonoUsuarioPrestamo;
    }

    public void setTelefonoUsuarioPrestamo(String telefonoUsuarioPrestamo) {
        this.telefonoUsuarioPrestamo = telefonoUsuarioPrestamo;
    }

    public String getNombreUsuarioPrestamo() {
        return nombreUsuarioPrestamo;
    }

    public void setNombreUsuarioPrestamo(String nombreUsuarioPrestamo) {
        this.nombreUsuarioPrestamo = nombreUsuarioPrestamo;
    }
    public int getIdLibroPrestamo() {
        return idLibroPrestamo;
    }

    public void setIdLibroPrestamo(int idLibroPrestamo) {
        this.idLibroPrestamo = idLibroPrestamo;
    }

    public String getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(String cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }

    public int getIdLibroPrestado() {
        return idLibroPrestado;
    }

    public void setIdLibroPrestado(int idLibroPrestado) {
        this.idLibroPrestado = idLibroPrestado;
    }

    public String getNombreLibroPrestado() {
        return nombreLibroPrestado;
    }

    public void setNombreLibroPrestado(String nombreLibroPrestado) {
        this.nombreLibroPrestado = nombreLibroPrestado;
    }

    public String getAutorLibroPrestado() {
        return autorLibroPrestado;
    }

    public void setAutorLibroPrestado(String autorLibroPrestado) {
        this.autorLibroPrestado = autorLibroPrestado;
    }

    public String getImagenLibroPrestado() {
        return imagenLibroPrestado;
    }

    public void setImagenLibroPrestado(String imagenLibroPrestado) {
        this.imagenLibroPrestado = imagenLibroPrestado;
    }

    public String getFehaLibroPrestado() {
        return fehaLibroPrestado;
    }

    public void setFehaLibroPrestado(String fehaLibroPrestado) {
        this.fehaLibroPrestado = fehaLibroPrestado;
    }

    public String getCorreoUsuarioPrestoLibro() {
        return correoUsuarioPrestoLibro;
    }

    public void setCorreoUsuarioPrestoLibro(String correoUsuarioPrestoLibro) {
        this.correoUsuarioPrestoLibro = correoUsuarioPrestoLibro;
    }


}
