package gestionventas.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CrearFacturaRequestDTO {

    @NotBlank(message = "La descripción no puede ser vacía")
    private String descripcion;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total no puede ser negativo")
    private BigDecimal total;

    @NotNull(message = "El subtotal es obligatorio")
    @PositiveOrZero(message = "El subtotal no puede ser negativo")
    private BigDecimal subtotal;
}