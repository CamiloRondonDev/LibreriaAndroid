package com.example.libreria.entidades;
// modelos reciben informacion para luego  mostrarla

public class Usuarios { ////esto lo utilizamos para hacer el login de aca pasamos Dbusuarios donde creamos los metodos
    private int id;
    private String nombre_usuario;
    private String telefono;
    private String direccion;
    private String correo_usuario;
    private String contraseña_usuario;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getContraseña_usuario() {
        return contraseña_usuario;
    }

    public void setContraseña_usuario(String contraseña_usuario) {
        this.contraseña_usuario = contraseña_usuario;
    }
}



