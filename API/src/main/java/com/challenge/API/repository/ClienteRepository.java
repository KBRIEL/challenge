package com.challenge.API.repository;

import com.challenge.API.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    public List<Cliente> findByNombre(String nombre);

    public List<Cliente> findByEmail(String email);

    public List<Cliente> findByCuit(String cuit);
    public boolean existsByEmail(String email);
    public boolean existsByCuit(String cuit);
}
