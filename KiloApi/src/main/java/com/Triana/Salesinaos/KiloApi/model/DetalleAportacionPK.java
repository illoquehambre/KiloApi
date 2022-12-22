package com.Triana.Salesinaos.KiloApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleAportacionPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(insertable = false, updatable = false)
    private Long aportacionId;

    @Column(insertable = false, updatable = false)
    private int numLinea;
}
