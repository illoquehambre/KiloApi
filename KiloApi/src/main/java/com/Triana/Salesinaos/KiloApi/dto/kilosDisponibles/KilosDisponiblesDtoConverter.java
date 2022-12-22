package com.Triana.Salesinaos.KiloApi.dto.kilosDisponibles;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaRespon;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.AportacionService;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class KilosDisponiblesDtoConverter {

    private final TipoAlimentoService tipoAlimentoService;
    private final AportacionService aportacionService;

    public TipoAlimentoToCajaRespon kilosDisponiblesToAlimento(KilosDisponibles k) {
        return TipoAlimentoToCajaRespon.builder()
                .id(k.getId())
                .nombre(k.getTipoAlimento().getNombre())
                .kgCantidad(k.getTipoAlimento().getKilosDisponibles().getCantidadDisponible())
                .build();
    }


    public KilosDisponiblesRespo kilosDtoKilosResponse(Long id) {
        List<DetalleAportacion> aux = new ArrayList<>();
        TipoAlimento tipoAlimento = tipoAlimentoService.findById(id).get();
        for (Aportacion p : aportacionService.findAll()) {
            for (DetalleAportacion aD : p.getDetalleAportacionList()) {
                if (aD.getTipoAlimento().equals(tipoAlimento)) {
                    aux.add(aD);
                }
            }
        }
        return KilosDisponiblesRespo.builder()
                .nombre(tipoAlimento.getNombre())
                .cantidadDisponible(tipoAlimento.getKilosDisponibles().getCantidadDisponible())
                .detalleAportacionList(aux.isEmpty() ? null : aux)
                .build();
    }
}
