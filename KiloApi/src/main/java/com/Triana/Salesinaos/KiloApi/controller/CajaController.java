package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.CajaDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.CajaResponseCreate;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public ResponseEntity<List<Caja>> cajaOne() {
        return ResponseEntity.ok(cajaService.findAll());
    }
}
