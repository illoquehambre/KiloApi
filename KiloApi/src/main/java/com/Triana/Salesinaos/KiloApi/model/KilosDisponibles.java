package com.Triana.Salesinaos.KiloApi.model;


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

    @MapsId
    @OneToOne
    @JoinColumn(name = "tipo_alimento_id", foreignKey = @ForeignKey(name = "FK_KILOSDISPONIBLES_KILOALIMENTO"))
    private TipoAlimento tipoAlimento;
}
