package gestionventas.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FacturaResponseDTO {

    private Long idFactura;

    private String descripcion;

    private LocalDateTime fechaHoraFactura;

    private BigDecimal total;

    private BigDecimal subtotal;
}