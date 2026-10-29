
# TechRepair Pro

Aplicación web para la gestión de reparaciones de dispositivos electrónicos, desarrollada con Angular, Java Spring Boot y arquitectura de microservicios.

## Funcionalidades

- Crear, consultar, editar y eliminar reparaciones.
- Gestionar estados: pendiente, en proceso y finalizada.
- Dashboard con estadísticas actualizadas.
- Consultar guías de reparación mediante la API de iFixit.
- Publicar eventos de cambio de estado mediante MQTT.
- Recibir eventos en un microservicio independiente de notificaciones.

## Tecnologías

### Frontend
- Angular
- TypeScript
- HTML y SCSS

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- API REST

### Comunicación
- MQTT
- Eclipse Mosquitto
- Eclipse Paho

### Herramientas
- IntelliJ IDEA
- Visual Studio Code
- Postman
- Git

## Arquitectura

```text
                  Angular
                     |
                  HTTP REST
                     |
              reparaciones-service
                 /          \
                /            \
          PostgreSQL       MQTT Broker
                               |
                       notificaciones-service

                  API externa iFixit
                         |
                  reparaciones-service
```

## Estructura del proyecto

```text
techrepair-pro/
├── backend/
│   ├── reparaciones-service/
│   └── notificaciones-service/
├── frontend/
│   └── techrepair-frontend/
├── infrastructure/
└── docs/
```

## Ejecución en entorno local

Requisitos:
- Java 21
- PostgreSQL
- Mosquitto MQTT
- Node.js y Angular CLI

Pasos:
1. Iniciar PostgreSQL y disponer de la base de datos `techrepair`.
2. Iniciar Mosquitto en el puerto 1883.
3. Configurar la conexión a PostgreSQL.
4. Ejecutar los dos microservicios Spring Boot.
5. Iniciar Angular con `ng serve` desde `frontend/techrepair-frontend`.
6. Abrir http://localhost:4200.

## Estado

Proyecto funcional en desarrollo, preparado como demostración de integración entre frontend, backend, base de datos, API externa y mensajería MQTT.
