package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Tiene;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CajaResponse {
    private String numCaja;
    private double kgTotales;
    private List<Tiene> tienes = new ArrayList<>();
}
