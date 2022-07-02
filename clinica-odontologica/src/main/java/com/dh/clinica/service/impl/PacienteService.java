package com.dh.clinica.service.impl;


import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

// TODO: este servicio debería implementar una interface que defina el contrato de los métodos que se usarán
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardar(Paciente paciente) {
        paciente.setFechaIngreso(new Date());
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        // Librería Objects permite hacer validaciones tratando nulos
        if(Objects.nonNull(buscar(id))) {
            pacienteRepository.deleteById(id);
            throw new ResourceNotFoundException("No existe paciente con id: " + id);
        }

    }

    public Paciente buscar(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("No existe paciente con id: " + id);
        }
        return pacienteRepository.findById(id).get();
    }

    public Paciente actualizar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}
