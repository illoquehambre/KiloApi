package com.Triana.Salesinaos.KiloApi;

import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.model.TipoAlimento;
import com.Triana.Salesinaos.KiloApi.repository.CajaRepository;
import com.Triana.Salesinaos.KiloApi.repository.DestinatarioRepository;
import com.Triana.Salesinaos.KiloApi.repository.TieneRepository;
import com.Triana.Salesinaos.KiloApi.repository.TipoAlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MainMentira {

    private final TipoAlimentoRepository tipoAlimentoService;
    private final DestinatarioRepository destinatarioRepository;
    private final CajaRepository cajaRepository;

    private final TieneRepository tieneRepository;

    @PostConstruct
    public void init() {


        TipoAlimento t1 = TipoAlimento.builder()
                .nombre("Patatas")
                .build();

        TipoAlimento t2 =TipoAlimento.builder()
                .nombre("Arroz")
                .build();

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

        List<TipoAlimento> tipoAlimentoList = List.of(
                TipoAlimento.builder().nombre("Banana").build(),
                TipoAlimento.builder().nombre("Pizza").build(),
                TipoAlimento.builder().nombre("Botella Cocacola").build()
        );

        tipoAlimentoService.saveAll(tipoAlimentoList);

        Tiene tien1 = Tiene.builder()
                .tipoAlimmento(t1)
                .caja(c1)
                .cantidadKgs(4)
                .build();

        tien1.addToCaja(c1);
        tieneRepository.save(tien1);


    }

}
