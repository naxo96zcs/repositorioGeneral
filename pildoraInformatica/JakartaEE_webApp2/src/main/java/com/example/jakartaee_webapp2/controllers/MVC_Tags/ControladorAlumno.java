package com.example.jakartaee_webapp2.controllers.MVC_Tags;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alumno")
public class ControladorAlumno {

    private String DIR_FORM = "MVC_Tags/resgistroFormulario";

    /**
     * Con esto se esta guardando el alumno para que viaje entre el registro y la ¿vista? (que se muestre)
     */
    @RequestMapping("/resgistroFormulario")
    public String resgistroFormulario(Model modelo){
        System.out.println("[ControladorAlumno]: Entro registro formulario");
        AlumnoMVCTags alumno = new AlumnoMVCTags();
        System.out.println("[ControladorAlumno]: Metiendo modelo de Alumno"+"\n");
        modelo.addAttribute("alumno",alumno); //attributeName = identificativo
        //Si se hacen subcarpetas hay que referenciar de esta manera
        return DIR_FORM;
    }
    /*//Forma alternativa utilizando la etiqueta @ModelAttribute
    //IGNORAR PROBABLEMENTE ESTE MAL
    @RequestMapping("/resgistroFormulario2")
    public String resgistroFormulario2(@ModelAttribute("alumnosValue")AlumnoMVCTags alumno,Model modelo){
        //AlumnoMVCTags alumno = new AlumnoMVCTags();
        modelo.addAttribute("alumnoValue",alumno); //attributeName = identificativo
        return DIR_FORM;
    }*/


    /**
     * Recibe por parametro el modelo del Alumno introducido, con su nombre identificativo
     *
     * @ModelAttribute, se utiliza para en vez de utilziar Model y recuperar de él atributo alumno
     * @Valid, para que valide el modelo entero del alumno (todos sus atributos etiquetados), su resultado lo guarda en "BindingResult"
     * @BindingResult, se utiliza para obtener el resultado de la valicaciones de los campos marcados con etiquetas (Bean Validation)
     *
     * Do: Muestra la informacion en el formulario
     *
     * @IMPORTANTE: El BindingResult debe ir detras de ModelAttribute para que no retorne un error en el navegador 400, cuando se produce el errror
     * hay que tener en cuenta que se va a volver al cuestionario, por lo tanto el modelAttribute modeloCuestionario debe ser asignado de nuevo
     *
     */
    @RequestMapping("/mostrarAlumno")
    public String mostrarAlumno (@Valid @ModelAttribute("alumno")AlumnoMVCTags alumno,BindingResult bindingResult,Model model){
        System.out.println("[ControladorAlumno]: Muestro alumno");
        System.out.println("Nombre: " +alumno.getNombre());
        //Se comprueba si el resultado de la validacion es correcto, sino lo es tira al formulario de nuevo
        if(bindingResult.hasErrors()){
            System.out.println("[ControladorAlumno]:Error en mostrar alumno");
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError aux:errorList){
                System.out.println("----------------------------------");
                System.out.println("Code: "+aux.getCode());
                System.out.println("Codes[]: "+aux.getCodes());
                System.out.println("Class: "+aux.getClass());
                System.out.println("Class: "+aux.getDefaultMessage());
            }
            //Se vuelve al cuestionario en caso de error por lo que hay que asignar este error
            model.addAttribute("alumno",alumno);


            return DIR_FORM;
        }
        //Para que se muestren en el siguiente .JSP deben estar todos los metodos setter definidos
        return "MVC_Tags/mostrarAlumnoRegistrado";
    }

    /**
     * Utilizado para comrpobar antes de realizar el "envio" de formulario que no se envian espacios en blanco como nombre o apellido
     * @param binder
     */
    @InitBinder
    public void miBinder(WebDataBinder binder){
        System.out.println("[Binder]");
        //Utilizado para eliminar los espacios en blanco. TRUE porque queremos que un espacio en blanco quiero que se transforme a nulo
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class,trimmerEditor);
    }
}
