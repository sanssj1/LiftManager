# DATA_DICTIONARY.md — Modelo de datos

| Tabla | Campos principales | Descripción |
|---|---|---|
| `app_users` | id, name, email, password_hash, role, active, building_id | Cuentas y permisos. `building_id` se usa para encargados. |
| `buildings` | id, name, address, city, contact_name, contact_phone | Edificios clientes. |
| `elevators` | id, code, brand, model, status, building_id | Equipos asociados a edificios. Código único por edificio. |
| `maintenance_contracts` | id, elevator_id, start_date, end_date, monthly_fee, active | Vigencia y abono del servicio contratado. |
| `maintenance_visits` | id, elevator_id, technician_id, scheduled_start, scheduled_end, status, work_performed, notes, created_at | Agenda e historial de intervenciones. |
| `claims` | id, elevator_id, created_by_id, assigned_to_id, title, description, priority, status, created_at, resolved_at | Reclamos y seguimiento. |

Las tablas y restricciones se generan/actualizan con Hibernate al iniciar (`ddl-auto: update`). No se deben modificar manualmente en producción sin coordinar el cambio con las entidades y la documentación. Para versiones futuras, adoptar migraciones Flyway.
