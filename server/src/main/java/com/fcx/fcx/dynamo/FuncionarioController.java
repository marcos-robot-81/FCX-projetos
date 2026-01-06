package com.fcx.fcx.dynamo;

import org.springframework.web.bind.annotation.*;
import java.util.List; // <--- Importante: Agora usamos Lista padrão do Java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository repository;

    public FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // 1. Salvar (POST)
    @PostMapping
    public Funcionario salvar(@RequestBody Funcionario funcionario) {
        return repository.adcionaFuncionario(funcionario);
    }

    // 2. Buscar por ID (GET)
    @GetMapping("/{matricula}")
    public Funcionario buscar(@PathVariable String matricula) {
        return repository.buscarPorId(matricula);
    }

    // 3. Listar Todos (GET) - CORRIGIDO AQUI
    @GetMapping
    public List<Funcionario> listar() { // <--- Mudou de PageIterable para List
        return repository.listarTodos();
    }
    
    
}