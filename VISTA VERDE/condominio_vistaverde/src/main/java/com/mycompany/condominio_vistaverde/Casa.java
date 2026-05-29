package com.mycompany.condominio_vistaverde;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    private int numero;
    private Propietario propietario;
    private List<Pago> pagos;

    public Casa(int numero) {
        this.numero = numero;
        this.pagos = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public Propietario getPropietario() { return propietario; }
    public List<Pago> getPagos() { return pagos; }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void agregarPago(Pago pago) {
        this.pagos.add(pago);
    }

    public boolean tienePago(String mes, int año) {
        return pagos.stream().anyMatch(p -> 
            p.getMes().equals(mes) && p.getAño() == año);
    }
}