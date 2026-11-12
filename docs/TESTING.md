# TESTING.md — Informe de pruebas

**Fecha de preparación:** 12/11/2026.  
**Versión:** 1.0.

## 1. Estado de ejecución

| Verificación | Estado | Evidencia / observación |
|---|---|---|
| Sintaxis del frontend JavaScript | Aprobada | `node --check frontend/assets/app.js` se ejecutó sin errores. |
| Suite JUnit | Pendiente de ejecución | El entorno de autoría no tiene Maven y no tiene acceso a internet para instalarlo. |
| Compilación Spring Boot | Pendiente de ejecución | Requiere Maven 3.9+ y descarga de dependencias. |
| Prueba con PostgreSQL real | Pendiente | Requiere instancia local o Neon. |
| Login / roles / API en ejecución | Pendiente | Requiere backend y base activos. |
| Prueba frontend desde URL pública | Pendiente | Requiere Render + Netlify + Neon configurados. |

**Importante:** no se declara que todos los tests pasan. Se creó la suite automatizada, pero debe ejecutarse en un entorno con Maven antes de entregar. La consigna de la materia exige todos los tests pasando: esta es una tarea bloqueante.

## 2. Suite automatizada disponible

Archivo: `backend/src/test/java/ar/com/liftmanager/service/BusinessRulesTest.java`.

Pruebas incluidas:
- Detección de solapamiento temporal.
- Aceptación de intervalos contiguos sin superposición.
- Validación de que la hora final sea posterior a la inicial.
- Inclusión del primer y último día de contrato.
- Rechazo de fechas fuera de vigencia.
- Rechazo de intervalos con fechas faltantes.

Ejecutar desde `backend/`:

```bash
mvn test
```

## 3. Matriz de pruebas manuales

Los casos detallados están en `docs/TEST_CASES.md`. La ejecución debe registrarse con fecha, usuario/rol, resultado esperado, resultado real y evidencia (captura o log sanitizado).

## 4. Resultado general

La documentación, estructura de la aplicación y pruebas unitarias iniciales están creadas. La validación integral del backend y el despliegue quedan pendientes; por eso, esta carpeta debe considerarse una **versión candidata de desarrollo**, no una entrega verificada de producción.
