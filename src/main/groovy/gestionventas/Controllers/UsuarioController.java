package gestionventas.Controllers;

import gestionventas.Dto.CrearUsuarioRequestDTO;
import gestionventas.Dto.LoginRequestDTO;
import gestionventas.Dto.LoginResponseDTO;
import gestionventas.Dto.UsuarioResponseDTO;
import gestionventas.Model.Rol;
import gestionventas.Services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/crear")
    public UsuarioResponseDTO crearUsuario(
            @Valid @RequestBody CrearUsuarioRequestDTO dto) {

        return usuarioService.crearUsuario(dto);
    }

    @PostMapping("/login")
    public LoginResponseDTO iniciarSesion(
            @Valid @RequestBody LoginRequestDTO dto) {

        return usuarioService.iniciarSesion(dto);
    }

    @GetMapping("/listar")
    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(
            @PathVariable("id") Long id) {

        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/correo/{correo}")
    public UsuarioResponseDTO buscarPorCorreo(
            @PathVariable String correo) {

        return usuarioService.buscarPorCorreo(correo);
    }

    @GetMapping("/rol/{rol}")
    public List<UsuarioResponseDTO> buscarPorRol(
            @PathVariable Rol rol) {

        return usuarioService.buscarPorRol(rol);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody CrearUsuarioRequestDTO dto) {

        return usuarioService.actualizarUsuario(id, dto);
    }

    @PatchMapping("/{id}/activar")
    public UsuarioResponseDTO activarUsuario(
            @PathVariable Long id) {

        return usuarioService.activarUsuario(id);
    }

    @PatchMapping("/{id}/desactivar")
    public UsuarioResponseDTO desactivarUsuario(
            @PathVariable Long id) {

        return usuarioService.desactivarUsuario(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(
            @PathVariable Long id) {

        usuarioService.eliminarUsuario(id);
    }

}
