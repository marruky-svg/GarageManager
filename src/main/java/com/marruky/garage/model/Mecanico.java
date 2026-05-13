package com.marruky.garage.model;

import com.marruky.garage.enums.EstadoMecanico;

public class Mecanico {
    private int id;
    private String nome;
    private String especialidade;
    private double precoHora;
    private EstadoMecanico estado;

    public Mecanico(int id, String nome, String especialidade, double precoHora){
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        setPrecoHora(precoHora);
        this.estado = EstadoMecanico.ATIVO;
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
    public EstadoMecanico getEstado() {
        return this.estado;
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

    private void setEstado(EstadoMecanico estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mecanico{id=" + id + ", nome='" + nome + "', especialidade='" + especialidade + "', precoHora=" + precoHora + ", estado=" + estado + "}";
    }
    //=========================
    //    MÉTODOS ESTADOS
    //=========================

    public void darBaixa() {
        if(estado != EstadoMecanico.ATIVO) {
            throw new IllegalStateException("Só é possível dar baixa a mecânicos ATIVOS. Estado atual: " + estado);
        }
        setEstado(EstadoMecanico.DE_BAIXA);
    }

    public void voltarAoServico() {
        if (estado != EstadoMecanico.DE_BAIXA) {
            throw new IllegalStateException(
                    "Só é possível voltar ao serviço a partir de DE_BAIXA. Estado atual: " + estado
            );
        }
        setEstado(EstadoMecanico.ATIVO);
    }

    public void despedir() {
        if (estado != EstadoMecanico.ATIVO && estado != EstadoMecanico.DE_BAIXA) {
            throw new IllegalStateException(
                    "Só é possível despedir mecânicos ATIVOS ou DE_BAIXA. Estado atual: " + estado
            );
        }
        setEstado(EstadoMecanico.DESPEDIDO);
    }

    public void reformar() {
        if (estado != EstadoMecanico.ATIVO) {
            throw new IllegalStateException(
                    "Só é possível reformar mecânicos ATIVOS. Estado atual: " + estado
            );
        }
        setEstado(EstadoMecanico.REFORMADO);
    }

}



