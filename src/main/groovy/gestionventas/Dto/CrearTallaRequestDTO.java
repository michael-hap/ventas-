package gestionventas.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrearTallaRequestDTO {

    @NotBlank(message = "El nombre de la talla es obligatorio")
    private String nombre;
}
