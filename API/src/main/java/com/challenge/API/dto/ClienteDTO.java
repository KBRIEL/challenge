package com.challenge.API.dto;
import com.challenge.API.model.Cliente;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private String nombre;
    private String apellido;
    private String razonSocial;
    private String cuit;
    private LocalDate fechaDeNacimiento;
    private String telefonoCelular;
    private String email;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    public ClienteDTO(Cliente cliente) {
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.razonSocial = cliente.getRazonSocial();
        this.cuit = cliente.getCuit();
        this.fechaDeNacimiento = cliente.getFechaDeNacimiento();
        this.telefonoCelular = cliente.getTelefonoCelular();
        this.email = cliente.getEmail();
        this.fechaCreacion = cliente.getFechaCreacion();
        this.fechaModificacion = cliente.getFechaModificacion();
    }

    public ClienteDTO(String nombre, String apellido, String razonSocial, String cuit, LocalDate fechaDeNacimiento,
                      String telefonoCelular, String email, LocalDate fechaCreacion, LocalDate fechaModificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.telefonoCelular = telefonoCelular;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public ClienteDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }
}

