package gestionventas.Services;
import gestionventas.Dto.CrearPrendaRequestDTO;
import gestionventas.Dto.PrendaResponseDTO;
import gestionventas.Mapper.PrendaMapper;
import gestionventas.Model.CategoriaPrenda;
import gestionventas.Model.Prenda;
import gestionventas.Model.Talla;
import gestionventas.Repository.CategoriaPrendaRepository;
import gestionventas.Repository.PrendaRepository;
import gestionventas.Repository.TallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrendaService {

    private final PrendaRepository prendaRepository;
    private final CategoriaPrendaRepository categoriaRepository;
    private final TallaRepository tallaRepository;
    private final PrendaMapper prendaMapper;

    public PrendaResponseDTO crearPrenda(CrearPrendaRequestDTO dto) {

        CategoriaPrenda categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada"));

        List<Talla> tallas = tallaRepository.findAllById(dto.getIdsTallas());

        Prenda prenda = prendaMapper.aEntidad(dto);

        prenda.setCategoria(categoria);
        prenda.setTallas(tallas);

        Prenda guardada = prendaRepository.save(prenda);

        return prendaMapper.toPrendaResponseDTO(guardada);
    }

    public List<PrendaResponseDTO> listarPrendas() {

        return prendaRepository.findAll()
                .stream()
                .map(prendaMapper::toPrendaResponseDTO)
                .collect(Collectors.toList());
    }

    public PrendaResponseDTO obtenerPorId(Long id) {

        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prenda no encontrada"));

        return prendaMapper.toPrendaResponseDTO(prenda);
    }

    public void eliminarPrenda(Long id) {

        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prenda no encontrada"));

        prendaRepository.delete(prenda);
    }

    public PrendaResponseDTO actualizarPrenda(
            Long id,
            CrearPrendaRequestDTO dto) {

        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Prenda no encontrada"));

        CategoriaPrenda categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() ->
                        new RuntimeException("Categoría no encontrada"));

        List<Talla> tallas = tallaRepository.findAllById(dto.getIdsTallas());

        prenda.setNombre(dto.getNombre());
        prenda.setImagen(dto.getImagen());
        prenda.setDescripcion(dto.getDescripcion());
        prenda.setDimensiones(dto.getDimensiones());
        prenda.setGenero(dto.getGenero());
        prenda.setTipoTela(dto.getTipoTela());
        prenda.setCategoria(categoria);
        prenda.setTallas(tallas);

        Prenda actualizada = prendaRepository.save(prenda);

        return prendaMapper.toPrendaResponseDTO(actualizada);
    }
}