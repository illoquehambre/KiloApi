package com.Triana.Salesinaos.KiloApi.dto.destinatario;

import lombok.*;


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

