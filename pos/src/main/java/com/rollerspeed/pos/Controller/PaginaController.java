package com.rollerspeed.pos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller


public class PaginaController {
    @GetMapping(value ="pagina")
    public String mostrarPagina() {
        return "pagina";
    }
    
}
    
    
