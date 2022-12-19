package com.Triana.Salesinaos.KiloApi.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class TipoAlimentoDto {
    private Long id;
    private String nombre;
    private double cantidad;

    /**
     public record TipoAlimentoDto (Long id, String nombre) {
     public static TipoAlimentoDto of(TipoAlimento tipoAlimento){
     return TipoAlimentoDto.builder()
     .id(tipoAlimento.getId())
     .nombre(tipoAlimento.getNombre())
     .build();
     }*/
}
