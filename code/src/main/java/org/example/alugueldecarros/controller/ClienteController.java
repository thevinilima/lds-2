package org.example.alugueldecarros.controller;

import org.example.alugueldecarros.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.alugueldecarros.model.Cliente;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes/lista";  // Página HTML ou JSP para listar clientes
    }

    // Mostrar formulário para criar um novo cliente
    @GetMapping("/novo")
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";  // Página HTML ou JSP para criar cliente
    }

    // Salvar um novo cliente
    @PostMapping
    public String salvarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";  // Redireciona para a lista de clientes
    }

    // Mostrar formulário para editar um cliente existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";  // Página HTML ou JSP para editar cliente
    }

    // Atualizar cliente
    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable Long id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    // Deletar cliente
    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + id));
        clienteRepository.delete(cliente);
        return "redirect:/clientes";
    }
}
