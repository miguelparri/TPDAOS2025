/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS2025.TP2025.presentacion;

import DAOS2025.TP2025.entidades.Raciones;
import DAOS2025.TP2025.servicio.RacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raciones")
@RequiredArgsConstructor
public class RacionesController {

    private final RacionesService service;

    @PostMapping
    public Raciones crear(@RequestBody Raciones r) {
        return service.guardar(r);
    }

    @GetMapping
    public List<Raciones> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Raciones obtener(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

