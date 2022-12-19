package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Caja implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String qr;

    private String numCaja;

    private double kilosTotales;


    @ManyToOne
    @JoinColumn(
            name = "caja_id",
            foreignKey = @ForeignKey(name = "FK_CAJA_DESTINATARIO"))
    private Destinatario destinatario;

    @Builder.Default
    @OneToMany(mappedBy = "caja")
    private List<Tiene> tieneList = new ArrayList<>();


}
