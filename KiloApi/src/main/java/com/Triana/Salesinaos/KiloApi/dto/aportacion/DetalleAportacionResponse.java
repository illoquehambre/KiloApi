package com.Triana.Salesinaos.KiloApi.dto.aportacion;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import lombok.Builder;

@Builder
public record DetalleAportacionResponse(int numLinea, String tipoAlimento, double kilos ) {

    public static DetalleAportacionResponse of(DetalleAportacion detalleAportacion){
        return DetalleAportacionResponse.builder()
                .numLinea(detalleAportacion.getId().getNumLinea())
                .tipoAlimento(detalleAportacion.getTipoAlimento().getNombre())
                .kilos(detalleAportacion.getCantidadEnKilos())
                .build();
    }
}
