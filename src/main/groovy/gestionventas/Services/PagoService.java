package gestionventas.Services;

import gestionventas.Dto.CrearPagoRequestDTO;
import gestionventas.Dto.PagoResponseDTO;
import gestionventas.Mapper.PagoMapper;
import gestionventas.Model.EstadoPago;
import gestionventas.Model.Pago;
import gestionventas.Model.Usuario;
import gestionventas.Repository.PagoRepository;
import gestionventas.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagoMapper pagoMapper;

    public PagoResponseDTO crearPago(CrearPagoRequestDTO dto) {

        Usuario cliente = null;

        if (dto.getIdCliente() != null) {
            cliente = usuarioRepository.findById(dto.getIdCliente())
                    .orElseThrow(() ->
                            new RuntimeException("Cliente no encontrado"));
        }

        Pago pago = pagoMapper.aEntidad(dto, cliente);

        pago.setFecha(LocalTime.now());

        // Estado inicial por defecto
        pago.setEstadoPago(EstadoPago.PENDIENTE);

        Pago guardado = pagoRepository.save(pago);

        return pagoMapper.toPagoResponseDTO(guardado);
    }

    public List<PagoResponseDTO> listarPagos() {

        return pagoRepository.findAll()
                .stream()
                .map(pagoMapper::toPagoResponseDTO)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO buscarPorId(Long id) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));

        return pagoMapper.toPagoResponseDTO(pago);
    }

    public List<PagoResponseDTO> buscarPorEstado(EstadoPago estado) {

        return pagoRepository.findByEstadoPago(estado)
                .stream()
                .map(pagoMapper::toPagoResponseDTO)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO actualizarPago(
            Long id,
            CrearPagoRequestDTO dto
    ) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));

        Usuario cliente = null;

        if (dto.getIdCliente() != null) {
            cliente = usuarioRepository.findById(dto.getIdCliente())
                    .orElseThrow(() ->
                            new RuntimeException("Cliente no encontrado"));
        }

        pago.setTotalPago(dto.getTotalPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setCliente(cliente);

        Pago actualizado = pagoRepository.save(pago);

        return pagoMapper.toPagoResponseDTO(actualizado);
    }

    public PagoResponseDTO actualizarEstado(
            Long id,
            EstadoPago estado
    ) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));

        pago.setEstadoPago(estado);

        Pago actualizado = pagoRepository.save(pago);

        return pagoMapper.toPagoResponseDTO(actualizado);
    }

    public void eliminarPago(Long id) {

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));

        pagoRepository.delete(pago);
    }
}