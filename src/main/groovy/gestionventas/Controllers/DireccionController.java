package gestionventas.Controllers;

import gestionventas.Dto.CrearDireccionRequestDTO;
import gestionventas.Dto.DireccionResponseDTO;
import gestionventas.Model.TarifaEnvio;
import gestionventas.Services.DireccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionService direccionService;

    @PostMapping
    public ResponseEntity<DireccionResponseDTO> crearDireccion(
            @Valid @RequestBody CrearDireccionRequestDTO dto
    ) {
        DireccionResponseDTO direccionCreada =
                direccionService.crearDireccion(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(direccionCreada);
    }

    @GetMapping
    public ResponseEntity<List<DireccionResponseDTO>> listarDirecciones() {

        List<DireccionResponseDTO> direcciones =
                direccionService.listarTodas();

        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/{idDireccion}")
    public ResponseEntity<DireccionResponseDTO> buscarPorId(
            @PathVariable("idDireccion") Long idDireccion
    ) {
        DireccionResponseDTO direccion =
                direccionService.buscarPorId(idDireccion);

        return ResponseEntity.ok(direccion);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<DireccionResponseDTO>> buscarPorCiudad(
            @PathVariable("ciudad") String ciudad
    ) {
        List<DireccionResponseDTO> direcciones =
                direccionService.buscarPorCiudad(ciudad);

        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<DireccionResponseDTO>> buscarPorDepartamento(
            @PathVariable("departamento") TarifaEnvio departamento
    ) {
        List<DireccionResponseDTO> direcciones =
                direccionService.buscarPorDepartamento(departamento);

        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/barrio/{barrio}")
    public ResponseEntity<List<DireccionResponseDTO>> buscarPorBarrio(
            @PathVariable("barrio") String barrio
    ) {
        List<DireccionResponseDTO> direcciones =
                direccionService.buscarPorBarrio(barrio);

        return ResponseEntity.ok(direcciones);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DireccionResponseDTO>> buscarPorTextoDireccion(
            @RequestParam("textoDireccion") String textoDireccion
    ) {
        return ResponseEntity.ok(
                direccionService.buscarPorTextoDireccion(textoDireccion)
        );
    }

    @PutMapping("/{idDireccion}")
    public ResponseEntity<DireccionResponseDTO> actualizarDireccion(
            @PathVariable("idDireccion") Long idDireccion,
            @Valid @RequestBody CrearDireccionRequestDTO dto
    ) {
        DireccionResponseDTO direccionActualizada =
                direccionService.actualizarDireccion(idDireccion, dto);

        return ResponseEntity.ok(direccionActualizada);
    }

    @DeleteMapping("/{idDireccion}")
    public ResponseEntity<Void> eliminarDireccion(
            @PathVariable("idDireccion") Long idDireccion
    ) {
        direccionService.eliminarDireccion(idDireccion);
        return ResponseEntity.noContent().build();
    }
}