package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Embeddable
public class Aportacion {

    @Id
    @GeneratedValue
    private Long id;


}
