package gestionventas.Services;

import gestionventas.Dto.CrearVentaRequestDTO;
import gestionventas.Dto.VentaResponseDTO;
import gestionventas.Mapper.VentaMapper;
import gestionventas.Model.EstadoVenta;
import gestionventas.Model.Venta;
import gestionventas.Repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    public VentaResponseDTO crearVenta(CrearVentaRequestDTO dto) {

        Venta venta = ventaMapper.aEntidad(dto);

        Venta guardada = ventaRepository.save(venta);

        return ventaMapper.toVentaResponseDTO(guardada);
    }

    public List<VentaResponseDTO> listarVentas() {

        return ventaRepository.findAll()
                .stream()
                .map(ventaMapper::toVentaResponseDTO)
                .collect(Collectors.toList());
    }

    public VentaResponseDTO buscarPorId(Long id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));

        return ventaMapper.toVentaResponseDTO(venta);
    }

    public List<VentaResponseDTO> buscarPorEstado(EstadoVenta estado) {

        return ventaRepository.findByEstadoVenta(estado)
                .stream()
                .map(ventaMapper::toVentaResponseDTO)
                .collect(Collectors.toList());
    }

    public void eliminarVenta(Long id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));

        ventaRepository.delete(venta);
    }
}