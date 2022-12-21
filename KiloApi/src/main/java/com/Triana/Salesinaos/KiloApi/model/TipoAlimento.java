package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class TipoAlimento {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    @OneToOne(mappedBy = "tipoAlimento", cascade = CascadeType.MERGE)
    private KilosDisponibles kilosDisponibles;

    public void addKilosToTipoAlimento(KilosDisponibles k, double kilosAgregados){
        k.setCantidadDisponible(k.getCantidadDisponible()+kilosAgregados);
        kilosDisponibles = k;
    }

}
