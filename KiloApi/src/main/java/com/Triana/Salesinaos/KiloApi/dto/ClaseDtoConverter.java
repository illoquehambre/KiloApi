package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClaseDtoConverter {


    private final ClaseService service;



    public ClaseResponse ClaseToClaseResponse(Clase c) {
        return ClaseResponse.
        builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .tutor(c.getTutor())
                .numAportaciones(service.cantidadAportaciones())
                .kilosTotales(service.countKgs())
                .build();
    }
}
