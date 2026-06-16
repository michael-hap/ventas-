package gestionventas.Mapper;

import gestionventas.Dto.TallaResponseDTO;
import gestionventas.Model.Talla;

public class TallaMapper {

    public TallaResponseDTO toTallaResponseDTO(Talla talla){

        TallaResponseDTO dto = new TallaResponseDTO();

        dto.setNombre(talla.getNombre());

        return dto;
    }

}
