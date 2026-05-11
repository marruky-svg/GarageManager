package com.marruky.garage.model;

public class Cliente {
     private int id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(int id, String nome, String telefone, String email){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    //SETTERS
    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', telefone='" + telefone + "', email='" + email + "'}";
    }
}
