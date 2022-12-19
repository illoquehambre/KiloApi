package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.CajaDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.CajaResponseCreate;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import lombok.RequiredArgsConstructor;
import com.Triana.Salesinaos.KiloApi.dto.CreateCajaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/caja")
public class CajaController {
    private final CajaService cajaService;
    private final CajaDtoConverter cajaDtoConverter;

    @PostMapping("/{id}/tipo/{IdtipoAlimento}/{cantidad}")
    public ResponseEntity<CajaResponseCreate> add2(@PathVariable Long id,
                                                   @PathVariable Long IdTipoAlimento,
                                                   @PathVariable double cantidad) {
        Optional<Caja> c = cajaService.findById(id);

        if (c.isPresent()) {
            if (!c.get().getTieneList().isEmpty()) {
                for (Tiene t : c.get().getTieneList()) {
                    if (t.getCaja().equals(c)) {
                        if (t.getTipoAlimmento().getId().equals(id)) {
                            t.setCantidadKgs(cantidad);

                        }
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(CajaResponseCreate.builder()
                    .id(c.get().getId())
                    .numCaja(c.get().getNumCaja())
                    .qr(c.get().getQr())
                    .destinatario(c.get().getDestinatario())
                    .kilosTotales(c.get().getKilosTotales())
                    .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Este método crea una caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado una nueva caja",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = @ExampleObject(value = """
                                            {
                                                "id": 1,
                                                "qr": "122234",
                                                "numCaja": "18",
                                                "kilosTotales": 0.0,
                                                "destinatario": null
                                            }
                                    """))}),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido correctamente los datos de caja",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Caja> addCaja(@RequestBody CreateCajaDto c) {
        if (c.getNumCaja() != "") {
            Caja nuevo = cajaDtoConverter.CreateCajaDtoToCaja(c);
            cajaService.add(nuevo);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @Operation(summary = "Este método muestra las cajas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado las cajas",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = @ExampleObject(value = """
                                        {
                                            "id": 2,
                                            "qr": "134",
                                            "numCaja": "18",
                                            "kilosTotales": 0.0,
                                            "destinatario": null
                                        }
                                    """
                            ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado niguna caja",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<Caja>> getCajas() {

        if (cajaService.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cajaService.findAll());
    }

    @Operation(summary = "Este método muestra una caja por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la caja",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = @ExampleObject(value = """
                                        {
                                            "id": 1,
                                            "qr": "134",
                                            "numCaja": "18",
                                            "kilosTotales": 0.0,
                                            "destinatario": null
                                        }
                                    """
                            ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado niguna caja con ese ID",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CajaResponseCreate> getCajasById(@PathVariable Long id) {
        if (cajaService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(cajaDtoConverter.createCajaToCajaResponse(cajaService.findById(id).get()));
    }
}

