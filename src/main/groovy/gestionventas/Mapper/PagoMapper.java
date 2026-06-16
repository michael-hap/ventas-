package gestionventas.Mapper;

import gestionventas.Dto.PagoResponseDTO;
import gestionventas.Model.EstadoPago;
import gestionventas.Model.Pago;
import gestionventas.Dto.CrearPagoRequestDTO;
import gestionventas.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {
    public PagoResponseDTO toPagoResponseDTO(Pago pago) {

        PagoResponseDTO dto = new PagoResponseDTO();

        dto.setIdPago(pago.getIdPago());
        dto.setEstadoPago(pago.getEstadoPago());
        dto.setFecha(pago.getFecha());

        return dto;
    }

    public Pago aEntidad(CrearPagoRequestDTO dto, Usuario cliente){

        Pago pago = new Pago();

        pago.setTotalPago(dto.getTotalPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstadoPago(EstadoPago.PENDIENTE);
        pago.setCliente(cliente);
        return pago;
    }
}
