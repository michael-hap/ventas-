package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name= "inventarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @NotNull(message = "El stock es obligatorio")
    @Column(name = "Stock", nullable = false)
    private int Stock;


}
