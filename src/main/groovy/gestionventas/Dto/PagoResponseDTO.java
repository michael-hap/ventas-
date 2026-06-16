package gestionventas.Dto;

import gestionventas.Model.EstadoPago;
import lombok.Data;

import java.time.LocalTime;

@Data
public class PagoResponseDTO {

    private Long idPago;

    private LocalTime fecha;

    private EstadoPago estadoPago;
}
