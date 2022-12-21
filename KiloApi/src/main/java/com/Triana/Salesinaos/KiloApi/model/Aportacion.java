package com.Triana.Salesinaos.KiloApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Entity
public class Aportacion {

    @Id@GeneratedValue
    private Long id;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreationTimestamp
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "clase_id", foreignKey = @ForeignKey(name= "FK_CLASE_APORTACION"))
    @JsonIgnore
    private Clase clase;

    @Builder.Default
    @OneToMany(mappedBy = "aportacion", cascade = CascadeType.ALL, orphanRemoval = true)
    //@Fetch(FetchMode.JOIN)
    private List<DetalleAportacion> detalleAportacionList = new ArrayList<>();


    public DetalleAportacion addDetalleAportacion(DetalleAportacion da) {
        detalleAportacionList.add(da);
        da.setAportacion(this);

        return da;

    }
    public void removeDetalleAportacion(DetalleAportacion da) {
        detalleAportacionList.remove(da);
        da.setAportacion(null);
    }

    public void addClase(Clase c) {
        this.clase = c;
        c.getListadoAportaciones().add(this);
    }

    public void removeClase(Clase c) {
        c.getListadoAportaciones().remove(this);
        this.clase = null;
    }

}
