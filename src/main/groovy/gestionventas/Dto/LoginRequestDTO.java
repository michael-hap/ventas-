package gestionventas.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @Email(message = "Debe ingresar un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correoElectronico;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;
}