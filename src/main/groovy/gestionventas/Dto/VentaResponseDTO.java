package gestionventas.Dto;

import gestionventas.Model.EstadoVenta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VentaResponseDTO {

    private Long idVenta;

    private EstadoVenta estadoVenta;

    private LocalDateTime fechaHoraRegistro;

    private BigDecimal total;
}