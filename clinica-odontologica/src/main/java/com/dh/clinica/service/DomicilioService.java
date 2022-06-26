package com.dh.clinica.service;


import com.dh.clinica.model.Domicilio;
import com.dh.clinica.repository.DomicilioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: este servicio debería implementar una interface que defina el contrato de los métodos que se usarán
@Service
public class DomicilioService {

    private final DomicilioRepository domicilioRepository;

    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Domicilio guardar(Domicilio domicilio) {
        domicilioRepository.save(domicilio);
        return domicilio;
    }

    public Domicilio buscar(Long id) {
        return domicilioRepository.findById(id).get();
    }

    public List<Domicilio> buscarTodos() {
        return domicilioRepository.findAll();
    }

    public void eliminar(Long id) {
        domicilioRepository.deleteById(id);
    }

}
