package gestionventas.Repository;

import gestionventas.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Talla, Long> {

    boolean existsByCiudad(String ciudad);
    Optional<Direccion> findByCiudad(String ciudad);

    List<Direccion> findByDepartamento(TarifaEnvio departamento);

    boolean existsByDireccion(String direccion);
    Optional<Direccion> findByDireccion(String direccion);

    boolean existsByBarrio(String barrio);
    Optional<Direccion> findByBarrio(String barrio);


}
