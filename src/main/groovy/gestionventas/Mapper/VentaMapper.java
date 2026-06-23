package gestionventas.Mapper;

import gestionventas.Dto.CrearVentaRequestDTO;
import gestionventas.Dto.VentaResponseDTO;
import gestionventas.Model.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {

    public Venta aEntidad(CrearVentaRequestDTO dto) {

        Venta venta = new Venta();

        venta.setEstadoVenta(dto.getEstadoVenta());
        venta.setTotal(dto.getTotal());

        return venta;
    }

    public VentaResponseDTO toVentaResponseDTO(Venta venta) {

        VentaResponseDTO dto = new VentaResponseDTO();

        dto.setIdVenta(venta.getIdVenta());
        dto.setEstadoVenta(venta.getEstadoVenta());
        dto.setFechaHoraRegistro(venta.getFechaHoraRegistro());
        dto.setTotal(venta.getTotal());

        return dto;
    }
}