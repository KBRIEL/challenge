import axios from "axios";
import React, { useEffect, useState } from "react";


const Clima = () => {
  const [clima, setClima] = useState(null);
  const [cargando, setCargando] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    if (!navigator.geolocation) {
      setError("La geolocalización no está soportada por el navegador");
      return;
    }

    setCargando(true);

    navigator.geolocation.getCurrentPosition(
      async (position) => {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        try {
          const response = await axios.get(
            "https://api.open-meteo.com/v1/forecast",
            {
              params: {
                latitude,
                longitude,
                current_weather: true,
                hourly: "relativehumidity_2m,precipitation_probability",
                daily: "temperature_2m_max,temperature_2m_min",
                timezone: "auto"
              }
            }
          );

          setClima({
            actual: response.data.current_weather.temperature,
            humedad: response.data.hourly.relativehumidity_2m[0],
            lluvia: response.data.hourly.precipitation_probability[0],
            max: response.data.daily.temperature_2m_max[0],
            min: response.data.daily.temperature_2m_min[0]
          });
        } catch (err) {
          console.error(err);
          setError("No se pudo obtener el clima");
        } finally {
          setCargando(false);
        }
      },
      () => {
        setError("No se pudo obtener la ubicación");
        setCargando(false);
      }
    );
  }, []);

  return (
    <div className="contenedor-form">
      <div className="formulario">
        <h2>🌤 Clima actual</h2>

        {cargando && <p>Cargando clima...</p>}
        {error && <p style={{ color: "red" }}>{error}</p>}

        {clima && (
          <div className="campo">
            <p>🌡 Temperatura actual: {clima.actual} °C</p>
            <p>🔺 Máxima: {clima.max} °C</p>
            <p>🔻 Mínima: {clima.min} °C</p>
            <p>💧 Humedad: {clima.humedad} %</p>
            <p>🌧 Prob. de lluvia: {clima.lluvia} %</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default Clima;
