package com.Triana.Salesinaos.KiloApi.controller;

import com.Triana.Salesinaos.KiloApi.dto.ranking.GetRankingDto;
import com.Triana.Salesinaos.KiloApi.service.ClaseService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
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
@OpenAPIDefinition(info = @Info(title ="Operación-Kilo API"))
@Tag(name = "Kilos Disponibles", description = "Esta clase implementa Restcontrollers para la entidad Ranking")
public class RankingController {
    private final ClaseService service;







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
