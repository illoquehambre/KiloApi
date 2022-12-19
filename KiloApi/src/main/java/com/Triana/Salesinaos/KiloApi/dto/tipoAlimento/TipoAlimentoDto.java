package com.Triana.Salesinaos.KiloApi.dto.tipoAlimento;

import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import lombok.Builder;
/***HECHO POR ROGE PERO HE TENIDO QUE MODIFICARLO POR CONFLICTO**/
@Builder
public record TipoAlimentoDto(Long id, String nombre) {
    public static TipoAlimentoDto of(TipoAlimento tipoAlimento){
        return TipoAlimentoDto.builder()
                .id(tipoAlimento.getId())
                .nombre(tipoAlimento.getNombre())
                .build();
    }
}
