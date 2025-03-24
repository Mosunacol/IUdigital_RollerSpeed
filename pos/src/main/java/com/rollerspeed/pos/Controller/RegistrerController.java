package com.rollerspeed.pos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rollerspeed.pos.Services.UserService;
import com.rollerspeed.pos.Model.User;
import com.rollerspeed.pos.Model.Role;
import com.rollerspeed.pos.Repository.RoleRepository;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/registrer")
public class RegistrerController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String showRegistrationForm(Model model) {
        System.out.println("Mostrando formulario de registro.");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll()); // Enviar roles al formulario
        return "RegistrerForm";  // Asegurar que el template existe en src/main/resources/templates/
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") User user, 
                               @RequestParam("roles") String roleName, // Recibir el rol seleccionado
                               BindingResult result, 
                               RedirectAttributes redirectAttributes) {
        System.out.println("Iniciando proceso de registro...");

        if (result.hasErrors()) {
            System.err.println("Errores en la validación del formulario.");
            return "RegistrerForm";
        }

        if (user == null) {
            System.err.println("Error: el objeto usuario es nulo.");
            redirectAttributes.addFlashAttribute("error", "Error interno: No se recibieron los datos del usuario.");
            return "redirect:/registrer";
        }

        try {
            System.out.println("Intentando registrar usuario: " + user.getUsername());

            if (userService.usernameExists(user.getUsername())) {
                System.err.println("El nombre de usuario ya está en uso: " + user.getUsername());
                redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya está en uso.");
                return "redirect:/registrer";
            }

            // Buscar el rol seleccionado en la base de datos
            Role role = roleRepository.findByName(roleName)
                                      .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

            // Asignar rol al usuario
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);

            // Guardar usuario con su rol
            userService.saveUser(user);
            System.out.println("Usuario registrado correctamente.");

            redirectAttributes.addFlashAttribute("message", "Usuario registrado con éxito!");
            return "redirect:/home/index";
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("error", "Error al registrar usuario.");
            return "redirect:/registrer";
        }
    }
}
