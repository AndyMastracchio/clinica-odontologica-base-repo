package com.dh.clinica.service;


import com.dh.clinica.model.Paciente;
import com.dh.clinica.repository.IDao;

import java.util.Date;
import java.util.List;

public class PacienteService {

    private final IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardar(Paciente paciente) {
        paciente.setFechaIngreso(new Date());
        return pacienteIDao.guardar(paciente);
    }

    public Paciente buscar(Integer id) {
        return pacienteIDao.buscar(id);
    }

    public List<Paciente> buscarTodos() {
        return pacienteIDao.buscarTodos();
    }

    public void eliminar(Integer id) {
        pacienteIDao.eliminar(id);
    }

    public Paciente actualizar(Paciente p) {
        return pacienteIDao.actualizar(p);
    }
}
