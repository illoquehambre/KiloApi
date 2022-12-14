package com.Triana.Salesinaos.KiloApi.controller;


import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clase")
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@Tag(name = "Clase", description = "Esta clase implementa Restcontrollers para la entidad Clase")
public class ClaseController {

    private final ClaseService claseService;
    private final ClaseDtoConverter converter;



    @Operation(summary = "Obtiene todos las clases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado clases",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "nombre": "2ºDam",
                                                    "tutor": "Luismi"
                                                },
                                                {
                                                    "id": 2,
                                                    "nombre": "1ºDam",
                                                    "tutor": "Eduardo"
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
    public ResponseEntity<List<ClaseDto>> getAllclases(){
    List<ClaseDto> data = new ArrayList<>();

    claseService.findAll().forEach(clase -> {
        data.add(claseService.toClaseDto(clase));
    });

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(data);
        }
    }


    @Operation(summary = "Obtiene una clase por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la clase",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "2ºDam",
                                                "tutor": "Luismi",
                                                "numAportaciones": 0,
                                                "kilosTotales": 0.0
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna clase",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "No existen aportaciones realizadas por esta clase",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClaseResponse> getClaseById(@PathVariable Long id){
        if (!claseService.existById(id))
            return ResponseEntity.notFound().build();
         else
            return ResponseEntity
                    .ok()
                    .body(converter.ClaseToClaseResponse(claseService.findById(id).get()));

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
                            array = @ArraySchema(schema = @Schema(implementation = ClaseDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 2,
                                                "nombre": "1ºDam",
                                                "tutor": "Miguel"
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido modificar la clase",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClaseDto> updateClase(@RequestBody ClaseDto clase, @PathVariable Long id){

    if(clase.nombre().isBlank()||clase.tutor().isBlank())
            return ResponseEntity.badRequest().build();
    else
        return ResponseEntity.of(
                claseService.findById(id).map(old -> {
                            old.setNombre(clase.nombre());
                            old.setTutor(clase.tutor());

                            return Optional.of(claseService.toClaseDto(claseService.add(old)));
                        })
                        .orElse(Optional.empty())
        );
    }

    @Operation(summary = "Este método elimina una clase localizada por su id")
    @ApiResponse(responseCode = "204", description = "Clase borrada con éxito",
            content = @Content)
    @Parameter(description = "El id de la clase que se quiere eliminar", name = "id", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClase(@PathVariable Long id){

        if(claseService.existById(id)) {
            claseService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

}
