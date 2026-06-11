package gestionventas.Repository;

import gestionventas.Model.GuiaTalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuiaTallaRepository extends JpaRepository<GuiaTalla, Long> {

    List<GuiaTalla> findByTalla_IdTalla(Long idTalla);

    List<GuiaTalla> findByPrenda_IdPrenda(Long idPrenda);

    Optional<GuiaTalla> findByTalla_IdTallaAndPrenda_IdPrenda(Long idTalla, Long idPrenda);

    boolean existsByTalla_IdTallaAndPrenda_IdPrenda(Long idTalla, Long idPrenda);

    List<GuiaTalla> findByPechoBetween(Double minimo, Double maximo);

    List<GuiaTalla> findByCinturaBetween(Double minimo, Double maximo);

    List<GuiaTalla> findByCaderaBetween(Double minimo, Double maximo);

    List<GuiaTalla> findByLargoBetween(Double minimo, Double maximo);
}