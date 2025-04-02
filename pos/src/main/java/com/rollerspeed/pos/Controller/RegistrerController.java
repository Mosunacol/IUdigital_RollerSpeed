package com.rollerspeed.pos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rollerspeed.pos.Model.User;
import com.rollerspeed.pos.Services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

// Importaciones de Swagger (Springdoc OpenAPI)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/registrer")
@Tag(name = "Registro de Usuarios", description = "Endpoints para registrar nuevos usuarios")
public class RegistrerController {

    @Autowired
    private UserService userService;

    /**
     * Muestra el formulario de registro.
     */
    @GetMapping
    @Operation(summary = "Mostrar el formulario de registro", description = "Retorna la página del formulario de registro")
    @ApiResponse(responseCode = "200", description = "Formulario de registro cargado correctamente")
    public String showRegistrationForm(
        Model model,
        HttpServletRequest request
    ) {
        model.addAttribute("user", new User());
        model.addAttribute("allowedRoles", List.of("ADMINISTRADOR", "ALUMNO", "INSTRUCTOR", "PUBLICO"));
        return "registrerForm";
    }

    /**
     * Registra un nuevo usuario.
     */
    @PostMapping
    @Operation(summary = "Registrar un nuevo usuario", description = "Guarda un nuevo usuario en la base de datos")
    @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes")
    @ApiResponse(responseCode = "409", description = "El nombre de usuario ya existe")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public String registerUser(
        @Parameter(description = "Datos del usuario a registrar", required = true)
        @ModelAttribute("user") @Valid User user,

        BindingResult result,

        RedirectAttributes redirectAttributes,

        @Parameter(hidden = true) // Ocultar este parámetro en la documentación
        HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Por favor, corrige los errores en el formulario.");
            return "redirect:/registrer";
        }

        try {
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("success", "¡Registro exitoso!");
            return "redirect:/home/index";

        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya existe");
            return "redirect:/registrer";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error inesperado: " + e.getMessage());
            return "redirect:/registrer";
        }
    }
}




