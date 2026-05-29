package com.mycompany.condominio_vistaverde;

public class Propietario {

    private String nombre;
    private String telefono;
    private String correo;
    private int numeroCasa;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Propietario(
            String nombre,
            String telefono,
            String correo,
            int numeroCasa
    ) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.numeroCasa = numeroCasa;
    }

    // =========================================
    // GETTERS
    // =========================================

    public String getNombre() {

        return nombre;
    }

    public String getTelefono() {

        return telefono;
    }

    public String getCorreo() {

        return correo;
    }

    public int getNumeroCasa() {

        return numeroCasa;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    public void setCorreo(String correo) {

        this.correo = correo;
    }

    public void setNumeroCasa(int numeroCasa) {

        this.numeroCasa = numeroCasa;
    }

    // =========================================
    // VALIDACIONES
    // =========================================

    public boolean tieneCorreo() {

        return correo != null
                && !correo.trim().isEmpty();
    }

    public boolean tieneTelefono() {

        return telefono != null
                && !telefono.trim().isEmpty();
    }

    // =========================================
    // TO STRING
    // =========================================

    @Override
    public String toString() {

        return "Propietario{"
                + "nombre='" + nombre + '\''
                + ", telefono='" + telefono + '\''
                + ", correo='" + correo + '\''
                + ", numeroCasa=" + numeroCasa
                + '}';
    }
}