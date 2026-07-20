package gestionventas.Controllers;

import gestionventas.Dto.CrearPagoRequestDTO;
import gestionventas.Dto.PagoResponseDTO;
import gestionventas.Model.EstadoPago;
import gestionventas.Services.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagoResponseDTO crearPago(
            @Valid @RequestBody CrearPagoRequestDTO dto
    ) {
        return pagoService.crearPago(dto);
    }

    @GetMapping
    public List<PagoResponseDTO> listarPagos() {
        return pagoService.listarPagos();
    }

    @GetMapping("/{id}")
    public PagoResponseDTO buscarPorId(
            @PathVariable("id") Long id
    ) {
        return pagoService.buscarPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public List<PagoResponseDTO> buscarPorEstado(
            @PathVariable("estado") EstadoPago estado
    ) {
        return pagoService.buscarPorEstado(estado);
    }

    @PutMapping("/{id}")
    public PagoResponseDTO actualizarPago(
            @PathVariable("id") Long id,
            @Valid @RequestBody CrearPagoRequestDTO dto
    ) {
        return pagoService.actualizarPago(id, dto);
    }

    @PatchMapping("/{id}/estado")
    public PagoResponseDTO actualizarEstado(
            @PathVariable("id") Long id,
            @RequestParam("estado") EstadoPago estado
    ) {
        return pagoService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPago(
            @PathVariable("id") Long id
    ) {
        pagoService.eliminarPago(id);
    }
}