package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.repository.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KilosDisponiblesService {
    private final KilosDisponiblesRepository repository;

    public KilosDisponibles add(KilosDisponibles kilosDisponibles) {
        return repository.save(kilosDisponibles);
    }


    public List<KilosDisponibles> findAll() {
        return repository.findAll();
    }

    public KilosDisponibles edit(KilosDisponibles kilosDisponibles) {
        return repository.save(kilosDisponibles);
    }

    public void delete(KilosDisponibles kilosDisponibles) {
        repository.delete(kilosDisponibles);
    }


}
