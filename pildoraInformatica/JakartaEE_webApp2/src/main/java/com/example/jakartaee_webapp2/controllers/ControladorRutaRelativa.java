package com.example.jakartaee_webapp2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Si se dejase así daría un error de ambigüedad porque hay dos metodos con la misma ruta y no sabe por cual tirar
 */

@Controller
@RequestMapping("/ruta-relativa")
public class ControladorRutaRelativa {

    @RequestMapping("/anadirInfoProcesoFormularioRequestParam")
    public String anadirInfoProcesoFormularioRequestParam(@RequestParam("nombre_alumno") String nombre, Model model){

        nombre+= " viene de un lugar diferente";
        System.out.println(getClass().getSimpleName()+": "+nombre);
        model.addAttribute("msg_procesado",nombre);
        return "HolaAlumnosSpring";
    }
}
