package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionClassPairDto;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionListResponse;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionResponse;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoDto;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.AportacionService;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
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
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/aportacion")
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@RequiredArgsConstructor
@Tag(name = "Aportacion", description = "Esta clase implementa Restcontrollers para la entidad Aportacion")
public class AportacionController {

    private final AportacionService aportacionService;
    private final TipoAlimentoService tipoAlimentoService;
    private final ClaseService claseService;

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
    @GetMapping("/")
    public ResponseEntity <List<AportacionListResponse>> findAll() {
        List<AportacionListResponse> aportacionListResponseList = new ArrayList<>();
        aportacionService.findAll().forEach(aportacion -> {
            aportacionListResponseList.add(aportacionService.toAportacionListReponse(aportacion));
        });

        if (aportacionListResponseList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity
                    .ok(aportacionListResponseList);
        }
    }



    @Operation(summary = "Este método lista una lista de pares de tipo de alimento y kilos de una aportación " +
            "y su fecha si la localiza por el id de la clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la aportación de la clase que buscaba",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionClassPairDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "fecha": "2022-12-21",
                                                    "detallesAportacion": {
                                                        "Atún": 3.0,
                                                        "Pizza": 3.0
                                                    }
                                                },
                                                {
                                                    "fecha": "2022-12-21",
                                                    "detallesAportacion": {
                                                        "Atún": 5.0,
                                                        "Pizza": 4.0
                                                    }
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna clase con ese id",
                    content = @Content),
    })
    @GetMapping("/clase/{id}")
    public ResponseEntity <List<AportacionClassPairDto>> getOneTipoAlimento (
            @Parameter(description = "Id de la clase de la que se quiere consultar la aportación")
            @PathVariable Long id) {

        Optional<Clase> c = claseService.findById(id);
        if (c.isEmpty() || id == null)
            return ResponseEntity.notFound().build();
        else{
            return ResponseEntity.ok().body(aportacionService.toAportacionClassPairDtoList(c.get()));
        }
    }


    @Operation(summary = "Este método crea una nueva aportacion y sus detalles de aportacion pertinentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva aportacion",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionResponse.class)),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 4,
                                        "claseId": 4,
                                        "fecha": "2022-12-19",
                                        "detallesAportacion": [
                                            {
                                                "id": {
                                                    "aportacionId": 4,
                                                    "numLinea": 1
                                                },
                                                "cantidadEnKilos": 2.0,
                                                "tipoAlimento": {
                                                    "id": 1,
                                                    "nombre": "verveverv"
                                                }
                                            },
                                            {
                                                "id": {
                                                    "aportacionId": 4,
                                                    "numLinea": 2
                                                },
                                                "cantidadEnKilos": 2.0,
                                                "tipoAlimento": {
                                                    "id": 2,
                                                    "nombre": "verveverv"
                                                }
                                            }
                                        ]
                                    }
                                    """)) }),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido correctamente los datos de aportacion",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<AportacionResponse> createAportcion(@RequestBody CreateAportacion create){
        AtomicReference<Boolean> comprobarId= new AtomicReference<>(true);
        create.listadoDetallesAportacion().forEach(createDetalleAportacion -> {
            if ((createDetalleAportacion.tipoAlimentoId()==null || !(tipoAlimentoService.existById(createDetalleAportacion.tipoAlimentoId()))))
                comprobarId.set(false);
        });
        if(!(create.claseId()==null || create.listadoDetallesAportacion().isEmpty() || !comprobarId.get()))

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(AportacionResponse.of(aportacionService.add(aportacionService.toAportacion(create))));
        else
            return ResponseEntity.badRequest().build();
    }

}
