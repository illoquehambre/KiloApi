package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionResponse;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.service.AportacionService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/aportacion")

@RequiredArgsConstructor
@Tag(name = "Aportacion", description = "Esta clase implementa Restcontrollers para la entidad Aportacion")
public class AportacionController {

    private final AportacionService aportacionService;
    private final TipoAlimentoService tipoAlimentoService;

    @PostMapping("/")
    public ResponseEntity<AportacionResponse> createAportcion(@RequestBody CreateAportacion create){
        AtomicReference<Boolean> comprobarId= new AtomicReference<>(true);
        create.listadoDetallesAportacion().forEach(createDetalleAportacion -> {
            if ((createDetalleAportacion.tipoAlimentoId()==null || !(tipoAlimentoService.existById(createDetalleAportacion.tipoAlimentoId()))))
                comprobarId.set(false);
        });
        if(!(create.claseId()==null || create.listadoDetallesAportacion()==null || create.listadoDetallesAportacion().isEmpty() || !comprobarId.get()))

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(AportacionResponse.of(aportacionService.add(aportacionService.toAportacion(create))));
        else
            return ResponseEntity.badRequest().build();
    }

}
