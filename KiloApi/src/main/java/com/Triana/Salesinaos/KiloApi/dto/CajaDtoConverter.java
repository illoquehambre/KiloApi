package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CajaDtoConverter {

    private final CajaService cajaService;
    private final TipoAlimentoService tipoAlimentoService;



}
