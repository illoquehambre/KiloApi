package com.Triana.Salesinaos.KiloApi.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCajaDto {
    private String numCaja, qr;

}
