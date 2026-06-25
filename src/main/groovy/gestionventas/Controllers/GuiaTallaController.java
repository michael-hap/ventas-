package gestionventas.Controllers;

import gestionventas.Model.GuiaTalla;
import gestionventas.Services.GuiaTallaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guias-talla")
@RequiredArgsConstructor
public class GuiaTallaController {

    private final GuiaTallaService guiaTallaService;

    @PostMapping
    public ResponseEntity<GuiaTalla> crearGuiaTalla(
            @RequestBody GuiaTalla guiaTalla,
            @RequestParam Long idTalla,
            @RequestParam Long idPrenda) {

        return ResponseEntity.ok(
                guiaTallaService.crearGuiaTalla(
                        guiaTalla,
                        idTalla,
                        idPrenda
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<GuiaTalla>> listarTodas() {

        return ResponseEntity.ok(
                guiaTallaService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuiaTalla> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                guiaTallaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuiaTalla> actualizarGuiaTalla(
            @PathVariable Long id,
            @RequestBody GuiaTalla guiaTalla,
            @RequestParam Long idTalla,
            @RequestParam Long idPrenda) {

        return ResponseEntity.ok(
                guiaTallaService.actualizarGuiaTalla(
                        id,
                        guiaTalla,
                        idTalla,
                        idPrenda
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGuiaTalla(
            @PathVariable Long id) {

        guiaTallaService.eliminarGuiaTalla(id);

        return ResponseEntity.noContent().build();
    }
}