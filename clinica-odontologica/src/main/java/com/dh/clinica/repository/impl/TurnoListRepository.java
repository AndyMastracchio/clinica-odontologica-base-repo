package com.dh.clinica.repository.impl;

import com.dh.clinica.model.Turno;
import com.dh.clinica.repository.IDao;

import java.util.ArrayList;
import java.util.List;

public class TurnoListRepository implements IDao<Turno> {

    private final List<Turno> turnos;

    public TurnoListRepository() {
        turnos = new ArrayList<>();
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        for (Turno turno : turnos) {
            if (turno.getId().equals(id)) {
                return turno;
            }
        }
        //return turnos.stream().filter(turno -> turno.getId().equals(id)).findFirst().get();
        return null;
    }

    @Override
    public void eliminar(Integer id) {

        for (Turno turno : turnos) {
            if (turno.getId().equals(id)) {
                turnos.remove(turno);
                return;
            }
        }

        //turnos.removeIf(turno -> turno.getId().equals(id));
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        turnos.add(turno);
        return turno;
    }
}
