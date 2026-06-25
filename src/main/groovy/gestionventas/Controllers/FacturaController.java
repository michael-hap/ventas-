package gestionventas.Controllers;
import gestionventas.Dto.CrearFacturaRequestDTO;
import gestionventas.Dto.FacturaResponseDTO;
import gestionventas.Services.FacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping
    public FacturaResponseDTO crearFactura(
            @Valid @RequestBody CrearFacturaRequestDTO dto
    ) {
        return facturaService.crearFactura(dto);
    }

    @GetMapping
    public List<FacturaResponseDTO> listarFacturas() {
        return facturaService.listarFacturas();
    }

    @GetMapping("/{id}")
    public FacturaResponseDTO obtenerPorId(
            @PathVariable Long id
    ) {
        return facturaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(
            @PathVariable Long id
    ) {
        facturaService.eliminarFactura(id);
    }
    @PutMapping("/{id}")
    public FacturaResponseDTO actualizarFactura(
            @PathVariable Long id,
            @Valid @RequestBody CrearFacturaRequestDTO dto
    ) {
        return facturaService.actualizarFactura(id, dto);
    }
}