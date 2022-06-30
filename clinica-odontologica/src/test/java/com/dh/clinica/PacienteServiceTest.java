package com.dh.clinica;


import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.DomicilioService;
import com.dh.clinica.service.PacienteService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
@SpringBootTest
public class PacienteServiceTest {
    private static PacienteService pacienteService;
    private DomicilioService domicilioService;

    @BeforeClass
    public static void cargarDataSet() {
        Domicilio domicilio =
                new Domicilio("Av Santa fe", "444", "CABA", "Buenos Aires");
        Paciente paciente1 = pacienteService.guardar(
                new Paciente("Santiago", "Paz", "88888888", new Date(), domicilio));
        Domicilio domicilio1 =
                new Domicilio("Av Avellaneda", "333", "CABA", "Buenos Aires");
        Paciente paciente2 = pacienteService.guardar(
                new Paciente("Micaela", "Perez", "99999999", new Date(), domicilio));
    }

    @Test
    public void agregarYBuscarPacienteTest() {
        Domicilio domicilio =
                new Domicilio("Calle", "123", "Temperley", "Buenos Aires");
        Paciente paciente = pacienteService.guardar(
                new Paciente("Tomas", "Pereyra", "12345678", new Date(), domicilio));

        Assert.assertNotNull(pacienteService.buscar(paciente.getId()));
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(3L);
        Assert.assertTrue(pacienteService.buscar(3L) == null);
    }

    @Test
    public void traerTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);
        System.out.println(pacientes);
    }

}
