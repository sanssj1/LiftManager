# LiftManager

**LiftManager** es una aplicación web para administrar operaciones de una empresa ficticia de mantenimiento de ascensores. Permite registrar edificios y equipos, asignar técnicos, gestionar contratos, programar visitas y seguir reclamos.

> Estado de esta entrega: código fuente y documentación preparados. El despliegue público todavía requiere configurar cuentas/variables propias en Neon, Render, Netlify y GitHub. No se incluyen credenciales ni usuarios/contraseñas predefinidos.

## Funcionalidades

- Inicio de sesión con token JWT y contraseñas BCrypt.
- Roles: `ADMIN`, `TECHNICIAN` y `BUILDING_MANAGER`.
- Gestión de edificios, ascensores, usuarios y contratos.
- Programación de mantenimientos, con validación de vigencia contractual y solapamientos de agenda.
- Inicio y cierre de visitas por el técnico asignado; el cierre exige descripción del trabajo realizado.
- Registro y seguimiento de reclamos, asignación a técnicos y transiciones controladas.
- Una incidencia crítica cambia el ascensor a `OUT_OF_SERVICE`; el administrador puede actualizar su estado cuando se verifica la condición real del equipo.
- Persistencia PostgreSQL.

## Arquitectura

| Componente | Tecnología | Carpeta |
|---|---|---|
| Frontend | HTML, CSS y JavaScript | `frontend/` |
| API | Java 21, Spring Boot 3.4.5 | `backend/` |
| Datos | PostgreSQL | Neon o local |
| Autenticación | JWT + BCrypt | Backend |
| Tests | JUnit 5 | `backend/src/test/` |

## Requisitos

- Java 21.
- Maven 3.9 o superior.
- Node.js no es obligatorio; se usa Python o cualquier servidor estático para servir el frontend.
- PostgreSQL 15 o superior, o una base PostgreSQL de Neon.

## Ejecutar localmente

### 1. Crear la base de datos

Podés iniciar PostgreSQL con Docker:

```bash
docker compose up -d db
```

O crear una base local llamada `liftmanager` y configurar `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD`.

### 2. Configurar variables del backend

En una terminal, definí estas variables. Para Windows PowerShell:

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/liftmanager"
$env:SPRING_DATASOURCE_USERNAME="postgres"
$env:SPRING_DATASOURCE_PASSWORD="tu_clave_local"
$env:APP_JWT_SECRET="una-clave-local-de-al-menos-32-caracteres"
$env:APP_BOOTSTRAP_ADMIN_EMAIL="admin@liftmanager.local"
$env:APP_BOOTSTRAP_ADMIN_PASSWORD="una-clave-local-segura-de-12+"
$env:CORS_ALLOWED_ORIGINS="http://localhost:5500,http://127.0.0.1:5500"
```

Linux/macOS usa `export VARIABLE="valor"`. No guardes claves reales en archivos versionados.

### 3. Iniciar backend

```bash
cd backend
mvn spring-boot:run
```

API: `http://localhost:8080`  
Health check: `http://localhost:8080/api/health`

### 4. Iniciar frontend

En otra terminal:

```bash
cd frontend
python -m http.server 5500
```

Abrí `http://localhost:5500`. La URL de API está en `frontend/config.js`; en local apunta a `http://localhost:8080`.

### 5. Ingreso inicial

Al iniciar el backend, se crea un administrador solo cuando están definidas `APP_BOOTSTRAP_ADMIN_EMAIL` y `APP_BOOTSTRAP_ADMIN_PASSWORD` y el email todavía no existe. La contraseña debe tener al menos 12 caracteres. El sistema almacena un hash BCrypt, nunca la contraseña original.

Después de ingresar, el administrador crea técnicos y administradores de edificios desde **Usuarios**.

## Pruebas

Desde `backend/`:

```bash
mvn test
```

El conjunto inicial de pruebas unitarias de `SchedulingRules` cubre intervalos de agenda, intervalos contiguos, límites de contrato y fechas faltantes. Ver `docs/TESTING.md` y `docs/TEST_CASES.md` para el estado y la matriz manual.

## Despliegue

- Frontend: Netlify, publicar la carpeta `frontend/`.
- Backend: Render, construir con `backend/Dockerfile`.
- Base de datos: Neon PostgreSQL.

Seguí la guía completa en [`docs/DEPLOYMENT.md`](docs/DEPLOYMENT.md). La app no está publicada todavía: el propietario del proyecto tiene que crear las cuentas, cargar las variables y confirmar el funcionamiento con URLs reales.

## Documentación de entrega

- [`docs/PROJECT.md`](docs/PROJECT.md): descripción y alcance.
- [`docs/SPEC.md`](docs/SPEC.md): reglas de negocio numeradas y requisitos.
- [`AGENTS.md`](AGENTS.md): reglas para agentes de IA.
- [`docs/PLAN.md`](docs/PLAN.md): tareas y estado.
- [`docs/TESTING.md`](docs/TESTING.md): informe de pruebas.
- [`docs/TEST_CASES.md`](docs/TEST_CASES.md): casos de prueba funcionales.
- [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md): arquitectura y decisiones.
- [`docs/USER_GUIDE.md`](docs/USER_GUIDE.md): guía de uso.
- [`docs/DEPLOYMENT.md`](docs/DEPLOYMENT.md): publicación y configuración.

## Seguridad y limitaciones conocidas

- Nunca subas `.env`, claves JWT, credenciales de Neon ni contraseñas al repositorio.
- El primer administrador se crea desde variables de entorno; no existe contraseña universal.
- `ddl-auto: update` se usa para facilitar la primera instalación académica. Para una operación productiva, migrar a Flyway y `ddl-auto: validate`.
- El control de solapamientos está en la capa de servicio. Para concurrencia de alto volumen, conviene agregar una restricción/estrategia transaccional específica en PostgreSQL.
- No se implementan pagos, facturación, notificaciones por WhatsApp, firma digital ni aplicación móvil en esta primera versión.
