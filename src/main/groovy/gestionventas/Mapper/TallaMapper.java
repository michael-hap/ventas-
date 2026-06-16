package gestionventas.Mapper;

import gestionventas.Dto.TallaResponseDTO;
import gestionventas.Model.Talla;
import org.springframework.stereotype.Component;

@Component
public class TallaMapper {

    public TallaResponseDTO toTallaResponseDTO(Talla talla){

        TallaResponseDTO dto = new TallaResponseDTO();

        dto.setNombre(talla.getNombre());

        return dto;
    }

}
