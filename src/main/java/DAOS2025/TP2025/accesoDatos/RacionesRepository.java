
package DAOS2025.TP2025.accesoDatos;



import DAOS2025.TP2025.entidades.Raciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacionesRepository extends JpaRepository<Raciones, Long> {

    // Ejemplo: buscar por recetaid
    List<Raciones> findByRecetaid(Long recetaid);

    // Ejemplo: buscar por fecha de preparación
    List<Raciones> findByFechaPreparacion(String fechaPreparacion);
}