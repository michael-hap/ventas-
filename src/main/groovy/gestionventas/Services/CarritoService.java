package gestionventas.Services;

import gestionventas.Model.Carrito;
import gestionventas.Model.DetalleCarrito;
import gestionventas.Model.Prenda;
import gestionventas.Model.Usuario;
import gestionventas.Repository.CarritoRepository;
import gestionventas.Repository.DetalleCarritoRepository;
import gestionventas.Repository.PrendaRepository;
import gestionventas.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final DetalleCarritoRepository detalleCarritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PrendaRepository prendaRepository;

    @Transactional
    public Carrito crearCarrito(Long idUsuario) {

        if (carritoRepository.existsByUsuario_IdUsuario(idUsuario)) {
            throw new RuntimeException(
                    "El usuario ya tiene un carrito"
            );
        }

        Usuario usuario = buscarUsuario(idUsuario);

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);

        return carritoRepository.save(carrito);
    }

    @Transactional(readOnly = true)
    public Carrito buscarCarritoPorUsuario(Long idUsuario) {

        return carritoRepository
                .findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() ->
                        new RuntimeException(
                                "El usuario no tiene un carrito"
                        )
                );
    }

    @Transactional
    public Carrito agregarPrenda(
            Long idUsuario,
            Long idPrenda,
            int cantidad
    ) {
        validarCantidad(cantidad);

        Carrito carrito = obtenerOCrearCarrito(idUsuario);

        Prenda prenda = buscarPrenda(idPrenda);

        DetalleCarrito detalleExistente =
                detalleCarritoRepository
                        .findByCarrito_IdCarritoAndPrenda_IdPrenda(
                                carrito.getIdCarrito(),
                                idPrenda
                        )
                        .orElse(null);

        if (detalleExistente != null) {

            int nuevaCantidad =
                    detalleExistente.getCantidad() + cantidad;

            detalleExistente.setCantidad(nuevaCantidad);

            detalleCarritoRepository.save(detalleExistente);

        } else {

            DetalleCarrito nuevoDetalle =
                    new DetalleCarrito();

            nuevoDetalle.setCarrito(carrito);
            nuevoDetalle.setPrenda(prenda);
            nuevoDetalle.setCantidad(cantidad);

            detalleCarritoRepository.save(nuevoDetalle);

            carrito.getDetalles().add(nuevoDetalle);
        }

        return carrito;
    }

    @Transactional
    public Carrito cambiarCantidad(
            Long idUsuario,
            Long idPrenda,
            int nuevaCantidad
    ) {
        validarCantidad(nuevaCantidad);

        Carrito carrito = buscarCarritoPorUsuario(idUsuario);

        DetalleCarrito detalle =
                buscarDetalle(carrito.getIdCarrito(), idPrenda);

        detalle.setCantidad(nuevaCantidad);

        detalleCarritoRepository.save(detalle);

        return carrito;
    }

    @Transactional
    public Carrito eliminarPrenda(
            Long idUsuario,
            Long idPrenda
    ) {
        Carrito carrito = buscarCarritoPorUsuario(idUsuario);

        DetalleCarrito detalle =
                buscarDetalle(carrito.getIdCarrito(), idPrenda);

        carrito.getDetalles().remove(detalle);

        detalleCarritoRepository.delete(detalle);

        return carrito;
    }

    @Transactional
    public Carrito vaciarCarrito(Long idUsuario) {

        Carrito carrito = buscarCarritoPorUsuario(idUsuario);

        carrito.getDetalles().clear();

        return carritoRepository.save(carrito);
    }

    @Transactional(readOnly = true)
    public List<DetalleCarrito> listarDetalles(Long idUsuario) {

        Carrito carrito = buscarCarritoPorUsuario(idUsuario);

        return detalleCarritoRepository
                .findByCarrito_IdCarrito(
                        carrito.getIdCarrito()
                );
    }

    private Carrito obtenerOCrearCarrito(Long idUsuario) {

        return carritoRepository
                .findByUsuario_IdUsuario(idUsuario)
                .orElseGet(() -> {

                    Usuario usuario = buscarUsuario(idUsuario);

                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);

                    return carritoRepository.save(nuevoCarrito);
                });
    }

    private Usuario buscarUsuario(Long idUsuario) {

        return usuarioRepository
                .findById(idUsuario)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe el usuario con id: "
                                        + idUsuario
                        )
                );
    }

    private Prenda buscarPrenda(Long idPrenda) {

        return prendaRepository
                .findById(idPrenda)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la prenda con id: "
                                        + idPrenda
                        )
                );
    }

    private DetalleCarrito buscarDetalle(
            Long idCarrito,
            Long idPrenda
    ) {
        return detalleCarritoRepository
                .findByCarrito_IdCarritoAndPrenda_IdPrenda(
                        idCarrito,
                        idPrenda
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "La prenda no está en el carrito"
                        )
                );
    }

    private void validarCantidad(int cantidad) {

        if (cantidad <= 0) {
            throw new RuntimeException(
                    "La cantidad debe ser mayor a cero"
            );
        }
    }
}