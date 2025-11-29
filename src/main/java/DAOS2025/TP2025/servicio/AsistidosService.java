/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS2025.TP2025.servicio;

import DAOS2025.TP2025.accesoDatos.AsistidosRepository;
import DAOS2025.TP2025.entidades.Asistidos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistidosService {

    private final AsistidosRepository repo;

    
    public Asistidos crear(Asistidos a) {


        if (repo.existsByNombreCompleto(a.getNombreCompleto())) {
            throw new RuntimeException("Ya existe un asistido con ese nombre completo.");
        }

  
        if (a.getDni() != null && repo.existsByDni(a.getDni())) {
            throw new RuntimeException("Ese DNI ya está registrado.");
        }

    
        a.setId(null);

        return repo.save(a);
    }

    // LISTAR
    public List<Asistidos> listar() {
        return repo.findAll();
    }

    // OBTENER POR ID
    public Asistidos obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistido no encontrado"));
    }

    // ACTUALIZAR
    public Asistidos actualizar(Long id, Asistidos nuevo) {

        Asistidos existente = obtener(id);


        if (!existente.getNombreCompleto().equals(nuevo.getNombreCompleto()) &&
                repo.existsByNombreCompleto(nuevo.getNombreCompleto())) {
            throw new RuntimeException("El nombre completo ya está registrado.");
        }


        if (nuevo.getDni() != null &&
                !nuevo.getDni().equals(existente.getDni()) &&
                repo.existsByDni(nuevo.getDni())) {
            throw new RuntimeException("El DNI ya está registrado.");
        }


        existente.setDni(nuevo.getDni());
        existente.setNombreCompleto(nuevo.getNombreCompleto());
        existente.setDomicilio(nuevo.getDomicilio());
        existente.setFechaNacimiento(nuevo.getFechaNacimiento());
        existente.setEdadRegistro(nuevo.getEdadRegistro());
        existente.setCiudadId(nuevo.getCiudadId());

        return repo.save(existente);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        Asistidos a = obtener(id);
        repo.delete(a);
    }
}
