package com.marruky.garage.repository;

import com.marruky.garage.model.Mecanico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class MecanicoRepository implements RepositorioGenerico<Mecanico> {

    private Map<Integer, Mecanico> mecanicos;


    public MecanicoRepository() {
        this.mecanicos = new HashMap<>();
    }
    //Implementações

    @Override
    public void adicionar(Mecanico entidade) {
        if(entidade == null) {
            throw new IllegalArgumentException("A entidade mecanico nao pode ser null");
        }
        if(mecanicos.containsKey(entidade.getId())) {
            throw new IllegalStateException("O mecanico a ser adicionada já existe");
        }
        mecanicos.put(entidade.getId(), entidade);
    }

    @Override
    public boolean remover(int id) {
         Mecanico mRemovido = mecanicos.remove(id);
         return mRemovido != null;
    }

    @Override
    public Optional<Mecanico> encontrarPorId(int id) {
       Mecanico mecanico = mecanicos.get(id);
       return Optional.ofNullable(mecanico);
    }

    @Override
    public List<Mecanico> listarTodos() {
        return new ArrayList<>(mecanicos.values());
    }

    @Override
    public int contar() {
        return mecanicos.size();
    }

    public List<Mecanico> pesquisar(String termo) {
        if(termo == null || termo.isBlank()) {
            return new ArrayList<>(mecanicos.values());
        }
        String termoLower = termo.toLowerCase();
        return mecanicos.values().stream()
                .filter(mecanico -> mecanico.getNome().toLowerCase().contains(termoLower)
                || mecanico.getEspecialidade().toLowerCase().contains(termoLower))
                .toList();
    }


    public List<Mecanico> listarOrdenadoPorNome() {
        return mecanicos.values().stream()
                .sorted(Comparator.comparing(Mecanico::getNome))
                .toList();
    }

    @Override
    public long contarComFiltro(Predicate<Mecanico> filtro) {
        return mecanicos.values().stream()
                .filter(filtro)
                .count();
    }
}


