package com.conexion.pildora.es.controlador;

import com.conexion.pildora.es.DAO.ClienteDAO;
import com.conexion.pildora.es.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class Controlador {
    /**
     * Inyeccion para los clientes
     */
    @Autowired
    private ClienteDAO clienteDAO;

    @RequestMapping("/lista")
    public String listaClientes(Model model) {
        System.out.println("[" + this.getClass().getSimpleName() + "]: lista clientes");
        ///obtener clientes desde DAO
        List<Cliente> clienteList = clienteDAO.getClientes();
        //agregar clientes al modelo
        model.addAttribute("clienteList", clienteList);
        return "tablaCliente";
    }

    /**
     * Mostrara el formulario para guardar el cliente
     *
     * @param model
     * @return
     */
    @RequestMapping("/formularioCliente")
    public String formularioCliente(Model model) {
        //Bind datos clientes
        Cliente cliente = new Cliente();
        model.addAttribute("clienteForm", cliente);
        return "formularioCliente";
    }

    /**
     * Coge de el contexto en el que se cargo agregraCliente vacio en el controlador anterior y con los datos introducidos
     * en el formulario se recuperan con @ModelAttribute y el nombre del objeto en el contexto
     *
     * @return
     */
    @PostMapping("/insertarCliente")
    public String insertarCliente(@ModelAttribute("agregarCliente") Cliente cliente) {
        System.out.println("######"+cliente.getId());
        Cliente consultaCl = clienteDAO.getCliente(cliente.getId());
        System.out.println("######"+consultaCl);
        if (consultaCl == null) {

            //Agregar el cliente
            clienteDAO.insertarCliente(cliente);
            System.out.println("[Controlador]: cliente insertado");
        }
        else {
            clienteDAO.actualizarCliente(cliente);
            System.out.println("[Controlador]: cliente modificado");
        }
        //Redirigir a la tabla
        return "redirect:/cliente/lista";
        //return "lista-clientes"; devolveria la lista vacia porque carga el jsp pero no tiene la info
    }

    /**
     * Acordarse que el orden de los param si se pone el model delante del request puede dar lugar a error
     *
     * @param clienteId
     * @param model
     * @return
     */
    @RequestMapping("/muestraFormularioActualizar")
    public String cargaModificarCliente(@RequestParam("clienteId") int clienteId, Model model) {
        try {
           // System.out.println("Controller: cargando cliente id " + clienteId);
            Cliente cliente = clienteDAO.getCliente(clienteId);
            model.addAttribute("clienteForm", cliente);
        } catch (Exception e) {
            System.out.println("[Controller]:Cliente no encontrado");
        }
        return "formularioCliente";
    }
/*
    @PostMapping("/clienteModificadoInsertado")
    public String clienteModificadoInsertado(@ModelAttribute("agregarCliente") Cliente cliente) {
        //buscar el cliente por si ya existe
        System.out.println("########" + cliente.getId());
        Cliente existente = clienteDAO.getCliente(cliente.getId());
        if (existente != null) {
            //modificar sus parametros?
            //guardarlo, persist crea un nuevo objeto, merge lo actualiza (todo lo demas esta deprecated: update, save ...)
            clienteDAO.actualizarCliente(cliente);
            System.out.println("[Controlador]: cliente modificado");
        } else
            System.out.println("[Controlador]:Cliente a modificar no existe, insertelo por favor");
        return "redirect:/cliente/lista";
    }*/

    @RequestMapping("/eliminarCliente")
    public String eliminarCliente(@RequestParam("clienteId") int clienteId, Model model){

        try {
            Cliente cliente = clienteDAO.getCliente(clienteId);
            model.addAttribute("clienteForm", cliente);
            System.out.println("[Controller]:Borrando cliente:"+cliente.getId());
            clienteDAO.eliminarClienteById(clienteId);
        } catch (Exception e) {
            System.out.println("[Controller]:Cliente no encontrado");
        }
        //jsp de formulario para aceptar el borrado, metiendole algun campo para indicar el borrado
        return "redirect:/cliente/lista";
    }

}
