import axios from "axios";
import React, { useState } from "react";

const ClienteForm = (props) => {

  const [form, setForm] = useState({
    nombre: "",
  apellido: "",
  razonSocial: "",
  cuit: "",
  fechaDeNacimiento: "",
  telefonoCelular: "",
  email: ""
  });

  const handleCarga = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
     const response = await axios.post("http://localhost:8080/cliente/insert", form,  {
    headers: {
      "Content-Type": "application/json"
    }
  }
  );

      alert("Cliente creado correctamente");
     console.log(response.data);

      setForm({
        
  nombre: "",
  apellido: "",
  razonSocial: "",
  cuit: "",
  fechaDeNacimiento: "",
  telefonoCelular: "",
  email: ""

      });

    } catch (error) {
      console.error(error);
      alert("Hubo un error al crear el cliente");
    }
  };

  return (
    <div className="contenedor-form">
      <form className="formulario" onSubmit={handleSubmit}>

        <div className="campo">
          <div className="titulo-campo">Nombre</div>
          <input
            type="text"
            name="nombre"
            value={form.nombre}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Apellido</div>
          <input
            type="text"
            name="apellido"
            value={form.apellido}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">CUIT</div>
          <input
            type="text"
            name="cuit"
            value={form.cuit}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Email</div>
          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Teléfono Celular</div>
          <input
            type="text"
            name="telefonoCelular"
            value={form.telefonoCelular}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Razón Social</div>
          <input
            type="text"
            name="razonSocial"
            value={form.razonSocial}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Fecha de Nacimiento</div>
          <input
            type="date"
            name="fechaDeNacimiento"
            value={form.fechaDeNacimiento}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <button type="submit">Guardar</button>
        </div>

      </form>
    </div>
  );
};

export default ClienteForm;
