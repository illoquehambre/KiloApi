package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.model.Aportacion;
import com.Triana.Salesinaos.KiloApi.model.DetalleAportacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AportacionRepository extends JpaRepository<Aportacion, Long> {
    Optional<DetalleAportacion> findFirstDetalleAportacionById(int id);

    @Query(value="select sum(d.cantidadEnKilos) from Aportacion a left join a.detalleAportacionList d where a.id = :id")
    double sumaKilosAportacion(@Param("id") Long id);


    @Query(value="select d from DetalleAportacion d join d.tipoAlimento t where t.id = :id")
    List<DetalleAportacion> findByTipoAlimento(@Param("id") Long id);
}
