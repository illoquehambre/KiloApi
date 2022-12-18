package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caja")
public class CajaController {
    private final CajaService cajaService;


}
