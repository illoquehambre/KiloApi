package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.service.DestinatarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinatario")
public class DestinatarioController {

    private final DestinatarioService destinatarioService;

    @PostMapping("/")
    public ResponseEntity<Destinatario> created(@RequestBody Destinatario d) {

        if (d.getNombre().isEmpty() && d.getDireccion().isEmpty() && d.getPersonaContacto().isEmpty()
                && d.getTelefono().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(destinatarioService.add(d));
    }

}
