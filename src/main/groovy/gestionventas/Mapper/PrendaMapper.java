package gestionventas.Mapper;

import gestionventas.Dto.CrearPrendaRequestDTO;
import gestionventas.Dto.PrendaResponseDTO;
import gestionventas.Model.Prenda;
import gestionventas.Model.Talla;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PrendaMapper {

    public Prenda aEntidad(CrearPrendaRequestDTO dto) {

        Prenda prenda = new Prenda();

        prenda.setNombre(dto.getNombre());
        prenda.setImagen(dto.getImagen());
        prenda.setDescripcion(dto.getDescripcion());
        prenda.setDimensiones(dto.getDimensiones());
        prenda.setGenero(dto.getGenero());
        prenda.setTipoTela(dto.getTipoTela());

        return prenda;
    }

    public PrendaResponseDTO toPrendaResponseDTO(Prenda prenda) {

        PrendaResponseDTO dto = new PrendaResponseDTO();

        dto.setIdPrenda(prenda.getIdPrenda());
        dto.setNombre(prenda.getNombre());
        dto.setImagen(prenda.getImagen());
        dto.setDescripcion(prenda.getDescripcion());
        dto.setDimensiones(prenda.getDimensiones());
        dto.setGenero(prenda.getGenero());
        dto.setTipoTela(prenda.getTipoTela());

        dto.setIdCategoria(
                prenda.getCategoria().getIdCategoria()
        );

        dto.setCategoria(
                prenda.getCategoria().getNombre()
        );

        dto.setTallas(
                prenda.getTallas()
                        .stream()
                        .map(Talla::getNombre)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}