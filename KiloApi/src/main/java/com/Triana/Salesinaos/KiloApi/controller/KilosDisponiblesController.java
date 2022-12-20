package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.KilosDisponiblesDto;
import com.Triana.Salesinaos.KiloApi.dto.TipoAlimentoToCajaDto;
import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.KilosDisponiblesService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kilosDisponibles")
@RequiredArgsConstructor
public class KilosDisponiblesController {

    private final KilosDisponiblesService kilosDisponiblesService;

    private final KilosDisponiblesDto kilosDisponiblesDto;

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
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimentoToCajaDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {{"id": 1, "nombre": "Pasta"}, "cantidadDisponible" : "5"},
                                                {{"id": 2, "nombre": "Aceite"}, "cantidadDisponible" : "15}",
                                                {{"id": 3, "nombre": "Arroz"}, "cantidadDisponible" : "25"},
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "La lista se encuentra vacía",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<TipoAlimentoToCajaDto>> findAll() {
        List<KilosDisponibles> kilosDisponiblesList = kilosDisponiblesService.findAll();

        if (kilosDisponiblesList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            List<TipoAlimentoToCajaDto> resultado =
                    kilosDisponiblesList.stream()
                            .map(k -> kilosDisponiblesDto.kilosDisponiblesToAlimento(k))
                            .collect(Collectors.toList());
            return ResponseEntity.ok(resultado);
        }
    }


    @GetMapping("/{idTipoAlimento}")
    public ResponseEntity<List<?>>findById(@PathVariable("idTipoAlimento") Long id){
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoService.findById(id);
        if (tipoAlimento.isPresent()){

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }





}
