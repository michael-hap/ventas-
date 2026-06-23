package gestionventas.Services;

import gestionventas.Model.GuiaTalla;
import gestionventas.Model.Prenda;
import gestionventas.Model.Talla;
import gestionventas.Repository.GuiaTallaRepository;
import gestionventas.Repository.PrendaRepository;
import gestionventas.Repository.TallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuiaTallaService {

    private final GuiaTallaRepository guiaTallaRepository;
    private final TallaRepository tallaRepository;
    private final PrendaRepository prendaRepository;

    @Transactional
    public GuiaTalla crearGuiaTalla(
            GuiaTalla guiaTalla,
            Long idTalla,
            Long idPrenda
    ) {
        validarMedidas(guiaTalla);

        Talla talla = buscarTallaPorId(idTalla);
        Prenda prenda = buscarPrendaPorId(idPrenda);

        validarTallaAsociadaAPrenda(prenda, talla);
        validarGuiaDuplicada(idTalla, idPrenda);

        guiaTalla.setTalla(talla);
        guiaTalla.setPrenda(prenda);

        return guiaTallaRepository.save(guiaTalla);
    }

    @Transactional(readOnly = true)
    public GuiaTalla buscarPorId(Long idGuia) {
        return buscarGuiaPorId(idGuia);
    }

    @Transactional(readOnly = true)
    public List<GuiaTalla> listarTodas() {
        return guiaTallaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<GuiaTalla> buscarPorTalla(Long idTalla) {
        buscarTallaPorId(idTalla);

        return guiaTallaRepository
                .findByTalla_IdTalla(idTalla);
    }

    @Transactional(readOnly = true)
    public List<GuiaTalla> buscarPorPrenda(Long idPrenda) {
        buscarPrendaPorId(idPrenda);

        return guiaTallaRepository
                .findByPrenda_IdPrenda(idPrenda);
    }

    @Transactional(readOnly = true)
    public GuiaTalla buscarPorTallaYPrenda(
            Long idTalla,
            Long idPrenda
    ) {
        return guiaTallaRepository
                .findByTalla_IdTallaAndPrenda_IdPrenda(
                        idTalla,
                        idPrenda
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe una guía para esa talla y prenda"
                        )
                );
    }

    @Transactional
    public GuiaTalla actualizarGuiaTalla(
            Long idGuia,
            GuiaTalla nuevosDatos,
            Long idTalla,
            Long idPrenda
    ) {
        GuiaTalla guiaActual = buscarGuiaPorId(idGuia);

        validarMedidas(nuevosDatos);

        Talla talla = buscarTallaPorId(idTalla);
        Prenda prenda = buscarPrendaPorId(idPrenda);

        validarTallaAsociadaAPrenda(prenda, talla);

        boolean cambioCombinacion =
                !guiaActual.getTalla()
                        .getIdTalla()
                        .equals(idTalla)
                        ||
                        !guiaActual.getPrenda()
                                .getIdPrenda()
                                .equals(idPrenda);

        if (cambioCombinacion) {
            validarGuiaDuplicada(idTalla, idPrenda);
        }

        guiaActual.setPecho(nuevosDatos.getPecho());
        guiaActual.setCintura(nuevosDatos.getCintura());
        guiaActual.setCadera(nuevosDatos.getCadera());
        guiaActual.setLargo(nuevosDatos.getLargo());

        guiaActual.setTalla(talla);
        guiaActual.setPrenda(prenda);

        return guiaTallaRepository.save(guiaActual);
    }

    @Transactional
    public void eliminarGuiaTalla(Long idGuia) {
        GuiaTalla guiaTalla = buscarGuiaPorId(idGuia);

        guiaTallaRepository.delete(guiaTalla);
    }

    @Transactional(readOnly = true)
    public List<GuiaTalla> buscarPorRangoPecho(
            Double minimo,
            Double maximo
    ) {
        validarRango(minimo, maximo);

        return guiaTallaRepository
                .findByPechoBetween(minimo, maximo);
    }

    @Transactional(readOnly = true)
    public List<GuiaTalla> buscarPorRangoCintura(
            Double minimo,
            Double maximo
    ) {
        validarRango(minimo, maximo);

        return guiaTallaRepository
                .findByCinturaBetween(minimo, maximo);
    }

    private GuiaTalla buscarGuiaPorId(Long idGuia) {
        return guiaTallaRepository
                .findById(idGuia)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la guía de talla con id: "
                                        + idGuia
                        )
                );
    }

    private Talla buscarTallaPorId(Long idTalla) {
        return tallaRepository
                .findById(idTalla)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la talla con id: "
                                        + idTalla
                        )
                );
    }

    private Prenda buscarPrendaPorId(Long idPrenda) {
        return prendaRepository
                .findById(idPrenda)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No existe la prenda con id: "
                                        + idPrenda
                        )
                );
    }

    private void validarGuiaDuplicada(
            Long idTalla,
            Long idPrenda
    ) {
        boolean existe = guiaTallaRepository
                .existsByTalla_IdTallaAndPrenda_IdPrenda(
                        idTalla,
                        idPrenda
                );

        if (existe) {
            throw new RuntimeException(
                    "Ya existe una guía para esa talla y esa prenda"
            );
        }
    }

    private void validarMedidas(GuiaTalla guiaTalla) {
        if (guiaTalla.getPecho() == null
                || guiaTalla.getCintura() == null
                || guiaTalla.getCadera() == null
                || guiaTalla.getLargo() == null) {

            throw new RuntimeException(
                    "Todas las medidas son obligatorias"
            );
        }

        if (guiaTalla.getPecho() <= 0
                || guiaTalla.getCintura() <= 0
                || guiaTalla.getCadera() <= 0
                || guiaTalla.getLargo() <= 0) {

            throw new RuntimeException(
                    "Todas las medidas deben ser mayores que cero"
            );
        }
    }

    private void validarTallaAsociadaAPrenda(
            Prenda prenda,
            Talla talla
    ) {
        if (prenda.getTallas() == null
                || prenda.getTallas().isEmpty()) {

            throw new RuntimeException(
                    "La prenda no tiene tallas asociadas"
            );
        }

        boolean tallaAsociada = prenda.getTallas()
                .stream()
                .anyMatch(tallaPrenda ->
                        tallaPrenda.getIdTalla()
                                .equals(talla.getIdTalla())
                );

        if (!tallaAsociada) {
            throw new RuntimeException(
                    "La talla seleccionada no está asociada a la prenda"
            );
        }
    }

    private void validarRango(
            Double minimo,
            Double maximo
    ) {
        if (minimo == null || maximo == null) {
            throw new RuntimeException(
                    "El mínimo y el máximo son obligatorios"
            );
        }

        if (minimo <= 0 || maximo <= 0) {
            throw new RuntimeException(
                    "Los valores deben ser mayores que cero"
            );
        }

        if (minimo > maximo) {
            throw new RuntimeException(
                    "El mínimo no puede ser mayor que el máximo"
            );
        }
    }
}