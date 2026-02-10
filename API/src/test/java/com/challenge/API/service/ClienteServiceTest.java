package com.challenge.API.service;

import com.challenge.API.Service.impl.ClienteServiceImpl;
import com.challenge.API.model.Cliente;
import com.challenge.API.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        LocalDate nacimiento = LocalDate.of(1960, 10, 30);

        cliente = new Cliente(
                "Diego Armando",
                "Maradona",
                "Maradona SA",
                "20-12345678-1",
                nacimiento,
                "1133557799",
                "d10s@futbol.com",
                LocalDate.now(),
                LocalDate.now()
        );
    }

    @Test
    void insertNombreVacio() {
        cliente.setNombre("");

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> clienteService.insert(cliente));

        assertEquals("El nombre es obligatorio.", ex.getMessage());
    }

    @Test
    void insertEmailDuplicado() {
        when(clienteRepository.existsByEmail(cliente.getEmail()))
                .thenReturn(true);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> clienteService.insert(cliente));

        assertEquals("El email ya se encuentra registrado.", ex.getMessage());
    }

    @Test
    void updateIdNoExisteError() {
        cliente.setId(1L);

        when(clienteRepository.existsById(1L))
                .thenReturn(false);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> clienteService.update(cliente));

        assertEquals("El ID no existe en la base de datos.", ex.getMessage());
    }
}
