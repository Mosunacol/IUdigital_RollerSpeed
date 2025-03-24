package com.rollerspeed.pos.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.pos.Repository.ProductRepository;
import com.rollerspeed.pos.Model.ProductModel;

@Service

public class CursoService {

    @Autowired
    private ProductRepository productRepository;



    public List<ProductModel> listarCursos() { // Convertir Iterable<ProductModel> a List<ProductModel>
        Iterable<ProductModel> cursosIterable = productRepository.findAll();
        return StreamSupport.stream(cursosIterable.spliterator(), false)
                           .collect(Collectors.toList());

    }

    public ProductModel guardarCurso(ProductModel curso) {
        return productRepository.save(curso);
         }

    public void eliminarCurso(Long id_curso) {
            productRepository.deleteById(id_curso);
        }

    public ProductModel modificarCurso(Long id_curso, ProductModel CursosActualizadas) {
            Optional<ProductModel> cursoOptional = productRepository.findById(id_curso);
            
        if (cursoOptional.isPresent()) {
            ProductModel CursoExistente = cursoOptional.get();
               

        // Actualizar los campos necesarios
            CursoExistente.setNombre(CursosActualizadas.getNombre());
            CursoExistente.setPrecio(CursosActualizadas.getPrecio());
            CursoExistente.setDescripcion(CursosActualizadas.getDescripcion());
            CursoExistente.setDuracion(CursosActualizadas.getDuracion());
            
                
            // Guardar curso actualizado
            return productRepository.save(CursoExistente);

            } else {
                // Manejar el caso en que el curso no exista
                throw new RuntimeException("Curso no encontrada con ID: " + id_curso);
            }
        }
    }

