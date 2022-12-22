package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoDto;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoAlimentoService {

    private final TipoAlimentoRepository repository;

    public List<TipoAlimento> findAll() { return repository.findAll(); }

    public TipoAlimento add(TipoAlimento tipo) {
        return repository.save(tipo);
    }

    public Boolean existById(Long id ){
        return repository.existsById(id );
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<TipoAlimento> findByName(String name) {
        return repository.findFirstByNombre(name);
    }
  
    public Optional<TipoAlimento> findById(Long id) {
        return repository.findById(id);
    }

    public TipoAlimento edit(TipoAlimento oldTipoAlimento) {
        return repository.save(oldTipoAlimento);
    }

    public TipoAlimento toTipoAlimento(TipoAlimentoDto alimentoDto) {
        return TipoAlimento.builder()
                .id(alimentoDto.id())
                .nombre(alimentoDto.nombre())
                .build();
    }

}
