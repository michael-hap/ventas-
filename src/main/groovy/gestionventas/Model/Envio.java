package gestionventas.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "envios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;

    @NotBlank(message = "La guía es obligatoria")
    @Column(name = "guia", nullable = false, unique = true)
    private String guia;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaHoraEnvio;

    @NotNull(message = "La dirección del envío es obligatoria")
    @ManyToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    @NotNull(message = "El estado del envío es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEnvio estado;

    @NotNull(message = "El costo del envío es obligatorio")
    @PositiveOrZero(message = "El costo del envío no puede ser negativo")
    @Column(name = "costo", nullable = false)
    private BigDecimal costo;

    @PrePersist
    @PreUpdate
    private void actualizarDatos() {
        if (fechaHoraEnvio == null) {
            fechaHoraEnvio = LocalDateTime.now();
        }
        asignarCostoPorDireccion();
    }

    private void asignarCostoPorDireccion() {
        if (direccion != null && direccion.getDepartamento() != null) {
            costo = direccion.getDepartamento().getCosto();
        }
    }
}
