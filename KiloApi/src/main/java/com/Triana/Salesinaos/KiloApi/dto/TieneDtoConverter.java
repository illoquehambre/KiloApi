package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TieneDtoConverter {

    private final CajaService cajaService;

    public TieneResponse tieneToTieneResponse(Long id, Long idTipoAlimento, double cantidad ){

        Tiene t = new Tiene();
        return TieneResponse.builder()
                .caja(cajaService.findById(id).get())
                .tieneList(cajaService.findById(id).get().getTieneList())
                .build();
    }

}
