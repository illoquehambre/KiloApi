package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.ranking.GetRankingDto;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
@Tag(name = "Ranking", description = "Esta clase muestra un ranking de las aportaciones de cada clase")
public class RankingController {
    private final ClaseService service;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ranking de aportaciones por clase",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Caja.class)),
                            examples = @ExampleObject(value = """
                                            [
                                                {
                                                    "id": 3,
                                                    "nombre": "2ºDam",
                                                    "cantidadKg": 40.0,
                                                    "media": 20.0,
                                                    "numAportaciones": 2
                                                }
                                            ]
                                    """
                            ))}),
            @ApiResponse(responseCode = "404",
                    description = "No hay aportaciones que mostrar",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<GetRankingDto>> getRanking(){
        List<GetRankingDto> list= new ArrayList<>();
        list = service.getRanking();

        if (list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

            return ResponseEntity.ok().body(list);





    }
}
