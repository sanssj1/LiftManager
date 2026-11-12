# AGENTS.md — Reglas para agentes de IA

## Objetivo
Mantener LiftManager como sistema web de gestión de mantenimiento de ascensores, respetando `docs/PROJECT.md` y `docs/SPEC.md`.

## Reglas obligatorias
1. Antes de cambiar una funcionalidad, leer `docs/PROJECT.md`, `docs/SPEC.md` y `docs/PLAN.md`.
2. No implementar funcionalidades de ReservaClub ni copiar sus pantallas o lógica.
3. No agregar reglas de negocio sin documentarlas primero en `docs/SPEC.md` con un identificador `RN` nuevo.
4. No romper los roles `ADMIN`, `TECHNICIAN` y `BUILDING_MANAGER` ni los permisos definidos en la SPEC.
5. Toda validación de negocio importante debe ejecutarse en el backend. La validación del frontend es solo una ayuda de uso.
6. No exponer `passwordHash`, tokens, secretos, datos internos ni stack traces en respuestas HTTP.
7. Nunca escribir credenciales reales en el código, commits, ejemplos o documentación. Usar variables de entorno.
8. Nunca agregar `.env`, archivos de claves, dumps con información personal ni credenciales al repositorio.
9. Mantener DTOs para entrada/salida HTTP; no serializar entidades JPA directamente.
10. Usar nombres de clases, métodos y variables en inglés; textos visibles y documentación en español.
11. Respetar Java 21, Spring Boot 3.4.x, PostgreSQL y el frontend HTML/CSS/JS sin framework, salvo decisión documentada.
12. Toda regla nueva o modificada debe tener al menos un test automatizado que la cubra.
13. Ejecutar `mvn test` antes de cerrar una tarea de backend y `node --check frontend/assets/app.js` ante cambios de JavaScript. Si el entorno no permite ejecutar algo, dejarlo explícito; nunca declarar tests verdes sin ejecutarlos.
14. Un cambio funcional por tarea y un commit por tarea. Mensajes de commit claros: `feat:`, `fix:`, `test:`, `docs:` o `chore:`.
15. Actualizar `docs/PLAN.md` cuando una tarea cambie de estado y `docs/TESTING.md` cuando cambie el resultado de pruebas.
16. No marcar el despliegue como terminado sin probar la URL pública de frontend, API y base de datos.
17. Mantener la API compatible con el frontend; documentar cualquier cambio de endpoint o DTO.
18. Manejar errores con mensajes claros y estados HTTP adecuados; no ocultar errores de negocio como éxitos.

## Flujo de trabajo
1. Identificar regla/requisito y tarea del plan.
2. Explicar brevemente el cambio previsto.
3. Implementar el cambio más pequeño que resuelva la tarea.
4. Agregar/actualizar tests.
5. Ejecutar los chequeos disponibles.
6. Actualizar documentación.
7. Crear un commit exclusivo para esa tarea.
8. Informar qué se cambió, qué se probó y qué quedó pendiente.

## Comandos de chequeo

```bash
cd backend && mvn test
node --check frontend/assets/app.js
```
