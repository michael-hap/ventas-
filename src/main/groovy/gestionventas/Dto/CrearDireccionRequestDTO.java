package gestionventas.Dto;

import gestionventas.Model.TarifaEnvio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearDireccionRequestDTO {

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotNull(message = "El departamento es obligatorio")
    private TarifaEnvio departamento;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    private String barrio;
}