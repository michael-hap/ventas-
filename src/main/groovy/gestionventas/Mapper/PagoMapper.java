package gestionventas.Mapper;

import gestionventas.Dto.CrearPagoRequestDTO;
import gestionventas.Dto.PagoResponseDTO;
import gestionventas.Model.EstadoPago;
import gestionventas.Model.Pago;
import gestionventas.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public PagoResponseDTO toPagoResponseDTO(Pago pago) {

        PagoResponseDTO dto = new PagoResponseDTO();

        dto.setIdPago(pago.getIdPago());
        dto.setFecha(pago.getFecha());
        dto.setTotalPago(pago.getTotalPago());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstadoPago(pago.getEstadoPago());

        if (pago.getCliente() != null) {
            dto.setIdCliente(pago.getCliente().getIdUsuario());
            dto.setNombreCliente(pago.getCliente().getNombreUsuario());
        }

        return dto;
    }

    public Pago aEntidad(CrearPagoRequestDTO dto, Usuario cliente) {

        Pago pago = new Pago();

        pago.setTotalPago(dto.getTotalPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstadoPago(EstadoPago.PENDIENTE);
        pago.setCliente(cliente);

        return pago;
    }
}