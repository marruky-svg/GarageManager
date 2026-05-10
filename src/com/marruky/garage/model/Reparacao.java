package com.marruky.garage.model;

import com.marruky.garage.enums.EstadoReparacao;
import com.marruky.garage.interfaces.Faturavel;
import com.marruky.garage.interfaces.Pesquisavel;


import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Reparacao implements Faturavel, Pesquisavel<Reparacao> {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Mecanico mecanico;
    private List<LinhaPeca> linhasPeca;
    private double horasTrabalho;
    private EstadoReparacao estado;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;

    public Reparacao(int id, Cliente cliente, Veiculo veiculo, Mecanico mecanico, double horasTrabalho) {
        setId(id);
        setCliente(cliente);
        setVeiculo(veiculo);
        setMecanico(mecanico);
        setHorasTrabalho(horasTrabalho);
        this.linhasPeca = new ArrayList<>();
        this.estado = EstadoReparacao.ABERTA;
        this.dataAbertura = LocalDateTime.now();
        this.dataConclusao = null;

    }

    //GETTERS
    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public List<LinhaPeca> getLinhasPeca() {
        return List.copyOf(linhasPeca);
    }

    public double getHorasTrabalho() {
        return horasTrabalho;
    }

    public EstadoReparacao getEstado() {
        return estado;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode estar em branco");
        }
        this.cliente = cliente;
    }

    public void setVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode estar em branco");
        }
        this.veiculo = veiculo;
    }

    public void setMecanico(Mecanico mecanico) {
        if (mecanico == null) {
            throw new IllegalArgumentException("Mecânico não pode estar em branco");
        }
        this.mecanico = mecanico;
    }

    public void setHorasTrabalho(double horasTrabalho) {
        if (horasTrabalho < 0) {
            throw new IllegalArgumentException("Horas de trabalho não podem ser negativas");
        }
        this.horasTrabalho = horasTrabalho;
    }

    private void setEstado(EstadoReparacao estado) {
        this.estado = estado;
    }


    //OPERACAO COM PECAS
    public void adicionarLinhaPeca(Peca peca, int quantidade) {
        if (estado != EstadoReparacao.ABERTA && estado != EstadoReparacao.EM_CURSO) {
            throw new IllegalStateException("Não é possível adicionar peças a uma reparação no estado " + estado);
        }
        LinhaPeca linha = new LinhaPeca(peca, quantidade);
        linhasPeca.add(linha);
    }

    public boolean removerLinhaPeca(int indice) {
        if (estado != EstadoReparacao.ABERTA && estado != EstadoReparacao.EM_CURSO) {
            throw new IllegalStateException("Não é possível remover peças de uma reparação no estado " + estado);
        }

        if (indice < 0 || indice >= linhasPeca.size()) {
            return false;
        }

        linhasPeca.remove(indice);
        return true;
    }

    public double calcularPrecoTotal() {
        double custoMaoDeObra = horasTrabalho * mecanico.getPrecoHora();

        double custoPecas = 0.0;
        for (LinhaPeca linha : linhasPeca) {
            custoPecas += linha.calcularSubtotalComIVA();
        }
        return custoMaoDeObra + custoPecas;
    }

    @Override
    public String toString() {
        return "Reparacao{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", veiculo=" + veiculo.getMatricula() +
                ", mecanico=" + mecanico.getNome() +
                ", linhasPeca=" + linhasPeca.size() +
                ", horasTrabalho=" + horasTrabalho +
                ", estado=" + estado +
                ", dataAbertura=" + dataAbertura +
                ", dataConclusao=" + dataConclusao +
                "}";
    }

    //INICIO DO FSM(FINITE STATE MACHINE)
    public void iniciarTrabalho() {
        if (estado != EstadoReparacao.ABERTA) {
            throw new IllegalStateException("Só é possível iniciar trabalho em reparações ABERTAS. Estado atual: " + estado);
        }
        setEstado(EstadoReparacao.EM_CURSO);
    }

    public void concluirTrabalho() {
        if (estado != EstadoReparacao.EM_CURSO) {
            throw new IllegalStateException("Só é possível concluir trabalho se estado de reparação for EM_CURSO. Estado atual: " + estado);
        }
        setEstado(EstadoReparacao.CONCLUIDA);
        this.dataConclusao = LocalDateTime.now();
    }

    public void faturar() {
        if (estado != EstadoReparacao.CONCLUIDA) {
            throw new IllegalStateException("Só é possível faturar uma reparação se estado for CONCLUIDA. Estado atual: " + estado);
        }
        setEstado(EstadoReparacao.FATURADA);
    }

    //IMPLEMENTAÇÕES DE FATURAVEL
    @Override
    public double calcularTotal() {
        return calcularPrecoTotal();
    }

    @Override
    public boolean podeSerFaturado() {
        return estado == EstadoReparacao.CONCLUIDA;
    }

    @Override
    public String getNumeroFatura() {
        return "FT " + dataAbertura.getYear() + "/" + String.format("%05d", id);
    }

    //IMPLEMENTAÇÕES DE PESQUISAVEL

    @Override
    public boolean correspondeA(String termo) {
        if (termo == null || termo.isBlank()) {
            return false;
        }
        String termoLower = termo.toLowerCase();

        return cliente.getNome().toLowerCase().contains(termoLower)
                || veiculo.getMatricula().toLowerCase().contains(termoLower)
                || mecanico.getNome().toLowerCase().contains(termoLower)
                || estado.toString().toLowerCase().contains(termoLower);
    }
}
