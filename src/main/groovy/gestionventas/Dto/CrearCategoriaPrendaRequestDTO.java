package gestionventas.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearCategoriaPrendaRequestDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

}