package com.Triana.Salesinaos.KiloApi.repository;

import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KilosDisponiblesRepository extends JpaRepository<KilosDisponibles, Long> {
    /**KILOSDISPONIBLES CONSULTAS MAYLOR**/

    /**
     * Devuelve 5 alumnos, ordenados por fecha de nacimiento descendente
     * cuyo apellido sea igual al proporcionado
     public List<Alumno> findTop5ByApellido1OrderByFechaNacimientoDesc(String apellido1);
     */

    /**
     * SELECT * FROM KILOS_DISPONIBLES ORDER BY CANTIDAD_DISPONIBLE DESC

    @Query("""
            select new com.Triana.Salesinaos.KiloApi.dto.tipoAlimento(
                k.getId(),k.getNombre() , k.getKilosDisponibles().getCantidadDisponible()
            )
            from tipoAlimento k
            
            """)
    List<TipoAlimentoToCajaDto> todosLosKgDesc(List<TipoAlimentoToCajaDto> listadoSinOrder);
    */

}
