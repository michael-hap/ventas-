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
@Table(name= "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @NotNull(message = "El estado de la venta no puede ser vacío")
    @Enumerated(EnumType.STRING)
    @Column(name = "Estado_venta", nullable = false)
    private EstadoVenta estadoVenta;

    @Column(updatable = false)
    private LocalDateTime fechaHoraRegistro;

    @NotNull(message = "El total no puede ser vacío")
    @Column(name = "Total", nullable = false)
    @PositiveOrZero
    private BigDecimal total;

}
