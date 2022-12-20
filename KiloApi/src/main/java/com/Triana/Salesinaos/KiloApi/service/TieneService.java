package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.model.TienePK;
import com.Triana.Salesinaos.KiloApi.repository.TieneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TieneService {

    private TieneRepository tieneRepository;


    public Optional<Tiene> findById(TienePK tienePK){
        return tieneRepository.findById(tienePK);
    }



}
