package com.marruky.garage.model;

public class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String matricula, String marca, String modelo, int ano, int cilindrada) {
        super(matricula, marca, modelo, ano);
        setCilindrada(cilindrada);
    }

    //GETTER
    public int getCilindrada() {
        return cilindrada;
    }

    //SETTER
    public void setCilindrada(int cilindrada) {
        if (cilindrada < 50 || cilindrada > 2500) {
            throw new IllegalArgumentException("Cilindrada inválida - deve estar entre 50cc e 2500cc");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public double calcularTaxaInspecao() {
        if(cilindrada < 250) {
            return 20.00;
        }
        return 28.00;
    }

    @Override
    public String toString() {
        return "Moto{" + super.toString() + ", cilindrada=" + cilindrada + "}";
    }
}
