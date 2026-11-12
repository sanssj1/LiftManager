# PROJECT.md — Definición del proyecto

## 1. Nombre
**LiftManager** — Plataforma de gestión de mantenimiento de ascensores.

## 2. Problema
Las empresas conservadoras de ascensores necesitan organizar edificios, equipos, contratos, visitas técnicas y reclamos. Cuando esos datos quedan dispersos entre planillas, mensajes y papeles, resulta difícil conocer el historial de cada equipo, detectar visitas pendientes y seguir una incidencia hasta su resolución.

## 3. Solución propuesta
Una aplicación web centralizada que permita a una empresa de mantenimiento administrar su cartera de edificios y ascensores, programar intervenciones, asignar técnicos y registrar los reclamos de sus clientes. Cada perfil ve y ejecuta las operaciones permitidas para su rol.

## 4. Usuarios
- **Administrador (`ADMIN`):** gestiona edificios, equipos, contratos, usuarios, visitas, asignaciones y estados de servicio.
- **Técnico (`TECHNICIAN`):** ve sus visitas y reclamos asignados; inicia una visita, registra tareas y la completa.
- **Administrador del edificio (`BUILDING_MANAGER`):** consulta únicamente su edificio, sus ascensores, visitas y reclamos; registra reclamos.

## 5. Alcance de la primera versión
Incluye autenticación, control por roles, edificios, ascensores, contratos, usuarios, agenda de visitas, registro de trabajo, reclamos, asignación y actualización de estados. Incluye persistencia PostgreSQL y despliegue separado de frontend/API/base.

## 6. Fuera de alcance
Facturación electrónica, cobro online, gestión contable, stock de repuestos, integración con WhatsApp/email, geolocalización de técnicos, firma digital, aplicación móvil nativa y sincronización offline.

## 7. Objetivo general
Desarrollar y publicar una aplicación web funcional que digitalice el circuito básico de mantenimiento de ascensores desde el alta del edificio hasta el cierre de una visita o reclamo.

## 8. Criterios de éxito
1. Un usuario autorizado puede iniciar sesión y solo accede a sus operaciones.
2. El administrador puede registrar edificio, ascensor, contrato y técnico.
3. El sistema impide programar visitas sin contrato vigente o con superposición horaria.
4. Un técnico puede iniciar una visita asignada y completarla con detalle obligatorio.
5. El sistema conserva el historial de visitas y reclamos.
6. Los reclamos críticos producen el estado `OUT_OF_SERVICE`.
7. La aplicación puede probarse desde URLs públicas después de configurar los proveedores de hosting.
