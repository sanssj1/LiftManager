# TEST_CASES.md — Matriz de pruebas funcionales

Completar la columna **Resultado real** y adjuntar capturas sin datos sensibles antes de la entrega final.

| ID | Rol | Acción | Resultado esperado | Resultado real |
|---|---|---|---|---|
| TC01 | Anónimo | Abrir login | Se muestra la pantalla de acceso | Pendiente |
| TC02 | Anónimo | Login con clave incorrecta | API rechaza; mensaje de credenciales incorrectas | Pendiente |
| TC03 | Admin | Login correcto | Abre el dashboard y muestra navegación completa | Pendiente |
| TC04 | Admin | Crear edificio con datos válidos | Edificio aparece en listado | Pendiente |
| TC05 | Admin | Crear ascensor vinculado | Ascensor aparece asociado al edificio | Pendiente |
| TC06 | Admin | Cargar contrato con fechas válidas | Contrato queda registrado | Pendiente |
| TC07 | Admin | Crear técnico | Técnico aparece en usuarios | Pendiente |
| TC08 | Admin | Crear encargado sin edificio | API rechaza la operación | Pendiente |
| TC09 | Admin | Programar visita con contrato vigente | Visita queda `SCHEDULED` | Pendiente |
| TC10 | Admin | Programar visita fuera de contrato | API rechaza la operación | Pendiente |
| TC11 | Admin | Programar dos visitas solapadas del mismo técnico | Segunda visita rechazada | Pendiente |
| TC12 | Admin | Programar dos visitas solapadas del mismo ascensor | Segunda visita rechazada | Pendiente |
| TC13 | Técnico A | Iniciar visita propia programada | Visita `IN_PROGRESS`; ascensor `UNDER_MAINTENANCE` | Pendiente |
| TC14 | Técnico B | Iniciar visita asignada a Técnico A | API responde 403 | Pendiente |
| TC15 | Técnico A | Completar visita con texto vacío | API rechaza la operación | Pendiente |
| TC16 | Técnico A | Completar visita con trabajo detallado | Visita `COMPLETED`; ascensor vuelve a operativo si correspondía | Pendiente |
| TC17 | Encargado A | Consultar edificio propio | Se muestran solo los equipos del edificio asociado | Pendiente |
| TC18 | Encargado A | Consultar edificio de Encargado B | API responde 403 o no expone el recurso | Pendiente |
| TC19 | Encargado | Crear reclamo normal | Reclamo aparece como `OPEN` | Pendiente |
| TC20 | Encargado | Crear reclamo crítico | Reclamo `OPEN`; ascensor `OUT_OF_SERVICE` | Pendiente |
| TC21 | Admin | Asignar reclamo a técnico activo | Reclamo `ASSIGNED` y responsable visible | Pendiente |
| TC22 | Admin | Asignar reclamo a usuario que no es técnico | API rechaza | Pendiente |
| TC23 | Técnico asignado | Cambiar `ASSIGNED` a `IN_PROGRESS` y luego `RESOLVED` | Transiciones aceptadas | Pendiente |
| TC24 | Técnico no asignado | Cambiar estado de reclamo ajeno | API responde 403 | Pendiente |
| TC25 | Cualquier rol | Llamar endpoint privado sin token | API responde 401 | Pendiente |
| TC26 | Público | Consultar `/api/health` | Respuesta `status=ok` | Pendiente |

## Evidencia
Guardar capturas y logs sanitizados en una carpeta `docs/evidence/`. No incluir tokens JWT, contraseñas, variables privadas, emails personales ni cadenas de conexión.
