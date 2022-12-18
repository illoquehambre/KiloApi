package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Destinatario implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, direccion, personaContacto, telefono;

    @OneToMany(mappedBy = "destinatario", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Caja> cajas = new ArrayList<>();


}
