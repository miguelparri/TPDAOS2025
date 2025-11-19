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
public class Raciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Obligatorio, mayor a 0
    @NotNull(message = "El stock preparado es obligatorio")
    @Positive(message = "El stock preparado debe ser un número positivo")
    private Long stockPreparado;

    // No debe enviarse al crear — solo lectura
    @Column(updatable = false)
    private Long stockRestante;

    // Obligatorio, mayor a 0
    @NotNull(message = "El ID de la receta es obligatorio")
    @Positive(message = "El ID de la receta debe ser positivo")
    private Long recetaid;

    // Obligatoria
    @NotBlank(message = "La fecha de preparación es obligatoria")
    private String fechaPreparacion;

    // Obligatoria
    @NotBlank(message = "La fecha de vencimiento es obligatoria")
    private String fechaVencimiento;
}
