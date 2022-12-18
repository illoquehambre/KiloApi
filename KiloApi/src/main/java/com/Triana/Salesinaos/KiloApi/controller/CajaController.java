package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.TieneDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.TieneResponse;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caja")
public class CajaController {
    private final CajaService cajaService;

    private final TieneDtoConverter tieneDtoConverter;

    @PostMapping("/{id}/tipo/{idTipoAlimento}/{cantidad}")
    public ResponseEntity<TieneResponse> add(@PathVariable Long id,
                                             @PathVariable Long idTipoAlimento,
                                             @PathVariable double cantidad) {
        return ResponseEntity.ok(tieneDtoConverter.tieneToTieneResponse(id, idTipoAlimento,cantidad));
    }
}
