package gestionventas.Controllers;

import gestionventas.Dto.CrearDetalleCarritoRequestDTO;
import gestionventas.Dto.DetalleCarritoResponseDTO;
import gestionventas.Services.DetalleCarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallescarrito")
@RequiredArgsConstructor
public class DetalleCarritoController {

    private final DetalleCarritoService detalleCarritoService;

    @PostMapping
    public ResponseEntity<DetalleCarritoResponseDTO> crearDetalle(
            @Valid @RequestBody CrearDetalleCarritoRequestDTO dto
    ) {
        DetalleCarritoResponseDTO detalleCreado =
                detalleCarritoService.crearDetalle(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(detalleCreado);
    }

    @GetMapping
    public ResponseEntity<List<DetalleCarritoResponseDTO>> listarTodos() {

        List<DetalleCarritoResponseDTO> detalles =
                detalleCarritoService.listarTodos();

        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{idDetalle}")
    public ResponseEntity<DetalleCarritoResponseDTO> buscarPorId(
            @PathVariable Long idDetalle
    ) {
        DetalleCarritoResponseDTO detalle =
                detalleCarritoService.buscarPorId(idDetalle);

        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/carrito/{idCarrito}")
    public ResponseEntity<List<DetalleCarritoResponseDTO>> buscarPorCarrito(
            @PathVariable Long idCarrito
    ) {
        List<DetalleCarritoResponseDTO> detalles =
                detalleCarritoService.buscarPorCarrito(idCarrito);

        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/prenda/{idPrenda}")
    public ResponseEntity<List<DetalleCarritoResponseDTO>> buscarPorPrenda(
            @PathVariable Long idPrenda
    ) {
        List<DetalleCarritoResponseDTO> detalles =
                detalleCarritoService.buscarPorPrenda(idPrenda);

        return ResponseEntity.ok(detalles);
    }

    @PutMapping("/{idDetalle}/cantidad")
    public ResponseEntity<DetalleCarritoResponseDTO> actualizarCantidad(
            @PathVariable Long idDetalle,
            @RequestParam Integer cantidad
    ) {
        DetalleCarritoResponseDTO detalleActualizado =
                detalleCarritoService.actualizarCantidad(
                        idDetalle,
                        cantidad
                );

        return ResponseEntity.ok(detalleActualizado);
    }

    @DeleteMapping("/{idDetalle}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long idDetalle
    ) {
        detalleCarritoService.eliminar(idDetalle);

        return ResponseEntity.noContent().build();
    }
}