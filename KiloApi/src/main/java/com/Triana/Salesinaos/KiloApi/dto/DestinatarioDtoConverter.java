package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.service.DestinatarioService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DestinatarioDtoConverter {

    private final DestinatarioService destinatarioService;


    public DestinatarioResponse destinatarioToDestinatarioResponse(Long id) {
        Destinatario d = destinatarioService.findById(id).get();
        return DestinatarioResponse.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .telefono(d.getTelefono())
                .personaContacto(d.getPersonaContacto())
                .direccion(d.getDireccion())
                .numCajas(d.getCajas().stream().count())
                .kgTotales(destinatarioService.showKgTotal(d))
                .build();
    }

    public DestinatarioDetalleResponse destinatarioToDestinatarioDetalleResponse(Long id) {
        Destinatario d = destinatarioService.findById(id).get();
        return DestinatarioDetalleResponse.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .direccion(d.getDireccion())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .cajaResponse(null)
                .build();
    }



}
