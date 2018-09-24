package com.example.root.practica4;


public class Peluchito {
    String nombre;
    int cantidad, id;
    double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Peluchito(String nombre, int cantidad, int id, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.id = id;
        this.precio = precio;
    }
}