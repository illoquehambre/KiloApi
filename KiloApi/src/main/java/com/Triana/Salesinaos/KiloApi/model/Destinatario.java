package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Destinatario{

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, direccion, personaContacto, telefono;

    @OneToMany(mappedBy = "destinatario", fetch = FetchType.LAZY)
    private List<Caja> cajas = new ArrayList<>();
}
