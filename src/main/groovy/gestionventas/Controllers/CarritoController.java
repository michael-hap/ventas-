package gestionventas.Controllers;

import gestionventas.Model.Carrito;
import gestionventas.Model.DetalleCarrito;
import gestionventas.Services.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping("/usuario/{idUsuario}")
    public Carrito crearCarrito(
            @PathVariable Long idUsuario) {

        return carritoService.crearCarrito(idUsuario);
    }

    @GetMapping("/usuario/{idUsuario}")
    public Carrito buscarCarritoPorUsuario(
            @PathVariable Long idUsuario) {

        return carritoService.buscarCarritoPorUsuario(idUsuario);
    }

    @PostMapping("/{idUsuario}/prendas/{idPrenda}")
    public Carrito agregarPrenda(
            @PathVariable Long idUsuario,
            @PathVariable Long idPrenda,
            @RequestParam int cantidad) {

        return carritoService.agregarPrenda(
                idUsuario,
                idPrenda,
                cantidad
        );
    }

    @PutMapping("/{idUsuario}/prendas/{idPrenda}")
    public Carrito cambiarCantidad(
            @PathVariable Long idUsuario,
            @PathVariable Long idPrenda,
            @RequestParam int cantidad) {

        return carritoService.cambiarCantidad(
                idUsuario,
                idPrenda,
                cantidad
        );
    }

    @DeleteMapping("/{idUsuario}/prendas/{idPrenda}")
    public Carrito eliminarPrenda(
            @PathVariable Long idUsuario,
            @PathVariable Long idPrenda) {

        return carritoService.eliminarPrenda(
                idUsuario,
                idPrenda
        );
    }

    @DeleteMapping("/{idUsuario}/vaciar")
    public Carrito vaciarCarrito(
            @PathVariable Long idUsuario) {

        return carritoService.vaciarCarrito(idUsuario);
    }

    @GetMapping("/{idUsuario}/detalles")
    public List<DetalleCarrito> listarDetalles(
            @PathVariable Long idUsuario) {

        return carritoService.listarDetalles(idUsuario);
    }
}