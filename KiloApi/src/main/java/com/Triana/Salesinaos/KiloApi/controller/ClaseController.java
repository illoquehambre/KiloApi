package com.Triana.Salesinaos.KiloApi.controller;


import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseResponse;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clase")
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@Tag(name = "Clase", description = "Esta clase implementa Restcontrollers para la entidad Clase")
public class ClaseController {


    private final ClaseService claseService;
    private final ClaseService service;
    private final ClaseDtoConverter converter;



    @Operation(summary = "Obtiene todos las clases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado clases",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Clase.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                [
                                                    {
                                                        "id": 1,
                                                        "nombre": "2ºDAM",
                                                        "tutor": "Luismi",
                                                        "listadoAportaciones": []
                                                    },
                                                    {
                                                        "id": 2,
                                                        "nombre": "1ºDAM",
                                                        "tutor": "Eduardo",
                                                        "listadoAportaciones": []
                                                    }
                                                ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna clase",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<Clase>> getAllclases(){
    List<Clase> data = claseService.findAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(data);
        }

        // Retocar este get cambiar el response entity y probablemente el .body()
    }


    @Operation(summary = "Obtiene una clase por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la clase",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Clase.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "1ºDAM", "tutor": "Eduardo", "numAportaciones": 4, "kilosTotales": 34},
                                                {"id": 2, "nombre": "2ºDAM", "tutor": "Luismi", "numAportaciones": 4, "kilosTotales": 32}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna clase",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClaseResponse> getClaseById(@PathVariable Long id){
        if (!service.existById(id))
            return ResponseEntity.notFound().build();
         else
            return ResponseEntity
                    .ok()
                    .body(converter.ClaseToClaseResponse(service.findById(id).get()));

    }



    @Operation(summary = "Este método crea una nueva clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva clase",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDto.class)),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 2,
                                        "nombre": "2º DAM",
                                        "tutor": "Luismi"
                                    }
                                    """)) }),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido correctamente los datos de la clase",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<ClaseDto> createClase(@RequestBody ClaseDto claseDtoRequest){
        if(!claseDtoRequest.nombre().isBlank() || !claseDtoRequest.tutor().isBlank())
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ClaseDto.of(claseService.add(claseService.toClase(claseDtoRequest))));
         else
            return ResponseEntity.badRequest().build();

    }
    @Operation(summary = "Modifica una clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha modificado corrrectamente una clase ",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Clase.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "1ºDAM", "tutor": "Miguel" },
                                                {"id": 1, "nombre": "2ºDAM", "tutor": "Luismi" }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha podido encontrar la clase",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido modificar la clase",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClaseDto> updateClase(@RequestBody ClaseDto clase, @PathVariable Long id){


        return ResponseEntity.of(
               claseService.findById(id).map(old -> {
                            old.setNombre(clase.nombre());
                            old.setTutor(clase.tutor());

                            return Optional.of(claseService.toClaseDto(claseService.add(old)));
                        })
                        .orElse(Optional.empty())
        );

        // Falta gestionar el Bad REquest
    }

    @Operation(summary = "Este método elimina una clase localizada por su id")
    @ApiResponse(responseCode = "204", description = "Clase borrada con éxito",
            content = @Content)
    @Parameter(description = "El id de la clase que se quiere eliminar", name = "id", required = true)
    @DeleteMapping("/")
    public ResponseEntity<?> deleteClase(@PathVariable Long id){

        // Una vez se cree el ranking quizás haya que hacer gestiones desde aquí

        if(claseService.existById(id)) {
            claseService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

}
