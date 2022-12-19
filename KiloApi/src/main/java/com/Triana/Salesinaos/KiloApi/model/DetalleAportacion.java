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
    private DetalleAportacionPK id ;

    @MapsId("aportacionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aportacion_id", foreignKey = @ForeignKey(name="FK_DETALLEAPORTACION_APORTACION"))
    private Aportacion aportacion;

    private double cantidadEnKilos;


    @ManyToOne
    @JoinColumn(name="tipo_alimento_id", foreignKey = @ForeignKey(name= "FK_DETALLEAPORTACION_TIPOALIMENTO"))
    private TipoAlimento tipoAlimento;



}
