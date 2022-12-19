package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CajaDtoConverter {

    public Caja CreateCajaDtoToCaja(CreateCajaDto c){

        return Caja.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();
    }

    public Caja CreateCajaDtoToCajaResponse(CajaResponse c){

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
    public CajaResponse createCajaToCajaResponse(Caja c){
        return CajaResponse.builder()
                .id(c.getId())
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .kilosTotales(c.getKilosTotales())
                .destinatario(c.getDestinatario())
                .build();

    }

    public CajaResponse createCajaToCajaResponse(CreateCajaDto c){
        return CajaResponse.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();

    }


}
