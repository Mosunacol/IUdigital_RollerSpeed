package com.rollerspeed.pos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.rollerspeed.pos.Services.UserService;
import com.rollerspeed.pos.Model.User;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users") // URL para acceder a la lista
    public String listUsers(Model model) {
        List<User> users = userService.findAllUsers(); // Usa el método del servicio
        model.addAttribute("users", users); // Envía la lista a la vista
        return "listarUsuarios"; // Nombre del archivo HTML (sin extensión)
    }
} 



