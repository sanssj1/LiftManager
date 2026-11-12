# LiftManager Frontend

Frontend estático en HTML, CSS y JavaScript sin framework.

## Ejecutar localmente
1. Iniciá el backend en `http://localhost:8080`.
2. Desde esta carpeta ejecutá `python -m http.server 5500`.
3. Abrí `http://localhost:5500`.

La URL del backend se configura en `config.js` mediante `window.LM_API_BASE`. Al desplegar en Netlify, reemplazá el valor por la URL HTTPS de Render (sin barra final) y agregá el dominio de Netlify a `CORS_ALLOWED_ORIGINS` del backend.
