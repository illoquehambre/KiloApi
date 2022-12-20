package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KilosDisponiblesDto {

    public TipoAlimentoToCajaDto kilosDisponiblesToAlimento(KilosDisponibles k) {
        return TipoAlimentoToCajaDto.builder()
                .id(k.getId())
                .nombre(k.getTipoAlimento().getNombre())
                .kgCantidad(k.getTipoAlimento().getKilosDisponibles().getCantidadDisponible())
                .build();
    }

   


}
