package com.mycompany.condominio_vistaverde;

public class Propietario {
    private String nombre;
    private String telefono;
    private String correo;
    private int numeroCasa;

    public Propietario(String nombre, String telefono, String correo, int numeroCasa) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.numeroCasa = numeroCasa;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public int getNumeroCasa() { return numeroCasa; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
}