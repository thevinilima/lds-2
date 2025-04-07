package com.example.lab2.controller;

import com.example.lab2.model.Automovel;
import com.example.lab2.repository.AutomovelRepository;
import com.example.lab2.repository.ClienteRepository;
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
@RequestMapping("/api/automoveis")
@Tag(name = "Automóveis", description = "API para gerenciamento de automóveis")
public class AutomovelRestController {

    @Autowired
    private AutomovelRepository automovelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @Operation(summary = "Listar todos os automóveis", description = "Retorna uma lista com todos os automóveis cadastrados")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public List<Automovel> listarAutomoveis() {
        return automovelRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um automóvel pelo ID", description = "Retorna um único automóvel com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Automóvel encontrado"),
            @ApiResponse(responseCode = "404", description = "Automóvel não encontrado", content = @Content)
    })
    public ResponseEntity<Automovel> buscarAutomovel(
            @Parameter(description = "ID do automóvel") @PathVariable Long id) {
        Optional<Automovel> automovel = automovelRepository.findById(id);
        return automovel.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo automóvel", description = "Cria um novo registro de automóvel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Automóvel criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<Automovel> criarAutomovel(
            @Parameter(description = "Dados do automóvel") @RequestBody Automovel automovel) {
        if (automovel.getProprietario() == null || automovel.getProprietario().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!clienteRepository.existsById(automovel.getProprietario().getId())) {
            return ResponseEntity.badRequest().build();
        }

        Automovel novoAutomovel = automovelRepository.save(automovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAutomovel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar automóvel", description = "Atualiza um automóvel existente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Automóvel atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Automóvel não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<Automovel> atualizarAutomovel(
            @Parameter(description = "ID do automóvel") @PathVariable Long id,
            @Parameter(description = "Dados atualizados do automóvel") @RequestBody Automovel automovel) {

        if (!automovelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (automovel.getProprietario() == null || automovel.getProprietario().getId() == null ||
                !clienteRepository.existsById(automovel.getProprietario().getId())) {
            return ResponseEntity.badRequest().build();
        }

        automovel.setId(id);
        Automovel automovelAtualizado = automovelRepository.save(automovel);
        return ResponseEntity.ok(automovelAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir automóvel", description = "Remove um automóvel com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Automóvel excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Automóvel não encontrado", content = @Content)
    })
    public ResponseEntity<Void> excluirAutomovel(
            @Parameter(description = "ID do automóvel") @PathVariable Long id) {

        if (!automovelRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        automovelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}