package com.Triana.Salesinaos.KiloApi.dto.tipoAlimento;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TipoAlimentoToCajaDto {
    private Long id;
    private String nombre;
    private double kgCantidad;
}
