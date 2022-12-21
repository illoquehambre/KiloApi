package com.Triana.Salesinaos.KiloApi.dto.aportacion;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Map;

@Builder
public record AportacionClassPairDto (LocalDate fecha, Map<String, Double> detallesAportacion){
}
