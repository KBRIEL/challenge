package com.challenge.API.dto;

import com.challenge.API.model.Cliente;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteUpdateDTO {
    private Long id;
    private String razonSolcial;
    private String telefonoCelular;
    private String email;



    public ClienteUpdateDTO(Long id,  String razonSolcial, String telefonoCelular, String email) {
        this.id= id;
        this.razonSolcial = razonSolcial;
        this.telefonoCelular = telefonoCelular;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public String getRazonSolcial() {
        return razonSolcial;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public String getEmail() {
        return email;
    }
}
