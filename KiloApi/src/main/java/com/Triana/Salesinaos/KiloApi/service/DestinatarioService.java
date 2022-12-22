package com.Triana.Salesinaos.KiloApi.service;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.repository.DestinatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.PreRemove;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinatarioService {

    private final DestinatarioRepository repository;
    private final CajaService cajaService;

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

    @PreRemove
    public void delete(Destinatario destinatario) {
        for(Caja c : destinatario.getCajas()){
            c.setDestinatario(null);
            cajaService.edit(c);
        }
        repository.delete(destinatario);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public double showKgTotal(Destinatario d) {
        double aux = 0;
        if (!d.getCajas().isEmpty()) {
            for (Caja caja : d.getCajas()) {
                aux += caja.getKilosTotales();
            }
            return aux;
        }
        return 0;

    }


}
