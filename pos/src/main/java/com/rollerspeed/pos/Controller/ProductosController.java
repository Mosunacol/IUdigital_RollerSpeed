package com.rollerspeed.pos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.List;

//import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ProductosServicios")
public class ProductosController {

    @GetMapping("/listarproductos")
    public String mostrarPagina(Model model) {
        model.addAttribute("Productos", "PRODUCTOS: Ofrecemos diferentes tipos articulos deportivos para la practica de patinaje");
        model.addAttribute("Servicios", "SERVICIOS: Tenemos a su disposición cursos de patinaje desde nivel principiante hasta Experto");
        
        // Crear una lista de productos
        List<Producto> productos = Arrays.asList(
            new Producto("Curso Principiante", 1200.00),
            new Producto("Curso Intermedio", 800.00),
            new Producto("Curso Experto", 500.00)
        );

        // Enviar la lista al modelo
        model.addAttribute("listaProductos", productos);


        return "productos/ProductosServicios";
    }


// Clase interna para representar un producto
public static class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
}


