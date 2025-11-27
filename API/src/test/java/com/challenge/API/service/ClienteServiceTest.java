package com.challenge.API.service;

import com.challenge.API.Service.impl.ClienteServiceImpl;
import com.challenge.API.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    public void insertarClienteTest(){

      Cliente clienteInsertado = clienteService.insert(cliente);

      assertEquals(clienteInsertado.getNombre(), cliente.getNombre());
      assertEquals(clienteInsertado.getApellido(), cliente.getApellido());
      assertEquals(clienteInsertado.getRazonSocial(), cliente.getRazonSocial());
      assertEquals(clienteInsertado.getCuit(), cliente.getCuit());
      assertEquals(clienteInsertado.getFechaDeNacimiento(), cliente.getFechaDeNacimiento());
      assertEquals(clienteInsertado.getTelefonoCelular(), cliente.getTelefonoCelular());
      assertEquals(clienteInsertado.getEmail(), cliente.getEmail());
      assertEquals(clienteInsertado.getFechaCreacion(), cliente.getFechaCreacion());
      assertEquals(clienteInsertado.getFechaModificacion(), cliente.getFechaModificacion());

      clienteService.delete(clienteInsertado.getId());
    }

    @Test
    void sinNombreLanzaExcepcionTest() {

        cliente.setNombre(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.insert(cliente)
        );
    }

    @Test
    public void modificarClienteTest() throws Exception {
        // insreto cliente nuevo en la base y lo recupero
        Cliente clienteInsertado = clienteService.insert(cliente);
        // constato que tenga los mismos datos
        assertEquals(clienteInsertado.getNombre(), cliente.getNombre());
        assertEquals(clienteInsertado.getApellido(), cliente.getApellido());
        assertEquals(clienteInsertado.getRazonSocial(), cliente.getRazonSocial());
        assertEquals(clienteInsertado.getCuit(), cliente.getCuit());
        assertEquals(clienteInsertado.getFechaDeNacimiento(), cliente.getFechaDeNacimiento());
        assertEquals(clienteInsertado.getTelefonoCelular(), cliente.getTelefonoCelular());
        assertEquals(clienteInsertado.getEmail(), cliente.getEmail());
        assertEquals(clienteInsertado.getFechaCreacion(), cliente.getFechaCreacion());
        assertEquals(clienteInsertado.getFechaModificacion(), cliente.getFechaModificacion());
        // le hago cambios permitidos y sin permitir
        clienteInsertado.setRazonSocial("nueva razon social");
        clienteInsertado.setEmail("nuevo@email.com");
        clienteInsertado.setTelefonoCelular("1122223333");
        clienteInsertado.setCuit("no se puede cambiar");
        // lo guardo en la base
        clienteService.update(clienteInsertado);
        // recupero el cliente con sus modificaciones
        Cliente clienteModificado = clienteService.get(clienteInsertado.getId()) ;
        // verifico que se hayan guardado los cambios permitidos
        assertEquals(clienteInsertado.getRazonSocial(), clienteModificado.getRazonSocial());
        assertEquals(clienteInsertado.getTelefonoCelular(), clienteModificado.getTelefonoCelular());
        assertEquals(clienteInsertado.getEmail(), clienteModificado.getEmail());
        // constato que no se haya cambiado el cuit
        assertNotEquals(clienteInsertado.getCuit(), clienteModificado.getCuit());
        //  elimino de la base el sujeto de prueba
        clienteService.delete(clienteInsertado.getId());
    }

    @Test
    public void getAllClientesTest(){
        List<Cliente> clientes = clienteService.getAll();

        assertEquals(clientes.size(),5);
        assertEquals(clientes.get(0).getId(), 1L);

    }

    @Test
    public void eliminarUnCliente() throws Exception {
        // se insreta cliente nuevo en la base y lo recupero
        Cliente clienteInsertado = clienteService.insert(cliente);
        //se verifica que exista
        assertNotNull(clienteService.get(clienteInsertado.getId()));
        // se elimina de la base de datos
        clienteService.delete(clienteInsertado.getId());
        // se verifica que no exista en a base de datos
        assertThrows(
                RuntimeException.class,
                () ->clienteService.get(clienteInsertado.getId())
        );


    }


}
