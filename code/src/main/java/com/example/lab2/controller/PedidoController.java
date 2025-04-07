package com.example.lab2.controller;

import com.example.lab2.model.Automovel;
import com.example.lab2.model.Cliente;
import com.example.lab2.model.PedidoAluguel;
import com.example.lab2.repository.AutomovelRepository;
import com.example.lab2.repository.ClienteRepository;
import com.example.lab2.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutomovelRepository automovelRepository;

    @GetMapping("/cliente/{clienteId}")
    public String listarPedidos(@PathVariable Long clienteId, Model model) {
        List<PedidoAluguel> pedidos = pedidoRepository.findByClienteId(clienteId);
        model.addAttribute("pedidos", pedidos);
        return "pedidos/lista";  // Página HTML para listar pedidos
    }

    @GetMapping("/novo/{clienteId}")
    public String mostrarFormularioCriacao(@PathVariable Long clienteId, Model model) {
        PedidoAluguel pedido = new PedidoAluguel();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido: " + clienteId));
        List<Automovel> automoveis = automovelRepository.findAll();  // Listar todos os automóveis para seleção

        pedido.setCliente(cliente);
        model.addAttribute("pedido", pedido);
        model.addAttribute("automoveis", automoveis);  // Passa a lista de automóveis para o formulário
        return "pedidos/formulario";  // Página HTML para criar pedido
    }

    @PostMapping("/salvar")
    public String salvarPedido(@ModelAttribute PedidoAluguel pedido, @RequestParam Long automovelId) {
        Automovel automovel = automovelRepository.findById(automovelId)
                .orElseThrow(() -> new IllegalArgumentException("Automóvel inválido: " + automovelId));
        pedido.setAutomovel(automovel);
        pedido.setStatus(PedidoAluguel.StatusPedido.PENDENTE);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/cliente/" + pedido.getCliente().getId();  // Redireciona para a lista de pedidos do cliente
    }
}
