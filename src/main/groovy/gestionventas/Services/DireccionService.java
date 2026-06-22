package gestionventas.Services;

import gestionventas.Dto.CrearDireccionRequestDTO;
import gestionventas.Dto.DireccionResponseDTO;
import gestionventas.Mapper.DireccionMapper;
import gestionventas.Model.Direccion;
import gestionventas.Model.TarifaEnvio;
import gestionventas.Repository.DireccionRepository;
import gestionventas.Repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final EnvioRepository envioRepository;
    private final DireccionMapper direccionMapper;

    @Transactional
    public DireccionResponseDTO crearDireccion(
            CrearDireccionRequestDTO dto
    ) {
        String ciudadLimpia = dto.getCiudad().trim();
        String direccionLimpia = dto.getDireccion().trim();
        String barrioLimpio = limpiarTextoOpcional(dto.getBarrio());

        validarDireccionDuplicada(
                direccionLimpia,
                ciudadLimpia
        );

        Direccion direccion = direccionMapper.aEntidad(dto);

        direccion.setCiudad(ciudadLimpia);
        direccion.setDireccion(direccionLimpia);
        direccion.setBarrio(barrioLimpio);

        Direccion direccionGuardada =
                direccionRepository.save(direccion);

        return direccionMapper
                .toDireccionResponseDTO(direccionGuardada);
    }

    @Transactional(readOnly = true)
    public DireccionResponseDTO buscarPorId(
            Long idDireccion
    ) {
        Direccion direccion =
                buscarEntidadPorId(idDireccion);

        return direccionMapper
                .toDireccionResponseDTO(direccion);
    }

    @Transactional(readOnly = true)
    public List<DireccionResponseDTO> listarTodas() {

        return direccionRepository.findAll()
                .stream()
                .map(direccionMapper::toDireccionResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DireccionResponseDTO> buscarPorCiudad(
            String ciudad
    ) {
        return direccionRepository
                .findByCiudadIgnoreCase(ciudad.trim())
                .stream()
                .map(direccionMapper::toDireccionResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DireccionResponseDTO> buscarPorDepartamento(
            TarifaEnvio departamento
    ) {
        return direccionRepository
                .findByDepartamento(departamento)
                .stream()
                .map(direccionMapper::toDireccionResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DireccionResponseDTO> buscarPorBarrio(
            String barrio
    ) {
        return direccionRepository
                .findByBarrioIgnoreCase(barrio.trim())
                .stream()
                .map(direccionMapper::toDireccionResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DireccionResponseDTO> buscarPorTextoDireccion(
            String texto
    ) {
        return direccionRepository
                .findByDireccionContainingIgnoreCase(texto.trim())
                .stream()
                .map(direccionMapper::toDireccionResponseDTO)
                .toList();
    }

    @Transactional
    public DireccionResponseDTO actualizarDireccion(
            Long idDireccion,
            CrearDireccionRequestDTO dto
    ) {
        Direccion direccionActual =
                buscarEntidadPorId(idDireccion);

        String nuevaCiudad = dto.getCiudad().trim();
        String nuevaDireccion = dto.getDireccion().trim();
        String nuevoBarrio =
                limpiarTextoOpcional(dto.getBarrio());

        boolean cambioUbicacion =
                !direccionActual.getCiudad()
                        .equalsIgnoreCase(nuevaCiudad)
                        ||
                        !direccionActual.getDireccion()
                                .equalsIgnoreCase(nuevaDireccion);

        if (cambioUbicacion) {
            validarDireccionDuplicada(
                    nuevaDireccion,
                    nuevaCiudad
            );
        }

        direccionActual.setCiudad(nuevaCiudad);
        direccionActual.setDepartamento(
                dto.getDepartamento()
        );
        direccionActual.setDireccion(nuevaDireccion);
        direccionActual.setBarrio(nuevoBarrio);

        Direccion actualizada =
                direccionRepository.save(direccionActual);

        return direccionMapper
                .toDireccionResponseDTO(actualizada);
    }

    @Transactional
    public void eliminarDireccion(Long idDireccion) {

        Direccion direccion =
                buscarEntidadPorId(idDireccion);

        boolean tieneEnvios =
                !envioRepository
                        .findByDireccion_IdDireccion(idDireccion)
                        .isEmpty();

        if (tieneEnvios) {
            throw new RuntimeException(
                    "No se puede eliminar la dirección porque "
                            + "tiene envíos asociados"
            );
        }

        direccionRepository.delete(direccion);
    }

    private Direccion buscarEntidadPorId(
            Long idDireccion
    ) {
        return direccionRepository
                .findById(idDireccion)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la dirección con id: "
                                        + idDireccion
                        )
                );
    }

    private void validarDireccionDuplicada(
            String direccion,
            String ciudad
    ) {
        boolean existe =
                direccionRepository
                        .existsByDireccionIgnoreCaseAndCiudadIgnoreCase(
                                direccion,
                                ciudad
                        );

        if (existe) {
            throw new RuntimeException(
                    "La dirección ya está registrada en esa ciudad"
            );
        }
    }

    private String limpiarTextoOpcional(String texto) {

        if (texto == null || texto.isBlank()) {
            return null;
        }

        return texto.trim();
    }
}