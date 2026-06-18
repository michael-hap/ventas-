package gestionventas.Repository;

import gestionventas.Model.Rol;
import gestionventas.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCorreoElectronico(String correoElectronico);
    Optional<Usuario> findByIdUsuario(Long idUsuario);

    List<Usuario> findByRol(Rol rol);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);


}
