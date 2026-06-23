package gestionventas.Services;

import gestionventas.Dto.CrearTallaRequestDTO;
import gestionventas.Dto.TallaResponseDTO;
import gestionventas.Mapper.TallaMapper;
import gestionventas.Model.Talla;
import gestionventas.Repository.TallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallaService {

    private final TallaRepository tallaRepository;
    private final TallaMapper tallaMapper;

    @Transactional
    public TallaResponseDTO crearTalla(
            CrearTallaRequestDTO dto
    ) {
        String nombreLimpio =
                normalizarNombre(dto.getNombre());

        validarNombreDuplicado(nombreLimpio);

        Talla talla = tallaMapper.aEntidad(dto);

        talla.setNombre(nombreLimpio);

        Talla tallaGuardada =
                tallaRepository.save(talla);

        return tallaMapper
                .toTallaResponseDTO(tallaGuardada);
    }

    @Transactional(readOnly = true)
    public TallaResponseDTO buscarPorId(Long idTalla) {

        Talla talla = buscarEntidadPorId(idTalla);

        return tallaMapper.toTallaResponseDTO(talla);
    }

    @Transactional(readOnly = true)
    public TallaResponseDTO buscarPorNombre(String nombre) {

        String nombreLimpio = normalizarNombre(nombre);

        Talla talla = tallaRepository
                .findByNombreIgnoreCase(nombreLimpio)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la talla: "
                                        + nombreLimpio
                        )
                );

        return tallaMapper.toTallaResponseDTO(talla);
    }

    @Transactional(readOnly = true)
    public List<TallaResponseDTO> listarTodas() {

        return tallaRepository
                .findAllByOrderByNombreAsc()
                .stream()
                .map(tallaMapper::toTallaResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TallaResponseDTO> buscarPorCoincidencia(
            String texto
    ) {
        return tallaRepository
                .findByNombreContainingIgnoreCase(
                        texto.trim()
                )
                .stream()
                .map(tallaMapper::toTallaResponseDTO)
                .toList();
    }

    @Transactional
    public TallaResponseDTO actualizarTalla(
            Long idTalla,
            CrearTallaRequestDTO dto
    ) {
        Talla talla = buscarEntidadPorId(idTalla);

        String nuevoNombre =
                normalizarNombre(dto.getNombre());

        boolean cambioNombre =
                !talla.getNombre()
                        .equalsIgnoreCase(nuevoNombre);

        if (cambioNombre) {
            validarNombreDuplicado(nuevoNombre);
        }

        talla.setNombre(nuevoNombre);

        Talla tallaActualizada =
                tallaRepository.save(talla);

        return tallaMapper
                .toTallaResponseDTO(tallaActualizada);
    }

    @Transactional
    public void eliminarTalla(Long idTalla) {

        Talla talla = buscarEntidadPorId(idTalla);

        boolean tienePrendas =
                talla.getPrendas() != null
                        && !talla.getPrendas().isEmpty();

        boolean tieneGuias =
                talla.getGuiasTalla() != null
                        && !talla.getGuiasTalla().isEmpty();

        if (tienePrendas) {
            throw new RuntimeException(
                    "No se puede eliminar la talla porque "
                            + "está asociada a una o más prendas"
            );
        }

        if (tieneGuias) {
            throw new RuntimeException(
                    "No se puede eliminar la talla porque "
                            + "tiene guías de talla asociadas"
            );
        }

        tallaRepository.delete(talla);
    }

    private Talla buscarEntidadPorId(Long idTalla) {

        return tallaRepository
                .findById(idTalla)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la talla con id: "
                                        + idTalla
                        )
                );
    }

    private void validarNombreDuplicado(String nombre) {

        if (tallaRepository
                .existsByNombreIgnoreCase(nombre)) {

            throw new RuntimeException(
                    "Ya existe una talla con el nombre: "
                            + nombre
            );
        }
    }

    private String normalizarNombre(String nombre) {

        return nombre.trim().toUpperCase();
    }
}