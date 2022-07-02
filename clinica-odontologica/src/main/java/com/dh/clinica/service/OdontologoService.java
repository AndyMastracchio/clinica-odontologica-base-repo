package com.dh.clinica.service;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface OdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    void eliminar(Long id) throws ResourceNotFoundException;

    Odontologo buscar(Long id) throws ResourceNotFoundException;

    List<Odontologo> buscarTodos();

    Odontologo actualizar(Odontologo odontologo);
}
