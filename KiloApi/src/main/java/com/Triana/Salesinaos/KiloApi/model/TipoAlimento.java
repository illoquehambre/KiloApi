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
public class TipoAlimento {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToOne(mappedBy = "tipoAlimento", cascade = CascadeType.ALL)
    private KilosDisponibles kilosDisponibles;

}
