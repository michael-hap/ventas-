package gestionventas.Repository;

import gestionventas.Model.Envio;
import gestionventas.Model.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findByGuia(String guia);

    boolean existsByGuia(String guia);

    List<Envio> findByEstado(EstadoEnvio estado);

    List<Envio> findByDireccion_IdDireccion(Long idDireccion);

    List<Envio> findByFechaHoraEnvioAfter(LocalDateTime fecha);

    List<Envio> findByFechaHoraEnvioBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );

    List<Envio> findByCostoGreaterThan(java.math.BigDecimal costo);

    List<Envio> findByCostoBetween(
            java.math.BigDecimal costoMin,
            java.math.BigDecimal costoMax
    );

    void deleteByGuia(String guia);

    }