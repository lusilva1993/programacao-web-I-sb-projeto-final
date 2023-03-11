package br.com.ada.projetoFinal.controller;

import br.com.ada.projetoFinal.dto.ClienteDTO;
import br.com.ada.projetoFinal.model.Cliente;
import br.com.ada.projetoFinal.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente/add")
    public String criarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                               BindingResult result, Model model) {
        if(result.hasErrors()) {
            return addCliente(model, cliente);
        }
        this.clienteService.createCliente(cliente);
        return "redirect:/cliente";
    }
    @GetMapping("/cliente")
    public ModelAndView cliente(
            @RequestParam(defaultValue = "1", value = "page") Integer numPagina,
            @RequestParam(defaultValue = "3", value = "size") Integer tamPagina) {
        //Model, ModelMap e ModelAndView
        ModelAndView modelAndView = new ModelAndView("clientes");
        Page<Cliente> clientePage = this.clienteService.listarPaginado(numPagina-1, tamPagina);
        modelAndView.addObject("clientes", clientePage.getContent());
        modelAndView.addObject("totalPages", clientePage.getTotalPages());
        modelAndView.addObject("currentPage", clientePage);
        modelAndView.addObject("pageSize", clientePage.getSize());
        return modelAndView;
    }

    @GetMapping("/cliente/add")
    public String addCliente(Model model, Cliente cliente) {
        model.addAttribute("add", Boolean.TRUE);
        model.addAttribute("cliente", Objects.nonNull(cliente) ? cliente : new Cliente());
        return "cliente-add";
    }

    @GetMapping("/cliente/{clienteId}/delete")
    public String deletarCliente(@PathVariable("clienteId") Long clienteId) {
        this.clienteService.removerClientePorId(clienteId);
        return "redirect:/clienteS";
    }

    @GetMapping("/cliente/{clienteId}/edit")
    public String mostrarEdicaoCliente(Model model, @PathVariable("clienteId") Long clienteId) {
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(clienteId);
        optionalCliente.ifPresent(cliente -> model.addAttribute("cliente", cliente));
        model.addAttribute("add", Boolean.FALSE);
        return "cliente-add";
    }

    @PutMapping("/cliente/{clienteId}/edit")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente,
                                @PathVariable("clienteId") Long clienteId) {
        cliente.setId(clienteId);
        this.clienteService.createCliente(cliente);
        return "redirect:/veiculos";
    }

}
