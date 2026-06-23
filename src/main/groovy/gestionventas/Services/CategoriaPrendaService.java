package gestionventas.Services;

import gestionventas.Dto.CategoriaPrendaResponseDTO;
import gestionventas.Dto.CrearCategoriaPrendaRequestDTO;
import gestionventas.Mapper.CategoriaPrendaMapper;
import gestionventas.Model.CategoriaPrenda;
import gestionventas.Repository.CategoriaPrendaRepository;
import gestionventas.Repository.PrendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaPrendaService {

    private final CategoriaPrendaRepository
            categoriaPrendaRepository;

    private final PrendaRepository prendaRepository;

    private final CategoriaPrendaMapper
            categoriaPrendaMapper;

    @Transactional
    public CategoriaPrendaResponseDTO crearCategoria(
            CrearCategoriaPrendaRequestDTO dto
    ) {
        String nombreLimpio =
                limpiarTexto(dto.getNombre());

        String descripcionLimpia =
                limpiarTexto(dto.getDescripcion());

        validarNombreDuplicado(nombreLimpio);

        CategoriaPrenda categoria =
                categoriaPrendaMapper.aEntidad(dto);

        categoria.setNombre(nombreLimpio);
        categoria.setDescripcion(descripcionLimpia);

        CategoriaPrenda categoriaGuardada =
                categoriaPrendaRepository.save(categoria);

        return categoriaPrendaMapper
                .toCategoriaPrendaResponseDTO(
                        categoriaGuardada
                );
    }

    @Transactional(readOnly = true)
    public CategoriaPrendaResponseDTO buscarPorId(
            Long idCategoria
    ) {
        CategoriaPrenda categoria =
                buscarEntidadPorId(idCategoria);

        return categoriaPrendaMapper
                .toCategoriaPrendaResponseDTO(categoria);
    }

    @Transactional(readOnly = true)
    public CategoriaPrendaResponseDTO buscarPorNombre(
            String nombre
    ) {
        String nombreLimpio = limpiarTexto(nombre);

        CategoriaPrenda categoria =
                categoriaPrendaRepository
                        .findByNombreIgnoreCase(
                                nombreLimpio
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "No existe la categoría: "
                                                + nombreLimpio
                                )
                        );

        return categoriaPrendaMapper
                .toCategoriaPrendaResponseDTO(categoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaPrendaResponseDTO>
    listarTodas() {

        return categoriaPrendaRepository
                .findAllByOrderByNombreAsc()
                .stream()
                .map(
                        categoriaPrendaMapper
                                ::toCategoriaPrendaResponseDTO
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CategoriaPrendaResponseDTO>
    buscarPorCoincidencia(String texto) {

        return categoriaPrendaRepository
                .findByNombreContainingIgnoreCase(
                        limpiarTexto(texto)
                )
                .stream()
                .map(
                        categoriaPrendaMapper
                                ::toCategoriaPrendaResponseDTO
                )
                .toList();
    }

    @Transactional
    public CategoriaPrendaResponseDTO
    actualizarCategoria(
            Long idCategoria,
            CrearCategoriaPrendaRequestDTO dto
    ) {
        CategoriaPrenda categoriaActual =
                buscarEntidadPorId(idCategoria);

        String nuevoNombre =
                limpiarTexto(dto.getNombre());

        String nuevaDescripcion =
                limpiarTexto(dto.getDescripcion());

        boolean cambioNombre =
                !categoriaActual.getNombre()
                        .equalsIgnoreCase(nuevoNombre);

        if (cambioNombre) {
            validarNombreDuplicado(nuevoNombre);
        }

        categoriaActual.setNombre(nuevoNombre);
        categoriaActual.setDescripcion(
                nuevaDescripcion
        );

        CategoriaPrenda categoriaActualizada =
                categoriaPrendaRepository
                        .save(categoriaActual);

        return categoriaPrendaMapper
                .toCategoriaPrendaResponseDTO(
                        categoriaActualizada
                );
    }

    @Transactional
    public void eliminarCategoria(Long idCategoria) {

        CategoriaPrenda categoria =
                buscarEntidadPorId(idCategoria);

        boolean tienePrendas =
                prendaRepository
                        .existsByCategoria_IdCategoria(
                                idCategoria
                        );

        if (tienePrendas) {
            throw new RuntimeException(
                    "No se puede eliminar la categoría "
                            + "porque tiene prendas asociadas"
            );
        }

        categoriaPrendaRepository.delete(categoria);
    }

    private CategoriaPrenda buscarEntidadPorId(
            Long idCategoria
    ) {
        return categoriaPrendaRepository
                .findById(idCategoria)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la categoría con id: "
                                        + idCategoria
                        )
                );
    }

    private void validarNombreDuplicado(
            String nombre
    ) {
        boolean existe =
                categoriaPrendaRepository
                        .existsByNombreIgnoreCase(nombre);

        if (existe) {
            throw new RuntimeException(
                    "Ya existe una categoría con el nombre: "
                            + nombre
            );
        }
    }

    private String limpiarTexto(String texto) {

        if (texto == null || texto.isBlank()) {
            throw new RuntimeException(
                    "El texto no puede estar vacío"
            );
        }

        return texto.trim();
    }
}