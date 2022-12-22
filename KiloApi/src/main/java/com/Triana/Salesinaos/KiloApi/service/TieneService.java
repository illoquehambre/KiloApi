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

    private final TieneRepository repository;

    public Optional<Tiene> findById(TienePK tienePK) {
        return repository.findById(tienePK);
    }

    public Tiene save(Tiene tiene) {
        return repository.save(tiene);
    }

    public void deleteById(TienePK tienePK) {
        repository.deleteById(tienePK);
    }


}
