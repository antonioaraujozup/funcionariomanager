package br.com.zup.edu.funcionariosmanager.controller;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;
import br.com.zup.edu.funcionariosmanager.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class AtualizarSalarioFuncionarioController {

    private final FuncionarioRepository repository;

    public AtualizarSalarioFuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PatchMapping("/funcionarios/{id}")
    public ResponseEntity<?> atualizarSalario(@PathVariable("id") Long id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        funcionario.atualizaSalario();

        repository.save(funcionario);

        return ResponseEntity.noContent().build();
    }
}
