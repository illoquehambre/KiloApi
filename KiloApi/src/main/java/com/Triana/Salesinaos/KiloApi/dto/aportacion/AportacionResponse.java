package com.Triana.Salesinaos.KiloApi.dto.aportacion;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;

import lombok.Builder;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public record AportacionResponse(Long id,Long claseId,LocalDate fecha, List<DetalleAportacionResponse> detallesAportacion) {
    public static AportacionResponse of(Aportacion aportacion){

        List<DetalleAportacionResponse> detallesResponse = new ArrayList<>();
        aportacion.getDetalleAportacionList().forEach(detalleAportacion -> {
            detallesResponse.add(DetalleAportacionResponse.of(detalleAportacion));
        });
        return AportacionResponse.builder()
                .id(aportacion.getId())
                .claseId(aportacion.getClase()!=null?aportacion.getClase().getId():null)
                .detallesAportacion(detallesResponse)
                .fecha(aportacion.getFecha())
                .build();
    }
}
