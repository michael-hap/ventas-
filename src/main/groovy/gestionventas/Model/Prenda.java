package gestionventas.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "prendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrenda;

    @NotBlank(message = "El nombre de la prenda es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La imagen es obligatoria")
    @Column(name = "imagen", nullable = false)
    private String imagen;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @NotBlank(message = "Las dimensiones son obligatorias")
    @Column(name = "dimensiones", nullable = false)
    private String dimensiones;

    @NotNull(message = "El género es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private Genero genero;

    @NotBlank(message = "El tipo de tela es obligatorio")
    @Column(name = "tipo_tela", nullable = false)
    private String tipoTela;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaPrenda categoria;

    @ManyToMany
    @JoinTable(
            name = "prenda_talla",
            joinColumns = @JoinColumn(name = "id_prenda"),
            inverseJoinColumns = @JoinColumn(name = "id_talla")
    )
    private List<Talla> tallas;
}