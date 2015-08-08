package com.rllayus.picaflor.modelo;

import java.io.Serializable;

/**
 * Created by CHUPITA on 30/05/2015.
 */
public class ProductItem implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private String urilogo;
    private String barcode;
    private float precio;
    private int estado;

    public ProductItem() {
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUrilogo() {
        return urilogo;
    }

    public void setUrilogo(String urilogo) {
        this.urilogo = urilogo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
