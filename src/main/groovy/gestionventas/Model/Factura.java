package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name= "facturas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    @NotNull
    @NotBlank(message = "La descripción no puede ser vacía")
    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(updatable = false)
    private LocalDateTime fechaHoraFactura;

    @NotNull(message = "El total no puede ser vacío")
    @Column(name = "Total", nullable = false)
    @PositiveOrZero
    private BigDecimal total;

    @NotNull(message = "El subtotal no puede ser vacío")
    @Column(name = "Subtotal", nullable = false)
    @PositiveOrZero
    private BigDecimal subtotal;


    /*Factura: id, fecha, subtotal, total, descripcion
    */
}
