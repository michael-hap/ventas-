package gestionventas.Controllers;

import gestionventas.Dto.CrearTallaRequestDTO;
import gestionventas.Dto.TallaResponseDTO;
import gestionventas.Services.TallaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tallas")
@RequiredArgsConstructor
public class TallaController {

    private final TallaService tallaService;

    @PostMapping
    public ResponseEntity<TallaResponseDTO> crearTalla(
            @Valid @RequestBody CrearTallaRequestDTO dto
    ) {
        TallaResponseDTO tallaCreada =
                tallaService.crearTalla(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tallaCreada);
    }

    @GetMapping
    public ResponseEntity<List<TallaResponseDTO>> listarTodas() {

        return ResponseEntity.ok(
                tallaService.listarTodas()
        );
    }

    @GetMapping("/{idTalla}")
    public ResponseEntity<TallaResponseDTO> buscarPorId(
            @PathVariable Long idTalla
    ) {
        return ResponseEntity.ok(
                tallaService.buscarPorId(idTalla)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TallaResponseDTO> buscarPorNombre(
            @PathVariable String nombre
    ) {
        return ResponseEntity.ok(
                tallaService.buscarPorNombre(nombre)
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TallaResponseDTO>> buscarPorCoincidencia(
            @RequestParam String texto
    ) {
        return ResponseEntity.ok(
                tallaService.buscarPorCoincidencia(texto)
        );
    }

    @PutMapping("/{idTalla}")
    public ResponseEntity<TallaResponseDTO> actualizarTalla(
            @PathVariable Long idTalla,
            @Valid @RequestBody CrearTallaRequestDTO dto
    ) {
        return ResponseEntity.ok(
                tallaService.actualizarTalla(idTalla, dto)
        );
    }

    @DeleteMapping("/{idTalla}")
    public ResponseEntity<Void> eliminarTalla(
            @PathVariable Long idTalla
    ) {
        tallaService.eliminarTalla(idTalla);

        return ResponseEntity.noContent().build();
    }
}
