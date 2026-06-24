package gestionventas.Controllers;

import gestionventas.Dto.CategoriaPrendaResponseDTO;
import gestionventas.Dto.CrearCategoriaPrendaRequestDTO;
import gestionventas.Services.CategoriaPrendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaPrendaController {

    private final CategoriaPrendaService categoriaPrendaService;

    @PostMapping
    public ResponseEntity<CategoriaPrendaResponseDTO> crearCategoria(
            @Valid @RequestBody CrearCategoriaPrendaRequestDTO dto
    ) {
        CategoriaPrendaResponseDTO categoriaCreada =
                categoriaPrendaService.crearCategoria(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaCreada);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaPrendaResponseDTO>> listarTodas() {

        return ResponseEntity.ok(
                categoriaPrendaService.listarTodas()
        );
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<CategoriaPrendaResponseDTO> buscarPorId(
            @PathVariable Long idCategoria
    ) {
        return ResponseEntity.ok(
                categoriaPrendaService.buscarPorId(idCategoria)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<CategoriaPrendaResponseDTO> buscarPorNombre(
            @PathVariable String nombre
    ) {
        return ResponseEntity.ok(
                categoriaPrendaService.buscarPorNombre(nombre)
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CategoriaPrendaResponseDTO>>
    buscarPorCoincidencia(
            @RequestParam String texto
    ) {
        return ResponseEntity.ok(
                categoriaPrendaService.buscarPorCoincidencia(texto)
        );
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaPrendaResponseDTO> actualizarCategoria(
            @PathVariable Long idCategoria,
            @Valid @RequestBody CrearCategoriaPrendaRequestDTO dto
    ) {
        return ResponseEntity.ok(
                categoriaPrendaService.actualizarCategoria(
                        idCategoria,
                        dto
                )
        );
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> eliminarCategoria(
            @PathVariable Long idCategoria
    ) {
        categoriaPrendaService.eliminarCategoria(idCategoria);

        return ResponseEntity.noContent().build();
    }
}