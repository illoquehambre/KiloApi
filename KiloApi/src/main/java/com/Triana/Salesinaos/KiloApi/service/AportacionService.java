package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionClassPairDto;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.AportacionListResponse;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateAportacion;
import com.Triana.Salesinaos.KiloApi.dto.aportacion.CreateDetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.*;
import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacionPK;
import com.Triana.Salesinaos.KiloApi.repository.AportacionRepository;
import com.Triana.Salesinaos.KiloApi.repository.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public List<DetalleAportacion> findByTipoAlimentoId(Long id) { return repository.findByTipoAlimento(id); }
    /*
    public String findNombreClaseById(Long id) {
        return claseService.findById(id).get().getNombre();
    }
    */
    public double sumKilosByAportacion(Long id) {
        return repository.sumaKilosAportacion(id);
    }

    public Optional<DetalleAportacion> findFirstDetalleAportacionById(int id){return repository.findFirstDetalleAportacionById(id);}

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
                    else{
                       KilosDisponibles k= KilosDisponibles.builder()
                                                    .tipoAlimento(tipoAlimentoService.findById(detalle.tipoAlimentoId()).get())
                                                    .id(detalle.tipoAlimentoId())
                                                    .build();
                        tipoAlimentoService.findById(detalle.tipoAlimentoId()).get().addKilosToTipoAlimento(k, detalle.kilos());
                        tipoAlimentoService.add(tipoAlimentoService.findById(detalle.tipoAlimentoId()).get());
                        kilosDisponiblesService.add(k);
                    }

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


    public AportacionListResponse toAportacionListReponse (Aportacion aportacion){
        return AportacionListResponse.builder()
                .fecha(aportacion.getFecha())
                .nombreClase(aportacion.getClase() != null ? aportacion.getClase().getNombre() : null)
                .kilosTotales(this.sumKilosByAportacion(aportacion.getId()))
                .build();
    }


    public List<Aportacion> findAll (){
        return repository.findAll();
    }


    public List<AportacionClassPairDto> toAportacionClassPairDtoList (Clase c) {

        List <AportacionClassPairDto> auxiliar = new ArrayList<>();

        c.getListadoAportaciones().forEach(aportacion -> {
            auxiliar.add(
            AportacionClassPairDto.builder()
                    .fecha(aportacion.getFecha())
                    .detallesAportacion(this.createPairList(aportacion))
                    .build()
            );
        });

        return auxiliar;
    }



    public Map<String, Double> createPairList (Aportacion aportacion) {
        Clase c = null;
        String nombre;
        Map<String, Double> mapClassKilos = new TreeMap<>();
        List<Aportacion> aportacionList = new ArrayList<>();

        aportacion.getDetalleAportacionList().forEach(detalleAportacion-> {
            mapClassKilos.put(detalleAportacion.getTipoAlimento().getNombre(), detalleAportacion.getCantidadEnKilos());
        });

        return mapClassKilos;
    }
}
