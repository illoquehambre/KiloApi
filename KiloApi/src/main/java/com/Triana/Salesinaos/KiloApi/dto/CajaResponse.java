package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CajaResponse{

    private Long id;

    private String qr, numCaja;
    private double kilosTotales;

    private Destinatario destinatario;


}
