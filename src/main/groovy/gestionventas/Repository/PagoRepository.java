package gestionventas.Repository;

import gestionventas.Model.EstadoPago;
import gestionventas.Model.MetodoPago;
import gestionventas.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByEstadoPago(EstadoPago estadoPago);

    List<Pago> findByMetodoPago(MetodoPago metodoPago);

    List<Pago> findByFechaAfter(LocalDateTime fecha);

    List<Pago> findByFechaBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );

    List<Pago> findByTotalPagoGreaterThan(Double totalPago);

    List<Pago> findByTotalPagoBetween(
            Double minimo,
            Double maximo
    );

    List<Pago> findByEstadoPagoAndMetodoPago(
            EstadoPago estadoPago,
            MetodoPago metodoPago
    );
}