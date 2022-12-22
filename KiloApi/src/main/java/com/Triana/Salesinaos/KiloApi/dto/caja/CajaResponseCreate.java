package com.Triana.Salesinaos.KiloApi.dto.caja;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CajaResponseCreate {
    private Long id;
    private String qr, numCaja;
    private double kilosTotales;
    private Destinatario destinatario;

}
