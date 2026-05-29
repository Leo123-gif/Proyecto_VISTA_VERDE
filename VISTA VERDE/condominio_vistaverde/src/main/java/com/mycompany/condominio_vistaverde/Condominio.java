package com.mycompany.condominio_vistaverde;

import java.util.ArrayList;
import java.util.List;

public class Condominio {

    private List<Casa> casas;
    private double cuotaMensual;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Condominio() {

        this.casas = new ArrayList<>();

        this.cuotaMensual = 1500.0;

        inicializarCasas();
    }

    // =========================================
    // INICIALIZAR CASAS
    // =========================================

    private void inicializarCasas() {

        for (int i = 1; i <= 30; i++) {

            casas.add(new Casa(i));
        }
    }

    // =========================================
    // GETTERS
    // =========================================

    public List<Casa> getCasas() {

        return casas;
    }

    public double getCuotaMensual() {

        return cuotaMensual;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setCasas(List<Casa> casas) {

        this.casas = casas;
    }

    public void setCuotaMensual(double cuotaMensual) {

        this.cuotaMensual = cuotaMensual;
    }

    // =========================================
    // MÉTODOS DE CASAS
    // =========================================

    public Casa getCasa(int numero) {

        return casas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public boolean existeCasa(int numero) {

        return casas.stream()
                .anyMatch(c -> c.getNumero() == numero);
    }

    public int cantidadCasas() {

        return casas.size();
    }

    // =========================================
    // PROPIETARIOS
    // =========================================

    public boolean registrarPropietario(
            int numeroCasa,
            Propietario propietario
    ) {

        Casa casa = getCasa(numeroCasa);

        if (casa != null) {

            if (casa.getPropietario() != null) {

                return false;
            }

            casa.setPropietario(propietario);

            return true;
        }

        return false;
    }

    public boolean eliminarPropietario(
            int numeroCasa
    ) {

        Casa casa = getCasa(numeroCasa);

        if (casa != null
                && casa.getPropietario() != null) {

            casa.eliminarPropietario();

            return true;
        }

        return false;
    }

    // =========================================
    // PAGOS
    // =========================================

    public boolean registrarPago(
            int numeroCasa,
            Pago pago
    ) {

        Casa casa = getCasa(numeroCasa);

        if (casa != null) {

            // EVITAR PAGOS DUPLICADOS
            if (casa.tienePago(
                    pago.getMes(),
                    pago.getAño()
            )) {

                return false;
            }

            casa.agregarPago(pago);

            return true;
        }

        return false;
    }

    public List<Pago> obtenerPagosCasa(
            int numeroCasa
    ) {

        Casa casa = getCasa(numeroCasa);

        if (casa != null) {

            return casa.getPagos();
        }

        return new ArrayList<>();
    }

    public boolean tienePago(
            int numeroCasa,
            String mes,
            int año
    ) {

        Casa casa = getCasa(numeroCasa);

        if (casa != null) {

            return casa.tienePago(mes, año);
        }

        return false;
    }

    // =========================================
    // MOROSIDAD
    // =========================================

    public List<Casa> obtenerCasasSinPropietario() {

        List<Casa> lista =
                new ArrayList<>();

        for (Casa casa : casas) {

            if (casa.getPropietario() == null) {

                lista.add(casa);
            }
        }

        return lista;
    }

    public List<Casa> obtenerCasasConPropietario() {

        List<Casa> lista =
                new ArrayList<>();

        for (Casa casa : casas) {

            if (casa.getPropietario() != null) {

                lista.add(casa);
            }
        }

        return lista;
    }

    // =========================================
    // TO STRING
    // =========================================

    @Override
    public String toString() {

        return "Condominio{"
                + "casas=" + casas.size()
                + ", cuotaMensual=" + cuotaMensual
                + '}';
    }
}