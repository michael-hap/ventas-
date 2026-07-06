package gestionventas.Controllers;

import gestionventas.Dto.CrearPrendaRequestDTO;
import gestionventas.Dto.PrendaResponseDTO;
import gestionventas.Services.PrendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prendas")
@RequiredArgsConstructor
public class PrendaController {

    private final PrendaService prendaService;

    @PostMapping("/{crear}")
    public PrendaResponseDTO crearPrenda(
            @Valid @RequestBody CrearPrendaRequestDTO dto) {

        return prendaService.crearPrenda(dto);
    }

    @GetMapping
    public List<PrendaResponseDTO> listarPrendas() {

        return prendaService.listarPrendas();
    }

    @GetMapping("/{id}")
    public PrendaResponseDTO obtenerPorId(
            @PathVariable Long id) {

        return prendaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public PrendaResponseDTO actualizarPrenda(
            @PathVariable Long id,
            @Valid @RequestBody CrearPrendaRequestDTO dto) {

        return prendaService.actualizarPrenda(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrenda(
            @PathVariable Long id) {

        prendaService.eliminarPrenda(id);
    }
}