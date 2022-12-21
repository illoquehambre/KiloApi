package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query(value="select sum(d.cantidadEnKilos) from Clase c join c.listadoAportaciones a join a.detalleAportacionList d where c.id = :id")
    double sumCantidadEnkilos(@Param("id") Long id);
   /* @Query("select count(c.listadoAportaciones) from Clase c")
    int numAportaciones();
    */


}
