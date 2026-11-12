# USER_GUIDE.md — Guía de uso

## Primer acceso (administrador)
1. El responsable técnico configura las variables de entorno para la base, el secreto JWT y el email/contraseña inicial.
2. Inicia la API. El backend crea el administrador inicial únicamente si no existe ese email.
3. El administrador abre el frontend e ingresa con esas credenciales.
4. Crea primero un edificio, luego los ascensores y sus contratos.
5. Crea los técnicos y los administradores de cada edificio.

## Administrador
- **Resumen:** indicadores de edificios, equipos, visitas y reclamos.
- **Edificios:** alta de edificios y datos de contacto.
- **Ascensores:** alta de equipos, carga de contratos y cambio manual de estado.
- **Mantenimientos:** programación de visitas con técnico, equipo, inicio y fin.
- **Reclamos:** consulta, asignación y seguimiento.
- **Usuarios:** alta de técnicos y encargados de edificios.

## Técnico
- Inicia sesión y abre **Mantenimientos** para ver su agenda.
- Usa **Iniciar** al comenzar una visita.
- Usa **Completar** al terminar e informa el trabajo realizado. Ese detalle es obligatorio.
- En **Reclamos** consulta solo los reclamos asignados y mueve su estado según el flujo permitido.

## Administrador del edificio
- Consulta sus edificios y ascensores.
- Consulta visitas y reclamos asociados.
- Crea un reclamo seleccionando ascensor, prioridad, título y descripción.
- Ante una falla crítica, el equipo queda marcado fuera de servicio para que la empresa tome medidas.

## Estados
- Ascensor: Operativo, Fuera de servicio, En mantenimiento.
- Visita: Programada, En proceso, Completada, Cancelada.
- Reclamo: Abierto, Asignado, En proceso, Resuelto, Cerrado.

## Buenas prácticas
No compartir usuarios. Usar contraseñas únicas y largas. Cerrar sesión al terminar. Nunca copiar tokens ni contraseñas en capturas de evidencia.
