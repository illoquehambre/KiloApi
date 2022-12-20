package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
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
