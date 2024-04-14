package com.leotoloza.tp4.data.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;

   private String password;

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}