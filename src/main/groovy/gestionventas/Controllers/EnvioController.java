package gestionventas.Controllers;

import gestionventas.Dto.CrearEnvioRequestDTO;
import gestionventas.Dto.EnvioResponseDTO;
import gestionventas.Model.EstadoEnvio;
import gestionventas.Services.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @PostMapping
    public EnvioResponseDTO crearEnvio(
            @Valid @RequestBody CrearEnvioRequestDTO dto) {

        return envioService.crearEnvio(dto);
    }

    @GetMapping("/{id}")
    public EnvioResponseDTO buscarPorId(
            @PathVariable Long id) {

        return envioService.buscarPorId(id);
    }

    @GetMapping("/guia/{guia}")
    public EnvioResponseDTO buscarPorGuia(
            @PathVariable String guia) {

        return envioService.buscarPorGuia(guia);
    }

    @GetMapping
    public List<EnvioResponseDTO> listarTodos() {

        return envioService.listarTodos();
    }

    @GetMapping("/estado/{estado}")
    public List<EnvioResponseDTO> listarPorEstado(
            @PathVariable EstadoEnvio estado) {

        return envioService.listarPorEstado(estado);
    }

    @GetMapping("/direccion/{idDireccion}")
    public List<EnvioResponseDTO> listarPorDireccion(
            @PathVariable Long idDireccion) {

        return envioService.listarPorDireccion(idDireccion);
    }

    @PatchMapping("/{id}/estado")
    public EnvioResponseDTO actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoEnvio estado) {

        return envioService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminarPorId(
            @PathVariable Long id) {

        envioService.eliminarPorId(id);
    }
}