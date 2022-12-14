package com.Triana.Salesinaos.KiloApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
//@NamedQuery(name = "Clase.findAllCustom", query = "SELECT c FROM Clase c")
public class Clase {

    @Id@GeneratedValue
    private Long id;

    private String nombre;

    private String tutor;

    @Builder.Default
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Aportacion> listadoAportaciones = new ArrayList<>();
    @PreRemove

    public void preRemoveClase() {

        listadoAportaciones.forEach(aport -> aport.setClase(null));
        this.setListadoAportaciones(null);
     }


}
