package com.marruky.garage.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface RepositorioGenerico<T> {
    void adicionar(T entidade);
    boolean remover(int id);
    Optional<T> encontrarPorId(int id);
    List<T> listarTodos();
    int contar();
    long contarComFiltro(Predicate<T> filtro);
}
