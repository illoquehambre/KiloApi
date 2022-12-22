package com.Triana.Salesinaos.KiloApi.dto.tipoAlimento;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TipoAlimentoToCajaRespon {
    private Long id;
    private String nombre;
    private double kgCantidad;
}
