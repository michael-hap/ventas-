package gestionventas.Dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CrearInventarioRequestDTO {

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;
}