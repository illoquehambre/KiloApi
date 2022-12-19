package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaseResponse {

    private Long id;
    private String nombre;
    private String tutor;
    private int numAportaciones;
    private double kilosTotales;

   /* private final ClaseService service;
    public static ClaseResponse of (Clase c) {
        return ClaseResponse
                .builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .tutor(c.getTutor())
                .numAportaciones()
                .build();
    }*/
}
