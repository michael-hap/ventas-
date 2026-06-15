package gestionventas.Dto;

import gestionventas.Model.MetodoPago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearPagoRequestDTO {

    @NotNull(message = "El total del pago es obligatorio")
    @Size(max = 100, message = "El total no puede sobrepasar de los 100 caracteres")
    public Double totalPago;

    @NotNull(message = "El metodo de pago es obligatorio")
    public MetodoPago metodoPago;


}
