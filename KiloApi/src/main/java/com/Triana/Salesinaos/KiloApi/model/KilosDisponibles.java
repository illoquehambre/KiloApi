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

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private TipoAlimento tipoAlimento;

    private double cantidadDisponible;









}
