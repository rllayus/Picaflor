package com.rllayus.picaflor.modelo;

import java.io.Serializable;

/**
 * Created by Ricardo Laredo on 04-Aug-15.
 */
public class Empresa implements Serializable{
    private Integer id;
    private String nombre;
    private String urilogo;
    private String estado;
    public Empresa() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrilogo() {
        return urilogo;
    }

    public void setUrilogo(String urilogo) {
        this.urilogo = urilogo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
