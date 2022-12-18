package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.CajaDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.ClaseDto;
import com.Triana.Salesinaos.KiloApi.dto.CreateCajaDto;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caja")
public class CajaController {


    private final CajaService cajaService;

    private final CajaDtoConverter cajaDtoConverter;

    @Operation(summary = "Este método crea una caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva caja",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = @ExampleObject(value = """
                                            {
                                                "id": 1,
                                                "qr": "122234",
                                                "numCaja": "18",
                                                "kilosTotales": 0.0,
                                                "destinatario": null
                                            }
                                    """)) }),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido correctamente los datos de caja",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Caja> addCaja(@RequestBody CreateCajaDto c){
        if (c.getNumCaja() != ""){
            Caja nuevo = cajaDtoConverter.CreateCajaDtoToCaja(c);
            cajaService.add(nuevo);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
