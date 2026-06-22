package gestionventas.Repository;

import gestionventas.Model.Direccion;
import gestionventas.Model.TarifaEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository
        extends JpaRepository<Direccion, Long> {

    List<Direccion> findByCiudadIgnoreCase(String ciudad);

    List<Direccion> findByDepartamento(
            TarifaEnvio departamento
    );

    List<Direccion> findByBarrioIgnoreCase(String barrio);

    List<Direccion> findByDireccionContainingIgnoreCase(
            String direccion
    );

    boolean existsByDireccionIgnoreCaseAndCiudadIgnoreCase(
            String direccion,
            String ciudad
    );
}