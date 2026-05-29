package com.mycompany.condominio_vistaverde;

public class Pago {
    private String mes;
    private int año;
    private double monto;
    private String estado; // "PAGADO", "PENDIENTE", "MOROSO"

    public Pago(String mes, int año, double monto, String estado) {
        this.mes = mes;
        this.año = año;
        this.monto = monto;
        this.estado = estado;
    }

    // Getters y Setters
    public String getMes() { return mes; }
    public int getAño() { return año; }
    public double getMonto() { return monto; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}