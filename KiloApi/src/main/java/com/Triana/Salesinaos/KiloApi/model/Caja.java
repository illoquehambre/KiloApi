package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Caja{
    @Id
    @GeneratedValue
    private Long id;

    private String qr, numCaja;
    private Double kilosTotales;


    @ManyToOne
    @JoinColumn(
            name = "caja_id",
            foreignKey = @ForeignKey(name = "FK_CAJA_DESTINATARIO"))
    private Destinatario destinatario;

}
