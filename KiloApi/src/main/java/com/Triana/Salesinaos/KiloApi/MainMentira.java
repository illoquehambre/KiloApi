package com.Triana.Salesinaos.KiloApi;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.CajaRepository;
import com.Triana.Salesinaos.KiloApi.repository.DestinatarioRepository;
import com.Triana.Salesinaos.KiloApi.repository.TipoAlimentoRepository;
import com.Triana.Salesinaos.KiloApi.service.TipoAlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MainMentira {

    private final TipoAlimentoRepository tipoAlimentoService;
    private final DestinatarioRepository destinatarioRepository;
    private final CajaRepository cajaRepository;

    @PostConstruct
    public void init() {


        TipoAlimento t1 = new TipoAlimento();
        t1.setNombre("Patatas");

        TipoAlimento t2 = new TipoAlimento();
        t2.setNombre("Arroz");

        tipoAlimentoService.save(t1);
        tipoAlimentoService.save(t2);

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

        Caja c1 = new Caja();
        c1.setQr("codigo1");
        c1.setKilosTotales(68);
        c1.setNumCaja("Caja 1");
        c1.setDestinatario(d1);

        Caja c2 = new Caja();
        c2.setQr("codigo2");
        c2.setKilosTotales(8);
        c2.setNumCaja("Caja 2");
        c2.setDestinatario(d2);

        cajaRepository.save(c1);
        cajaRepository.save(c2);




    }

}
