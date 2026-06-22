package gestionventas.Dto;

import gestionventas.Model.Genero;
import lombok.Data;

import java.util.List;

@Data
public class PrendaResponseDTO {

    private Long idPrenda;

    private String nombre;

    private String imagen;

    private String descripcion;

    private String dimensiones;

    private Genero genero;

    private String tipoTela;

    private Long idCategoria;

    private String categoria;

    private List<String> tallas;
}