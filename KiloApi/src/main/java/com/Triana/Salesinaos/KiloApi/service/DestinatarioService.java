package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.DestinatarioRepository;
import com.Triana.Salesinaos.KiloApi.repository.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinatarioService {

    private final DestinatarioRepository repository;

    public Destinatario add(Destinatario destinatario) {
        return repository.save(destinatario);
    }

    public Optional<Destinatario> findById(Long id) {
        return repository.findById(id);
    }

    public List<Destinatario> findAll() {
        return repository.findAll();
    }

    public Destinatario edit(Destinatario destinatario) {
        return repository.save(destinatario);
    }

    public void delete(Destinatario destinatario) {
        repository.delete(destinatario);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
