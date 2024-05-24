# Proyecto de API de Usuarios

Este proyecto es una API de usuarios que permite la creación y gestión de usuarios y sus teléfonos asociados.

## Requisitos

- **JDK 17**: Este proyecto fue desarrollado y probado con JDK 17. Es posible que funcione con versiones superiores, pero no se ha verificado.
- **Maven 3.9.1**: Utilizado para la gestión de dependencias y construcción del proyecto. Se recomienda utilizar esta versión para evitar incompatibilidades.

# Base de Datos H2 (memoria)

No es necesario crear manualmente las tablas de la base de datos, ya que JPA se encarga de generarlas automáticamente al iniciar la aplicación. Tampoco hace falta un script.

Para acceder a la base de datos en memoria, ingresar a: http://localhost:8080/h2-console

JDBC URL:	jdbc:h2:mem:mydb
User Name:	sa
password: (no hay contraseña)

# Construir el proyecto:

mvn clean install

# Ejecutar la aplicación:

mvn spring-boot:run

# Uso de la API

Creación de Usuarios

Para crear un usuario, envía una solicitud POST a /api/v1/ms-users-api/user con el siguiente formato:

{
  "name": "Nombre del Usuario",
  "email": "email@dominio.com",
  "password": "contraseña",
  "phones": [
    {
      "number": "123456789",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}

# Autenticación

1. Al crear un usuario, recibirás una respuesta que incluye un campo token. Este token debe ser utilizado para la autenticación en futuras solicitudes.

2. Puedes hacer uso de auth/login usando un email y contraseña cuando lo necesites. 

# Configuración en Postman:

Copia el valor del campo token.
En Postman, selecciona "Authorization".
En el tipo de autorización, selecciona "Bearer Token".
Pega el token copiado en el campo correspondiente.

# Iniciar Sesión
Si no capturaste el token al crear el usuario, puedes iniciar sesión para obtener uno nuevo:

URL: http://localhost:8080/api/v1/ms-users-api/auth/login
Método: POST
payload:
{
  "email": "email@dominio.com",
  "password": "contraseña"
}

La respuesta incluirá un nuevo token que podrás utilizar para la autorización.

# Gestión de usuario:

puedes añadir, buscar, modificar o elimnar cualquier usuario que desees.

Utiliza el endpoint POST /api/v1/ms-users-api/user/ para agregar un usuario nuevo. Además, puedes incluir sus teléfonos teléfonos:

payload:
{
  {
    "name": "Nombre Demo",
    "email": "email@dominio.com",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        },
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
}

# Gestión de Teléfonos
Los teléfonos se pueden gestionar de dos maneras:

Al crear un usuario:
Puedes incluir un array de teléfonos en el campo phones al crear un usuario.

Creación posterior:
Utiliza el endpoint POST /api/v1/ms-users-api/user/{userId}/phone para agregar teléfonos a un usuario existente. Reemplaza {userId} con el ID del usuario correspondiente.

URL: http://localhost:8080/api/v1/ms-users-api/users/{userId}/phone
Método: POST
Cuerpo:
json
{
  "number": "123456789",
  "citycode": "1",
  "countrycode": "57"
}
