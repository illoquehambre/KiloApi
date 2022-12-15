package com.Triana.Salesinaos.KiloApi.model;

import lombok.*;

import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.GeneratedValue;
=======
>>>>>>> 7167be38f563606c99fdd6b6f15dc1d9325b992b
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Tiene {
<<<<<<< HEAD

    @Id
    @GeneratedValue
    private Long id;

=======
    @Id
    private Long id;
>>>>>>> 7167be38f563606c99fdd6b6f15dc1d9325b992b
}
