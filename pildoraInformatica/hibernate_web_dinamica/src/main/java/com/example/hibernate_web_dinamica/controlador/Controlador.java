package com.example.hibernate_web_dinamica.controlador;

import com.example.hibernate_web_dinamica.dao.ClienteDAO;
import com.example.hibernate_web_dinamica.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class Controlador {
    /**
     * Inyeccion para los clientes
     */
  /*  @Autowired
    private ClienteDAO clienteDAO;*/

    @RequestMapping("/lista")
    public String listaClientes(Model model){
        System.out.println("hola!!");
        /*///obtener clientes desde DAO
        List<Cliente> clienteList = clienteDAO.getClientes();*/
        //agragar clientes al modelo
        return "lista-clientes";
    }



}
