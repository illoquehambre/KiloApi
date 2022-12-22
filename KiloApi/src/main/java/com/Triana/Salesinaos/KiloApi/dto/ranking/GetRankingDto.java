package com.Triana.Salesinaos.KiloApi.dto.ranking;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetRankingDto {
    private Long id;
    private String nombre;
    private double cantidadKg;
    private double media;
    private long numAportaciones;



}
