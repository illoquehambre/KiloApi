package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class CajaDtoConverter {
    private final CajaService cajaService;
  public CajaResponseCreate createCajaToCajaResponseCreate(Long id){
      Caja c = cajaService.findById(id).get();
      return CajaResponseCreate.builder()
              .id(c.getId())
              .qr(c.getQr())
              .numCaja(c.getNumCaja())
              .kilosTotales(c.getKilosTotales())
              .destinatario(c.getDestinatario())

              .build();
  }
}
