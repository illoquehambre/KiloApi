package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Clase {

    @Id@GeneratedValue
    private Long id;
    private String nombre;
    private String tutor;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Aportacion> listadoAportaciones;
    @PreRemove
    public void preRemoveClase() {
        listadoAportaciones.forEach(aport -> aport.setClase(null));
    }

}
