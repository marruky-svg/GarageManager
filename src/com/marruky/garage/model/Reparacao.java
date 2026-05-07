package com.marruky.garage.model;

import com.marruky.garage.enums.EstadoReparacao;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Reparacao {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Mecanico mecanico;
    private List<Peca> pecas;
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
        this.pecas = new ArrayList<>();
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

    public List<Peca> getPecas() {
        return List.copyOf(pecas);
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
    public void adicionarPeca(Peca peca) {
        if (peca == null) {
            throw new IllegalArgumentException("Peça não pode estar em branco");
        }
        if (estado != EstadoReparacao.EM_CURSO && estado != EstadoReparacao.ABERTA) {
            throw new IllegalStateException(
                    "Não é possível adicionar peças a uma reparação no estado " + estado
            );
        }
        this.pecas.add(peca);
    }

    public boolean removerPeca(Peca peca) {
        if (peca == null) {
            throw new IllegalArgumentException("Peça não pode estar em branco");
        }
        if (estado != EstadoReparacao.EM_CURSO && estado != EstadoReparacao.ABERTA) {
            throw new IllegalStateException(
                    "Não é possível remover peças de uma reparação no estado " + estado
            );
        }
        return this.pecas.remove(peca);
    }

    public double calcularPrecoTotal() {
        double custoMaoDeObra = horasTrabalho * mecanico.getPrecoHora();

        double custoPecas = 0.0;
        for (Peca peca : pecas) {
            custoPecas += peca.calcularPrecoComIVA();
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
                ", pecas=" + pecas.size() +
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
}
