package com.Triana.Salesinaos.KiloApi.dto.kilosDisponibles;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaDto;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.AportacionRepository;
import com.Triana.Salesinaos.KiloApi.repository.KilosDisponiblesRepository;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class KilosDisponiblesDto {

    private final TipoAlimentoService tipoAlimentoService;
    private final AportacionRepository aportacionRepository;

    public TipoAlimentoToCajaDto kilosDisponiblesToAlimento(KilosDisponibles k) {
        return TipoAlimentoToCajaDto.builder()
                .id(k.getId())
                .nombre(k.getTipoAlimento().getNombre())
                .kgCantidad(k.getTipoAlimento().getKilosDisponibles().getCantidadDisponible())
                .build();
    }


    public KilosDisponiblesRespo kilosDtoKilosResponse(Long id) {
        List<DetalleAportacion> aux = new ArrayList<>();
        TipoAlimento tipoAlimento = tipoAlimentoService.findById(id).get();
        for (Aportacion p : aportacionRepository.findAll()) {
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
