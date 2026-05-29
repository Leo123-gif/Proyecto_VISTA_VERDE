package com.mycompany.condominio_vistaverde;

public class Pago {

    private String mes;
    private int año;
    private double monto;
    private String estado;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Pago(
            String mes,
            int año,
            double monto,
            String estado
    ) {

        this.mes = mes;
        this.año = año;
        this.monto = monto;
        this.estado = estado;
    }

    // =========================================
    // GETTERS
    // =========================================

    public String getMes() {

        return mes;
    }

    public int getAño() {

        return año;
    }

    public double getMonto() {

        return monto;
    }

    public String getEstado() {

        return estado;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setMes(String mes) {

        this.mes = mes;
    }

    public void setAño(int año) {

        this.año = año;
    }

    public void setMonto(double monto) {

        this.monto = monto;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }

    // =========================================
    // TO STRING
    // =========================================

    @Override
    public String toString() {

        return "Pago{"
                + "mes='" + mes + '\''
                + ", año=" + año
                + ", monto=" + monto
                + ", estado='" + estado + '\''
                + '}';
    }
}