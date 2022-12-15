package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class DetalleAportacion {

    @EmbeddedId
    private Aportacion id =  new Aportacion();

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long numLinea;

    private double cantidadEnKilos;


}
