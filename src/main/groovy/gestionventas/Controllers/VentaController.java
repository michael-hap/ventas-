package gestionventas.Controllers;

import gestionventas.Dto.CrearVentaRequestDTO;
import gestionventas.Dto.VentaResponseDTO;
import gestionventas.Model.EstadoVenta;
import gestionventas.Services.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public VentaResponseDTO crearVenta(
            @Valid @RequestBody CrearVentaRequestDTO dto) {

        return ventaService.crearVenta(dto);
    }

    @GetMapping
    public List<VentaResponseDTO> listarVentas() {

        return ventaService.listarVentas();
    }

    @GetMapping("/{id}")
    public VentaResponseDTO buscarPorId(
            @PathVariable("id") Long id) {

        return ventaService.buscarPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public List<VentaResponseDTO> buscarPorEstado(
            @PathVariable("estado") EstadoVenta estado) {

        return ventaService.buscarPorEstado(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(
            @PathVariable("id") Long id) {

        ventaService.eliminarVenta(id);
    }
}