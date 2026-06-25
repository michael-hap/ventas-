package gestionventas.Services;

import gestionventas.Dto.CrearFacturaRequestDTO;
import gestionventas.Dto.FacturaResponseDTO;
import gestionventas.Mapper.FacturaMapper;
import gestionventas.Model.Factura;
import gestionventas.Repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;

    public FacturaResponseDTO crearFactura(CrearFacturaRequestDTO dto) {

        Factura factura = facturaMapper.aEntidad(dto);

        Factura guardada = facturaRepository.save(factura);

        return facturaMapper.toFacturaResponseDTO(guardada);
    }

    public List<FacturaResponseDTO> listarFacturas() {

        return facturaRepository.findAll()
                .stream()
                .map(facturaMapper::toFacturaResponseDTO)
                .collect(Collectors.toList());
    }

    public FacturaResponseDTO obtenerPorId(Long id) {

        return facturaMapper.toFacturaResponseDTO(
                buscarFactura(id)
        );
    }

    public FacturaResponseDTO actualizarFactura(
            Long id,
            CrearFacturaRequestDTO dto) {

        Factura factura = buscarFactura(id);

        factura.setDescripcion(dto.getDescripcion());
        factura.setSubtotal(dto.getSubtotal());
        factura.setTotal(dto.getTotal());

        Factura actualizada = facturaRepository.save(factura);

        return facturaMapper.toFacturaResponseDTO(actualizada);
    }

    public void eliminarFactura(Long id) {

        Factura factura = buscarFactura(id);

        facturaRepository.delete(factura);
    }

    private Factura buscarFactura(Long id) {

        return facturaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Factura no encontrada"
                        ));
    }
}