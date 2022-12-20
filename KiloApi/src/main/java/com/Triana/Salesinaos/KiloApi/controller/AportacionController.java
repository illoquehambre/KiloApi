package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionListResponse;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionResponse;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.service.AportacionService;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import com.Triana.Salesinaos.KiloApi.service.KilosDisponiblesService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
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
    private final KilosDisponiblesService kilosDisponiblesService;
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
                                                    "id": 1,
                                                    "nombre": "Patatas"
                                                },
                                                {
                                                    "id": 2,
                                                    "nombre": "Arroz"
                                                },
                                                {
                                                    "id": 7,
                                                    "nombre": "Banana"
                                                },
                                                {
                                                    "id": 8,
                                                    "nombre": "Pizza"
                                                },
                                                {
                                                    "id": 9,
                                                    "nombre": "Botella Cocacola"
                                                },
                                                {
                                                    "id": 10,
                                                    "nombre": "Mejillones"
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
        if(!(create.claseId()==null || claseService.findById(create.claseId()).isEmpty() || create.listadoDetallesAportacion().isEmpty() || !comprobarId.get()))

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(AportacionResponse.of(aportacionService.add(aportacionService.toAportacion(create))));
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Este método actualiza los kilos de un detalle aportacion, modificandolos tambien en kilos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado correctamente la aportacion",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionResponse.class)),
                            examples = @ExampleObject(value = """
                                    {
                                         "id": 3,
                                         "claseId": 3,
                                         "fecha": "2022-12-20",
                                         "detallesAportacion": [
                                             {
                                                 "id": {
                                                     "aportacionId": 3,
                                                     "numLinea": 1
                                                 },
                                                 "cantidadEnKilos": 4.0,
                                                 "tipoAlimento": {
                                                     "id": 1,
                                                     "nombre": "cerveza",
                                                     "kilosDisponibles": {
                                                         "id": 1,
                                                         "cantidadDisponible": 7.0
                                                     }
                                                 }
                                             },
                                             {
                                                 "id": {
                                                     "aportacionId": 3,
                                                     "numLinea": 2
                                                 },
                                                 "cantidadEnKilos": 3.0,
                                                 "tipoAlimento": {
                                                     "id": 1,
                                                     "nombre": "cerveza",
                                                     "kilosDisponibles": {
                                                         "id": 1,
                                                         "cantidadDisponible": 7.0
                                                     }
                                                 }
                                             }
                                         ]
                                     }
                                    """)) }),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido correctamente los datos de aportacion",
                    content = @Content),
    })
    @PutMapping("/{id}/linea/{num}/kg/{numKg}")
    public ResponseEntity<AportacionResponse> updateAportacion(@PathVariable Long id, @PathVariable int num, @PathVariable double numKg){
        AtomicReference<Boolean> bad= new AtomicReference<>(false);
        AtomicReference<Boolean> encontrado= new AtomicReference<>(false);
      //Este put no tiene cuerpo de peticion
        //Solo modifica los kg de un detalle aportacion
        //se comprueba que es viable con los kilos disponibles y siu no lanza bad request
        if (aportacionService.findById(id).isPresent() ){
            aportacionService.findById(id).get().getDetalleAportacionList().forEach(detalle->{

                if(detalle.getId().getNumLinea()==num && !encontrado.get()){//se comprueba que existe el detalle aportacion ene la aportacion y que la cantidad de kilos disponibles a modificar sea valida
                    if(numKg<detalle.getCantidadEnKilos()){
                        if((kilosDisponiblesService.findById(detalle.getTipoAlimento().getId()).get().getCantidadDisponible()+(numKg-detalle.getCantidadEnKilos())>=0)){
                            tipoAlimentoService.findById(detalle.getTipoAlimento().getId()).get()
                                    .addKilosToTipoAlimento(kilosDisponiblesService.findById(detalle.getTipoAlimento().getId()).get(), (numKg-detalle.getCantidadEnKilos()));
                            detalle.setCantidadEnKilos(numKg);
                            kilosDisponiblesService.add(kilosDisponiblesService.findById(detalle.getTipoAlimento().getId()).get());
                        }else
                            bad.set(true);
                    }else{
                        tipoAlimentoService.findById(detalle.getTipoAlimento().getId()).get()
                                .addKilosToTipoAlimento(kilosDisponiblesService.findById(detalle.getTipoAlimento().getId()).get(), (numKg-detalle.getCantidadEnKilos()));
                        detalle.setCantidadEnKilos(numKg);
                        kilosDisponiblesService.add(kilosDisponiblesService.findById(detalle.getTipoAlimento().getId()).get());
                    }

                }else if(encontrado.get())
                    bad.set(true);


            });


        }else
            bad.set(true);

        if(!bad.get())
            return ResponseEntity.ok(AportacionResponse.of(aportacionService.add(aportacionService.findById(id).get())));
        else
            return ResponseEntity.badRequest().build();

        //s modifican los kilos de kilosDisponibles en funcion de la diferencia entre lo que se haya indicado y lo aportado anteriormente



    }

}
