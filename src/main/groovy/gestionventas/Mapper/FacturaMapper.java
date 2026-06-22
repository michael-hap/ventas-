package gestionventas.Mapper;

import gestionventas.Dto.CrearFacturaRequestDTO;
import gestionventas.Dto.FacturaResponseDTO;
import gestionventas.Model.Factura;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public Factura aEntidad(CrearFacturaRequestDTO dto) {

        Factura factura = new Factura();

        factura.setDescripcion(dto.getDescripcion());
        factura.setTotal(dto.getTotal());
        factura.setSubtotal(dto.getSubtotal());

        return factura;
    }

    public FacturaResponseDTO toFacturaResponseDTO(Factura factura) {

        FacturaResponseDTO dto = new FacturaResponseDTO();

        dto.setIdFactura(factura.getIdFactura());
        dto.setDescripcion(factura.getDescripcion());
        dto.setFechaHoraFactura(factura.getFechaHoraFactura());
        dto.setTotal(factura.getTotal());
        dto.setSubtotal(factura.getSubtotal());

        return dto;
    }
}