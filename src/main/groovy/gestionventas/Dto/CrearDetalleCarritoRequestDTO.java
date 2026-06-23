package gestionventas.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CrearDetalleCarritoRequestDTO {

    @NotNull(message = "El carrito es obligatorio")
    private Long idCarrito;

    @NotNull(message = "La prenda es obligatoria")
    private Long idPrenda;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;
}