package com.Triana.Salesinaos.KiloApi.dto.caja;

import com.Triana.Salesinaos.KiloApi.model.Caja;
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
    public Caja CreateCajaDtoToCaja(CreateCajaDto c){

        return Caja.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();
    }



    public CreateCajaDto createCajaToCajaDto(Caja c){
        return CreateCajaDto.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();

    }
    public CajaResponseCreate createCajaToCajaResponse(Caja c){
        return CajaResponseCreate.builder()
                .id(c.getId())
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .kilosTotales(c.getKilosTotales())
                .destinatario(c.getDestinatario())
                .build();

    }
//Este metodo esta duplicado con el llamado CreateCajaDtoToCaja pd:Nacho :D
   /* public Caja createCajaDtoToCajaResponse(CreateCajaDto c){
        return Caja.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();

    }
*/

}
