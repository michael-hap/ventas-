package gestionventas.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity
@Table(name= "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Column(name = "Nombre", nullable = false)
    private String nombreUsuario;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Column(name = "Correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotNull(message = "El rol del usuario es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "Rol", nullable = false)
    private Rol rol;

    @Column(name = "Activo", nullable = false)
    private boolean activo = true;
}