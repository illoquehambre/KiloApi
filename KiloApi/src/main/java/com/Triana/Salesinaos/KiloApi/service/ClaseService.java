package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.repository.ClaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository repository;
    public List<Clase> findAll(){return repository.findAll();}
    public Optional<Clase> findById(Long id){return repository.findById(id);}
    public double countKgs(){return  repository.countCantidadEnKgs();}
    public int cantidadAportaciones(){return  repository.numAportaciones();}
    public Boolean existById(Long id ){
        return repository.existsById(id );
    }




}
