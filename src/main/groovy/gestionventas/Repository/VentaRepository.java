package gestionventas.Repository;

import gestionventas.Model.EstadoVenta;
import gestionventas.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByEstadoVenta(EstadoVenta estadoVenta);

    List<Venta> findByFechaHoraRegistroBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );

    List<Venta> findByTotalGreaterThanEqual(BigDecimal total);

    List<Venta> findByTotalLessThanEqual(BigDecimal total);

    Long countByEstadoVenta(EstadoVenta estadoVenta);

    boolean existsByIdVenta(Long idVenta);
}