Este proyecto consiste en el desarrollo de una API REST para gestionar
un sistema de facturación, implementada con Spring Boot y MySQL.

Tecnologías utilizadas:

Lenguaje: Java 17+
Framework: Spring Boot 3.x
ORM: Spring Data JPA / Hibernate
Base de Datos: MySQL
Herramientas: Lombok y RestTemplate.

Estructura de Base de Datos

El esquema en MySQL consta de las tablas clientes, productos, facturas y detalle_factura,
vinculadas exclusivamente mediante relaciones 1:N
(Cliente→Factura, Factura→Detalle, Producto→Detalle).
Este diseño garantiza integridad referencial, normalización adecuada y una estructura
consistente para operaciones de lectura y escritura en el módulo de facturación.

Colección Postman incluida

La colección se encuentra en:

src/main/resources/json/FacturacionAPI.postman_collection.json
Incluye pruebas para:


Crear / Listar / Obtener Clientes

Crear / Listar / Obtener Productos

Crear Factura (DTO completo)

Obtener Factura por ID

🧪 Endpoints principales
Clientes
Método	     Endpoint	        Descripción
POST	    / api/clientes	    Crear cliente
GET	       /  api/clientes	    Listar clientes
GET	      /api/clientes/{id}    Obtener cliente por ID
PUT	     /api/clientes/{id}	    Actualizar
DELETE	/api/clientes/{id}	    Eliminar

Productos
Método	     Endpoint
POST	     /api/productos
GET	        /api/productos
GET	       /api/productos/{id}

Facturas
Método        	Endpoint
POST	       /api/facturas
GET	          /api/facturas/{id}

 DTO para crear Factura
{
 "clienteId": 1,
"items": [
{
"codigoProducto": "P001",
"cantidad": 2,
"precioUnitario": 120.50
}
]
}

 Manejo de errores

Las respuestas de error se devuelven en un formato común:

{
"message": "Error interno del servidor",
"detail": "Descripción técnica del error"
}


Esto se maneja mediante un @ControllerAdvice.