package com.Triana.Salesinaos.KiloApi.dto.clase;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import lombok.Builder;

@Builder
public record ClaseDto (Long id, String nombre, String tutor){


    public static ClaseDto of(Clase clase){
        return ClaseDto.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .tutor(clase.getTutor())
                .build();
    }
}
