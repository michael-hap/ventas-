package gestionventas.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CrearGuiaTallaRequestDTO {

    @NotNull
    @Positive
    private Double pecho;

    @NotNull
    @Positive
    private Double cintura;

    @NotNull
    @Positive
    private Double cadera;

    @NotNull
    @Positive
    private Double largo;

    @NotNull
    private Long idTalla;

    @NotNull
    private Long idPrenda;
}