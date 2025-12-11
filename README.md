🌐 Sistema de Gestión de Pedidos (Full-Stack)
Antonio Almada - Comision C25254 Talento Tech - BackEnd con Java 2025

Este repositorio contiene la aplicación completa de un sistema de gestión de pedidos, construida con una API REST utilizando Spring Boot (Backend) y una aplicación web desacoplada (Frontend).

🚀 Arquitectura del Proyecto

El proyecto está dividido en dos directorios principales:

backend-springboot/: Contiene la lógica del servidor, la API RESTful, y la conexión a la base de datos MySQL.

frontend-web/: Contiene la interfaz de usuario que consume los servicios del backend.

💻 Backend: API RESTful con Spring Boot

El backend maneja toda la lógica de negocio, persistencia de datos y exposición de la API.

🛠️ Tecnologías Utilizadas

Lenguaje: Java 21

Framework: Spring Boot 3.x

Base de Datos: MySQL

Persistencia: Spring Data JPA / Hibernate

Construcción: Maven

Dependencias Adicionales: Lombok

📋 Módulos Principales (Modelos/Entidades)

Basado en los controladores y modelos detectados, la API expone los siguientes recursos:

Recurso

Descripción

/clientes

Gestión completa de la información de los clientes.

/productos

Listado y administración del inventario de productos.

/pedidos

Creación y consulta de pedidos (que incluyen detalles y estado de anulación).

