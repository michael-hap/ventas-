package gestionventas.Mapper;

import gestionventas.Dto.CrearUsuarioRequestDTO;
import gestionventas.Dto.UsuarioResponseDTO;
import gestionventas.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.isActivo());

        return dto;
    }

    public Usuario aEntidad(CrearUsuarioRequestDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());
        usuario.setActivo(true);

        return usuario;
    }
}