package com.rollerspeed.pos.Controller;

import com.rollerspeed.pos.Model.ProductModel;
import com.rollerspeed.pos.Services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogoController {

    @Autowired
    private CursoService cursoService;

    
    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
    List<ProductModel> cursos = cursoService.listarCursos();
    System.out.println("Cursos obtenidos: " + cursos); // 🔹 Verifica que la lista tiene datos
    model.addAttribute("Cursos", cursos);
    return  "fragments/catalogo";
}
}
