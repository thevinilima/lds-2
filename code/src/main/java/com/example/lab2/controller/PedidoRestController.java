package com.example.lab2.controller;

import com.example.lab2.model.Automovel;
import com.example.lab2.model.Cliente;
import com.example.lab2.model.PedidoAluguel;
import com.example.lab2.repository.AutomovelRepository;
import com.example.lab2.repository.ClienteRepository;
import com.example.lab2.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos de Aluguel", description = "API para gerenciamento de pedidos de aluguel de automóveis")
public class PedidoRestController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AutomovelRepository automovelRepository;

    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista com todos os pedidos de aluguel")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public List<PedidoAluguel> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um pedido pelo ID", description = "Retorna um único pedido com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    public ResponseEntity<PedidoAluguel> buscarPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id) {
        Optional<PedidoAluguel> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar pedidos por cliente", description = "Retorna todos os pedidos de um cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    public ResponseEntity<List<PedidoAluguel>> listarPedidosPorCliente(
            @Parameter(description = "ID do cliente") @PathVariable Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        List<PedidoAluguel> pedidos = pedidoRepository.findByClienteId(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    @Operation(summary = "Criar novo pedido", description = "Cria um novo pedido de aluguel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou cliente/automóvel não encontrado", content = @Content)
    })
    public ResponseEntity<PedidoAluguel> criarPedido(
            @Parameter(description = "Dados do pedido") @RequestBody PedidoAluguel pedido) {

        // Validar cliente
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null ||
                !clienteRepository.existsById(pedido.getCliente().getId())) {
            return ResponseEntity.badRequest().build();
        }

        // Validar automóvel
        if (pedido.getAutomovel() == null || pedido.getAutomovel().getId() == null ||
                !automovelRepository.existsById(pedido.getAutomovel().getId())) {
            return ResponseEntity.badRequest().build();
        }

        // Definir status inicial
        if (pedido.getStatus() == null) {
            pedido.setStatus(PedidoAluguel.StatusPedido.PENDENTE);
        }

        PedidoAluguel novoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pedido", description = "Atualiza um pedido existente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<PedidoAluguel> atualizarPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @Parameter(description = "Dados atualizados do pedido") @RequestBody PedidoAluguel pedido) {

        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Validar cliente
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null ||
                !clienteRepository.existsById(pedido.getCliente().getId())) {
            return ResponseEntity.badRequest().build();
        }

        // Validar automóvel
        if (pedido.getAutomovel() == null || pedido.getAutomovel().getId() == null ||
                !automovelRepository.existsById(pedido.getAutomovel().getId())) {
            return ResponseEntity.badRequest().build();
        }

        pedido.setIdPedido(id);
        PedidoAluguel pedidoAtualizado = pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido", description = "Atualiza apenas o status de um pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Status inválido", content = @Content)
    })
    public ResponseEntity<PedidoAluguel> atualizarStatusPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @Parameter(description = "Novo status do pedido") @RequestParam PedidoAluguel.StatusPedido status) {

        Optional<PedidoAluguel> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PedidoAluguel pedido = pedidoOpt.get();
        pedido.setStatus(status);
        PedidoAluguel pedidoAtualizado = pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pedido", description = "Remove um pedido com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    public ResponseEntity<Void> excluirPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id) {

        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}