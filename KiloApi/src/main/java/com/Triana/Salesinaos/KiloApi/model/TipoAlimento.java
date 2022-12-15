package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Embeddable
public class TipoAlimento implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;




}
