package com.Triana.Salesinaos.KiloApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KilosAportacionPK implements Serializable {

    private Long tipoAlimentoId;

}
