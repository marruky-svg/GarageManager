package com.marruky.garage.model;

public class Carro extends Veiculo {
    private int numeroPortas;

    public Carro(String matricula, String marca, String modelo, int ano, int numeroPortas) {
        super(matricula, marca, modelo, ano);
        setNumeroPortas(numeroPortas);
    }

    //GETTER
    public int getNumeroPortas() {
        return this.numeroPortas;
    }

    //SETTER
    public void setNumeroPortas(int numeroPortas) {
        if (numeroPortas < 2 || numeroPortas > 5) {
            throw new IllegalArgumentException("Número de portas inválido, valores entre 2 e 4");
        }

        this.numeroPortas = numeroPortas;
    }

    @Override
    public double calcularTaxaInspecao() {
        return 35.00;
    }

    @Override
    public String toString() {
        return "Carro{" + super.toString() + ", numeroPortas=" + numeroPortas + "}";
    }
}
