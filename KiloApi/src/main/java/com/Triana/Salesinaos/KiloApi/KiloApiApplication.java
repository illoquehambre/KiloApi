package com.Triana.Salesinaos.KiloApi;

import com.Triana.Salesinaos.KiloApi.model.KilosAportacionPK;
import com.Triana.Salesinaos.KiloApi.model.KilosDisponibles;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.service.KilosDisponiblesService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class KiloApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KiloApiApplication.class, args);
    }
    

}
