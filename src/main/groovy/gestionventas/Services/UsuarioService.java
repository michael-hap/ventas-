package gestionventas.Services;

import gestionventas.Config.JwtService;
import gestionventas.Dto.CrearUsuarioRequestDTO;
import gestionventas.Dto.UsuarioResponseDTO;
import gestionventas.Mapper.UsuarioMapper;
import gestionventas.Model.Rol;
import gestionventas.Model.Usuario;
import gestionventas.Repository.UsuarioRepository;
import gestionventas.Dto.LoginRequestDTO;
import gestionventas.Dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



    public UsuarioResponseDTO crearUsuario(CrearUsuarioRequestDTO dto) {

        if (usuarioRepository.existsByCorreoElectronico(dto.getCorreoElectronico())) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }

        Usuario usuario = usuarioMapper.aEntidad(dto);

        usuario.setContrasenia(
                passwordEncoder.encode(dto.getContrasenia())
        );

        Usuario guardado = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioResponseDTO(guardado);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO buscarPorCorreo(String correo) {

        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> buscarPorRol(Rol rol) {

        return usuarioRepository.findByRol(rol)
                .stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO actualizarUsuario(
            Long id,
            CrearUsuarioRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(dto.getRol());

        if (dto.getContrasenia() != null &&
                !dto.getContrasenia().isBlank()) {

            usuario.setContrasenia(
                    passwordEncoder.encode(dto.getContrasenia())
            );
        }

        Usuario actualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toUsuarioResponseDTO(actualizado);
    }

    public void eliminarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO activarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        usuario.setActivo(true);

        return usuarioMapper.toUsuarioResponseDTO(
                usuarioRepository.save(usuario)
        );
    }

    public UsuarioResponseDTO desactivarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        usuario.setActivo(false);

        return usuarioMapper.toUsuarioResponseDTO(
                usuarioRepository.save(usuario)
        );
    }
    public LoginResponseDTO iniciarSesion(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findByCorreoElectronico(dto.getCorreoElectronico())
                .orElseThrow(() ->
                        new RuntimeException("Correo o contraseña incorrectos"));

        if (!passwordEncoder.matches(dto.getContrasenia(), usuario.getContrasenia())) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        if (!usuario.isActivo()) {
            throw new RuntimeException("El usuario está desactivado");
        }

        String token = jwtService.generarToken(
                usuario.getCorreoElectronico(),
                usuario.getRol().name()
        );

        return new LoginResponseDTO(
                token,
                usuario.getNombreUsuario(),
                usuario.getRol().name()
        );
    }
}