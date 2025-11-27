package com.challenge.API.Service.impl;

import com.challenge.API.Service.IClienteService;
import com.challenge.API.model.Cliente;
import com.challenge.API.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;





@Slf4j
@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente insert(Cliente cliente) {

        // Validacin de campos obligatorios
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            log.warn("El campo 'nombre' está vacío o es nulo");
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (cliente.getApellido() == null || cliente.getApellido().isBlank()) {
            log.warn("El campo 'apellido' está vacío o es nulo");
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }

        if (cliente.getRazonSocial() == null || cliente.getRazonSocial().isBlank()) {
            log.warn("El campo 'razon social' está vacío o es nulo");
            throw new IllegalArgumentException("La razón social es obligatoria.");
        }

        if (cliente.getCuit() == null || cliente.getCuit().isBlank()) {
            log.warn("El campo 'CUIT' está vacío o es nulo");
            throw new IllegalArgumentException("El CUIT es obligatorio.");
        }

        if (cliente.getFechaDeNacimiento() == null) {
            log.warn("la fecha de nacimiento es nula");
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }

        if (cliente.getTelefonoCelular() == null || cliente.getTelefonoCelular().isBlank()) {
            log.warn("El campo 'telefono celular' está vacío o es nulo");
            throw new IllegalArgumentException("El teléfono celular es obligatorio.");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            log.warn("El campo 'email' está vacío o es nulo");
            throw new IllegalArgumentException("El email es obligatorio.");
        }

        // Validacion de longitud
        if (cliente.getNombre().length() > 100) {
            log.warn("El campo 'nombre' supera los 100 caracteres.");
            throw new IllegalArgumentException("El nombre no puede superar los 100 caracteres.");
        }

        if (cliente.getApellido().length() > 100) {
            log.warn("El campo 'apellido' supera los 100 caracteres.");
            throw new IllegalArgumentException("El apellido no puede superar los 100 caracteres.");
        }

        if (cliente.getRazonSocial().length() > 150) {
            log.warn("El campo 'razon social' supera los 150 caracteres.");
            throw new IllegalArgumentException("La razón social no puede superar los 150 caracteres.");
        }

        if (cliente.getCuit().length() > 20) {
            log.warn("El campo 'CUIT' supera los 20 caracteres.");
            throw new IllegalArgumentException("El CUIT no puede superar los 20 caracteres.");
        }

        if (cliente.getTelefonoCelular().length() > 30) {
            log.warn("El campo 'telefono celular' supera los 30 caracteres.");
            throw new IllegalArgumentException("El teléfono celular no puede superar los 30 caracteres.");
        }

        if (cliente.getEmail().length() > 150) {
            log.warn("El campo 'email' supera los 150 caracteres.");
            throw new IllegalArgumentException("El email no puede superar los 150 caracteres.");
        }

        // Validacion de unicidad
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            log.warn("El email ya se encuentra registrado.");
            throw new IllegalArgumentException("El email ya se encuentra registrado.");
        }else{
            log.info("El email se ha registrado con exito.");
            clienteRepository.findByEmail(cliente.getEmail());
        }

        if (clienteRepository.existsByCuit(cliente.getCuit())) {
            log.warn("El CUIT ya se encuentra registrado.");
            throw new IllegalArgumentException("El CUIT ya se encuentra registrado.");
        }else{
            log.info("El CUIT se ha registrado con exito.");
            clienteRepository.findByCuit(cliente.getCuit());
        }


        return clienteRepository.save(cliente);
    }

    @Override
    public void update(Cliente cliente) {

        //TODO me baso en los campos que creo que pueden ser modificables de un cliente
        // campos como el nombre, apellido, fecha de nacimiento o cuit no pueden ser modificados

        if (!clienteRepository.existsById(cliente.getId())){
            log.warn("El id no existe");
            throw new IllegalArgumentException("El ID no existe en la base de datos.");
        }

        Cliente clienteBase = clienteRepository.findById(cliente.getId()).get();

        // Razon Social
        if (cliente.getRazonSocial() != null && !cliente.getRazonSocial().isBlank()) {
            if (cliente.getRazonSocial().length() > 150) {
                log.warn("El campo 'razon social' supera los 150 caracteres.");
                throw new IllegalArgumentException("La razón social no puede superar los 150 caracteres.");
            }
            clienteBase.setRazonSocial(cliente.getRazonSocial());
            log.info(" se modifico la razon social");
        }
        // Email
        if (cliente.getEmail() != null && !cliente.getEmail().isBlank()) {
            if (cliente.getEmail().length() > 150) {
                log.warn("El campo 'email' supera los 150 caracteres.");
                throw new IllegalArgumentException("El email no puede superar los 150 caracteres.");
            }
            if (clienteRepository.existsByEmail(cliente.getEmail())) {
                log.warn("El email ya se encuentra registrado.");
                throw new IllegalArgumentException("El email ya se encuentra registrado.");
            }
            log.info(" se modifico el email");
            clienteBase.setEmail(cliente.getEmail());
        }

        // Celular
        if (cliente.getTelefonoCelular() != null && !cliente.getTelefonoCelular().isBlank()) {
            if (cliente.getTelefonoCelular().length() > 30) {
                log.warn("El campo 'telefono celular' supera los 30 caracteres.");
                throw new IllegalArgumentException("El teléfono celular no puede superar los 30 caracteres.");
            }
            clienteBase.setTelefonoCelular(cliente.getTelefonoCelular());
            log.info(" se modifico el telefono celular");
        }

        clienteBase.setFechaModificacion(LocalDate.now());
        log.info(" fecha de modificaacion: "+ LocalDate.now());

       clienteRepository.save(clienteBase);
    }

    @Override
    public Cliente get(Long clienteId) throws Exception {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el cliente con el id: " + clienteId));

    }

    @Override
    public boolean delete(Long clienteId) {

        if( clienteRepository.existsById(clienteId)){
            clienteRepository.deleteById(clienteId);
            log.info(" el cliente con el ID: "+ clienteId + "fue eliminado");
            return true;
        }else{
            log.warn(" el cliente con el ID: "+ clienteId + "no se pude eliminar porque no existe en la base de datos");
            return false;
        }

    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> search(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Object existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public Object existsById(long id) {
        return clienteRepository.existsById(id);
    }
}
