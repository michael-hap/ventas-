package gestionventas.Services;

import gestionventas.Dto.CrearInventarioRequestDTO;
import gestionventas.Dto.InventarioResponseDTO;
import gestionventas.Mapper.InventarioMapper;
import gestionventas.Model.Inventario;
import gestionventas.Repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;

    public InventarioResponseDTO crearInventario(CrearInventarioRequestDTO dto) {

        Inventario inventario = inventarioMapper.aEntidad(dto);

        Inventario guardado = inventarioRepository.save(inventario);

        return inventarioMapper.toInventarioResponseDTO(guardado);
    }

    public List<InventarioResponseDTO> listarInventarios() {

        return inventarioRepository.findAll()
                .stream()
                .map(inventarioMapper::toInventarioResponseDTO)
                .collect(Collectors.toList());
    }

    public InventarioResponseDTO buscarPorId(Long id) {

        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventario no encontrado"));

        return inventarioMapper.toInventarioResponseDTO(inventario);
    }

    public InventarioResponseDTO actualizarInventario(
            Long id,
            CrearInventarioRequestDTO dto) {

        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventario no encontrado"));

        inventario.setStock(dto.getStock());

        Inventario actualizado = inventarioRepository.save(inventario);

        return inventarioMapper.toInventarioResponseDTO(actualizado);
    }

    public void eliminarInventario(Long id) {

        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventario no encontrado"));

        inventarioRepository.delete(inventario);
    }
}