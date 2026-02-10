package com.challenge.API.Service;

import com.challenge.API.model.Cliente;

import java.util.List;

public interface IClienteService {

    public Cliente insert(Cliente cliente);
    public void update(Cliente cliente);
    public Cliente get(Long clienteId) throws Exception;
    public boolean delete(Long clienteId);
    public List<Cliente> getAll();
    public List<Cliente> search ( String nombre);

    public Object existsByEmail(String email);
    public Object existsById(long id);
}
