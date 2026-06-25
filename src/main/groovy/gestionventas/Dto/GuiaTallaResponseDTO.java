package gestionventas.Dto;

import lombok.Data;

@Data
public class GuiaTallaResponseDTO {

    private Long idGuiaTalla;

    private Double pecho;

    private Double cintura;

    private Double cadera;

    private Double largo;

    private Long idTalla;

    private String talla;

    private Long idPrenda;

    private String nombrePrenda;
}
