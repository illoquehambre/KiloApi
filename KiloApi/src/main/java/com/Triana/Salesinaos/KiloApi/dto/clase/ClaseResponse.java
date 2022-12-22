package com.Triana.Salesinaos.KiloApi.dto.clase;

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

}
