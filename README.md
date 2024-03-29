# KiloApi
## API REST con SPRING - Proyecto de prácticas para estudiantes de 2ºDAM.

<img src="https://img.shields.io/badge/Spring--Framework-5.7-green"/> <img src="https://img.shields.io/badge/Apache--Maven-3.8.6-blue"/> <img src="https://img.shields.io/badge/Java-17.0-brightgreen"/>

 <img src="https://niixer.com/wp-content/uploads/2020/11/spring-boot.png" width="500" alt="Spring Logo"/>
 
___


## **Documentación**

:point_right: [Dirección SWAGGER-IO](http://localhost:8080/swagger-ui-kiloapi.html)

:point_right: Se incluye también una colección de Postman para probar datos.


## **Introducción** :speech_balloon:

Este es un ejercicio práctico para el desarrollo de una API REST en lenguaje Java y manejando diferentes tecnologías en la que destaca **Spring**.

También se ha prácticado el manejo de **PostMan**, **Swagger** y la metodología ágil **SCRUM** para el reparto de tareas a través la ramificación de estas con **GitHub**.

El proyecto trata de realizar una API REST para la gestión de una operación de recaudación de donaciones de alimentos de un colegio, el control y gestión de esos alimentos, el reparto y gestión de los destinatarios. Además podrá incorporar un ranking para conocer cuáles han sido las clases más solidarias del colegio.



Se pueden realizar las siguientes funcionalidades: 	:point_right:
* Listado de CLASES
* Busqueda de una CLASE buscada por su id
* Creación de una nueva CLASE
* Edición de una CLASE
* Borrado de una CLASE

* Listado de los diferentes TIPOS DE ALIMENTO
* Búsqueda de un TIPO DE ALIMENTO por su id
* Creación de un nuevo TIPO DE ALIMENTO
* Edición de un TIPO DE ALIMEMTO
* Borrado de un TIPO DE ALIMENTO


* Listado de todas las APORTACIONES
* Listado de las APORTACIONES de una determinada clase localizada por su id
* Búsqueda de una APORTACIÓN buscada por su id
* Creación de un nueva APORTACIÓN
* Edición de un TIPO DE ALIMEMTO
* Borrado de una APORTACIÓN localizada por su id
* Borrado de un DETALLE DE UNA APORTACIÓN localizado por su id


* Listado del total de KILOS DISPONIBLES de todos los alimentos
* Listado de los KILOS DISPONIBLES de un tipo de alimento

* RANKING de solidaridad de las clases medido por sus aportaciones

* Listado de todos los DESTINATARIOS
* Búsqueda general de un DESTINATARIO por su id
* Búsqueda de los detalles de un DESTINATARIO por su id
* Creación de un nuevo DESTINATARIO
* Edición de un DESTINATARIO
* Borrado de un DESTINATARIO buscado por su id


* Listado general de CAJAS 
* Búsqueda de una CAJA por su id
* Creación de una nueva CAJA
* Edición de los datos generales de una CAJA
* Edición del contenido de una CAJA
* Borrado de una CAJA
* Borrado de un TIPO DE ALIMENTO de una CAJA
* Creación de una CAJA asiganda a un DESTINATARIO


---

## **Tecnologías utilizadas** 

Para realizar este proyecto hemos utilizado:

1. [Spring Boot 2.7.5](https://spring.io/) - Dependencias: **Spring-Web**, **JPA-HIBERNATE**, **H2 Database**, **Sprin-doc Open-api**, **Lombok**
2. [Apache Maven 3.8.6](https://maven.apache.org/)
3. [Postman](https://www.postman.com/)
4. [GitHub](https://github.com/)
5. [springdoc 1.6.13](https://springdoc.org/)
6. [Swagger](https://swagger.io/)



### Ejemplos del Código Usado: 

**JAVA**:
```Java
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAportacion(
            @Parameter(description = "Id de la aportación de la que quiere borrar", name = "id", required = true)
            @PathVariable Long id) {
        if (aportacionService.findById(id).isPresent()) {
            Aportacion aportacion = aportacionService.findById(id).get();
            if (aportacionService.findById(id).get().getDetalleAportacionList().isEmpty())
                aportacionService.deleteById(id);
            else {
                Iterator<DetalleAportacion> aux = aportacion.getDetalleAportacionList().iterator();
                while (aux.hasNext()) {
                    DetalleAportacion detalle = aux.next();
                    TipoAlimento tipoAlimento = tipoAlimentoService.findById(detalle.getTipoAlimento().getId()).get();
                    double cantidadDisponible = tipoAlimento.getKilosDisponibles().getCantidadDisponible();
                    if (detalle.getCantidadEnKilos() <= cantidadDisponible)
                        tipoAlimento.getKilosDisponibles().setCantidadDisponible(cantidadDisponible - detalle.getCantidadEnKilos());
                    aux.remove();
                }
                if (aportacion.getDetalleAportacionList().isEmpty())
                    aportacionService.deleteById(id);
            }
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

```

**Documentación**

```Java
    @Operation(summary = "Este método lista todas las aportaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado al menos una aportación",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionListResponse.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "fecha": "2022-12-21",
                                                    "nombreClase": "2ºDAM",
                                                    "kilosTotales": 7.0
                                                },
                                                {
                                                    "fecha": "2022-12-21",
                                                    "nombreClase": "1ºDAM",
                                                    "kilosTotales": 5.0
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna aportación",
                    content = @Content),
    })
```


---
## **Arranque**

Realiza un *git clone* de la siguiente dirección: 
*https://github.com/illoquehambre/KiloApi*

```console
git clone https://github.com/illoquehambre/KiloApi.git
```

Dirígete hasta la carpeta:

> cd ./KiloApi/


**Primero** tienes que tener instalado Apache Maven y sería recomendable tener alguna IDE, como **Intellij IDEA** o **VisualStudio Code**

Ejecuta el siguiente comando:
    
    mvn complile
    
    
Ejecuta el siguiente comando:
    
    mvn clean


Ejecuta el comando:

    mvn spring-boot:run
    
    
Si diese algún error, realiza el siguiente comando:  

    mvn dependencies:resolve
    ---> 100% 

___
## **Autores**

Este proyecto ha sido realizado por: 

* [Rogelio Mohigefer Barrera - GITHUB](https://github.com/RogeMB)
* [Ignacio Moreno Gómez - GITHUB](https://github.com/illoquehambre)
* [Maylor David Bustamante Mercado - GITHUB](https://github.com/MaylorSr)
* [Carlos Ortega Reina - GITHUB](https://github.com/CarlitrosPicaTecla)

Estudiantes de 2º Desarrollo de Aplicaciones Multiplataforma, grado 
superior de formación profesional en la ciudad de Sevilla, España.

### **Thump up :+1: And if it was useful for you, star it! :star: :smiley:**

___
## **TODO**

Tareas realizadas y cosas por hacer.

[ ] Fix possible future errors
___


