package com.rollerspeed.pos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/pagina")
    public String mostrarPagina(Model model) {
        model.addAttribute("mision", "La Escuela de Formación llamada RollerSpeed tiene como fin la formación integral de personas y deportistas, conectando positivamente al practicante con el patín como instrumento de transformación. Nuestros cursos están enfocados en fortalecer las bases del patinaje en cada practicante a través de sus distintos niveles formativos, consiguiendo que una vez superados estos niveles puedan iniciar su proceso especializado en alguna modalidad de patinaje.");
        model.addAttribute("vision", "Posicionarse como la mejor escuela de patinaje, líder a nivel nacional e internacional posibilitando el desarrollo integral de todos nuestros deportistas entrenados a traves del tiempo mediante nuestros procesos deportivos.");
        model.addAttribute("valores", "Nos Caracteriza el respeto, el trabajo en equipo, el compromiso, la responsabilidad, el esfuerzo, la convivencia, la superación y la motivación.");
        model.addAttribute("servicios", "En la escuela de patinaje, aprenderás desde mantener el equilibrio y deslizarse con confianza hasta dominar giros, saltos más avanzados y patinaje de velocidad. Estas clases de patinaje te proporcionará las habilidades y la confianza necesarias para convertirte en un patinador seguro y habilidoso. Además, esta práctica fortalece las piernas, cintura, abdomen y glúteos; mejora la resistencia física, entre otros beneficios");
        return "pagina"; // Debe coincidir con el nombre del archivo HTML en templates
    }
}