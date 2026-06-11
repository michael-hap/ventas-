package gestionventas.Repository;

import gestionventas.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaPrendaRepository extends JpaRepository<CategoriaPrenda, Long> {

    Optional<CategoriaPrenda> findByNombre(String nombre);
    Optional<CategoriaPrenda> findByDescripcion(String descripcion);

}
