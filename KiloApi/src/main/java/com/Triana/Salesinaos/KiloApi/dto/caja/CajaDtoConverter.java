package com.Triana.Salesinaos.KiloApi.dto.caja;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaDto;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.CajaService;
import com.Triana.Salesinaos.KiloApi.service.TieneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CajaDtoConverter {

    private final CajaService cajaService;
    private final TieneService tieneService;
    public CajaResponsePost CreateCajaToCajaResponsePost(Caja c) {
        TipoAlimentoToCajaDto tipoAlimentoCajaDto = new TipoAlimentoToCajaDto();
        List<TipoAlimentoToCajaDto> listadoA = new ArrayList<>();
        for (Tiene listado : c.getTieneList()) {
            tipoAlimentoCajaDto.setId(listado.getTipoAlimmento().getId());
            tipoAlimentoCajaDto.setNombre(listado.getTipoAlimmento().getNombre());
            tipoAlimentoCajaDto.setKgCantidad(listado.getCantidadKgs());
            listadoA.add(tipoAlimentoCajaDto);
        }

        return CajaResponsePost.builder()
                .id(c.getId())
                .qr(c.getQr())
                .numCaja(c.getNumCaja())
                .kilosTotales(c.getKilosTotales())
                .destinatarioNombre(
                        c.getDestinatario() == null ?
                                "No asignado" : c.getDestinatario().getNombre())
                .tipoAlimentoToCajaDtoList(listadoA)
                .build();
    }

    public Caja CreateCajaDtoToCaja(CreateCajaDto c) {

        return Caja.builder()
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .build();
    }

    public CajaResponseCreate createCajaToCajaResponse(Caja c) {
        return CajaResponseCreate.builder()
                .id(c.getId())
                .numCaja(c.getNumCaja())
                .qr(c.getQr())
                .kilosTotales(c.getKilosTotales())
                .destinatario(c.getDestinatario())
                .build();
    }


}
