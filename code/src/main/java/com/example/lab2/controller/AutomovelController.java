package com.example.lab2.controller;

import com.example.lab2.model.Automovel;
import com.example.lab2.model.Cliente;
import com.example.lab2.repository.AutomovelRepository;
import com.example.lab2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/automoveis")
public class AutomovelController {

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os automóveis
    @GetMapping
    public String listarAutomoveis(Model model) {
        List<Automovel> automoveis = automovelRepository.findAll();
        model.addAttribute("automoveis", automoveis);
        return "automoveis/lista"; // Página HTML para listar automóveis
    }

    // Mostrar formulário para criar um novo automóvel
    @GetMapping("/novo")
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("automovel", new Automovel());
        model.addAttribute("clientes", clienteRepository.findAll()); // Passa a lista de clientes
        return "automoveis/formulario"; // Página HTML para criar automóvel
    }

    // Salvar um novo automóvel
    @PostMapping("/salvar")
    public String salvarAutomovel(@ModelAttribute Automovel automovel) {
        automovelRepository.save(automovel);
        return "redirect:/automoveis"; // Redireciona para a lista de automóveis
    }

    // Mostrar formulário para editar um automóvel existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Automovel automovel = automovelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Automóvel inválido: " + id));
        model.addAttribute("automovel", automovel);
        model.addAttribute("clientes", clienteRepository.findAll()); // Passa a lista de clientes
        return "automoveis/formulario"; // Página HTML para editar automóvel
    }

    // Excluir um automóvel
    @GetMapping("/excluir/{id}")
    public String excluirAutomovel(@PathVariable Long id) {
        automovelRepository.deleteById(id);
        return "redirect:/automoveis"; // Redireciona para a lista de automóveis
    }
}