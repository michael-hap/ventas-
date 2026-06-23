package gestionventas.Dto;

import gestionventas.Model.EstadoVenta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrearVentaRequestDTO {

    @NotNull(message = "El estado de la venta es obligatorio")
    private EstadoVenta estadoVenta;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total no puede ser negativo")
    private BigDecimal total;
}