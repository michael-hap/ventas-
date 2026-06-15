package gestionventas.Dto;

import gestionventas.Model.EstadoEnvio;
import gestionventas.Model.TarifaEnvio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EnvioResponseDTO {

    private Long idEnvio;

    private String guia;

    private LocalDateTime fechaHoraEnvio;

    private EstadoEnvio estado;

    private BigDecimal costo;

    private Long idDireccion;

    private String direccion;

    private String barrio;

    private String ciudad;

    private TarifaEnvio departamento;
}