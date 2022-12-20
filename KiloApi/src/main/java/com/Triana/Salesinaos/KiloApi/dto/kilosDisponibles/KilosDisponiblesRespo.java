package com.Triana.Salesinaos.KiloApi.dto.kilosDisponibles;

import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class KilosDisponiblesRespo {
    private String nombre;
    private double cantidadDisponible;

    private List<DetalleAportacion> detalleAportacionList = new ArrayList<>();
}
