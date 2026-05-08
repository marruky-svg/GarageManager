package com.marruky.garage.repository;

import com.marruky.garage.model.Cliente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;


public class ClienteRepository implements RepositorioGenerico<Cliente> {
    private Map<Integer, Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new HashMap<>();
    }

    //IMPLEMENTAÇÕES


    @Override
    public void adicionar(Cliente entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("Cliente não pode ser null");
        }
        if (clientes.containsKey(entidade.getId())) {
            throw new IllegalStateException(
                    "Já existe um cliente com este id " + entidade.getId());
        }
        clientes.put(entidade.getId(), entidade);
    }

    @Override
    public boolean remover(int id) {
        Cliente removido = clientes.remove(id);
        return removido != null;
    }

    @Override
    public Optional<Cliente> encontrarPorId(int id) {
        Cliente cliente = clientes.get(id);
        return Optional.ofNullable(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public int contar() {
        return clientes.size();
    }

    public List<Cliente> pesquisar(String termo) {
        if (termo == null || termo.isBlank()) {
            return new ArrayList<>(clientes.values());
        }
        String termoLower = termo.toLowerCase();
        return clientes.values().stream()
                .filter(c -> c.getNome().toLowerCase().contains(termoLower)
                        || c.getEmail().toLowerCase().contains(termoLower))
                .toList();
    }

    public List<Cliente> listarOrdenadoPorNome() {
        return clientes.values().stream()
                .sorted(Comparator.comparing(Cliente::getNome))
                .toList();
    }

    //Predicate<Cliente> -> interface funcional(funcao que recebe Cliente e devolve boolean)
    //O metodo aceita uma função como parâmetro - assinatura totalmente genérica
    public long contarComFiltro(Predicate<Cliente> filtro) {
        return clientes.values().stream()
                .filter(filtro)
                .count();
    }


}
