package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateDetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacionPK;
import com.Triana.Salesinaos.KiloApi.repository.AportacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AportacionService {
    private final AportacionRepository repository;
    private final TipoAlimentoService tipoAlimentoService;
    private final ClaseService claseService;
    public Aportacion add(Aportacion aportacion) {
        return repository.save(aportacion);
    }


    public Optional<Aportacion> findById(Long id){return repository.findById(id);}
    public Aportacion toAportacion(CreateAportacion create) {

        Aportacion aportacion = Aportacion.builder()
                .clase(claseService.findById(create.claseId()).get())//No se gestiona si no existe la clase
                .detalleAportacionList(new ArrayList<>())
                .build();

        repository.save(aportacion);

        //Hay que hacer un add y un save primero de aportacion pa que se genere su id y deje de petar
    List<DetalleAportacion> detalleAportacionesList = new ArrayList<>();

                create.listadoDetallesAportacion().forEach(detalle -> {
                    tipoAlimentoService.findById(detalle.tipoAlimentoId());

                    detalleAportacionesList.add(aportacion.addDetalleAportacion(
                            DetalleAportacion.builder()
                                    .id(new DetalleAportacionPK(aportacion.getId(), detalleAportacionesList.size()+1))
                                    .cantidadEnKilos(detalle.kilos())
                                    .aportacion(aportacion)
                                    .tipoAlimento((tipoAlimentoService.findById(detalle.tipoAlimentoId()).get()))//falta gestionar si el idAlimento no existe
                            .build()
                    ));

                        });

                return aportacion;

                   /* return DetalleAportacion.builder()
                            .id(new DetalleAportacionPK(aportacion.getId(),
                                    ))
                            .cantidadEnKilos(detalle.kilos())
                            .aportacion(aportacion)
                            .tipoAlimento((tipoAlimentoService.findById(detalle.tipoAlimentoId()).get()))//falta gestionar si el idAlimento no existe
                            .build();

                    )
                }
                ));*/




    }
}
