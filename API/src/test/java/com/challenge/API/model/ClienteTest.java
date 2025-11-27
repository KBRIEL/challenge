package com.challenge.API.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ClienteTest {

   private Cliente cliente = new Cliente();

    @BeforeEach()
    public void Constructor() {
        LocalDate nacimiento = LocalDate.of(1960, 10, 30);
        LocalDate fechaCreacion = LocalDate.now();
        LocalDate fechaModificacion = LocalDate.now();

         cliente = new Cliente(
                "Diego Armando",
                "Maradona",
                "Maradona SA",
                "20-12345678-1",
                nacimiento,
                "1133557799",
                "d10s@futbol.com",
                fechaCreacion,
                fechaModificacion
        );

        assertEquals("Diego Armando", cliente.getNombre());
        assertEquals("Maradona", cliente.getApellido());
        assertEquals("Maradona SA", cliente.getRazonSocial());
        assertEquals("20-12345678-1", cliente.getCuit());
        assertEquals(nacimiento, cliente.getFechaDeNacimiento());
        assertEquals("1133557799", cliente.getTelefonoCelular());
        assertEquals("d10s@futbol.com", cliente.getEmail());
        assertEquals(fechaCreacion, cliente.getFechaCreacion());
        assertEquals(fechaModificacion, cliente.getFechaModificacion());
    }



    @Test
    public void testId() {
        Cliente cliente = new Cliente();
        cliente.setId(10L);
        assertEquals(10L, cliente.getId());
    }

    @Test
    public void testNombre() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        assertEquals("Carlos", cliente.getNombre());
    }

    @Test
    public void testApellido() {
        Cliente cliente = new Cliente();
        cliente.setApellido("Gomez");
        assertEquals("Gomez", cliente.getApellido());
    }

    @Test
    public void testRazonSocial() {
        Cliente cliente = new Cliente();
        cliente.setRazonSocial("Empresa SRL");
        assertEquals("Empresa SRL", cliente.getRazonSocial());
    }

    @Test
    public void testCuit() {
        Cliente cliente = new Cliente();
        cliente.setCuit("23-98765432-1");
        assertEquals("23-98765432-1", cliente.getCuit());
    }

    @Test
    public void testFechaNacimiento() {
        Cliente cliente = new Cliente();
        LocalDate fecha = LocalDate.of(2001, 1, 1);
        cliente.setFechaDeNacimiento(fecha);
        assertEquals(fecha, cliente.getFechaDeNacimiento());
    }

    @Test
    public void testTelefono() {
        Cliente cliente = new Cliente();
        cliente.setTelefonoCelular("1166443322");
        assertEquals("1166443322", cliente.getTelefonoCelular());
    }

    @Test
    public void testEmail() {
        Cliente cliente = new Cliente();
        cliente.setEmail("correo@test.com");
        assertEquals("correo@test.com", cliente.getEmail());
    }

    @Test
    public void testFechaCreacion() {
        Cliente cliente = new Cliente();
        LocalDate fecha = LocalDate.now();
        cliente.setFechaCreacion(fecha);
        assertEquals(fecha, cliente.getFechaCreacion());
    }

    @Test
    public void testFechaModificacion() {
        Cliente cliente = new Cliente();
        LocalDate fecha = LocalDate.now();
        cliente.setFechaModificacion(fecha);
        assertEquals(fecha, cliente.getFechaModificacion());
    }

    @Test
    public void testClienteSinCampos() {
        Cliente cliente = new Cliente();
        assertNull(cliente.getId());
        assertNull(cliente.getNombre());
        assertNull(cliente.getApellido());
        assertNull(cliente.getRazonSocial());
        assertNull(cliente.getCuit());
        assertNull(cliente.getFechaDeNacimiento());
        assertNull(cliente.getTelefonoCelular());
        assertNull(cliente.getEmail());
        assertNull(cliente.getFechaCreacion());
        assertNull(cliente.getFechaModificacion());
    }


}
