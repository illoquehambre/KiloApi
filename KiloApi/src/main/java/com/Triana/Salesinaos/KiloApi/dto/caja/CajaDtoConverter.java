package com.Triana.Salesinaos.KiloApi.dto.caja;

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

    public CreateCajaDto createCajaToCajaDto(Caja c){
        return CreateCajaDto.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();

    }

}
