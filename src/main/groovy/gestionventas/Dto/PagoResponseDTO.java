package gestionventas.Dto;

import gestionventas.Model.EstadoPago;

import java.time.LocalTime;

public class PagoResponseDTO {

    private Long idPago;

    private LocalTime fecha;

    private EstadoPago estadoPago;
}
