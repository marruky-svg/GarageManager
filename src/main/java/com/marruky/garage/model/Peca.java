package com.marruky.garage.model;

public class Peca {
    private int id;
    private String referencia;
    private String nome;
    private double precoUnitario;
    private int stock;
    private boolean disponivel;
    public static final double IVA = 0.23;

    public Peca(int id, String referencia, String nome, double precoUnitario, int stock){
        setId(id);
        setReferencia(referencia);
        setNome(nome);
        setPrecoUnitario(precoUnitario);
        setStock(stock);
        this.disponivel = true;
    }

    //GETTERS
    public int getId(){
        return id;
    }

    public String getReferencia(){
        return referencia;
    }

    public String getNome(){
        return nome;
    }

    public double getPrecoUnitario(){
        return precoUnitario;
    }

    public int getStock(){
        return stock;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    //SETTERS

    public void setId(int id){
        this.id = id;
    }

    public void setReferencia(String referencia){
        if(referencia == null || referencia.isBlank()) {
            throw new IllegalArgumentException("Referencia não pode estar em branco");
        }
        this.referencia = referencia;
    }

    public void setNome(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome não pode estar em branco");
        }
        this.nome = nome;
    }

    public void setPrecoUnitario(double precoUnitario){
        if(precoUnitario <= 0) {
            throw new IllegalArgumentException("Preço não pode ser 0 ou negativo");
        }
        this.precoUnitario = precoUnitario;
    }

    public void setStock(int stock){
        if(stock < 0){
            throw new IllegalArgumentException("Stock não pode ser negativo");
        }
        this.stock = stock;
    }

    public double calcularPrecoComIVA(){
        return precoUnitario * (1 + IVA);
    }

    @Override
    public String toString() {
        return "Peca{id=" + id +
                ", referencia='" + referencia + '\'' +
                ", nome='" + nome + '\'' +
                ", precoUnitario=" + precoUnitario +
                ", stock=" + stock +
                ", disponivel=" + disponivel + "}";
    }

    public void descontinuar() {
        if (!disponivel) {
            throw new IllegalStateException("Peça já está descontinuada");
        }
        this.disponivel = false;
    }

    public void disponibilizar() {
        if (disponivel) {
            throw new IllegalStateException("Peça já está disponível");
        }
        this.disponivel = true;
    }
}

