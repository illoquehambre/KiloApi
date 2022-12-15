package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoAlimentoService {

    private final TipoAlimentoRepository repository;

    public TipoAlimento add(TipoAlimento tipo) {
        return repository.save(tipo);
    }

    public Optional<TipoAlimento> findByName(String name) {
        return repository.findFirstByName(name);
    }

}
