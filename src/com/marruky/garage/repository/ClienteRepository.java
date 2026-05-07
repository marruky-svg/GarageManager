package com.marruky.garage.repository;

import com.marruky.garage.model.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClienteRepository implements RepositorioGenerico<Cliente> {
    private Map<Integer, Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new HashMap<>();
    }

    //IMPLEMENTAÇÕES


    @Override
    public void adicionar(Cliente entidade) {
        if(entidade == null) {
            throw new IllegalArgumentException("Cliente não pode ser null");
        }
        if(clientes.containsKey(entidade.getId())) {
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
}
