package com.Triana.Salesinaos.KiloApi.dto.caja;

import com.Triana.Salesinaos.KiloApi.model.Tiene;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CajaResponse {
    private String numCaja;
    private double kgTotales;
    private List<Tiene> tienes = new ArrayList<>();
}
