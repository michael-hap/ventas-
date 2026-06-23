package gestionventas.Services;

import gestionventas.Dto.CrearDetalleCarritoRequestDTO;
import gestionventas.Dto.DetalleCarritoResponseDTO;
import gestionventas.Mapper.DetalleCarritoMapper;
import gestionventas.Model.Carrito;
import gestionventas.Model.DetalleCarrito;
import gestionventas.Model.Prenda;
import gestionventas.Repository.CarritoRepository;
import gestionventas.Repository.DetalleCarritoRepository;
import gestionventas.Repository.PrendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetalleCarritoService {

    private final DetalleCarritoRepository detalleCarritoRepository;
    private final CarritoRepository carritoRepository;
    private final PrendaRepository prendaRepository;
    private final DetalleCarritoMapper detalleCarritoMapper;

    public DetalleCarritoResponseDTO crearDetalle(
            CrearDetalleCarritoRequestDTO dto) {

        Carrito carrito = carritoRepository
                .findById(dto.getIdCarrito())
                .orElseThrow(() ->
                        new RuntimeException("Carrito no encontrado"));

        Prenda prenda = prendaRepository
                .findById(dto.getIdPrenda())
                .orElseThrow(() ->
                        new RuntimeException("Prenda no encontrada"));

        DetalleCarrito detalle = new DetalleCarrito();

        detalle.setCarrito(carrito);
        detalle.setPrenda(prenda);
        detalle.setCantidad(dto.getCantidad());

        detalle = detalleCarritoRepository.save(detalle);

        return detalleCarritoMapper
                .toDetalleCarritoResponseDTO(detalle);
    }

    public List<DetalleCarritoResponseDTO> listarTodos() {

        return detalleCarritoRepository.findAll()
                .stream()
                .map(detalleCarritoMapper::toDetalleCarritoResponseDTO)
                .collect(Collectors.toList());
    }

    public DetalleCarritoResponseDTO buscarPorId(Long id) {

        DetalleCarrito detalle = detalleCarritoRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Detalle no encontrado"));

        return detalleCarritoMapper
                .toDetalleCarritoResponseDTO(detalle);
    }

    public List<DetalleCarritoResponseDTO> buscarPorCarrito(
            Long idCarrito) {

        return detalleCarritoRepository
                .findByCarrito_IdCarrito(idCarrito)
                .stream()
                .map(detalleCarritoMapper::toDetalleCarritoResponseDTO)
                .collect(Collectors.toList());
    }

    public List<DetalleCarritoResponseDTO> buscarPorPrenda(
            Long idPrenda) {

        return detalleCarritoRepository
                .findByPrenda_IdPrenda(idPrenda)
                .stream()
                .map(detalleCarritoMapper::toDetalleCarritoResponseDTO)
                .collect(Collectors.toList());
    }

    public DetalleCarritoResponseDTO actualizarCantidad(
            Long idDetalle,
            Integer cantidad) {

        if (cantidad <= 0) {
            throw new RuntimeException(
                    "La cantidad debe ser mayor que cero");
        }

        DetalleCarrito detalle = detalleCarritoRepository
                .findById(idDetalle)
                .orElseThrow(() ->
                        new RuntimeException("Detalle no encontrado"));

        detalle.setCantidad(cantidad);

        detalle = detalleCarritoRepository.save(detalle);

        return detalleCarritoMapper
                .toDetalleCarritoResponseDTO(detalle);
    }

    public void eliminar(Long idDetalle) {

        DetalleCarrito detalle = detalleCarritoRepository
                .findById(idDetalle)
                .orElseThrow(() ->
                        new RuntimeException("Detalle no encontrado"));

        detalleCarritoRepository.delete(detalle);
    }
}