package com.Triana.Salesinaos.KiloApi.dto.destinatario;

import com.Triana.Salesinaos.KiloApi.dto.caja.CajaResponsePost;
import com.Triana.Salesinaos.KiloApi.dto.tipoAlimento.TipoAlimentoToCajaDto;
import com.Triana.Salesinaos.KiloApi.model.Caja;
import com.Triana.Salesinaos.KiloApi.model.Destinatario;
import com.Triana.Salesinaos.KiloApi.model.Tiene;
import com.Triana.Salesinaos.KiloApi.service.DestinatarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DestinatarioDtoConverter {

    private final DestinatarioService destinatarioService;


    public DestinatarioResponse destinatarioToDestinatarioResponse(Long id) {
        Destinatario d = destinatarioService.findById(id).get();
        return DestinatarioResponse.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .telefono(d.getTelefono())
                .personaContacto(d.getPersonaContacto())
                .direccion(d.getDireccion())
                .numCajas(d.getCajas().stream().count())
                .kgTotales(destinatarioService.showKgTotal(d))
                .build();
    }

    public DestinatarioDetalleResponse destinatarioToDestinatarioDetalleResponse(Long id) {
        Destinatario d = destinatarioService.findById(id).get();
        TipoAlimentoToCajaDto tipoAlimentoCajaDto = new TipoAlimentoToCajaDto();
        /**Tenemos un listado de alimentos de cada caja***/
        List<TipoAlimentoToCajaDto> listadoA = new ArrayList<>();
        CajaResponsePost cajaResponsePost = new CajaResponsePost();
        /**Tenemos un listado de caja que proviene de que el destinatario tiene cajas
         * y cajas tiene un listado de tiene*/
        List<CajaResponsePost> cajas = new ArrayList<>();
        for (Caja c : d.getCajas()) {
            cajaResponsePost.setId(c.getId());
            cajaResponsePost.setQr(c.getQr());
            cajaResponsePost.setNumCaja(c.getNumCaja());
            cajaResponsePost.setKilosTotales(c.getKilosTotales());
            cajaResponsePost.setDestinatarioNombre(c.getDestinatario().getNombre());
            for (Tiene listado : c.getTieneList()) {
                tipoAlimentoCajaDto.setId(listado.getTipoAlimmento().getId());
                tipoAlimentoCajaDto.setNombre(listado.getTipoAlimmento().getNombre());
                tipoAlimentoCajaDto.setKgCantidad(listado.getTipoAlimmento().getKilosDisponibles().getCantidadDisponible());
                listadoA.add(tipoAlimentoCajaDto);
            }
            cajaResponsePost.setTipoAlimentoToCajaDtoList(listadoA);
            cajas.add(cajaResponsePost);
        }


        return DestinatarioDetalleResponse.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .direccion(d.getDireccion())
                .personaContacto(d.getPersonaContacto())
                .telefono(d.getTelefono())
                .cajaResponsePosts(d.getCajas().isEmpty() ? null : cajas)
                .build();
    }


}
