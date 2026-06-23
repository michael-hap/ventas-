package gestionventas.Dto;

import gestionventas.Model.EstadoPago;
import gestionventas.Model.MetodoPago;
import lombok.Data;

import java.time.LocalTime;

@Data
public class PagoResponseDTO {

    private Long idPago;

    private LocalTime fecha;

    private Double totalPago;

    private MetodoPago metodoPago;

    private EstadoPago estadoPago;

    private Long idCliente;

    private String nombreCliente;
}