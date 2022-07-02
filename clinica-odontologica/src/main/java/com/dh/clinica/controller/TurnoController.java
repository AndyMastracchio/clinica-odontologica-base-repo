package com.dh.clinica.controller;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService,
                           OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping("/new")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno)
            throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<Turno> response;
        if (pacienteService.buscar(turno.getPaciente().getId()) != null &&
                odontologoService.buscar(turno.getOdontologo().getId()) != null) {
            response = ResponseEntity.ok(turnoService.registrarTurno(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> response;
        // Esta validacion no esta en el enunciado del ejericio, pero se las dejo para que la tengan.
        if (turnoService.buscar(id) != null) {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Long id) {
        Turno turno = turnoService.buscar(id);
        return ResponseEntity.ok(turno);
    }
}
