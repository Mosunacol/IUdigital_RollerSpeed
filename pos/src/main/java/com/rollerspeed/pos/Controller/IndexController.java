package com.rollerspeed.pos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping(value = "/home")


public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    
    @GetMapping("/index")
    public String home (Model model) {
                //  // Obtener al usuario autenticado
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String username = auth.getName(); // Nombre del usuario autenticado
        
               //  // Pasar el nombre del usuario al modelo
                model.addAttribute("user", username);
               
              
               logger.info("Usuario autenticado: {}", username);
               System.out.println("Usuario autenticado: " + username);
               
       
               return "index"; // Nombre de la vista en src/main/resources/templates/index.html
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            return "redirect:/home/index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalida la sesión
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        // Redirige al usuario a la página de inicio o login
        return "redirect:/home/login?logout=true";
    }

    }    

