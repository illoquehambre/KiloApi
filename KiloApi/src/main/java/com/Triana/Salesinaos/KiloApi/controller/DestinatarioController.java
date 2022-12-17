package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.DestinatarioService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinatario")
public class DestinatarioController {

    private final DestinatarioService destinatarioService;

    @Operation(summary = "Crea un nuevo destinatario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Crea un nuevo destinatario otorgandole los atributos necesarios para ello",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Destinatario.class),
                            examples = {@ExampleObject(
                                    value = """
                                                                                        
                                            {
                                            "id" : "",
                                            "nombre" : "Maylor",
                                            "direccion": "Avenida Alvar Núñez",
                                            "personaContacto": "Maylor David",
                                            "telefono": "489846984"
                                            }                                                                                        
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear un Destinatario, datos mal introducidos",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Destinatario> created(@RequestBody Destinatario d) {

        if (d.getNombre().isEmpty() && d.getDireccion().isEmpty() && d.getPersonaContacto().isEmpty()
                && d.getTelefono().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(destinatarioService.add(d));
    }


}
