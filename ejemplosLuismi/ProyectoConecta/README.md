# ProyectoConecta

## Descripción 

Este programa realiza la gestión de los contactos que ha tenido los profesores con las empresas de prácticas, incluyendo elemenos como la demanda, las convocatorias, los trabajadores y las familias profesionales entre otros.

>[!TIP]
>No hace falta usar el docker-compose up -d porque este ya se hace automaticamente al iniciar el proyecto.

## Credenciales de acceso 🔑

A la hora de usar la base de datos en Postgres:

>[!IMPORTANT]
>Nombre del servidor: proyectoConecta</br>
>Dirección del servidor: postgresql-spring</br>
>Puerto: 5432</br>
>Nombre de usuario: fran</br>
>Contraseña: 12345678

## Manual de Uso 📋
### Recursos necesarios
- Spring Boot.
- Dependencias de Spring: Lombok, H2 Database, Spring Data JPA, Spring Web, Docker-compose y Postgresql.
  
### Como usar
-Para ver la documentación de Swagger entrar en esta url: http://localhost:8080/swagger-ui/index.html#/</br>
-Para ver la base de datos de Postgresql usar esta url: http://localhost:5050
>[!IMPORTANT]
>Correo: admin@admin.com</br></br>
>Contraseña: 1

## Prueba de Peticiones

>[!NOTE]
>A la hora de hacer las peticiones que requieran id si se pone la id: "1" funciona correctamente todo.</br></br>
>En las peticiones que buscan todas las entidades, se usa True para las que estan borradas y False para las que no.
