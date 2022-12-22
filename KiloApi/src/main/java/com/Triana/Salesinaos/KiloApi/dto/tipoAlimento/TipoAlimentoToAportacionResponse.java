package com.Triana.Salesinaos.KiloApi.dto.tipoAlimento;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TipoAlimentoToAportacionResponse {
    private String nombre;
    private double kilosDisponibles;
    private List<Aportacion> aportacionList = new ArrayList<>();


}
