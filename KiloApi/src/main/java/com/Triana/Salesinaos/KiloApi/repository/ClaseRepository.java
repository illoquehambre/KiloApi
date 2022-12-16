package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query("select sum(d.cantidadEnKilos) from Clase c join Aportacion a join DetalleAportacion d ")
    double countCantidadEnKgs();
    @Query("select count(Aportacion) from Clase")
    int numAportaciones();



}
