package com.Triana.Salesinaos.KiloApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;


@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TienePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Column(insertable = false, updatable = false)
    private Long tipoAlimento_id;

    @Column(insertable = false, updatable = false)
    private Long caja_id;
}
