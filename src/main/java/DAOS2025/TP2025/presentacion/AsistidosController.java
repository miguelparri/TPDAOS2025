/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS2025.TP2025.presentacion;

import DAOS2025.TP2025.entidades.Asistidos;
import DAOS2025.TP2025.servicio.AsistidosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistidos")
@RequiredArgsConstructor
public class AsistidosController {

    private final AsistidosService service;

    @PostMapping
    public Asistidos crear(@RequestBody Asistidos a) {
        return service.crear(a);
    }

    @GetMapping
    public List<Asistidos> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Asistidos obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public Asistidos actualizar(@PathVariable Long id, @RequestBody Asistidos a) {
        return service.actualizar(id, a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
