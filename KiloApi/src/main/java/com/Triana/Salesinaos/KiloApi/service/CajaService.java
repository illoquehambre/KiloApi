package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.*;
import com.Triana.Salesinaos.KiloApi.repository.CajaRepository;
import com.Triana.Salesinaos.KiloApi.repository.KilosDisponiblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CajaService {
    private final CajaRepository repository;

    private final KilosDisponiblesRepository kilosDisponiblesRepository;

    public List<Caja> findAll() {
        return repository.findAll();
    }

    public Caja add(Caja caja) {
        return repository.save(caja);
    }

    public Optional<Caja> findById(Long id) {
        return repository.findById(id);
    }

    public Caja edit(Caja caja) {
        return repository.save(caja);
    }

    public void delete(Caja caja) {
        findAll().remove(caja);
        repository.delete(caja);
    }

    @PreRemove
    public void deleteById(Long id) {
        Caja c = findById(id).get();
        if (c.getTieneList().isEmpty()) {
            repository.deleteById(id);
        }

        c.getTieneList().forEach(tiene -> {
            /*Al ser en la entidad KilosDisponibles
            tiene como id el id de TipoAlimento no es factible un
            findAll ya que podemos buscar por su id que se irá recorriendo
            con tieneList que tiene un TipoAlimento que tiene el id
             */
            TipoAlimento select = kilosDisponiblesRepository
                    .findById(tiene.getTipoAlimmento()
                            .getId()).get().getTipoAlimento();
            select.getKilosDisponibles()
                    .setCantidadDisponible(select.getKilosDisponibles()
                            .getCantidadDisponible() + tiene.getTipoAlimmento().getKilosDisponibles()
                            .getCantidadDisponible());

        });

        repository.deleteById(id);
    }

    public double obtenerKgCaja(Caja c) {
        double aux = 0;
        if (!c.getTieneList().isEmpty()) {
            for (Tiene t : c.getTieneList()) {
                aux += t.getCantidadKgs();
            }
            return aux;
        }
        return 0;
    }



}
