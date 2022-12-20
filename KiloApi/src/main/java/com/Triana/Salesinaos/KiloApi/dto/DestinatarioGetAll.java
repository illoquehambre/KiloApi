package com.Triana.Salesinaos.KiloApi.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DestinatarioGetAll {
    private Long id;
    private String nombre, direccion, personaContacto, telefono;
    private List<String> cajasAsignadas;
    private double kgTotales;


}
