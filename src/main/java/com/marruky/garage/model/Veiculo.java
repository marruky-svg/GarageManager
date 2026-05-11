package com.marruky.garage.model;

public abstract class Veiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private int ano;


    public Veiculo(String matricula, String marca, String modelo, int ano) {
        setMatricula(matricula);
        setMarca(marca);
        setModelo(modelo);
        setAno(ano);
    }

    public String descricaoBase() {
        return marca + " " + modelo + " (" + matricula + ", " + ano + ")";
    }

    public abstract double calcularTaxaInspecao();

    //GETTERS
    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }


    //SETTERS
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula inválida e/ou vazia");
        }
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Marca não pode estar vazia");
        }
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo não pode estar vazio");
        }
        this.modelo = modelo;
    }

    public void setAno(int ano) {
        if (ano < 1900 || ano > 2026) {
            throw new IllegalArgumentException("Ano inválido - deve estar entre 1900 e 2026");
        }
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Veiculo{marca='" + marca + "', modelo='" + modelo + "', matricula='" + matricula + "', ano=" + ano + "}";
    }
}

