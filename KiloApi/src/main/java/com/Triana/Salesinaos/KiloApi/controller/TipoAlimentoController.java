package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoDto;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.AportacionService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipoalimento")
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@Tag(name = "Tipo Alimento", description = "Esta clase implementa Restcontrollers para la entidad Tipo Alimento")
public class TipoAlimentoController {

    private final TipoAlimentoService tipoAlimentoService;
    private final AportacionService aportacionService;


    @Operation(summary = "Este método lista todos tipos de alimentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado al menos un tipo de alimento",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimentoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "nombre": "cerveza",
                                                    "kilosDisponibles": 7.0
                                                },
                                                {
                                                    "id": 4,
                                                    "nombre": "patatas",
                                                    "kilosDisponibles": 0.0
                                                }
                                            ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún tipo de alimento",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity <List<TipoAlimentoDto>> findAll() {
        List<TipoAlimento> tipoAlimentoList = tipoAlimentoService.findAll();

        if (tipoAlimentoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity
                    .ok(tipoAlimentoList.stream().map(TipoAlimentoDto::of).toList()); //.collect(Collectors.toList())
        }
    }


    @Operation(summary = "Este método lista un tipo de alimento si lo localiza por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado el tipo de alimento buscado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimentoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "cerveza",
                                                "kilosDisponibles": 7.0
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún tipo de alimento con ese id",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoAlimentoDto> getOneTipoAlimento (
            @Parameter(description = "Id del tipo de alimento que se quiere encontrar", name = "id", required = true)
            @PathVariable Long id){
        if (tipoAlimentoService.findById(id).isEmpty() || id == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(TipoAlimentoDto.of(tipoAlimentoService.findById(id).get()));
    }



    @Operation(summary = "Crea un nuevo tipo de alimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Tipo de alimento creado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimento.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                    "id": 1,
                                                    "nombre": "cerveza",
                                                    "kilosDisponibles": 0.0
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear un alimento",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<TipoAlimentoDto> createTipoAlimento(@RequestBody TipoAlimentoDto tipoAlimento){//Debe retornar un Dto
        if(tipoAlimento.nombre().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(TipoAlimentoDto.of(tipoAlimentoService.add(tipoAlimentoService.toTipoAlimento(tipoAlimento))));
        
    }

    @Operation(summary = "Este método edita un tipo de alimento si es localizado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado el tipo de alimento",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimentoDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "aceitunas",
                                                "kilosDisponibles": 7.0
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No se han introducido bien los datos requeridos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TipoAlimentoDto> editTipoAlimento(
            @Parameter(description = "Id del tipo de alimento que se quiera editar", name = "id", required = true)
            @RequestBody TipoAlimentoDto tipoAlimentoDtoRequest,
            @PathVariable Long id) {
        if(tipoAlimentoService.existById(id) && !tipoAlimentoDtoRequest.nombre().isBlank()) {
            return ResponseEntity.of(tipoAlimentoService.findById(id).map(oldTipoAlimento -> {
                oldTipoAlimento.setNombre(tipoAlimentoDtoRequest.nombre());
                return TipoAlimentoDto.of(tipoAlimentoService.edit(oldTipoAlimento));
            }));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }



    @Operation(summary = "Elimina un tipo de alimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Tipo de alimento eliminado",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TipoAlimento.class))
                    )}),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<TipoAlimento> deleteTipoAlimento(@PathVariable Long id){
        if(tipoAlimentoService.existById(id)&& aportacionService.findByTipoAlimentoId(id).isEmpty())
            tipoAlimentoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
