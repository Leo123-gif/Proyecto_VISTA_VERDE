package com.mycompany.condominio_vistaverde;

import java.util.ArrayList;
import java.util.List;

public class Condominio {
    private List<Casa> casas;
    private double cuotaMensual;

    public Condominio() {
        this.casas = new ArrayList<>();
        this.cuotaMensual = 1500.0;
        inicializarCasas();
    }

    private void inicializarCasas() {
        for (int i = 1; i <= 30; i++) {
            casas.add(new Casa(i));
        }
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public Casa getCasa(int numero) {
        return casas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public double getCuotaMensual() { return cuotaMensual; }
    public void setCuotaMensual(double cuotaMensual) { 
        this.cuotaMensual = cuotaMensual; 
    }

    // Métodos de negocio
    public void registrarPropietario(int numeroCasa, Propietario propietario) {
        Casa casa = getCasa(numeroCasa);
        if (casa != null) {
            casa.setPropietario(propietario);
        }
    }

    public void registrarPago(int numeroCasa, Pago pago) {
        Casa casa = getCasa(numeroCasa);
        if (casa != null) {
            casa.agregarPago(pago);
        }
    }
}