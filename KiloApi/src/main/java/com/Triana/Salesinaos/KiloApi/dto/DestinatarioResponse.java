package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DestinatarioResponse {

    private Long id;
    private String nombre, direccion, personaContacto, telefono;
    private Long numCajas;
    private double kgTotales;


}

