package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateDetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.*;
import com.Triana.Salesinaos.KiloApi.repository.AportacionRepository;
import com.Triana.Salesinaos.KiloApi.repository.KilosDisponiblesRepository;
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
    private final KilosDisponiblesService kilosDisponiblesService;
    private final ClaseService claseService;
    public Aportacion add(Aportacion aportacion) {
        return repository.save(aportacion);
    }


    public Optional<Aportacion> findById(Long id){return repository.findById(id);}
    public Aportacion toAportacion(CreateAportacion create) {

        Aportacion aportacion = Aportacion.builder()
                .clase(claseService.findById(create.claseId()).get())
                .detalleAportacionList(new ArrayList<>())
                .build();

        repository.save(aportacion);
    List<DetalleAportacion> detalleAportacionesList = new ArrayList<>();

                create.listadoDetallesAportacion().forEach(detalle -> {

                    //checkear si existe ya un kilos disponibles de ese tipo de alimento
                    //Si: Se añade a este
                    //No: Se crea uno nuevo kilosDisponibles y se añade

                    if(kilosDisponiblesService.existById(detalle.tipoAlimentoId()))
                        tipoAlimentoService.findById(detalle.tipoAlimentoId()).get().addKilosToTipoAlimento(kilosDisponiblesService.findById(detalle.tipoAlimentoId()).get(), detalle.kilos());
                    else
                        KilosDisponibles.builder()
                                .cantidadDisponible(detalle.kilos())
                                .tipoAlimento(tipoAlimentoService.findById(detalle.tipoAlimentoId()).get())
                                .id(detalle.tipoAlimentoId())
                                .build();



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



    }


}
