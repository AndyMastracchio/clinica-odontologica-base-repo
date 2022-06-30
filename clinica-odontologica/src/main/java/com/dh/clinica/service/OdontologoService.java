package com.dh.clinica.service;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.repository.OdontologoRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;



// TODO: este servicio debería implementar una interface que defina el contrato de los métodos que se usarán
@Service
public class OdontologoService {

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        LOGGER.info("GUARDANDO UN ODONTOLOGO - EN SERVICE - METODO registrarOdontologo");
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        // Librería Objects permite hacer validaciones tratando nulos
        if(Objects.isNull(buscar(id))) {
            throw new ResourceNotFoundException("No existe odontologo con id: " + id);
        }
        odontologoRepository.deleteById(id);
    }

    public Odontologo buscar(Long id) {
        return odontologoRepository.findById(id).get();
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }


}
