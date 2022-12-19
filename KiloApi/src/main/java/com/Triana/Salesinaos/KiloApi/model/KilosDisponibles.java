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

    @OneToOne
    @MapsId
    private TipoAlimento tipoAlimento;

    @Id
    private Long id;

    private double cantidadDisponible;









}
