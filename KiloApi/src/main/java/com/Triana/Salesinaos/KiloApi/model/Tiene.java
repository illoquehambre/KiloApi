package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Tiene implements Serializable {

    @Builder.Default
    @EmbeddedId
    private TienePK id = new TienePK();

    @ManyToOne
    @MapsId("tipoAlimento_id")
    @JoinColumn(name = "tipoAlimento_id", foreignKey = @ForeignKey(name = "FK_TIPOSALIMENTOS_TIENE"))
    private TipoAlimento tipoAlimmento;

    @ManyToOne
    @MapsId("caja_id")
    @JoinColumn(name = "caja_id", foreignKey = @ForeignKey(name = "FK_CAJA_TIENE"))
    private Caja caja;

    @Column(name = "cantidadKgs")
    private double cantidadKgs;

     /*
        HELPERS
     */

    public void addToCajaToTipo(Caja c, TipoAlimento tipo) {
        this.setTipoAlimmento(tipo);
        this.setCaja(c);
        this.setCantidadKgs(tipo.getKilosDisponibles().getCantidadDisponible());
        c.getTieneList().add(this);
    }

    public void removeFromToCajaToTipo(Caja c) {
        c.getTieneList().remove(this);
        caja = null;
    }

}
