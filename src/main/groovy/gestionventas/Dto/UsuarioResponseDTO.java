package gestionventas.Dto;

import gestionventas.Model.Rol;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long idUsuario;

    private String nombreUsuario;

    private String correoElectronico;

    private String telefono;

    private Rol rol;

    private boolean activo;
}