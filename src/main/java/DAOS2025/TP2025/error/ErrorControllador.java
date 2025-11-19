/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS2025.TP2025.error;


import DAOS2025.TP2025.excepciones.DuplicadoExcepcion;
import DAOS2025.TP2025.excepciones.EntidadNoEncontradaExcepcion;
import DAOS2025.TP2025.excepciones.ValidacionExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorControllador {

    // Maneja entidad no encontrada (404)
    @ExceptionHandler(EntidadNoEncontradaExcepcion.class)
    public ResponseEntity<Object> handleNotFound(EntidadNoEncontradaExcepcion ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Maneja duplicados y validaciones (400)
    @ExceptionHandler({DuplicadoExcepcion.class, ValidacionExcepcion.class})
    public ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // Maneja cualquier error no controlado (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
    }

    // Construir JSON estándar de respuesta
    private ResponseEntity<Object> buildResponse(HttpStatus status, String mensaje) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", mensaje);

        return new ResponseEntity<>(body, status);
    }
}
