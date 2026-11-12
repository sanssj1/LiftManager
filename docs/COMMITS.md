# COMMITS.md — Trazabilidad de tareas

El historial de Git del repositorio local contiene un commit por cada tarea de desarrollo T01–T23 documentada en `docs/PLAN.md`. Cada mensaje lleva el identificador `Txx` para poder relacionarlo con el plan.

- T31 — `fix: forzar autenticación JWT sin formulario ni HTTP Basic`, que evita mecanismos de login alternativos de Spring.

Las tareas T24–T30 todavía no tienen commit de cierre porque requieren ejecutar pruebas en un entorno con Maven, crear/configurar cuentas externas y publicar URLs reales. Cuando se realicen, cada una debe cerrarse en un commit separado.

Para revisar el historial:

```bash
git log --oneline --decorate
```
