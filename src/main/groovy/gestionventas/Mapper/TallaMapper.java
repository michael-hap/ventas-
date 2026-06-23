package gestionventas.Mapper;

import gestionventas.Dto.CrearTallaRequestDTO;
import gestionventas.Dto.TallaResponseDTO;
import gestionventas.Model.Talla;
import org.springframework.stereotype.Component;

@Component
public class TallaMapper {

    public TallaResponseDTO toTallaResponseDTO(Talla talla) {

        TallaResponseDTO dto = new TallaResponseDTO();

        dto.setIdTalla(talla.getIdTalla());
        dto.setNombre(talla.getNombre());

        return dto;
    }

    public Talla aEntidad(CrearTallaRequestDTO dto) {

        Talla talla = new Talla();

        talla.setNombre(dto.getNombre());

        return talla;
    }
}