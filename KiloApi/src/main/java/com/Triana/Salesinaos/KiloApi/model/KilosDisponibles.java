package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity

public class KilosDisponibles{

    @Builder.Default
    @EmbeddedId
    private KilosAportacionPK id =  new KilosAportacionPK();
    private double cantidadDisponible;


}
