package com.dh.clinica.repository;

import java.util.List;

public interface IDao<T> {

    T guardar(T t);

    T buscar(Integer id);

    void eliminar(Integer id);

    List<T> buscarTodos();

    T actualizar(T t);
}
