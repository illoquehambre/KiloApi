package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.repository.;
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

    public Optional<KilosDisponibles> findById(Long id) {
        return repository.findById(id);
    }

    public List<KilosDisponibles> findAll() {
        return repository.findAll();
    }

    public KilosDisponibles edit(KilosDisponibles artist) {
        return repository.save(artist);
    }

    public void delete(KilosDisponibles artist) {
        repository.delete(artist);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
