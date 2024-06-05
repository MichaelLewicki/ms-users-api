# Proyecto de API de Usuarios

Este proyecto es una API de usuarios que permite la creación y gestión de usuarios y sus teléfonos asociados.

## Requisitos

- **JDK 17**: Este proyecto fue desarrollado y probado con JDK 17. Es posible que funcione con versiones superiores, pero no se ha verificado.
- **Maven 3.9.1**: Utilizado para la gestión de dependencias y construcción del proyecto. Se recomienda utilizar esta versión para evitar incompatibilidades.

# Base de Datos H2 (memoria)

No es necesario crear manualmente las tablas de la base de datos, ya que JPA se encarga de generarlas automáticamente al iniciar la aplicación. Tampoco hace falta un script.

Para acceder a la base de datos en memoria, ingresar a: http://localhost:8080/h2-console

Luego ingresar las siguientes credenciales:

1. JDBC URL:	jdbc:h2:mem:mydb
2. User Name:	sa
3. password: (no hay contraseña)

# Swagger

Puedes acceder a la swagger-ui ejecutando la app y accediendo a http://localhost:8080/swagger-ui/index.html

La respuesta incluirá un nuevo token que podrás utilizar para la autorización.

# Construir el proyecto

mvn clean install

# Ejecutar la aplicación

mvn spring-boot:run

# Autenticación

Para poder generar un login que permita acceder a todos endpoints de la api, se pueden hacer dos cosas:

1. Al crear un usuario, recibirás una respuesta que incluye un campo token. Este token debe ser utilizado para la autenticación en futuras solicitudes.

2. Puedes hacer uso de auth/login usando un email y contraseña cuando lo necesites. Este endpoint devuelve un token como respuesta. 

# Autenticación en Postman

1. Copia el valor del campo token.

2. En Postman, selecciona "Authorization" a nivel de tu colección o en la request que estés utilizando.

3. En el tipo de autorización, selecciona "Bearer Token".

4. Pega el token copiado en el campo Token.
