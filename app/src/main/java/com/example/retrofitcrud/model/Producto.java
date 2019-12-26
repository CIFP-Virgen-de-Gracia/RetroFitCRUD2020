package com.example.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Clase modelo de producto
 */

public class Producto {

    // Indicamos los campos
    // ID, columna id de la tabla productos
    @SerializedName("id")
    @Expose
    private Long id;

    // nombre, columna nombre,
    @SerializedName("nombre")
    @Expose
    private String nombre;

    public Producto() {
    }

    public Producto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return String.format(
                "Producto[id=%d, nombre='%s']",
                id, nombre);
    }
}
