package com.Triana.Salesinaos.KiloApi.dto.caja;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CajaResponsePost {

    private Long id;
    private String qr, numCaja;
    private double kilosTotales;
    private String destinatarioNombre;
    private List<TipoAlimentoToCajaDto> tipoAlimentoToCajaDtoList = new ArrayList<>();

}
