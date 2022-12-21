package com.Triana.Salesinaos.KiloApi.dto.aportacion;

import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public record AportacionResponse(Long id,Long claseId,LocalDate fecha, List<DetalleAportacionResponse> detallesAportacion) {//Preguntar a Luismi si deberia usarDto de detalleAportacion
    public static AportacionResponse of(Aportacion aportacion){

        List<DetalleAportacionResponse> detallesResponse = new ArrayList<>();
        aportacion.getDetalleAportacionList().forEach(detalleAportacion -> {
            detallesResponse.add(DetalleAportacionResponse.of(detalleAportacion));
        });
        return AportacionResponse.builder()
                .id(aportacion.getId())
                .claseId(aportacion.getId())
                .detallesAportacion(detallesResponse)
                .fecha(aportacion.getFecha())
                .build();
    }
}
