package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TipoAlimentoController {

    private final TipoAlimentoService service;


    @Operation(summary = "Crea un nuevo tipo de alimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Tipo de alimento creado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {"id": 1, "nombre": "Pasta"},
                                                {"id": 2, "nombre": "Aceite"},
                                                {"id": 3, "nombre": "Arroz"}
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear un alimento",
                    content = @Content),
    })
    @PostMapping("/tipoAlimento/")
    public ResponseEntity<TipoAlimento> createTipoAlimento(@RequestBody String nombretipo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.findByName(nombretipo).get());
    }
}
