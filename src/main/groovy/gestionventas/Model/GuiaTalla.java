package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "guias_talla")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuiaTalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGuia;

    @NotNull(message = "La medida de pecho es obligatoria")
    private Double pecho;

    @NotNull(message = "La medida de cintura es obligatoria")
    private Double cintura;

    @NotNull(message = "La medida de cadera es obligatoria")
    private Double cadera;

    @NotNull(message = "La medida de largo es obligatoria")
    private Double largo;

    @ManyToOne
    private Prenda prenda;

    @ManyToOne
    private Talla talla;
}