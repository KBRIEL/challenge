import axios from "axios";
import React, { useEffect, useState } from "react";



const FormularioClima = () => {
  const [query, setQuery] = useState("");
  const [resultados, setResultados] = useState([]);
  const [ciudades, setCiudades] = useState([]);
  const [clima, setClima] = useState(null);
  const [ciudadActual, setCiudadActual] = useState("");
  const [cargando, setCargando] = useState(false);
  const [error, setError] = useState("");

  const buscarCiudad = async (texto) => {
    setQuery(texto);
    if (texto.length < 3) {
      setResultados([]);
      return;
    }

    const res = await axios.get(
      "https://geocoding-api.open-meteo.com/v1/search",
      {
        params: {
          name: texto,
          count: 5,
          language: "es"
        }
      }
    );

    setResultados(res.data.results || []);
  };

  
  const obtenerClima = async (ciudad) => {
    setCargando(true);
    setCiudadActual(ciudad.name);

    try {
      const res = await axios.get(
        "https://api.open-meteo.com/v1/forecast",
        {
          params: {
            latitude: ciudad.latitude,
            longitude: ciudad.longitude,
            current_weather: true,
            hourly: "relativehumidity_2m,precipitation_probability",
            daily: "temperature_2m_max,temperature_2m_min",
            timezone: "auto"
          }
        }
      );

      setClima({
        actual: res.data.current_weather.temperature,
        max: res.data.daily.temperature_2m_max[0],
        min: res.data.daily.temperature_2m_min[0],
        humedad: res.data.hourly.relativehumidity_2m[0],
        lluvia: res.data.hourly.precipitation_probability[0]
      });

    
      setCiudades((prev) =>
        prev.find((c) => c.id === ciudad.id) ? prev : [...prev, ciudad]
      );

      setResultados([]);
      setQuery("");
    } catch {
      setError("No se pudo obtener el clima");
    } finally {
      setCargando(false);
    }
  };

  return (
    <div className="contenedor-form">
      <div className="formulario">
        <h2>Buscar Ciudad</h2>

    
        <input
          className="input"
          placeholder="Buscar ciudad..."
          value={query}
          onChange={(e) => buscarCiudad(e.target.value)}
        />

       
        {resultados.length > 0 && (
          <ul className="dropdown">
            {resultados.map((c) => (
              <li key={c.id} onClick={() => obtenerClima(c)}>
                {c.name}, {c.country}
              </li>
            ))}
          </ul>
        )}

      
        {cargando && <p>Cargando clima...</p>}
        {clima && (
          <div className="campo">
            <h3>{ciudadActual}</h3>
            <p>🌡 {clima.actual} °C</p>
            <p>🔺 Máx: {clima.max} °C</p>
            <p>🔻 Mín: {clima.min} °C</p>
            <p>💧 Humedad: {clima.humedad} %</p>
            <p>🌧 Lluvia: {clima.lluvia} %</p>
          </div>
        )}

       
        {ciudades.length > 0 && (
          <>
            <h4>📍 Ciudades guardadas</h4>
            <ul className="lista-ciudades">
              {ciudades.map((c) => (
                <li key={c.id} onClick={() => obtenerClima(c)}>
                  {c.name}, {c.country}
                </li>
              ))}
            </ul>
          </>
        )}

        {error && <p className="error">{error}</p>}
      </div>
    </div>
  );
};

export default FormularioClima;
