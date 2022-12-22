package com.Triana.Salesinaos.KiloApi.dto.aportacion;
import lombok.Builder;
import java.time.LocalDate;


@Builder
public record AportacionListResponse (LocalDate fecha, String nombreClase, double kilosTotales){
}
