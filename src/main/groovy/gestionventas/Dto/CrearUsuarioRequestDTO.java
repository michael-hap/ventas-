package gestionventas.Dto;

import gestionventas.Model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearUsuarioRequestDTO {

    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Size(max = 50, message = "El nombre no puede sobrepasar los 50 caracteres")
    public String nombreUsuario;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Size(max = 100, message = "El correo electrónico no puede sobrepasar los 100 caracteres")
    private String correoElectronico;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String contrasenia;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(min = 5, max = 15, message = "El número de telefono debe estar entre 5 y 15 caracteres")
    private String telefono;

    @NotNull(message = "El rol del usuario es obligatorio")
    private Rol rol;
}
