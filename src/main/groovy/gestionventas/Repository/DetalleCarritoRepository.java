package gestionventas.Repository;

import gestionventas.Model.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {

    List<DetalleCarrito> findByCarrito_IdCarrito(Long idCarrito);

    List<DetalleCarrito> findByPrenda_IdPrenda(Long idPrenda);

    DetalleCarrito findByCarrito_IdCarritoAndPrenda_IdPrenda(
            Long idCarrito,
            Long idPrenda
    );

    boolean existsByCarrito_IdCarritoAndPrenda_IdPrenda(
            Long idCarrito,
            Long idPrenda
    );

    void deleteByCarrito_IdCarritoAndPrenda_IdPrenda(
            Long idCarrito,
            Long idPrenda
    );
}