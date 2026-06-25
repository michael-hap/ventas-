package gestionventas.Mapper;

import gestionventas.Dto.CrearGuiaTallaRequestDTO;
import gestionventas.Dto.GuiaTallaResponseDTO;
import gestionventas.Model.GuiaTalla;
import org.springframework.stereotype.Component;

@Component
public class GuiaTallaMapper {

    public GuiaTallaResponseDTO toResponseDTO(
            GuiaTalla guiaTalla) {

        GuiaTallaResponseDTO dto =
                new GuiaTallaResponseDTO();

        dto.setIdGuiaTalla(
                guiaTalla.getIdGuia());

        dto.setPecho(
                guiaTalla.getPecho());

        dto.setCintura(
                guiaTalla.getCintura());

        dto.setCadera(
                guiaTalla.getCadera());

        dto.setLargo(
                guiaTalla.getLargo());

        dto.setIdTalla(
                guiaTalla.getTalla().getIdTalla());

        dto.setTalla(
                guiaTalla.getTalla().getNombre());

        dto.setIdPrenda(
                guiaTalla.getPrenda().getIdPrenda());

        dto.setNombrePrenda(
                guiaTalla.getPrenda().getNombre());

        return dto;
    }

    public GuiaTalla aEntidad(
            CrearGuiaTallaRequestDTO dto) {

        GuiaTalla guiaTalla =
                new GuiaTalla();

        guiaTalla.setPecho(dto.getPecho());
        guiaTalla.setCintura(dto.getCintura());
        guiaTalla.setCadera(dto.getCadera());
        guiaTalla.setLargo(dto.getLargo());

        return guiaTalla;
    }
}