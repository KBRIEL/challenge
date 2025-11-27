package com.challenge.API.service;

import com.challenge.API.Service.impl.ClienteServiceImpl;
import com.challenge.API.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Autowired
    private ClienteServiceImpl clienteService;
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
    void insertNombreVacio() {
        cliente.setNombre("");

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> clienteService.insert(cliente));

        assertEquals("El nombre es obligatorio.", ex.getMessage());
    }

    @Test
    public void insertEmailDuplicado() {

        when(clienteService.existsByEmail(cliente.getEmail())).thenReturn(true);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> clienteService.insert(cliente));

        assertEquals("El email ya se encuentra registrado.", ex.getMessage());
    }

    public void updateOk() throws Exception {


    }


    @Test
    void updateIdNoExisteError() {
        when(clienteService.existsById(1L)).thenReturn(false);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> clienteService.update(cliente));

        assertEquals("El ID no existe en la base de datos.", ex.getMessage());
    }



}
