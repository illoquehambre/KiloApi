package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TipoAlimentoRepository extends JpaRepository<TipoAlimento, Long> {


    Optional<TipoAlimento> findFirstByNombre(String name);
}
