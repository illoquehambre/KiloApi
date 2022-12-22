package com.Triana.Salesinaos.KiloApi.dto.aportacion;

import lombok.*;
import java.util.List;

@Builder
public record CreateAportacion(Long claseId, List<CreateDetalleAportacion> listadoDetallesAportacion){
}
