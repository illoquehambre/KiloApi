package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.CajaDtoConverter;
import com.Triana.Salesinaos.KiloApi.dto.CreateCajaDto;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caja")
public class CajaController {

    private final CajaService cajaService;
    private final CajaDtoConverter cajaDtoConverter;

    @PostMapping("/")
    public ResponseEntity<Caja> addCaja(@RequestBody CreateCajaDto c){
        if (c.getNumCaja() != ""){
            Caja nuevo = cajaDtoConverter.CreateCajaDtoToCaja(c);
            cajaService.add(nuevo);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
