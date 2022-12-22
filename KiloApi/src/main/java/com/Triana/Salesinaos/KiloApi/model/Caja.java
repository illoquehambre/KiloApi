package com.Triana.Salesinaos.KiloApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(
            name = "destinatario_id",
            foreignKey = @ForeignKey(name = "FK_CAJA_DESTINATARIO"))
    private Destinatario destinatario;

    @Builder.Default
    @OneToMany(mappedBy = "caja")
    private List<Tiene> tieneList = new ArrayList<>();


    /***Helper**/
    public void addDestinatarioToCaja(Destinatario d) {
        destinatario = d;
        d.getCajas().add(this);
    }


}
