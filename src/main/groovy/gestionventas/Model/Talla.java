package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tallas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTalla;

    @NotBlank(message = "El nombre de la talla es obligatorio")
    @Column(name = "nombre_talla", nullable = false, unique = true)
    private String nombre;

    @ManyToMany(mappedBy = "tallas")
    private List<Prenda> prendas;

    @OneToMany(mappedBy = "talla", cascade = CascadeType.ALL)
    private List<GuiaTalla> guiasTalla;
}