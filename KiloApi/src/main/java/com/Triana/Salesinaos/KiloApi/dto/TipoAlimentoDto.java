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
}
