# ARCHITECTURE.md — Arquitectura

## Vista general

```text
Navegador
  └── Frontend estático (Netlify)
        └── HTTPS / JSON / Bearer JWT
              └── API REST (Spring Boot en Render)
                    └── PostgreSQL (Neon)
```

## Capas del backend
- `controller`: endpoints HTTP y validación de DTOs.
- `dto`: contratos de entrada y salida; evita exponer entidades.
- `service`: reglas de negocio, autorización contextual y casos de uso.
- `repository`: acceso a PostgreSQL mediante Spring Data JPA.
- `model`: entidades y enumeraciones del dominio.
- `security`: generación y validación de JWT.
- `config`: configuración Spring Security, CORS y errores.

## Entidades y relaciones

```text
Building 1 ─── N Elevator 1 ─── N MaintenanceContract
                    │
                    ├── 1 ─── N MaintenanceVisit N ─── 1 AppUser (technician)
                    │
                    └── 1 ─── N Claim N ─── 1 AppUser (createdBy)
                                          └── 0..1 AppUser (assignedTo)
Building 1 ─── N AppUser (building managers)
```

## Decisiones técnicas
- Java 21 y Spring Boot para la API.
- PostgreSQL como base relacional.
- JWT stateless para autenticar llamadas.
- BCrypt para hashes de contraseñas.
- HTML/CSS/JavaScript para frontend sencillo y desplegable como sitio estático.
- Los DTOs controlan los campos que salen y entran de la API.
- Las reglas que protegen datos o estado se ejecutan en backend; ocultar botones en frontend no se considera autorización.

## Configuración
La conexión y los secretos llegan por variables de entorno. Ver `.env.example` como lista ilustrativa y `docs/DEPLOYMENT.md` para los valores de producción.
