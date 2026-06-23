package gestionventas.Mapper;

import gestionventas.Dto.CrearInventarioRequestDTO;
import gestionventas.Dto.InventarioResponseDTO;
import gestionventas.Model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public Inventario aEntidad(CrearInventarioRequestDTO dto) {

        Inventario inventario = new Inventario();

        inventario.setStock(dto.getStock());

        return inventario;
    }

    public InventarioResponseDTO toInventarioResponseDTO(Inventario inventario) {

        InventarioResponseDTO dto = new InventarioResponseDTO();

        dto.setIdInventario(inventario.getIdInventario());
        dto.setStock(inventario.getStock());

        return dto;
    }
}