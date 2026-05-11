package com.marruky.garage.interfaces;

public interface Faturavel {

    double IVA_NORMAL = 0.23;

    double calcularTotal();
    String getNumeroFatura();
    boolean podeSerFaturado();

}
