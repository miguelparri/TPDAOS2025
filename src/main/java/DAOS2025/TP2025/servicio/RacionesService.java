package DAOS2025.TP2025.servicio;

import DAOS2025.TP2025.entidades.Raciones;
import DAOS2025.TP2025.accesoDatos.RacionesRepository;
import DAOS2025.TP2025.excepciones.EntidadNoEncontradaExcepcion;
import DAOS2025.TP2025.excepciones.ValidacionExcepcion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RacionesService {

    private final RacionesRepository racionesRepository;

    // ----------------------------------------------------
    // Crear
    // ----------------------------------------------------
    public Raciones guardar(Raciones r) {

        validarRacion(r);

        // Regla del enunciado:
        // stockRestante = stockPreparado al crear
        r.setStockRestante(r.getStockPreparado());

        return racionesRepository.save(r);
    }

    // ----------------------------------------------------
    // Listar
    // ----------------------------------------------------
    public List<Raciones> listar() {
        return racionesRepository.findAll();
    }

    // ----------------------------------------------------
    // Buscar
    // ----------------------------------------------------
    public Raciones buscarPorId(Long id) {
        return racionesRepository.findById(id)
                .orElseThrow(() ->
                        new EntidadNoEncontradaExcepcion("No existe una ración con id: " + id));
    }

    // ----------------------------------------------------
    // Eliminar
    // ----------------------------------------------------
    public void eliminar(Long id) {
        if (!racionesRepository.existsById(id)) {
            throw new EntidadNoEncontradaExcepcion("No existe la ración a eliminar con id: " + id);
        }
        racionesRepository.deleteById(id);
    }

    // ----------------------------------------------------
    // Validaciones
    // ----------------------------------------------------
    private void validarRacion(Raciones r) {

        if (r.getStockPreparado() == null || r.getStockPreparado() < 0) {
            throw new ValidacionExcepcion("El stock preparado no puede ser negativo ni nulo.");
        }

        if (r.getRecetaid() == null || r.getRecetaid() <= 0) {
            throw new ValidacionExcepcion("El id de la receta debe ser válido.");
        }

        LocalDate fechaPrep = validarFecha(r.getFechaPreparacion(), "fecha de preparación");
        LocalDate fechaVenc = validarFecha(r.getFechaVencimiento(), "fecha de vencimiento");

        // Validación que te faltaba:
        if (!fechaVenc.isAfter(fechaPrep)) {
            throw new ValidacionExcepcion("La fecha de vencimiento debe ser posterior a la fecha de preparación.");
        }
    }

    private LocalDate validarFecha(String fecha, String nombreCampo) {
        try {
            return LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new ValidacionExcepcion("La " + nombreCampo + " no tiene un formato válido (usar YYYY-MM-DD).");
        }
    }
}
