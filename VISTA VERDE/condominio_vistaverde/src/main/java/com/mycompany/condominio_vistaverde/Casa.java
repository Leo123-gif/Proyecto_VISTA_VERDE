package com.mycompany.condominio_vistaverde;

import java.util.ArrayList;
import java.util.List;

public class Casa {

    private int numero;
    private Propietario propietario;
    private List<Pago> pagos;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Casa(int numero) {

        this.numero = numero;
        this.pagos = new ArrayList<>();
    }

    // =========================================
    // GETTERS
    // =========================================

    public int getNumero() {

        return numero;
    }

    public Propietario getPropietario() {

        return propietario;
    }

    public List<Pago> getPagos() {

        return pagos;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setNumero(int numero) {

        this.numero = numero;
    }

    public void setPropietario(
            Propietario propietario
    ) {

        this.propietario = propietario;
    }

    public void setPagos(
            List<Pago> pagos
    ) {

        this.pagos = pagos;
    }

    // =========================================
    // MÉTODOS
    // =========================================

    public void agregarPago(Pago pago) {

        this.pagos.add(pago);
    }

    public void eliminarPropietario() {

        this.propietario = null;
    }

    public boolean tienePago(
            String mes,
            int año
    ) {

        return pagos.stream().anyMatch(
                p -> p.getMes().equalsIgnoreCase(mes)
                        && p.getAño() == año
        );
    }

    public int cantidadPagos() {

        return pagos.size();
    }

    // =========================================
    // TO STRING
    // =========================================

    @Override
    public String toString() {

        return "Casa{"
                + "numero=" + numero
                + ", propietario="
                + (propietario != null
                        ? propietario.getNombre()
                        : "SIN PROPIETARIO")
                + ", pagos=" + pagos.size()
                + '}';
    }
}