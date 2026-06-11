package gestionventas.Repository;

import gestionventas.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findByDescripcionContainingIgnoreCase(String descripcion);

    List<Factura> findByFechaHoraFacturaBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );

    List<Factura> findByFechaHoraFacturaAfter(LocalDateTime fecha);

    List<Factura> findByTotalGreaterThan(BigDecimal total);

    List<Factura> findByTotalBetween(
            BigDecimal minimo,
            BigDecimal maximo
    );

    List<Factura> findBySubtotalBetween(
            BigDecimal minimo,
            BigDecimal maximo
    );
}