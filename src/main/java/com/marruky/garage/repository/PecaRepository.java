package com.marruky.garage.repository;

import com.marruky.garage.model.Peca;

import java.util.*;
import java.util.function.Predicate;

public class PecaRepository implements RepositorioGenerico<Peca>{

    private Map<Integer, Peca> pecas;

    public PecaRepository() {
        this.pecas = new HashMap<>();
    }
    //Implementações


    @Override
    public void adicionar(Peca entidade) {
        if(entidade == null) {
            throw new IllegalArgumentException("A peça a ser adicionada não pode ser null");
        }
        if(pecas.containsKey(entidade.getId())) {
            throw new IllegalStateException("A peça já foi adicionada");
        }
        pecas.put(entidade.getId(),entidade);
    }

    @Override
    public Optional<Peca> encontrarPorId(int id) {
        Peca peca = pecas.get(id);
        return Optional.ofNullable(peca);
    }

    @Override
    public List<Peca> listarTodos() {
        return new ArrayList<>(pecas.values());
    }

    @Override
    public int contar() {
        return pecas.size();
    }


    public List<Peca> pesquisar(String termo) {
        if(termo == null || termo.isBlank()) {
            return new ArrayList<>(pecas.values());
        }

        String termoLower = termo.toLowerCase();

        return pecas.values().stream()
                .filter(p -> p.getNome().toLowerCase().contains(termoLower)
                        || p.getReferencia().toLowerCase().contains(termoLower))
                .toList();
    }


    public List<Peca> listarOrdenadoPorNome() {
        return pecas.values().stream()
                .sorted(Comparator.comparing(Peca::getNome))
                .toList();
    }

    @Override
    public long contarComFiltro(Predicate<Peca> filtro) {
        return pecas.values().stream()
                .filter(filtro)
                .count();
    }
}
