package com.Triana.Salesinaos.KiloApi;

import com.Triana.Salesinaos.KiloApi.model.*;
import com.Triana.Salesinaos.KiloApi.repository.*;
import com.Triana.Salesinaos.KiloApi.service.KilosDisponiblesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MainMentira {
    private final TipoAlimentoRepository tipoAlimentoService;
    private final DestinatarioRepository destinatarioRepository;
    private final CajaRepository cajaRepository;
    private final KilosDisponiblesService kilosDisponiblesService;
    private final TieneRepository tieneRepository;
    @PostConstruct
    public void init(){
        TipoAlimento t1 = TipoAlimento.builder()
                .nombre("Patatas")
                .build();
        tipoAlimentoService.save(t1);

        KilosDisponibles k = KilosDisponibles.builder()
                .tipoAlimento(t1)
                .id(t1.getId())
                .build();
        t1.addKilosToTipoAlimento(k, 7);
        tipoAlimentoService.save(t1);
        kilosDisponiblesService.add(k);
        Caja c1 = Caja.builder()
                .qr("Codigo-1")
                .kilosTotales(68)
                .numCaja("Caja-123")
                .build();

        cajaRepository.save(c1);

        TienePK tienePK = new TienePK(t1.getId(), c1.getId());

        Tiene tiene1 = Tiene.builder()
                .id(tienePK)
                .build();

        tiene1.addToCajaToTipo(c1, t1);
        tieneRepository.save(tiene1);


    }


/*
    private final TipoAlimentoRepository tipoAlimentoService;
    private final DestinatarioRepository destinatarioRepository;
    private final CajaRepository cajaRepository;
    private final KilosDisponiblesService kilosDisponiblesService;
    private final TieneRepository tieneRepository;

    @PostConstruct
    public void init() {

        TipoAlimento t1 = TipoAlimento.builder()
                .nombre("Patatas")
                .build();
        tipoAlimentoService.save(t1);

        KilosDisponibles k = KilosDisponibles.builder()
                .tipoAlimento(t1)
                .id(t1.getId())
                .build();
        t1.addKilosToTipoAlimento(k, 7);
        tipoAlimentoService.save(t1);
        kilosDisponiblesService.add(k);

    }

        TipoAlimento t1 = TipoAlimento.builder()
                .nombre("Patatas")
                .build();

        TipoAlimento t2 = TipoAlimento.builder()
                .nombre("Arroz")
                .build();

        KilosDisponibles k = KilosDisponibles.builder()
                .cantidadDisponible(7)
                .build();

        tipoAlimentoService.save(t1);
        tipoAlimentoService.save(t2);

        t1.addKilosToTipoAlimento(k, 2);
        t2.addKilosToTipoAlimento(k, 3);

        kilosDisponiblesRepository.save(k);
        tipoAlimentoService.save(t1);
        tipoAlimentoService.save(t2);

        Caja c1 = Caja.builder()
                .qr("Codigo-1")
                .kilosTotales(68)
                .numCaja("Caja-123")
                .build();

        Caja c2 = Caja.builder()
                .qr("Codigo-2")
                .kilosTotales(8.45)
                .numCaja("Caja-1234")
                .build();

        cajaRepository.save(c1);
        cajaRepository.save(c2);

        TienePK tienePK = new TienePK(c1.getId(), t1.getId());

        Tiene tiene1 = Tiene.builder()
                .id(tienePK)
                .build();

        tiene1.addToCajaToTipo(c1, t1);
        tieneRepository.save(tiene1);

        Destinatario d1 = new Destinatario();
        d1.setNombre("Maylor");
        d1.setDireccion("Avenida alvar nuñez");
        d1.setTelefono("609835692");
        d1.setPersonaContacto("Maylor Bustamante");

        Destinatario d2 = new Destinatario();
        d2.setNombre("Juan");
        d2.setDireccion("Avenida Coria");
        d2.setTelefono("609835692");
        d2.setPersonaContacto("Juan Mercado");

        destinatarioRepository.save(d1);
        destinatarioRepository.save(d2);


    }
*/
}
