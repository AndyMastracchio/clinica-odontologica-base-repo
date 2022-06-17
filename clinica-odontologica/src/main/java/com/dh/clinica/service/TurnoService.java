package com.dh.clinica.service;

import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.IDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TurnoService {

    private final IDao<Turno> turnoRepository;
    ObjectMapper mapper = new ObjectMapper();

    public TurnoService(IDao<Turno> turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public Turno registrarTurno(Turno turno) {
        return turnoRepository.guardar(turno);
    }

    public List<TurnoDto> listar() {
        List<Turno> listTurno = turnoRepository.buscarTodos();
        List<TurnoDto> listTurnoDto = new ArrayList<>();
        TurnoDto turnoDto;
        for (Turno turno : listTurno) {
            turnoDto = mapper.convertValue(turno, TurnoDto.class);
            listTurnoDto.add(turnoDto);
        }
        return listTurnoDto;
    }

    public void eliminar(Integer id) {
        turnoRepository.eliminar(id);
    }

    public Turno actualizar(Turno turno) {
        return turnoRepository.actualizar(turno);
    }

    public Turno buscar(Integer id) {
        return turnoRepository.buscar(id);
    }

}
