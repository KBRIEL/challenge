import axios from "axios";
import React, { useState } from "react";

const ClienteForm = (props) => {

  const [form, setForm] = useState({
    nombre: "",
    apellido: "",
    cuit: "",
    email: "",
    telefono_celular: "",
    razon_social: "",
    fecha_nacimiento: ""
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
     // const response = await axios.post("http://localhost:8080/cliente/insert", form);

      alert("Cliente creado correctamente");
      //console.log(response.data);

      setForm({
        nombre: "",
        apellido: "",
        cuit: "",
        email: "",
        telefono_celular: "",
        razon_social: "",
        fecha_nacimiento: ""
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
            name="telefono_celular"
            value={form.telefono_celular}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Razón Social</div>
          <input
            type="text"
            name="razon_social"
            value={form.razon_social}
            onChange={handleCarga}
          />
        </div>

        <div className="campo">
          <div className="titulo-campo">Fecha de Nacimiento</div>
          <input
            type="date"
            name="fecha_nacimiento"
            value={form.fecha_nacimiento}
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
