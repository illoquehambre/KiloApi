package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

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

    /**
     * CREO QUE MAS BIEN USARÍA UN DTO PARA LOS FUTUROS GET DE DESTINATARIO, YA QUE PARA CREAR NO HARÍA FALTA UN DTO
     @OneToMany(mappedBy = "destinatario", fetch = FetchType.LAZY)
     @Builder.Default private List<Caja> cajas = new ArrayList<>();
     **/

}
