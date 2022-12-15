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
<<<<<<< HEAD

=======
@Entity
>>>>>>> 7167be38f563606c99fdd6b6f15dc1d9325b992b
public class Destinatario{

    @Id
    @GeneratedValue
    private Long id;

    private String nombre, direccion, personaContacto, telefono;

    @OneToMany(mappedBy = "destinatario", fetch = FetchType.LAZY)
    private List<Caja> cajas = new ArrayList<>();
}
