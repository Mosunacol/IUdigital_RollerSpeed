package com.rollerspeed.pos.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Genera getters, setters, toString, etc. (requiere Lombok)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos")

public class ProductModel {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable =  false)
    private long  duracion;

    @Column(name = "ruta_foto", length = 255) // Almacena la ruta de la imagen
    private String rutaFoto; // Campo para almacenar la ruta de la imagen)

    
}