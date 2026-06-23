package gestionventas.Dto;

import lombok.Data;

@Data
public class DetalleCarritoResponseDTO {

    private Long idDetalleCarrito;

    private Long idCarrito;

    private Long idPrenda;

    private String nombrePrenda;

    private Integer cantidad;
}