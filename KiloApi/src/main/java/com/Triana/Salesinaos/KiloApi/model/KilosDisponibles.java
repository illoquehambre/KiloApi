package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class KilosDisponibles {
    @Id
    @GeneratedValue
    @OneToOne
    @JoinColumn(name = "kilosDisponibles_id")
    private KilosDisponibles kilosDisponibles;
    private double cantidadDisponible;
}
