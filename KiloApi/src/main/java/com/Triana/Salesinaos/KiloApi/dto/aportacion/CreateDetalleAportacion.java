package com.Triana.Salesinaos.KiloApi.dto.aportacion;

import lombok.*;


@Builder
public record CreateDetalleAportacion(Long tipoAlimentoId, double kilos){
}
