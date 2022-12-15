package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class KilosDisponibles {
    @Id
    @GeneratedValue
    //Falta las asociacion maylor perro
    //private TipoAlimento tipoAlimento;

    private double cantidadDisponible;
}
