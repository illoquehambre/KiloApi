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


    public void addKilosToTipoAlimento(KilosDisponibles k){
        k.setId(this.getId());
        k.setTipoAlimento(this);
        kilosDisponibles = k;
    }

}
