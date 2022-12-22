package com.Triana.Salesinaos.KiloApi.dto.caja;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaRespon;
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
    private List<TipoAlimentoToCajaRespon> alimentos = new ArrayList<>();

}
