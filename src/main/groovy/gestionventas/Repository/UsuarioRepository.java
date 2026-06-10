package gestionventas.Repository;

import gestionventas.Model.Rol;
import gestionventas.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    List<Usuario> findByRol(Rol rol);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByIdUsuario(String idUsuario);
}
