package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.dto.ClaseDto;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.ClaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository repository;
    public List<Clase> findAll(){return repository.findAll();}

    public Clase add(Clase clase) {
        return repository.save(clase);
    }

    public Clase toClase(ClaseDto claseDto) {
        return Clase.builder()
                .id(claseDto.id())
                .nombre(claseDto.nombre())
                .tutor(claseDto.tutor())
                .build();
    }
}
