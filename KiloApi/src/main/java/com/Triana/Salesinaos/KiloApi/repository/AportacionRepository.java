package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AportacionRepository extends JpaRepository<Aportacion, Long> {
    Optional<DetalleAportacion> findFirstDetalleAportacionById(Long id);
}
