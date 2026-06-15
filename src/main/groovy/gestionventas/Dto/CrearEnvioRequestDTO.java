package gestionventas.Dto;

import gestionventas.Model.EstadoEnvio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearEnvioRequestDTO {

    @NotBlank(message = "La guía es obligatoria")
    private String guia;

    @NotNull(message = "La dirección es obligatoria")
    private Long idDireccion;

    @NotNull(message = "El estado del envío es obligatorio")
    private EstadoEnvio estado;
}