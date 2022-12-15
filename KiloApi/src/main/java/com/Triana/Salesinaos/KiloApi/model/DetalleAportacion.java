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

    @Builder.Default
    @EmbeddedId
    private DetalleAportacionPK id =  new DetalleAportacionPK();

    @MapsId("aportacionId")
    @ManyToOne
    @JoinColumn(name = "aportacion_id", foreignKey = @ForeignKey(name="FK_DETALLEAPORTACION_APORTACION"))
    private Aportacion aportacion;

    private double cantidadEnKilos;

    public void addtoAportacion(Aportacion aportacion) {
        this.aportacion = aportacion;
        aportacion.getDetalleAportacion().add(this);
    }

    public void removeFromAportacion(Aportacion aportacion) {
        aportacion.getDetalleAportacion().remove(this);
        this.aportacion = null;
    }

}
