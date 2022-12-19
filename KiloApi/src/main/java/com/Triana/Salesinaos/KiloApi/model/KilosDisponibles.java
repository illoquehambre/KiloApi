package com.Triana.Salesinaos.KiloApi.model;

import com.Triana.Salesinaos.KiloApi.dto.TipoAlimentoDto;
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

    // @Builder.Default
    // @EmbeddedId
    // private KilosAportacionPK id =  new KilosAportacionPK();


    @Id
    private Long id;

    private double cantidadDisponible;

    @MapsId
    @OneToOne
    @JoinColumn(name = "tipo_alimento_id", foreignKey = @ForeignKey(name = "FK_KILOSDISPONIBLES_KILOALIMENTO"))
    private TipoAlimento tipoAlimento;
}
