package gestionventas.Mapper;


import gestionventas.Dto.CategoriaPrendaResponseDTO;
import gestionventas.Model.CategoriaPrenda;
import org.springframework.stereotype.Component;

@Component
public class CategoriaPrendaMapper {

    public CategoriaPrendaResponseDTO toCategoriaPrendaResponseDTO(CategoriaPrenda categoriaPrenda){

        CategoriaPrendaResponseDTO dto = new CategoriaPrendaResponseDTO();

        dto.setIdCategoria(categoriaPrenda.getIdCategoria());

        dto.setNombre(categoriaPrenda.getNombre());

        return dto;
    }

}
