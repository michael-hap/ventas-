package gestionventas.Repository;

import gestionventas.Model.Genero;
import gestionventas.Model.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Long> {

    List<Prenda> findByNombre(String nombre);

    List<Prenda> findByNombreContainingIgnoreCase(String nombre);

    List<Prenda> findByGenero(Genero genero);

    List<Prenda> findByTipoTela(String tipoTela);

    List<Prenda> findByCategoria_IdCategoria(Long idCategoria);

    List<Prenda> findByCategoria_IdCategoriaAndGenero(
            Long idCategoria,
            Genero genero
    );

    boolean existsByNombre(String nombre);
}