package gestionventas.Mapper;

import gestionventas.Dto.PagoResponseDTO;
import gestionventas.Model.Pago;
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
}
