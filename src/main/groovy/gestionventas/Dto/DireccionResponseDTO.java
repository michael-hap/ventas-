package gestionventas.Dto;

import gestionventas.Model.TarifaEnvio;
import lombok.Data;

@Data
public class DireccionResponseDTO {

    private Long idDireccion;

    private String ciudad;

    private TarifaEnvio departamento;

    private String direccion;

    private String barrio;
}