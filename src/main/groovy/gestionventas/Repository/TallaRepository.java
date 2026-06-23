package gestionventas.Repository;

import gestionventas.Model.Talla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    Optional<Talla> findByNombreIgnoreCase(String nombre);

    List<Talla> findByNombreContainingIgnoreCase(String nombre);

    List<Talla> findAllByOrderByNombreAsc();
}