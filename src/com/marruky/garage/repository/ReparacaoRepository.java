package com.marruky.garage.repository;


import com.marruky.garage.model.Reparacao;
import java.util.*;
import java.util.function.Predicate;

public class ReparacaoRepository implements RepositorioGenerico<Reparacao> {
    private Map<Integer, Reparacao> reparacoes;

    public ReparacaoRepository() {
        this.reparacoes = new HashMap<>();
    }

    //IMPLEMENTAÇÕES


    @Override
    public void adicionar(Reparacao entidade) {
        if(entidade == null) {
            throw new IllegalArgumentException("A entidade a ser adicionada não pode ser null");
        }
        if(reparacoes.containsKey(entidade.getId())) {
            throw new IllegalStateException("A reparacao a ser adicionada já existe");
        }
        reparacoes.put(entidade.getId(), entidade);
    }

    @Override
    public boolean remover(int id) {
        Reparacao reparacao = reparacoes.remove(id);
        return reparacao != null;
    }

    @Override
    public Optional<Reparacao> encontrarPorId(int id) {
        Reparacao reparacao = reparacoes.get(id);
        return Optional.ofNullable(reparacao);
    }

    @Override
    public List<Reparacao> listarTodos() {
        return new ArrayList<>(reparacoes.values());
    }

    @Override
    public int contar() {
        return reparacoes.size();
    }

    public List<Reparacao> pesquisar(String termo) {
        if(termo == null || termo.isBlank()) {
            return new ArrayList<>(reparacoes.values());
        }
        String termoLower = termo.toLowerCase();
        return reparacoes.values().stream()
                .filter(r -> r.getNumeroFatura().toLowerCase().contains(termoLower))
                .toList();
    }

    public List<Reparacao> listarOrdenadoPorNumeroFatura()
    {
        return reparacoes.values().stream()
                .sorted(Comparator.comparing(Reparacao::getNumeroFatura))
                .toList();
    }

    public long contarComFiltro(Predicate<Reparacao> filtro) {
        return reparacoes.values().stream()
                .filter(filtro)
                .count();
    }
}
