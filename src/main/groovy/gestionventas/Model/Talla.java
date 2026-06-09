package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name= "tallas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Talla {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idTalla;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "Nombre_prenda", nullable = false)
    private String nombre;

    @NotNull(message = "La referencia de la prenda es obligatoria")
    @OneToMany(mappedBy = "Prendas", cascade = CascadeType.ALL)
    private List<Prenda> prendas;

    @NotNull(message= "La guía es obligatoria")
    @OneToMany(mappedBy = "Guia", cascade = CascadeType.ALL)
    private List<GuiaTalla> guiaTallas;

}
