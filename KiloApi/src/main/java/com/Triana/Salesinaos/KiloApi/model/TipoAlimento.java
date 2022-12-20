package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Embeddable
public class TipoAlimento implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    @OneToOne
    @JoinColumn(
            name = "tipoAlimento")
    private KilosDisponibles kilosDisponibles;

    //No se puede setear una primary key
    //Este metodo deberia solo servir para añadir
    //Se debe hacer un service.add tras sumar los kilos disponibles actuales a los agregados

    public void addKilosToTipoAlimento(KilosDisponibles k, double kilosAgregados){
        k.setCantidadDisponible(k.getCantidadDisponible()+kilosAgregados);
        kilosDisponibles = k;
    }

}
