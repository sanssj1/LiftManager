
# LiftManager

**LiftManager** es una aplicación web para administrar las operaciones de una empresa ficticia de mantenimiento de ascensores. Permite registrar edificios y equipos, asignar técnicos, gestionar contratos, programar visitas de mantenimiento y realizar el seguimiento de reclamos.

## Enlaces del proyecto

- **Aplicación web:** https://liftmanager1234.netlify.app
- **Backend / API:** https://liftmanager.onrender.com
- **Estado de la API:** https://liftmanager.onrender.com/api/health
- **Repositorio GitHub:** https://github.com/sanssj1/LiftManager
- **Base de datos:** PostgreSQL alojada en Neon.
- **Consola de Neon:** https://console.neon.tech/

> La aplicación y el backend están publicados en internet. La base de datos se administra desde Neon y no tiene una interfaz pública de acceso directo.

## Funcionalidades

- Inicio de sesión con autenticación JWT y contraseñas protegidas mediante BCrypt.
- Gestión de roles: `ADMIN`, `TECHNICIAN` y `BUILDING_MANAGER`.
- Gestión de edificios, ascensores, usuarios y contratos.
- Programación de mantenimientos.
- Validación de vigencia contractual y solapamientos de agenda.
- Inicio y cierre de visitas por parte del técnico asignado.
- Registro obligatorio de la descripción del trabajo realizado al cerrar una visita.
- Registro, asignación y seguimiento de reclamos.
- Transiciones de estado controladas para los reclamos.
- Una incidencia crítica cambia el ascensor a `OUT_OF_SERVICE`.
- El administrador puede actualizar el estado del ascensor cuando se verifica su condición real.
- Persistencia de información mediante PostgreSQL.

## Arquitectura y tecnologías

| Componente | Tecnología |
|---|---|
| Frontend | HTML, CSS y JavaScript |
| Backend | Java 21 y Spring Boot 3.4.5 |
| Base de datos | PostgreSQL |
| Hosting del frontend | Netlify |
| Hosting del backend | Render |
| Base de datos en la nube | Neon |
| Autenticación | JWT y BCrypt |
| Pruebas | JUnit 5 |
| Control de versiones | Git y GitHub |

## Estructura del proyecto

```text
LiftManager/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
├── frontend/
│   ├── assets/
│   ├── config.js
│   └── index.html
├── docs/
│   ├── PROJECT.md
│   ├── SPEC.md
│   ├── PLAN.md
│   ├── TESTING.md
│   ├── TEST_CASES.md
│   ├── ARCHITECTURE.md
│   ├── USER_GUIDE.md
│   └── DEPLOYMENT.md
├── AGENTS.md
├── README.md
└── netlify.toml
```

## Requisitos para ejecutar localmente

- Java 21.
- Maven 3.9 o superior.
- PostgreSQL 15 o superior, o una base de datos Neon.
- Python o cualquier servidor HTTP estático para servir el frontend.

## Ejecución local

### 1. Configurar la base de datos

Se puede iniciar PostgreSQL mediante Docker:

```bash
docker compose up -d db
```

También se puede utilizar una instancia local de PostgreSQL o una base de datos de Neon.

### 2. Configurar las variables de entorno

En Windows PowerShell:

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/liftmanager"
$env:SPRING_DATASOURCE_USERNAME="postgres"
$env:SPRING_DATASOURCE_PASSWORD="tu-clave-local"
$env:APP_JWT_SECRET="una-clave-local-de-al-menos-32-caracteres"
$env:APP_BOOTSTRAP_ADMIN_EMAIL="admin@liftmanager.local"
$env:APP_BOOTSTRAP_ADMIN_PASSWORD="una-clave-local-segura-de-12+"
$env:CORS_ALLOWED_ORIGINS="http://localhost:5500,http://127.0.0.1:5500"
```

No guardar credenciales reales en archivos versionados ni compartirlas públicamente.

### 3. Iniciar el backend

Desde la carpeta del proyecto:

```bash
cd backend
mvn spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

Health check:

```text
http://localhost:8080/api/health
```

### 4. Iniciar el frontend

En otra terminal:

```bash
cd frontend
python -m http.server 5500
```

Abrir en el navegador:

```text
http://localhost:5500
```

La URL del backend se configura desde `frontend/config.js`.

### 5. Crear el administrador inicial

El administrador inicial se crea al iniciar el backend cuando están definidas las variables:

- `APP_BOOTSTRAP_ADMIN_EMAIL`
- `APP_BOOTSTRAP_ADMIN_PASSWORD`

La contraseña debe tener al menos 12 caracteres. El sistema almacena su hash BCrypt, no la contraseña original.

Luego, el administrador puede crear técnicos y administradores de edificios desde la sección **Usuarios**.

## Pruebas

Para ejecutar las pruebas automatizadas desde la carpeta `backend/`:

```bash
mvn test
```

El proyecto cuenta con pruebas unitarias de las reglas de programación de visitas (`SchedulingRules`), que contemplan:

- Intervalos de agenda.
- Intervalos contiguos.
- Límites de vigencia contractual.
- Fechas faltantes.

También se realiza una validación sintáctica del JavaScript del frontend.

Consultar `docs/TESTING.md` y `docs/TEST_CASES.md` para obtener más información sobre las pruebas.

## Despliegue

El proyecto utiliza los siguientes servicios:

| Servicio | Plataforma | Función |
|---|---|---|
| Frontend | Netlify | Publicación de la aplicación web |
| Backend | Render | Ejecución de la API Spring Boot |
| Base de datos | Neon | Almacenamiento PostgreSQL |
| Repositorio | GitHub | Código fuente y documentación |

### Variables de entorno del backend

Para el despliegue se utilizan las siguientes variables:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `APP_JWT_SECRET`
- `CORS_ALLOWED_ORIGINS`
- `APP_BOOTSTRAP_ADMIN_EMAIL` (si se necesita crear el administrador inicial)
- `APP_BOOTSTRAP_ADMIN_PASSWORD` (si se necesita crear el administrador inicial)

Las credenciales deben configurarse desde el panel de Render y nunca subirse al repositorio.

Consultar `docs/DEPLOYMENT.md` para conocer los detalles de configuración.

## Documentación

| Archivo | Descripción |
|---|---|
| `docs/PROJECT.md` | Descripción y alcance del proyecto |
| `docs/SPEC.md` | Reglas de negocio y requisitos numerados |
| `AGENTS.md` | Instrucciones para agentes de IA |
| `docs/PLAN.md` | Plan de trabajo y estado de las tareas |
| `docs/TESTING.md` | Informe de pruebas |
| `docs/TEST_CASES.md` | Casos de prueba funcionales |
| `docs/ARCHITECTURE.md` | Arquitectura y decisiones técnicas |
| `docs/USER_GUIDE.md` | Guía de uso de la aplicación |
| `docs/DEPLOYMENT.md` | Configuración y publicación |

## Seguridad y limitaciones conocidas

- No subir archivos `.env`, contraseñas, claves JWT ni credenciales de Neon al repositorio.
- No existe una contraseña universal o un usuario administrador predefinido.
- El primer administrador se crea mediante variables de entorno.
- Se utiliza `ddl-auto: update` para facilitar la instalación académica inicial. Para un entorno productivo se recomienda implementar migraciones con Flyway y utilizar `ddl-auto: validate`.
- La validación de solapamientos se realiza en la capa de servicio. Para escenarios de concurrencia elevada se recomienda incorporar una estrategia transaccional específica en PostgreSQL.
- Esta primera versión no implementa pagos, facturación, notificaciones por WhatsApp, firma digital ni aplicación móvil.

## Autoría

Proyecto desarrollado como trabajo final de **Generación T**, con el objetivo de aplicar conocimientos de desarrollo web, programación backend, bases de datos, autenticación, pruebas y despliegue de aplicaciones.
