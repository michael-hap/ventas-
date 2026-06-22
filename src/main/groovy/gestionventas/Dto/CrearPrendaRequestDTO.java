package gestionventas.Dto;

import gestionventas.Model.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CrearPrendaRequestDTO {

    @NotBlank(message = "El nombre de la prenda es obligatorio")
    private String nombre;

    @NotBlank(message = "La imagen es obligatoria")
    private String imagen;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "Las dimensiones son obligatorias")
    private String dimensiones;

    @NotNull(message = "El género es obligatorio")
    private Genero genero;

    @NotBlank(message = "El tipo de tela es obligatorio")
    private String tipoTela;

    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;

    @NotEmpty(message = "Debe seleccionar al menos una talla")
    private List<Long> idsTallas;
}