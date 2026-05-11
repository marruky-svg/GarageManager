package com.marruky.garage.model;

public class Caminhao extends Veiculo {
    private double pesoMaximoKg;
    private int numeroEixos;

    public Caminhao(String matricula, String marca, String modelo, int ano, double pesoMaximoKg, int numeroEixos) {
        super(matricula, marca, modelo, ano);
        setPesoMaximoKg(pesoMaximoKg);
        setNumeroEixos(numeroEixos);
    }

    //GETTERS
    public double getPesoMaximoKg() {
        return pesoMaximoKg;
    }

    public int getNumeroEixos() {
        return numeroEixos;
    }

    //SETTERS
    public void setPesoMaximoKg(double pesoMaximoKg) {
        if (pesoMaximoKg <= 0 || pesoMaximoKg > 15000) {
            throw new IllegalArgumentException("Peso máximo deve ser maior que 0 kg e até 15000 kg");
        }
        this.pesoMaximoKg = pesoMaximoKg;
    }

    public void setNumeroEixos(int numeroEixos) {
        if (numeroEixos < 2 || numeroEixos > 6) {
            throw new IllegalArgumentException("Número de eixos deve estar entre 2 e 6");
        }
        this.numeroEixos = numeroEixos;
    }

    @Override
    public double calcularTaxaInspecao() {
        return 50.00 + (numeroEixos * 5.00) + (pesoMaximoKg * 0.01);
    }

    @Override
    public String toString() {
        return "Caminhao{" + super.toString() + ", pesoMaximoKg=" + pesoMaximoKg + ", numeroEixos=" + numeroEixos + "}";
    }

}
