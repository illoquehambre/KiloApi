package com.Triana.Salesinaos.KiloApi.controller;


import com.Triana.Salesinaos.KiloApi.dto.caja.CajaResponsePost;


import com.Triana.Salesinaos.KiloApi.dto.caja.CajaDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.caja.CreateCajaDto;
import com.Triana.Salesinaos.KiloApi.dto.caja.CajaResponseCreate;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.model.TienePK;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.TieneRepository;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import com.Triana.Salesinaos.KiloApi.service.TieneService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/caja")
@RestController
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@Tag(name = "Caja", description = "Esta clase implementa Restcontrollers para la entidad Caja")
public class CajaController {
    private final CajaService cajaService;
    private final TipoAlimentoService tipoAlimentoService;
    private final CajaDtoConverter cajaDtoConverter;
    private final TieneService tieneService;

    private final TieneRepository tieneRepository;

    @Operation(summary = "Actualizar la cantidad de kg de la caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha actializado los kg que contiene la caja",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caja.class),
                            examples = @ExampleObject(value = """
                                            {
                                             "id" : "1",
                                             "qr": "Link",
                                             "numCaja":"123",
                                              "kilosTotales": "69",
                                              "destinatarioNombre":"Carlo",
                                              "tipoAlimentoToCajaDtoList" :
                                               [{"id": "1", "nombre": "Arróz", "kgCantidad": "8"}] 
                                            }
                                    """))}),
            @ApiResponse(responseCode = "400",
                    description = "No existe dicha caja o dicho alimento " +
                            "o posiblemente la cantidad sea menor que" +
                            " 0 o mayor que los kg de dicho alimento.",
                    content = @Content),
    })

    @PostMapping("/{id}/tipo/{idtipoAlimento}/{cantidad}")
    public ResponseEntity<CajaResponsePost> addCantidadToCaja(
            @PathParam("id, idTipoAlimento, cantidad")
            @Parameter(description = """
                    Id de la caja
                    Id del tipo de alimento al que le restaremos la
                    cantidad que se le va a añadir a la caja de la cantidad de kg totales,
                    cantidad de kg para añadirle a la caja de los kilos totales que posee dicha caja.
                     """)
            @PathVariable("id") Long id,
            @PathVariable("idtipoAlimento") Long idTipoAlimento,
            @PathVariable("cantidad") double cantidad) {
        /**Obtenemos la caja por el id y obtenemos el TipoAlimento por su id**/
        Optional<Caja> c = cajaService.findById(id);
        Optional<TipoAlimento> t = tipoAlimentoService.findById(idTipoAlimento);
        TienePK tienePK = new TienePK(t.get().getId(), c.get().getId());
        Optional<Tiene> aux = tieneRepository.findById(tienePK);
        /**COMPROBAMOMS SI EXISTE LA CAJA, EL TIPO ALIMENTO**/
        if (c.isEmpty() || t.isEmpty()) {
            if (aux.isEmpty()) {
                TienePK tienePK1 = new TienePK(t.get().getId(), c.get().getId());
                Tiene tiene = Tiene.builder()
                        .id(tienePK1)
                        .build();
                tiene.addToCajaToTipo(c.get(), t.get());
                tieneRepository.save(tiene);
                cajaService.edit(c.get());
            }
            if (cantidad > 0 && cantidad < t.get().getKilosDisponibles().getCantidadDisponible()) {
                c.get().setKilosTotales(c.get().getKilosTotales() + cantidad);
                aux.get().setCantidadKgs(aux.get().getCantidadKgs() + cantidad);
                t.get().getKilosDisponibles().setCantidadDisponible(t.get().getKilosDisponibles().getCantidadDisponible() - cantidad);
                cajaService.edit(c.get());
                tieneRepository.save(aux.get());
                tipoAlimentoService.edit(t.get());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(cajaDtoConverter.CreateCajaToCajaResponsePost(c.get()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }

    @Operation(summary = "Actualiza la cantidad de kg de la caja")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado la caja",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caja.class),
                            examples = @ExampleObject(value = """
                                            {
                                            
                                            }
                                    """))}),
            @ApiResponse(responseCode = "400",
                    description = "No existe dicha caja o dicho alimento " +
                            "o posiblemente la cantidad sea menor que" +
                            " 0 o mayor que los kg de dicho alimento.",
                    content = @Content),
    })
    @PutMapping("/{id}/tipo/{IdtipoAlimento}/{cantidad}")
    public ResponseEntity<CajaResponsePost> editCantidadToCaja(@PathVariable("id") Long id, @PathVariable("IdtipoAlimento") Long IdTipoAlimento,
                                                               @PathVariable("cantidad") double cantidad){
        Optional<Caja> caja = cajaService.findById(id);
        Optional<TipoAlimento> tipoAlimento = tipoAlimentoService.findById(IdTipoAlimento);

        if (caja.isPresent() && tipoAlimento.isPresent() && !caja.get().getTieneList().isEmpty()){
            TienePK tienePK = new TienePK(id, IdTipoAlimento);
            Optional<Tiene> tiene = tieneService.findById(tienePK);
            if (tiene.isPresent()){
                if (cantidad > 0 && tipoAlimento.get().getKilosDisponibles().getCantidadDisponible()>=cantidad){
                    caja.get().setKilosTotales(caja.get().getKilosTotales() + cantidad);
                    tiene.get().setCantidadKgs(tiene.get().getCantidadKgs() + cantidad);
                    tipoAlimento.get().getKilosDisponibles()
                            .setCantidadDisponible(tipoAlimento.get()
                                    .getKilosDisponibles()
                                    .getCantidadDisponible() - cantidad);
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(cajaDtoConverter
                                    .CreateCajaToCajaResponsePost(caja.get()));
                }
                if (cantidad < 0 && tipoAlimento.get().getKilosDisponibles().getCantidadDisponible()>=cantidad){
                    caja.get().setKilosTotales(caja.get().getKilosTotales() + cantidad);
                    tiene.get().setCantidadKgs(tiene.get().getCantidadKgs() + cantidad);
                    tipoAlimento.get().getKilosDisponibles()
                            .setCantidadDisponible(tipoAlimento.get()
                                    .getKilosDisponibles()
                                    .getCantidadDisponible() - cantidad);
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(cajaDtoConverter
                                    .CreateCajaToCajaResponsePost(caja.get()));
                }
                if (cantidad==0){
                    tipoAlimentoService.deleteById(IdTipoAlimento);
                    return ResponseEntity.status(HttpStatus.OK).build();

                }

            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
    public ResponseEntity<CajaResponseCreate> addCaja(@RequestBody CreateCajaDto c) {
        if (c.getNumCaja() != "") {
            Caja nuevo = cajaDtoConverter.CreateCajaDtoToCaja(c);
            cajaService.add(nuevo);

            return ResponseEntity.status(HttpStatus.CREATED).body(cajaDtoConverter.createCajaToCajaResponse(nuevo));

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

    @PutMapping("/{id}")
    public ResponseEntity<CajaResponseCreate> editCaja(@RequestBody CreateCajaDto c, @PathVariable Long id) {

        if (c.getNumCaja().isEmpty() || c.getQr().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Caja nueva = cajaService.findById(id).get();
        nueva.setNumCaja(c.getNumCaja());
        nueva.setQr(c.getQr());
        cajaService.edit(nueva);
        return ResponseEntity.status(HttpStatus.OK).body(cajaDtoConverter.createCajaToCajaResponse(nueva));


    }


    @Operation(summary = "Elimina una caja mediante el id que le pasemos como parámetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado la caja",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Caja.class))})
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathParam("id") @Parameter(description = "Id de la caja a borrar")
                                    @PathVariable Long id) {
        Optional<Caja> caja = cajaService.findById(id);
        if (caja.isPresent()) {
            cajaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

