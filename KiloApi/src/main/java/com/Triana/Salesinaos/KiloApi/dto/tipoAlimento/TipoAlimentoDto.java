package com.Triana.Salesinaos.KiloApi.dto.tipoAlimento;

import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
/***HECHO POR ROGE PERO HE TENIDO QUE MODIFICARLO POR CONFLICTO**/
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TipoAlimentoDto (Long id, String nombre, KilosDisponibles kilosDisponibles) {
    public static TipoAlimentoDto of(TipoAlimento tipoAlimento){
        return TipoAlimentoDto.builder()
                .id(tipoAlimento.getId())
                .nombre(tipoAlimento.getNombre())
                .kilosDisponibles(tipoAlimento.getKilosDisponibles())
                .build();
    }
}
