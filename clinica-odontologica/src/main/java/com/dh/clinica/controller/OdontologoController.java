package com.dh.clinica.controller;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
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
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/new")
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscar(id);
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/update")
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        ResponseEntity<Odontologo> response = null;

        if (odontologo.getId() != null && odontologoService.buscar(odontologo.getId()) != null) {
            response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String> response = null;

        odontologoService.eliminar(id);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
