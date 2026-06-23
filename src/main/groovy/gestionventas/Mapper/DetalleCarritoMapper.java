package gestionventas.Mapper;

import gestionventas.Dto.DetalleCarritoResponseDTO;
import gestionventas.Model.DetalleCarrito;
import org.springframework.stereotype.Component;

@Component
public class DetalleCarritoMapper {

    public DetalleCarritoResponseDTO toDetalleCarritoResponseDTO(
            DetalleCarrito detalleCarrito) {

        DetalleCarritoResponseDTO dto =
                new DetalleCarritoResponseDTO();

        dto.setIdDetalleCarrito(
                detalleCarrito.getIdDetalleCarrito());

        dto.setIdCarrito(
                detalleCarrito.getCarrito().getIdCarrito());

        dto.setIdPrenda(
                detalleCarrito.getPrenda().getIdPrenda());

        dto.setNombrePrenda(
                detalleCarrito.getPrenda().getNombre());

        dto.setCantidad(
                detalleCarrito.getCantidad());

        return dto;
    }
}