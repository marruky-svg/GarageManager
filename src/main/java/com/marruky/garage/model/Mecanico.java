package com.marruky.garage.model;

public class Mecanico {
    private int id;
    private String nome;
    private String especialidade;
    private double precoHora;

    public Mecanico(int id, String nome, String especialidade, double precoHora){
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        setPrecoHora(precoHora);
    }

    //GETTERS
    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getEspecialidade(){
        return this.especialidade;
    }
    public double getPrecoHora(){
        return this.precoHora;
    }

    //SETTERS
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }
    public void setPrecoHora(double precoHora){
        if(precoHora <= 0){
            throw new IllegalArgumentException("Preço não pode ser negativo ou zero");
        }
        this.precoHora = precoHora;
    }

    @Override
    public String toString() {
        return "Mecanico{id=" + id + ", nome='" + nome + "', especialidade='" + especialidade + "', precoHora=" + precoHora + "}";
    }
}
