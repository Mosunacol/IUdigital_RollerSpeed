package com.rollerspeed.pos.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/user")

public class UserController {
    @GetMapping(value = "/listar")
    public String listarUsuarios(@RequestParam(required = false) String param) {
        System.out.println("Parámetro recibido: " + param);
        return "ListarUsuarios"; // Retorna la vista "ListarUsuarios.html"
    }
}  



