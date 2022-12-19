package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     }


}
