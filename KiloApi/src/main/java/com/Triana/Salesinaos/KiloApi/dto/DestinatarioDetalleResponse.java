package com.Triana.Salesinaos.KiloApi.dto;

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
    private List<CajaResponsePost> cajaResponsePosts = new ArrayList<>();


}
