package gestionventas.Repository;

import gestionventas.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByStockGreaterThan(int stock);

    List<Inventario> findByStockLessThan(int stock);

    List<Inventario> findByStock(int stock);
}