package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.dto.ranking.GetRankingDto;
import com.Triana.Salesinaos.KiloApi.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query(value="select sum(d.cantidadEnKilos) from Clase c join c.listadoAportaciones a join a.detalleAportacionList d where c.id = :id")
    double sumCantidadEnkilos(@Param("id") Long id);
   /* @Query("select count(c.listadoAportaciones) from Clase c")
    int numAportaciones();
    */

    @Query("""
            SELECT NEW com.Triana.Salesinaos.KiloApi.dto.ranking.GetRankingDto(C.id, C.nombre,SUM(DA.cantidadEnKilos),AVG(DA.cantidadEnKilos)) FROM Clase C JOIN Aportacion A ON C.id = A.clase.id JOIN DetalleAportacion DA ON A.id = DA.aportacion.id GROUP BY C.id ORDER BY SUM(DA.cantidadEnKilos) DESC""")
    List<GetRankingDto> rankingClases();

}
