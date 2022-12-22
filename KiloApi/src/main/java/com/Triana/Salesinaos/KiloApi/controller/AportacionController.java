package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.*;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.model.*;
import com.Triana.Salesinaos.KiloApi.service.KilosDisponiblesService;
import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoDto;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionResponse;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.Clase;
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
import java.util.Iterator;
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

        if (aportacionListResponseList.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(aportacionListResponseList);

    }



    @Operation(summary = "Este método lista una lista de pares de tipo de alimento y kilos de una aportación " +
            "y su fecha si la localiza por el id de la clase y si, además, esa clase tiene aportaciones")
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
            @Parameter(description = "Id de la clase de la que se quiere consultar la aportación", name = "id",
                    required = true)
            @PathVariable Long id) {

        Optional<Clase> c = claseService.findById(id);

        if (c.isEmpty() || id == null || c.get().getListadoAportaciones().isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(aportacionService.toAportacionClassPairDtoList(c.get()));

    }


    @Operation(summary = "Este método crea una nueva aportacion y sus detalles de aportacion pertinentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva aportacion",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionResponse.class)),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 3,
                                        "claseId": 3,
                                        "fecha": "2022-12-21",
                                        "detallesAportacion": [
                                            {
                                                "numLinea": 1,
                                                "tipoAlimento": "cerveza",
                                                "kilos": 2.0
                                            },
                                            {
                                                "numLinea": 2,
                                                "tipoAlimento": "cerveza",
                                                "kilos": 3.0
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
            if ((createDetalleAportacion.kilos()<=0 || createDetalleAportacion.tipoAlimentoId()==null
                    || !(tipoAlimentoService.existById(createDetalleAportacion.tipoAlimentoId()))))
                comprobarId.set(false);
        });
        if(!(create.claseId()==null || claseService.findById(create.claseId()).isEmpty()
                || create.listadoDetallesAportacion().isEmpty() || !comprobarId.get()))

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(AportacionResponse.of(aportacionService.add(aportacionService.toAportacion(create))));
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Este método actualiza los kilos de un detalle aportacion, modificandolos tambien en " +
            "kilos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado correctamente la aportacion",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionResponse.class)),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 3,
                                        "claseId": 3,
                                        "fecha": "2022-12-21",
                                        "detallesAportacion": [
                                            {
                                                "numLinea": 1,
                                                "tipoAlimento": "cerveza",
                                                "kilos": 2.0
                                            },
                                            {
                                                "numLinea": 2,
                                                "tipoAlimento": "cerveza",
                                                "kilos": 3.0
                                            }
                                        ]
                                    }
                                    """)) }),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido correctamente los datos de aportacion",
                    content = @Content),
    })
    @PutMapping("/{id}/linea/{num}/kg/{numKg}")
    public ResponseEntity<AportacionResponse> updateAportacion(@PathVariable Long id,
                                                               @PathVariable int num,
                                                               @PathVariable double numKg){
        AtomicReference<Boolean> bad= new AtomicReference<>(false);
        AtomicReference<Boolean> encontrado= new AtomicReference<>(false);

        Optional<Aportacion> aportacion = aportacionService.findById(id);

        if (aportacion.isPresent() ){
            aportacion.get().getDetalleAportacionList().forEach(detalle->{

                Optional<KilosDisponibles> kilosDisponibles =kilosDisponiblesService.findById(detalle.getTipoAlimento().getId());
                TipoAlimento tipoAlimento = tipoAlimentoService.findById(detalle.getTipoAlimento().getId()).get();
                if(detalle.getId().getNumLinea()==num && !encontrado.get() && kilosDisponibles.isPresent()) {
                    if(numKg<detalle.getCantidadEnKilos() ){
                        if((kilosDisponibles.get().getCantidadDisponible()+(numKg-detalle.getCantidadEnKilos())>=0)){
                            tipoAlimento.addKilosToTipoAlimento(kilosDisponibles.get(), (numKg-detalle.getCantidadEnKilos()));
                            detalle.setCantidadEnKilos(numKg);
                            kilosDisponiblesService.add(kilosDisponibles.get());
                        }else
                            bad.set(true);
                    }else{
                        tipoAlimento.addKilosToTipoAlimento(kilosDisponibles.get(), (numKg-detalle.getCantidadEnKilos()));
                        detalle.setCantidadEnKilos(numKg);
                        kilosDisponiblesService.add(kilosDisponibles.get());                    }
                }else if(encontrado.get())
                    bad.set(true);
            });
        }else
            bad.set(true);

        if(!bad.get())
            return ResponseEntity.ok(AportacionResponse.of(aportacionService.add(aportacion.get())));
        else
            return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Este método busca una aportacion por su id y la muestra junto con un listado de sus detalles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado correctamente la aportacion",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AportacionResponse.class)),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 3,
                                        "claseId": 3,
                                        "fecha": "2022-12-21",
                                        "detallesAportacion": [
                                            {
                                                "numLinea": 1,
                                                "tipoAlimento": "cerveza",
                                                "kilos": 2.0
                                            },
                                            {
                                                "numLinea": 2,
                                                "tipoAlimento": "cerveza",
                                                "kilos": 3.0
                                            }
                                        ]
                                    }
                                    """)) }),
            @ApiResponse(responseCode = "404",
                    description = "No se han podido encontrar correctamente los datos de aportacion",
                    content = @Content),
    })
    @GetMapping("/{id}")
    ResponseEntity<AportacionResponse> getById(
            @Parameter(description = "Id de la aportación de la que quiere encontrar", name = "id", required = true)
            @PathVariable Long id){
        if (aportacionService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity
                    .ok()
                    .body(AportacionResponse.of(aportacionService.findById(id).get()));
    }

    @Operation(summary = "Elimina un tipo de aportacion junto con todos sus detalles de aportacion ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Aportacion eliminada",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimento.class))
                    )}),

    })
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
    @Operation(summary = "Este método elimina un detalle de una aportación si encuentra la aportación por el id y " +
            "puede borrar sus detalles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado el detalle de aportación que buscaba y ha eliminado lo que " +
                            "es posible elimnar",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DetalleAportacionResponse.class)),
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
            @ApiResponse(responseCode = "204",
                    description = "No se ha encontrado ninguna aportación con ese id",
                    content = @Content),
    })
    @DeleteMapping("/{id}/linea/{num}")
    public ResponseEntity<AportacionResponse> deleteDetalleAportacion(
            @Parameter(description = "Id de la aportación de la que quiere borrar sus detalles", name = "id",
                    required = true)
            @PathVariable Long id,
            @Parameter(description = "Id de la aportación de la que quiere borrar sus detalles", name = "num",
                    required = true)
            @PathVariable int num) {

        Optional<Aportacion> aportacion = aportacionService.findById(id);


        if(aportacion.isEmpty() || id == null || num <= 0)
            return ResponseEntity.noContent().build();
            // he añadido esta opción porque encuentro lógico que devuelva
            // un 404 en lugar de un 200 si no encuentra aportaciones con ese id
        else {

            List<DetalleAportacion> detalleAportacions = aportacion.get().getDetalleAportacionList();

            if(!detalleAportacions.isEmpty()) {
                Iterator<DetalleAportacion> iter = detalleAportacions.iterator();

                while (iter.hasNext()) {
                    DetalleAportacion detalleAportacion = iter.next();
                    double cantidadKilosDetalleAportacion = detalleAportacion.getCantidadEnKilos();
                    int idDetalleAportacion = detalleAportacion.getId().getNumLinea();
                    TipoAlimento tipoAlimento = tipoAlimentoService.findById(detalleAportacion.getTipoAlimento().getId()).get();
                    double cantKilosTipoAlimento = tipoAlimento.getKilosDisponibles().getCantidadDisponible();

                    if(idDetalleAportacion == num && cantidadKilosDetalleAportacion > 0
                            && cantKilosTipoAlimento >= cantidadKilosDetalleAportacion){
                        tipoAlimento.getKilosDisponibles()
                                .setCantidadDisponible(cantKilosTipoAlimento - cantidadKilosDetalleAportacion);
                        iter.remove();
                    }
                }

                if(detalleAportacions.isEmpty()){
                    aportacionService.deleteById(id);
                    return ResponseEntity.noContent().build();
                    // Este return es necesario puesto que si una aportación queda vacía, se borra,
                    // y al intentar encontrarla por el id puede devolver un 500
                }

            }
            return ResponseEntity
                    .ok()
                    .body(AportacionResponse.of(aportacionService.add(aportacion.get())));
        }

    }

}
