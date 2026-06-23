package gestionventas.Dto;

import gestionventas.Model.MetodoPago;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CrearPagoRequestDTO {

    @NotNull(message = "El total del pago es obligatorio")
    @Positive(message = "El total debe ser mayor que cero")
    private Double totalPago;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    private Long idCliente;
}