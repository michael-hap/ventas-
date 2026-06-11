package gestionventas.Repository;

import gestionventas.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TallaRepository extends JpaRepository<Talla, Long> {
    boolean existsByNombre(String nombre);
    Optional<Talla> findByNombre(String nombre);

    List<Talla> findByNombreContainingIgnoreCase(String nombre);
}
