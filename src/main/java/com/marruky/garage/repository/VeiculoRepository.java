package com.marruky.garage.repository;

import com.marruky.garage.model.Peca;
import com.marruky.garage.model.Veiculo;

import java.util.*;
import java.util.function.Predicate;

public class VeiculoRepository {
    private Map<String, Veiculo> veiculos;

    public VeiculoRepository() {
        this.veiculos = new HashMap<>();
    }

    //IMPLEMENTAÇÕES



    public void adicionar(Veiculo entidade) {
        if(entidade == null) {
            throw new IllegalArgumentException("A entidade a ser adicionada nao pode ser null");
        }

        if(veiculos.containsKey(entidade.getMatricula())) {
            throw new IllegalStateException("O veículo a ser adicionado já existe!");
        }

        veiculos.put(entidade.getMatricula(), entidade);
    }


    public boolean remover(String matricula) {
        Veiculo veiculo = veiculos.remove(matricula);
        return veiculo != null;
    }

    public Optional<Veiculo> encontrarPorId(String matricula) {
        Veiculo veiculo = veiculos.get(matricula);
        return Optional.ofNullable(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return new ArrayList<>(veiculos.values());
    }

    public int contar() {
        return veiculos.size();
    }

    public List<Veiculo> pesquisar(String termo) {
        if(termo == null || termo.isBlank()) {
            return new ArrayList<>(veiculos.values());
        }

        String termoLower = termo.toLowerCase();

        return veiculos.values().stream()
                .filter(veiculo -> veiculo.getMatricula().toLowerCase().contains(termoLower)
                || veiculo.getModelo().toLowerCase().contains(termoLower))
                .toList();
    }

    public List<Veiculo> listarOrdenadoPorMatricula() {
        return veiculos.values().stream()
                .sorted(Comparator.comparing(Veiculo::getMatricula))
                .toList();
    }

    public long contarComFiltro(Predicate<Veiculo> filtro) {
        return veiculos.values().stream()
                .filter(filtro)
                .count();
    }
}
