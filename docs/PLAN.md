# PLAN.md — Plan de trabajo y estado

Los estados `[x]` indican que el código/documentación correspondiente está creado en el repositorio local. No significan por sí solos que el backend haya compilado ni que el despliegue esté publicado. Las verificaciones pendientes se dejan sin marcar.

## Desarrollo documentado y preparado
- [x] T01 — Definir el problema, objetivo y alcance de LiftManager.
- [x] T02 — Escribir SPEC con reglas de negocio numeradas RN01–RN40 y requisitos.
- [x] T03 — Crear AGENTS.md con reglas para agentes de IA.
- [x] T04 — Crear el esqueleto Spring Boot, Maven y configuración de aplicación.
- [x] T05 — Crear entidades de dominio y enumeraciones.
- [x] T06 — Crear repositorios y DTOs REST.
- [x] T07 — Implementar autenticación JWT, BCrypt y configuración de seguridad.
- [x] T08 — Implementar usuarios, edificios, ascensores y contratos.
- [x] T09 — Implementar programación, validación y cierre de mantenimientos.
- [x] T10 — Implementar reclamos, asignación y flujo de estados.
- [x] T11 — Implementar controladores REST, health check y manejo de errores.
- [x] T12 — Crear pruebas unitarias iniciales de reglas de agenda.
- [x] T13 — Crear estructura HTML y pantalla de login.
- [x] T14 — Crear estilos responsive.
- [x] T15 — Conectar pantallas frontend con la API y completar flujos por rol.
- [x] T16 — Preparar configuración de API para frontend local y publicado.
- [x] T17 — Crear Dockerfile, Compose y configuración base de Render/Netlify.
- [x] T18 — Documentar arquitectura y diccionario de datos.
- [x] T19 — Escribir guía de uso por rol.
- [x] T20 — Crear informe de pruebas y matriz de casos manuales.
- [x] T21 — Escribir instrucciones de despliegue.
- [x] T22 — Crear README, `.gitignore` y ejemplo de variables sin credenciales reales.
- [x] T23 — Agregar GitHub Actions CI y registrar la planificación.

## Verificaciones y publicación pendientes — bloqueantes para la entrega
- [ ] T24 — Ejecutar `mvn test` en un equipo con Maven 3.9+; corregir y repetir hasta que pase.
- [ ] T25 — Ejecutar pruebas manuales de Admin, Técnico y Administrador del edificio; completar `docs/TEST_CASES.md`.
- [ ] T26 — Crear Neon PostgreSQL y configurar secretos/variables en Render.
- [ ] T27 — Desplegar backend en Render y verificar `https://.../api/health`.
- [ ] T28 — Publicar frontend en Netlify y configurar CORS con el dominio real.
- [ ] T29 — Probar el recorrido integral usando solamente las URLs públicas.
- [ ] T30 — Publicar el repositorio en GitHub y pegar su URL real en el campus.

## Regla de cierre
Cada tarea debe tener su commit individual, pruebas/evidencia apropiadas y documentación actualizada. No marcar T24–T30 como terminadas hasta disponer de los resultados reales.
