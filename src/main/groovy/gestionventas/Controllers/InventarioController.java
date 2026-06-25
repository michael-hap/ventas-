package gestionventas.Controllers;

import gestionventas.Dto.CrearInventarioRequestDTO;
import gestionventas.Dto.InventarioResponseDTO;
import gestionventas.Services.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crearInventario(
            @RequestBody @Valid CrearInventarioRequestDTO dto) {

        return ResponseEntity.ok(
                inventarioService.crearInventario(dto)
        );
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listarInventarios() {

        return ResponseEntity.ok(
                inventarioService.listarInventarios()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                inventarioService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizarInventario(
            @PathVariable Long id,
            @RequestBody @Valid CrearInventarioRequestDTO dto) {

        return ResponseEntity.ok(
                inventarioService.actualizarInventario(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(
            @PathVariable Long id) {

        inventarioService.eliminarInventario(id);

        return ResponseEntity.noContent().build();
    }
}