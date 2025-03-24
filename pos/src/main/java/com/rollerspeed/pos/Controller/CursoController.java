package com.rollerspeed.pos.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.rollerspeed.pos.Services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.rollerspeed.pos.Model.ProductModel;




@Tag(name = "Cursos de Patinaje", description = "Cursos ofrecidos de diferentes niveles por la escuela Roller Speed")
@Controller
@RequestMapping("/Cursos")

public class CursoController {
    @Autowired 
    private CursoService cursoService;

    private static final String UPLOAD_DIR = "C:/Temp/uploads/";

    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Devuelve una lista de usuarios registrados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos  obtenidos correctamente",
                content = @Content(schema = @Schema(implementation = ProductModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )

    @GetMapping("/listar")
        public String listarCursos(Model model){
            //  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //  String username = auth.getName(); // Nombre del usuario autenticado
             model.addAttribute("Cursos",cursoService.listarCursos());
            //  model.addAttribute("user",username);
            return "listarCursos";
        }

    
    @GetMapping("/agregar") 
    public String mostrarFormulario(Model model){
        model.addAttribute("Cursos", new ProductModel());
        return "CursoForm";
    }

    @Operation(
        summary = "Guardar curso(s) seleccionado(s)",
        description = "Recolecta variables del formulario para la seleccion de los cursos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida correctamente",
                content = @Content(schema = @Schema(implementation = ProductModel.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }

    )

    @PostMapping("/guardar")
    public String guardarCurso(
        @ModelAttribute ProductModel curso,
        @RequestParam("foto") MultipartFile foto, // Parámetro para la imagen
        Model model) throws IOException {

        // Guardar la imagen y obtener la ruta
        String nombreArchivo = guardarImagen(foto);
        curso.setRutaFoto(nombreArchivo); // Asignar la ruta de la imagen al curso

        // Guardar el curso en la base de datos
        cursoService.guardarCurso(curso);

        return "redirect:/Cursos/listar"; // Redirigir a la lista de cursos
    }

    @GetMapping("/eliminar/{id_curso}")
        public String eliminarCurso(@PathVariable Long id_curso) {
        cursoService.eliminarCurso(id_curso);
         return "redirect:/Cursos/listar"; // Redirigir a la lista de flores
    }

    private String guardarImagen(MultipartFile foto) throws IOException {
    if (foto.isEmpty()) {
        throw new IllegalArgumentException("La imagen no puede estar vacía");
    }

    // Crear el directorio si no existe
    Path uploadPath = Paths.get(UPLOAD_DIR);

    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    }

     // Generar un nombre único para la imagen
     String nombreArchivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename();
     Path filePath = uploadPath.resolve(nombreArchivo);
     Files.copy(foto.getInputStream(), filePath);

     // Devolvemos solo el nombre del archivo (no la ruta completa)
     return nombreArchivo;

}






   




    
}
