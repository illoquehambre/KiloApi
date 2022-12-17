package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder

public record ClaseDto (Long id, String nombre, String tutor){
    public ClaseDto {
        Objects.requireNonNull(id);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(tutor);
    }

    public static ClaseDto of(Clase clase){
        return ClaseDto.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .tutor(clase.getTutor())
                .build();
    }
}
