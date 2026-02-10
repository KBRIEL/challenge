package com.challenge.API.controller;

import com.challenge.API.Service.impl.ClienteServiceImpl;
import com.challenge.API.dto.ClienteDTO;
import com.challenge.API.dto.ClienteUpdateDTO;
import com.challenge.API.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(clienteService.getAll().stream().map(ClienteDTO::new).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) throws Exception {
        try{
           Cliente cliente = clienteService.get(id);
            return  ResponseEntity.ok(new ClienteDTO(cliente));
        }catch (HttpClientErrorException.BadRequest b){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el cliente con el id : " + id);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String nombre){
        return ResponseEntity.ok(clienteService.search(nombre).stream().map(ClienteDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente(dto.getNombre(), dto.getApellido(), dto.getRazonSocial(),
                dto.getCuit(), dto.getFechaDeNacimiento(), dto.getTelefonoCelular(), dto.getEmail(), dto.getFechaCreacion(), dto.getFechaModificacion());

        return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteDTO(clienteService.insert(cliente)));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ClienteUpdateDTO dto) {
        Cliente cliente = new Cliente(dto.getId(),  dto.getRazonSolcial(),
                  dto.getTelefonoCelular(), dto.getEmail());

        clienteService.update(cliente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if (clienteService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
