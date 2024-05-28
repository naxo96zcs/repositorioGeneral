package com.example.jakartaee_webapp2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HolaAlumnosController {
    //Peticion que devuelve el formulario pedido
    /**
     * El String que retorna el nombre de los formularios (JSP) creados en /vista
     */

    /**
     * Proporciona el formulario para introducri el nombre
     * @return Mismo nombre que el jsp al que se referencia pero sin la terminacion
     */
    @RequestMapping("/muestraFormulario") //para especificar la URL
    public String muestraFormulario(){
        return "HolaAlumnosFormulario";
    }
    /**
     * Proporciona el formulario de respuesta
     * @return Mismo nombre que el jsp al que se referencia pero sin la terminacion
     */
    @RequestMapping("/procesarFormulario")
    public String procesarFormulario(){
        return "HolaAlumnosSpring";
    }

    /**
     * Inyectar informacion al modelo para que venga con informacion adicional
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/anadirInfoProcesoFormulario")
    public String anadirInfoProcesoFormulario(HttpServletRequest request, Model model){

        //Rescata el valor de los parametros, se puede sustituir por @RequestParam
        String nombre=request.getParameter("nombre_alumno");

        nombre+= " es el mas chulo";
        System.out.println(getClass().getSimpleName()+": "+nombre);

        //Identificador + objeto, añade en el modelo la informacion, CUIDADO se extrae sin el 'param.'
        model.addAttribute("msg_procesado",nombre);

        return "HolaAlumnosSpring";
    }

    /**
     * IGual que el de arriba pero usando la anotiacion @RequestParam
     */
    @RequestMapping("/anadirInfoProcesoFormularioRequestParam")
    public String anadirInfoProcesoFormularioRequestParam(@RequestParam("nombre_alumno") String nombre, Model model){

        nombre+= " es el mas chulo";
        System.out.println(getClass().getSimpleName()+": "+nombre);
        model.addAttribute("msg_procesado",nombre);
        return "HolaAlumnosSpring";
    }
}
