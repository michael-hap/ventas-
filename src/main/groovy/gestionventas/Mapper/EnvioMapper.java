package gestionventas.Mapper;

import gestionventas.Dto.CrearEnvioRequestDTO;
import gestionventas.Dto.EnvioResponseDTO;
import gestionventas.Model.Direccion;
import gestionventas.Model.Envio;
import org.springframework.stereotype.Component;

@Component
public class EnvioMapper {

    public EnvioResponseDTO toEnvioResponseDTO(Envio envio) {

        EnvioResponseDTO dto = new EnvioResponseDTO();

        dto.setIdEnvio(envio.getIdEnvio());
        dto.setGuia(envio.getGuia());
        dto.setFechaHoraEnvio(envio.getFechaHoraEnvio());
        dto.setEstado(envio.getEstado());
        dto.setCosto(envio.getCosto());

        dto.setIdDireccion(envio.getDireccion().getIdDireccion());
        dto.setDireccion(envio.getDireccion().getDireccion());
        dto.setBarrio(envio.getDireccion().getBarrio());
        dto.setCiudad(envio.getDireccion().getCiudad());
        dto.setDepartamento(envio.getDireccion().getDepartamento());

        return dto;
    }

    public Envio aEntidad(CrearEnvioRequestDTO dto, Direccion direccion) {

        Envio envio = new Envio();

        envio.setGuia(dto.getGuia());
        envio.setEstado(dto.getEstado());
        envio.setDireccion(direccion);

        return envio;
    }
}