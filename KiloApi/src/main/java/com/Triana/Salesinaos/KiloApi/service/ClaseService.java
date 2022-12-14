package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.dto.clase.ClaseDto;
import com.Triana.Salesinaos.KiloApi.dto.ranking.GetRankingDto;
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

    public List<GetRankingDto> getRanking(){return  repository.rankingClases();}
    public List<Clase> findAll(){return repository.findAll();}
    public Optional<Clase> findById(Long id){return repository.findById(id);}
    public double countKgs(Long id){return repository.sumCantidadEnkilos(id);}

    public Boolean existById(Long id ){
        return repository.existsById(id );
    }

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
    public ClaseDto toClaseDto(Clase clase) {
        return ClaseDto.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .tutor(clase.getTutor())
                .build();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
