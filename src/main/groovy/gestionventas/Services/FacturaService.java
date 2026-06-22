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

        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Factura no encontrada"));

        return facturaMapper.toFacturaResponseDTO(factura);
    }

    public void eliminarFactura(Long id) {

        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Factura no encontrada"));

        facturaRepository.delete(factura);
    }
}