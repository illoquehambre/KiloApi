package com.Triana.Salesinaos.KiloApi.dto.tipoAlimento;

import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TipoAlimentoDto (Long id, String nombre, double kilosDisponibles) {
    public static TipoAlimentoDto of(TipoAlimento tipoAlimento){
        return TipoAlimentoDto.builder()
                .id(tipoAlimento.getId())
                .nombre(tipoAlimento.getNombre())
                .kilosDisponibles(tipoAlimento.getKilosDisponibles()==null?0:tipoAlimento.getKilosDisponibles().getCantidadDisponible())
                .build();
    }
}
