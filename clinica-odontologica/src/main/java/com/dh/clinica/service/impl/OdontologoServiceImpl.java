package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.OdontologoRepository;
import com.dh.clinica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoServiceImpl.class);

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        LOGGER.info("GUARDANDO UN ODONTOLOGO - EN SERVICE - METODO registrarOdontologo");
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        // Librería Objects permite hacer validaciones tratando nulos
        if (Objects.nonNull(buscar(id))) {
            odontologoRepository.deleteById(id);
        }
    }

    public Odontologo buscar(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("No existe odontologo con id: " + id);
        }
        return odontologoRepository.findById(id).get();
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }


}
