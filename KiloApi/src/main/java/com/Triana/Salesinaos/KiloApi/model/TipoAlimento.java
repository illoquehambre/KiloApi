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
public class TipoAlimento {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    @OneToOne(mappedBy = "tipoAlimento", cascade = CascadeType.ALL)
    private KilosDisponibles kilosDisponibles;
 
    public void addKilosToTipoAlimento(KilosDisponibles k){
        k.setId(this.getId());
        k.setTipoAlimento(this);
        kilosDisponibles = k;
    }

}
