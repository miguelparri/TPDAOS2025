/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOS2025.TP2025.accesoDatos;

import DAOS2025.TP2025.entidades.Asistidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistidosRepository extends JpaRepository<Asistidos, Long> {

    boolean existsByNombreCompleto(String nombreCompleto);

    boolean existsByDni(Long dni);
}
