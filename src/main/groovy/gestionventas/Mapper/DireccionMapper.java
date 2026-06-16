package gestionventas.Mapper;

import gestionventas.Dto.DireccionResponseDTO;
import gestionventas.Dto.CrearDireccionRequestDTO;
import gestionventas.Model.Direccion;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    public DireccionResponseDTO toDireccionResponseDTO(Direccion direccion) {

        DireccionResponseDTO dto = new DireccionResponseDTO();

        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCiudad(direccion.getCiudad());
        dto.setDepartamento(direccion.getDepartamento());
        dto.setDireccion(direccion.getDireccion());
        dto.setBarrio(direccion.getBarrio());

        return dto;
    }

    public Direccion aEntidad(CrearDireccionRequestDTO dto) {

        Direccion direccion = new Direccion();

        direccion.setCiudad(dto.getCiudad());
        direccion.setDepartamento(dto.getDepartamento());
        direccion.setDireccion(dto.getDireccion());
        direccion.setBarrio(dto.getBarrio());

        return direccion;
    }
}