package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.dto.caja.CajaResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DestinatarioDetalleResponse {

    private Long id;
    private String nombre, direccion, personaContacto, telefono;
    private List<CajaResponse> cajaResponse = new ArrayList<>();


}
