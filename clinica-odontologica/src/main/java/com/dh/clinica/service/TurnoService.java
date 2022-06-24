package com.dh.clinica.service;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.OdontologoRepository;
import com.dh.clinica.repository.PacienteRepository;
import com.dh.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TurnoService {

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

    public Turno registrarTurno(Turno turno) {
        Odontologo odontologo = odontologoRepository.findById(turno.getOdontologo().getId()).get();
        turno.setOdontologo(odontologo);
        Paciente paciente = pacienteRepository.findById(turno.getPaciente().getId()).get();
        turno.setPaciente(paciente);
        turno.setDate(new Date());
        return turnoRepository.save(turno);
    }

    public List<Turno> listar() {
        return turnoRepository.findAll();
    }

    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.save(turno);
    }

    public Turno buscar(Long id) {
        return turnoRepository.findById(id).get();
    }

}
