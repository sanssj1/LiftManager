# DEPLOYMENT.md — Publicación web

Esta guía prepara la publicación en **Neon (PostgreSQL)**, **Render (API)** y **Netlify (frontend)**. Requiere crear las cuentas y configurar las variables desde sus paneles. El repositorio no contiene credenciales.

## 1. GitHub
1. Crear un repositorio nuevo y público llamado `LiftManager`.
2. Subir el contenido de esta carpeta, sin `.env`, credenciales, `target/` ni tokens.
3. Confirmar que `AGENTS.md`, `README.md`, `docs/`, `backend/` y `frontend/` estén visibles.

## 2. Neon — base de datos
1. Crear un proyecto PostgreSQL en Neon.
2. Crear o usar la base de datos asignada.
3. Copiar host, database, usuario y contraseña desde el panel de conexión.
4. Configurar en Render:
   - `SPRING_DATASOURCE_URL`: `jdbc:postgresql://HOST/BASE?sslmode=require`
   - `SPRING_DATASOURCE_USERNAME`: usuario de Neon.
   - `SPRING_DATASOURCE_PASSWORD`: contraseña de Neon.
5. Verificar que la cadena use el host y nombre correctos de Neon y que `sslmode=require` esté presente.

No pegar la cadena de conexión en GitHub, capturas ni mensajes públicos.

## 3. Render — backend

El archivo `backend/Dockerfile` compila el proyecto con Maven y ejecuta el JAR en Java 21. Crear un Web Service desde el repositorio usando Docker y el Dockerfile `backend/Dockerfile` (contexto raíz del repo).

Configurar variables de entorno:

| Variable | Valor |
|---|---|
| `SPRING_DATASOURCE_URL` | JDBC URL de Neon con `sslmode=require` |
| `SPRING_DATASOURCE_USERNAME` | Usuario Neon |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña Neon (secreto) |
| `APP_JWT_SECRET` | Cadena aleatoria larga, mínimo 32 caracteres |
| `APP_BOOTSTRAP_ADMIN_EMAIL` | Email que usará el administrador inicial |
| `APP_BOOTSTRAP_ADMIN_PASSWORD` | Contraseña inicial de 12+ caracteres (secreto) |
| `CORS_ALLOWED_ORIGINS` | URL exacta de Netlify, por ejemplo `https://tu-sitio.netlify.app` |
| `TZ` | `America/Argentina/Buenos_Aires` |

Configurar el health check como `/api/health`. Una vez desplegado, probar `https://TU-API.onrender.com/api/health`. Debe responder JSON con `status: ok`.

## 4. Netlify — frontend
1. Crear un sitio nuevo desde el mismo repositorio.
2. Configurar **Publish directory** como `frontend` (sin build command).
3. Editar `frontend/config.js` y reemplazar `http://localhost:8080` por la URL HTTPS pública de Render, sin barra final.
4. Hacer commit y esperar a que Netlify publique el cambio.
5. Volver a Render y dejar `CORS_ALLOWED_ORIGINS` con el dominio exacto de Netlify.

## 5. Validación pública obligatoria
- Abrir el frontend desde una ventana privada del navegador.
- Iniciar sesión con el usuario administrador inicial.
- Crear edificio, ascensor, contrato y usuario técnico.
- Programar una visita; comprobar que aparece en la cuenta del técnico.
- Iniciar y completar visita con descripción.
- Ingresar como encargado y crear un reclamo.
- Verificar que un reclamo crítico marque el ascensor fuera de servicio.
- Cerrar sesión y comprobar que no se puede acceder a endpoints privados sin autenticación.
- Guardar capturas sin tokens ni datos sensibles en `docs/evidence/`.

## 6. Importante
La publicación no está completada mientras no existan las URLs definitivas y se ejecute la prueba integral. Los servicios gratuitos pueden suspender o demorar el arranque; revisar los límites y condiciones vigentes de cada proveedor antes de la entrega.
