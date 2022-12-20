package com.Triana.Salesinaos.KiloApi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class KilosDisponibles{


    @Id
    private Long id;

    private double cantidadDisponible;
    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(name = "tipo_alimento_id", foreignKey = @ForeignKey(name = "FK_KILOSDISPONIBLES_KILOALIMENTO"))
    private TipoAlimento tipoAlimento;
}
