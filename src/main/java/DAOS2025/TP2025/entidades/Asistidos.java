/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS2025.TP2025.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Asistidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Positive(message = "El DNI debe ser un número positivo")
    private Long dni;


    @NotBlank(message = "El nombre completo es obligatorio")
    @Column(unique = true)
    private String nombreCompleto;

    private String domicilio;

    private String fechaNacimiento;

    @NotNull(message = "La edad de registro es obligatoria")
    @Positive(message = "La edad de registro debe ser mayor a cero")
    private Integer edadRegistro;

    @NotNull(message = "La ciudadId es obligatoria")
    @Positive(message = "La ciudadId debe ser positiva")
    private Long ciudadId;
}
