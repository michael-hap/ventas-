package gestionventas.Services;

import gestionventas.Dto.CrearEnvioRequestDTO;
import gestionventas.Dto.EnvioResponseDTO;
import gestionventas.Mapper.EnvioMapper;
import gestionventas.Model.Direccion;
import gestionventas.Model.Envio;
import gestionventas.Model.EstadoEnvio;
import gestionventas.Repository.DireccionRepository;
import gestionventas.Repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepository;
    private final DireccionRepository direccionRepository;
    private final EnvioMapper envioMapper;

    @Transactional
    public EnvioResponseDTO crearEnvio(CrearEnvioRequestDTO dto) {

        validarGuiaNoRepetida(dto.getGuia());

        Direccion direccion = buscarDireccionPorId(dto.getIdDireccion());

        Envio envio = envioMapper.aEntidad(dto, direccion);

        Envio envioGuardado = envioRepository.save(envio);

        return envioMapper.toEnvioResponseDTO(envioGuardado);
    }

    @Transactional(readOnly = true)
    public EnvioResponseDTO buscarPorId(Long idEnvio) {

        Envio envio = buscarEnvioPorId(idEnvio);

        return envioMapper.toEnvioResponseDTO(envio);
    }

    @Transactional(readOnly = true)
    public EnvioResponseDTO buscarPorGuia(String guia) {

        Envio envio = envioRepository.findByGuia(guia)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe un envío con la guía: " + guia
                        )
                );

        return envioMapper.toEnvioResponseDTO(envio);
    }

    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> listarTodos() {

        return envioRepository.findAll()
                .stream()
                .map(envioMapper::toEnvioResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> listarPorEstado(EstadoEnvio estado) {

        return envioRepository.findByEstado(estado)
                .stream()
                .map(envioMapper::toEnvioResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<EnvioResponseDTO> listarPorDireccion(Long idDireccion) {

        buscarDireccionPorId(idDireccion);

        return envioRepository.findByDireccion_IdDireccion(idDireccion)
                .stream()
                .map(envioMapper::toEnvioResponseDTO)
                .toList();
    }

    @Transactional
    public EnvioResponseDTO actualizarEstado(
            Long idEnvio,
            EstadoEnvio nuevoEstado
    ) {

        Envio envio = buscarEnvioPorId(idEnvio);

        validarCambioEstado(envio.getEstado(), nuevoEstado);

        envio.setEstado(nuevoEstado);

        Envio envioActualizado = envioRepository.save(envio);

        return envioMapper.toEnvioResponseDTO(envioActualizado);
    }

    @Transactional
    public void eliminarPorId(Long idEnvio) {

        Envio envio = buscarEnvioPorId(idEnvio);

        envioRepository.delete(envio);
    }

    private Envio buscarEnvioPorId(Long idEnvio) {

        return envioRepository.findById(idEnvio)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe el envío con id: " + idEnvio
                        )
                );
    }

    private Direccion buscarDireccionPorId(Long idDireccion) {

        return direccionRepository.findById(idDireccion)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la dirección con id: " + idDireccion
                        )
                );
    }

    private void validarGuiaNoRepetida(String guia) {

        if (envioRepository.existsByGuia(guia)) {
            throw new RuntimeException(
                    "Ya existe un envío con la guía: " + guia
            );
        }
    }

    private void validarCambioEstado(
            EstadoEnvio estadoActual,
            EstadoEnvio nuevoEstado
    ) {

        if (estadoActual == nuevoEstado) {
            throw new RuntimeException(
                    "El envío ya se encuentra en el estado: " + nuevoEstado
            );
        }
    }
}