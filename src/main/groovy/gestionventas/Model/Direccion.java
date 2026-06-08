package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name= "direcciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @NotBlank(message = "La ciudad es obligatoria")
    @Column(name=  "Ciudad", nullable = false)
    private String ciudad;

    @NotNull(message = "El departamento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "Departamento", nullable = false)
    private TarifaEnvio departamento;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(name = "Direccion", nullable = false)
    private String direccion;

    @Column(name = "Barrio")
    private String barrio;
}
