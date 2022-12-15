package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Tiene {

    @Id
    @GeneratedValue
    private Long id;

}
