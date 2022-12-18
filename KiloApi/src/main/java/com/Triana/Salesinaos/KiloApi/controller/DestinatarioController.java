package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.DestinatarioDetalleResponse;
import com.Triana.Salesinaos.KiloApi.dto.DestinatarioDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.DestinatarioResponse;
import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import com.Triana.Salesinaos.KiloApi.service.DestinatarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinatario")
public class DestinatarioController {

    private final DestinatarioService destinatarioService;

    private final DestinatarioDtoConverter destinatarioDtoConverter;

    private final CajaService cajaService;

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


    @Operation(summary = "Edita un destinatario ya creado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Edita los datos de un destinatario ya creado",
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
                    description = "No se ha podido editar el destinatario, datos mal introducidos",
                    content = @Content),

            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún destinatario con este Id",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Destinatario> edit
            (@RequestBody Destinatario d,
             @PathVariable Long id) {
        Optional<Destinatario> d1 = destinatarioService.findById(id);
        if (destinatarioService.findById(id).isPresent()) {
            if (d.getNombre().isEmpty() && d.getDireccion().isEmpty() && d.getPersonaContacto().isEmpty()
                    && d.getTelefono().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.of(d1.map(old -> {
                old.setNombre(d.getNombre());
                old.setDireccion(d.getDireccion());
                old.setTelefono(d.getTelefono());
                old.setPersonaContacto(d.getPersonaContacto());

                destinatarioService.edit(old);
                return old;
            }));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    @Operation(summary = "Elimina un destinatario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Ya se ha eliminado el destinatario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Destinatario.class)
                    )})
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Destinatario> d1 = destinatarioService.findById(id);
        if (d1.isPresent())
            destinatarioService.delete(d1.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Mostrar destinatario con detalles fuera de sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el destinatario.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Destinatario.class),
                            examples = {@ExampleObject(
                                    value = """
                                                                                        
                                            {
                                            "id" : "1",
                                            "nombre" : "Maylor",
                                            "direccion": "Avenida Alvar Núñez",
                                            "personaContacto": "Maylor David",
                                            "telefono": "489846984",
                                            "numCajas": "4",
                                            "kgTotales": "9"
                                            }                                                                                        
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún destinatario con este Id",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<DestinatarioResponse> findById(@PathVariable Long id) {
        Optional<Destinatario> d1 = destinatarioService.findById(id);
        if (d1.isPresent()) {
            return ResponseEntity.ok(destinatarioDtoConverter.destinatarioToDestinatarioResponse(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<DestinatarioDetalleResponse> findByIdDetalle(@PathVariable Long id) {
        Optional<Destinatario> d1 = destinatarioService.findById(id);
        if (d1.isPresent()) {
            return ResponseEntity.ok(destinatarioDtoConverter.destinatarioToDestinatarioDetalleResponse(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
