# SPEC.md — Especificación funcional

**Versión:** 1.0 · **Estado:** alcance inicial implementado en código. Ver `docs/TESTING.md` para verificación pendiente.

## 1. Roles
- `ADMIN`: administración global.
- `TECHNICIAN`: ejecución de visitas y reclamos asignados.
- `BUILDING_MANAGER`: usuario vinculado a un edificio.

## 2. Reglas de negocio numeradas

### Edificios y equipos
- **RN01.** Un edificio debe tener nombre, dirección y ciudad.
- **RN02.** Un edificio puede registrar uno o varios ascensores.
- **RN03.** El código del ascensor debe ser único dentro de su edificio.
- **RN04.** Cada ascensor pertenece a un solo edificio.
- **RN05.** Un administrador del edificio solo puede consultar la información del edificio al que está vinculado y de sus ascensores.
- **RN06.** Los estados válidos de un ascensor son `OPERATIONAL`, `OUT_OF_SERVICE` y `UNDER_MAINTENANCE`.

### Usuarios y permisos
- **RN07.** Solo un `ADMIN` puede crear edificios, ascensores, contratos y usuarios.
- **RN08.** El usuario se identifica por un email único.
- **RN09.** Un usuario `TECHNICIAN` debe estar activo para recibir visitas o reclamos.
- **RN10.** Un usuario `BUILDING_MANAGER` debe estar asociado a un edificio.
- **RN11.** Un técnico solo puede iniciar o completar visitas que estén asignadas a su usuario.
- **RN12.** Un técnico solo puede consultar y actualizar reclamos asignados a su usuario.

### Contratos
- **RN13.** Un contrato pertenece a un ascensor y contiene fecha de inicio, fecha de fin y abono mensual positivo.
- **RN14.** La fecha de fin de un contrato no puede ser anterior a la fecha de inicio.
- **RN15.** No puede crearse un segundo contrato vigente para un ascensor.
- **RN16.** Una visita solo puede programarse dentro de la vigencia de un contrato activo del ascensor.

### Mantenimientos
- **RN17.** La hora de fin de una visita debe ser posterior a la hora de inicio.
- **RN18.** No se pueden programar visitas en el pasado.
- **RN19.** Un técnico no puede tener dos visitas `SCHEDULED` o `IN_PROGRESS` que se superpongan.
- **RN20.** Un ascensor no puede tener dos visitas `SCHEDULED` o `IN_PROGRESS` que se superpongan.
- **RN21.** Una visita solo puede ser iniciada si está `SCHEDULED`.
- **RN22.** Al iniciar una visita, su estado pasa a `IN_PROGRESS` y el ascensor pasa a `UNDER_MAINTENANCE`.
- **RN23.** Una visita solo puede completarse si está `IN_PROGRESS`.
- **RN24.** Para completar una visita, el técnico debe cargar una descripción no vacía del trabajo realizado.
- **RN25.** Al completar una visita, su estado pasa a `COMPLETED`; si el ascensor estaba `UNDER_MAINTENANCE`, vuelve a `OPERATIONAL`.
- **RN26.** Las visitas completadas conservan el detalle del trabajo y las observaciones.

### Reclamos
- **RN27.** Un administrador del edificio puede crear reclamos asociados únicamente a un ascensor de su edificio; el administrador global también puede crearlos.
- **RN28.** Un reclamo registra ascensor, título, descripción, prioridad, creador, fecha y estado.
- **RN29.** Las prioridades válidas son `LOW`, `NORMAL`, `HIGH` y `CRITICAL`.
- **RN30.** Al crear un reclamo `CRITICAL`, el ascensor pasa a `OUT_OF_SERVICE`.
- **RN31.** Solo el administrador puede asignar un reclamo a un técnico activo.
- **RN32.** Un reclamo `OPEN` puede pasar a `ASSIGNED` al asignarse.
- **RN33.** Un reclamo `ASSIGNED` puede pasar a `IN_PROGRESS` o `CLOSED`.
- **RN34.** Un reclamo `IN_PROGRESS` puede pasar a `RESOLVED`.
- **RN35.** Un reclamo `RESOLVED` puede pasar a `CLOSED`.
- **RN36.** No se permiten transiciones desde `CLOSED` ni saltos de estado no especificados.
- **RN37.** Al resolver un reclamo se registra la fecha y hora de resolución.

### Seguridad y datos
- **RN38.** Las contraseñas se almacenan como hash BCrypt; nunca en texto plano.
- **RN39.** La API requiere autenticación salvo login y health check.
- **RN40.** Los secretos se configuran mediante variables de entorno y no se versionan.

## 3. Requisitos funcionales
- **RF01:** iniciar sesión y obtener un token JWT.
- **RF02:** consultar el resumen de edificios, ascensores, visitas y reclamos accesibles al usuario.
- **RF03:** crear y listar edificios.
- **RF04:** crear y listar ascensores.
- **RF05:** registrar contratos de mantenimiento.
- **RF06:** crear y listar usuarios según permisos.
- **RF07:** programar, listar, iniciar y completar visitas.
- **RF08:** crear, listar, asignar y actualizar reclamos.
- **RF09:** cambiar el estado del ascensor desde el panel de administración.
- **RF10:** consultar el estado de salud de la API.

## 4. Requisitos no funcionales
- **RNF01:** interfaz web responsive para desktop y móvil.
- **RNF02:** persistencia en PostgreSQL.
- **RNF03:** API REST JSON bajo `/api`.
- **RNF04:** autenticación stateless mediante JWT.
- **RNF05:** validaciones de negocio implementadas en backend.
- **RNF06:** mensajes de error sin datos internos ni stack traces.
- **RNF07:** la aplicación debe desplegarse para poder probarse desde internet.

## 5. Endpoints principales

| Método | Endpoint | Acceso |
|---|---|---|
| POST | `/api/auth/login` | Público |
| GET | `/api/health` | Público |
| GET / POST | `/api/buildings` | Autenticado / Admin para alta |
| GET | `/api/elevators` | Autenticado |
| POST | `/api/buildings/{id}/elevators` | Admin |
| POST | `/api/elevators/{id}/contracts` | Admin |
| PATCH | `/api/elevators/{id}/status` | Admin |
| GET / POST | `/api/users` | Admin |
| GET / POST | `/api/visits` | Autenticado / Admin para alta |
| PATCH | `/api/visits/{id}/start` | Técnico asignado o Admin |
| PATCH | `/api/visits/{id}/complete` | Técnico asignado o Admin |
| GET / POST | `/api/claims` | Autenticado / Admin o encargado para alta |
| PATCH | `/api/claims/{id}/assign` | Admin |
| PATCH | `/api/claims/{id}/status` | Técnico asignado o Admin |

## 6. Criterios de aceptación de la primera versión
1. El login rechaza credenciales incorrectas.
2. Las rutas privadas no responden sin token válido.
3. Un encargado no puede consultar un edificio ajeno.
4. La agenda impide solapamientos por técnico y por ascensor.
5. La agenda rechaza visitas sin contrato vigente.
6. Una visita no puede cerrarse sin trabajo realizado.
7. Un técnico no puede alterar una visita ajena.
8. Un reclamo crítico cambia el estado del ascensor.
9. Un reclamo no puede saltarse las transiciones definidas.
10. No hay secretos en el repositorio.
