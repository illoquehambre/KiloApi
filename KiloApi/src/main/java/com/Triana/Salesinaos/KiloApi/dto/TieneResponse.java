package com.Triana.Salesinaos.KiloApi.dto;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class TieneResponse {

    private Caja caja;
    private List<Tiene> tieneList = new ArrayList<>();

}
