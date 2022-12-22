package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.kilosDisponibles.KilosDisponiblesDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.kilosDisponibles.KilosDisponiblesRespo;
import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaRespon;
import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.KilosDisponiblesService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kilosDisponibles")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@Tag(name = "Kilos Disponibles", description = "Esta clase implementa Restcontrollers para la entidad Kilos disponibles")
public class KilosDisponiblesController {

    private final KilosDisponiblesService kilosDisponiblesService;

    private final KilosDisponiblesDtoConverter kilosDisponiblesDtoConverter;

    private final TipoAlimentoService tipoAlimentoService;


    @Operation(summary = """
            Lista con los datos los kilos disponibles de todos los 
            alimentos: id del tipo de alimento, nombre y cantidad de kilos
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = """
                            Muestra la lista usando un Dto que muestra tres simples datos
                            """,
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimentoToCajaRespon.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Pasta"}, "kgCantidad" : "5"},
                                                {"id": 2, "nombre": "Aceite"}, "kgCantidad" : "15}",
                                                {"id": 3, "nombre": "Arroz"}, "kgCantidad" : "25"},
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "La lista se encuentra vacía",
                    content = @Content),
    })

    @GetMapping("/")
    public ResponseEntity<List<TipoAlimentoToCajaRespon>> findAllTipoAlimento() {
        List<KilosDisponibles> kilosDisponiblesList = kilosDisponiblesService.findAll();
        if (kilosDisponiblesList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            List<TipoAlimentoToCajaRespon> resultado =
                    kilosDisponiblesList.stream()
                            .map(k -> kilosDisponiblesDtoConverter.kilosDisponiblesToAlimento(k))
                            .collect(Collectors.toList());

            return ResponseEntity.ok(resultado);
        }
    }

    @Operation(summary = """
            Obtiene un alimento con su cantidad disponible y muestra una lista 
            de los detalles de aportación de dicho alimento
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = """
                            Muestra un alimento con su cantidad y detalles de aportación.
                            """,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = KilosDisponiblesRespo.class),
                            examples = {@ExampleObject(
                                    value = """
                                           {"nombre":"Patatas"
                                           ,"cantidadDisponible":7.0,
                                           "detalleAportacionList":null}                                     
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado un alimento con este Id",
                    content = @Content)
    })
    @GetMapping("/{idTipoAlimento}")
    public ResponseEntity<KilosDisponiblesRespo> findByIdTipoAlimento(
            @PathParam("idTipoAlimento")
            @Parameter(description = "Id del tipo de alimento")
                                                              @PathVariable("idTipoAlimento") Long id) {
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoService.findById(id);
        if (tipoAlimento.isPresent()) {
            return ResponseEntity.ok(kilosDisponiblesDtoConverter.kilosDtoKilosResponse(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
