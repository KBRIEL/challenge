package com.challenge.API.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String apellido;
    @Column( name = "razon_social", nullable = false, length = 150)
    private String razonSocial;
    @Column(unique = true, nullable = false, length = 20)
    private String cuit;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaDeNacimiento;
    @Column( name = "telefono_celular", nullable = false, length = 30)
    private String telefonoCelular;
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @Column(name = "fecha_creacion", updatable = false)
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @Column(name = "fecha_modificacion", updatable = false)
    @CreationTimestamp
    private LocalDate fechaModificacion;

    public Cliente(String nombre, String apellido, String razonSocial, String cuit, LocalDate fechaDeNacimiento,
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

    public Cliente(Long id, String razonSocial, String telefonoCelular, String email) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.telefonoCelular = telefonoCelular;
        this.email = email;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
