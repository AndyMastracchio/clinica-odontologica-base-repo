package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.OdontologoRepository;
import com.dh.clinica.repository.PacienteRepository;
import com.dh.clinica.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// TODO: este servicio debería implementar una interface que defina el contrato de los métodos que se usarán
@Service
public class TurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final OdontologoRepository odontologoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository,
                        OdontologoRepository odontologoRepository,
                        PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Turno registrarTurno(Turno turno) throws BadRequestException {

        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());

        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());


        if (paciente.isEmpty() || odontologo.isEmpty()) {
            throw new BadRequestException("El paciente o el odontologo no existen");
        }

        turno.setOdontologo(odontologo.get());
        turno.setPaciente(paciente.get());


        turno.setDate(new Date());
        LOGGER.info("TURNO GUARDADO EXITOSAMENTE");
        return turnoRepository.save(turno);
    }

    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        // Librería Objects permite hacer validaciones tratando nulos
        if(Objects.isNull(buscar(id))) {
            throw new ResourceNotFoundException("No existe turno con id: " + id);
        }
        turnoRepository.deleteById(id);
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Turno buscar(Long id) {
        return turnoRepository.findById(id).get();
    }

}
